package com.ph.security.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.ph.security.common.context.BaseContextHandler;
import com.ph.security.common.entity.User;
import com.ph.security.common.util.CookiesUtil;
import com.ph.security.zuul.future.AuthFuture;
import com.ph.security.zuul.properties.ZuulProperties;
import feign.Feign;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class AccessFilter extends ZuulFilter {

    //ThreadLocal<String> localToken = new ThreadLocal<String>();

    private final AtomicReference<String> accessToken = new AtomicReference<String>();

    private final AtomicReference<ScheduledThreadPoolExecutor> executor = new AtomicReference<ScheduledThreadPoolExecutor>();

    @Autowired
    private ZuulProperties zuulProperties;

    @Value("${zuul.prefix}")
    private String zuulPre;

    User user;

    @Autowired
    Jedis jedis;

    @Autowired
    AuthFuture authFuture;

    @Autowired
    private EurekaClient discoveryClient; //此处报错可以忽略

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            HttpServletRequest request = ctx.getRequest();
            HttpServletResponse httpServletResponse = ctx.getResponse();
            final String requestUri = StringUtils.substringAfterLast(request.getRequestURI(),zuulProperties.getAuth().getLoginInstance());
            logger.info("但前请求路径======"+requestUri);
            String redirectUrl = "";
            //String token = (String) jedis.get("token");
            Cookie token_cookie = CookiesUtil.getCookieByName(request,"token");
            Cookie redirectUrl_cookie = CookiesUtil.getCookieByName(request,"redirectUrl");
            if (isStartWith(requestUri)) {
                return null;
            }

            String token = null;
            if (request.getRequestURI().endsWith(zuulProperties.getAuth().getLoginPage())){
                if (StringUtils.equalsIgnoreCase("post",request.getMethod())) {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    if (username != null && password != null) {
                        token = getToken(username, password);
                        if (token != null) {
                            //BaseContextHandler.setToken(token);
                            token_cookie = new Cookie("token", token);
                            token_cookie.setMaxAge(3600);
                            token_cookie.setDomain("172.18.110.115");
                            token_cookie.setPath("/");
                            httpServletResponse.addCookie(token_cookie);
                            //accessToken.get().set(token);
                        /*user = new User();
                        user.setPassword(password);
                        user.setUsername(username);
                        executor.compareAndSet(null, scheduledExecutor());*/
                        if (redirectUrl_cookie == null) {
                            redirectUrl = zuulProperties.getAuth().getHomePage();
                        }else {
                            redirectUrl = redirectUrl_cookie.getValue();
                        }
                        }
                    } else {
                        loginRedirect(httpServletResponse);
                    }
                }else {
                    return null;
                }
            } else {
                redirectUrl_cookie = new Cookie("redirectUrl", request.getRequestURI());
                redirectUrl_cookie.setMaxAge(60);
                redirectUrl_cookie.setDomain("172.18.110.115");
                redirectUrl_cookie.setPath("/");
                httpServletResponse.addCookie(redirectUrl_cookie);
            }

            if (token_cookie == null){
                //jedis.set(request.getRemoteAddr()+"redirectUrl","http://localhost:"+"8002"+request.getRequestURI());
                loginRedirect(httpServletResponse);
            } else {
                ctx.addZuulRequestHeader("Authorization", token_cookie.getValue());
                /*token = request.getHeader("Authorization");
                String url = StringUtils.substringAfter(request.getRequestURI(),"/api/ph-security-demo-client");
                HttpResponse resp = HttpRequest.get("http://localhost:8601/api/authen/userVerify")
                        .query("token", "PToken" + token).query("resource", request.getRequestURI() + ":" + request.getMethod())
                        .send();

                logger.info("token是否有效==========" + resp.statusCode());
                if (resp.statusCode() != 200) {
                    //jedis.del("token");
                    *//*Cookie removeCookie = new Cookie("token", null);
                    removeCookie.setMaxAge(0);
                    httpServletResponse.addCookie(removeCookie);*//*
                    //accessToken.set(null);
                    //jedis.set(request.getRemoteHost() + "redirectUrl", "http://localhost:" + "8002" + request.getRequestURI());
                    logger.info("自定义配置登录页面" + zuulProperties.getAuth().getLoginPage());
                    loginRedirect(httpServletResponse);
                } else {*/
                    if (!StringUtils.isBlank(redirectUrl)){
                        homePageRedirect(httpServletResponse,redirectUrl);
                    }else {
                        return null;
                    }
               // }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return null;
        }
    }

    private void loginRedirect(HttpServletResponse httpServletResponse) throws IOException {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka(zuulProperties.getZuulInstance(), false);
        //httpServletResponse.sendRedirect(zuulProperties.getAuth().getLoginPage());
        httpServletResponse.sendRedirect("http://"+instance.getIPAddr()+":"+instance.getPort()+zuulPre+"/"+zuulProperties.getAuth().getLoginInstance()+zuulProperties.getAuth().getLoginPage());
        return;
    }

    public void homePageRedirect(HttpServletResponse httpServletResponse,String redirectUrl) throws IOException {
        httpServletResponse.sendRedirect(redirectUrl);
        return;
    }

    public String getToken(String username,String password){
        String token = null;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        InstanceInfo authInstance = discoveryClient.getNextServerFromEureka(zuulProperties.getAuth().getAuthInstance(), false);
        HttpResponse response = HttpRequest.post("http://172.18.110.115:"+authInstance.getPort()+zuulProperties.getAuth().getToken().getCreate_token_url()).contentType("application/json").body(jsonObject.toJSONString())
                .send();
        if (response.statusCode() == 200) {
            token = JSON.parseObject(response.body()).getString("token");
        }
        return token;
    }

    /**
     * URI是否以什么打头
     *
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        for (String s : zuulProperties.getAuth().getIgnorePre().split(",")) {
            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return flag;
    }
}
