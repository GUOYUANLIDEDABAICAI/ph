package com.ph.security.agent.interceptor;

import com.ph.security.agent.annotation.ClientSecurity;
import com.ph.security.agent.annotation.IgnoreAuthSecurity;
import com.ph.security.agent.exception.AuthenticationServerErrorException;
import com.ph.security.agent.util.TokenUtil;
import com.ph.security.common.context.BaseContextHandler;
import com.ph.security.common.entity.User;
import com.ph.security.common.util.CookiesUtil;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.CompletableFuture;

public class UserAuthInterceptor extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(getClass());

    private String authHost;
    private String tokenHead;

    /**
     * @param tokenHead 认证信息，默认access-token
     */
    public UserAuthInterceptor(String authHost, String tokenHead) {
        this.authHost = authHost;
        this.tokenHead = tokenHead;
    }

    /**
     * 默认access-token
     */
    public UserAuthInterceptor(String authHost) {
        this.authHost = authHost;
        this.tokenHead = "access-token";
    }

    @Override
    public boolean preHandle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object handler) throws Exception {
        logger.info("UserAuthInterceptor开始拦截url=============="+httpRequest.getRequestURI());
        //Cookie cookieByName = CookiesUtil.getCookieByName(httpRequest, "token");
        if (!"/login".equals(httpRequest.getRequestURI())){
            getCurrentUser(httpRequest);
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        IgnoreAuthSecurity methodAnnotation = handlerMethod.getBeanType().getAnnotation(IgnoreAuthSecurity.class);
        if (methodAnnotation == null)
            methodAnnotation = handlerMethod.getMethodAnnotation(IgnoreAuthSecurity.class);
        logger.info("是否有免校验注解======"+methodAnnotation);
        if (methodAnnotation != null) {
            return super.preHandle(httpRequest, httpResponse, handler);
        }

        if (httpRequest.getHeader("Authorization") == null) {
            logger.info("Authorization为空");
            return false;
        }
        logger.info("准备发生校验请求");
        int statuCode = getStatusCode("PToken"+httpRequest.getHeader("Authorization"),httpRequest.getRequestURI() + ":" + httpRequest.getMethod()).get();
        if (statuCode == 200) {
            return super.preHandle(httpRequest, httpResponse, handler);
        } else if (statuCode == 401) {
            //removeCookie(httpResponse);
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return false;
        } else {
            removeCookie(httpResponse);
            throw new AuthenticationServerErrorException("鉴权其他异常！");
        }
       // return super.preHandle(httpRequest, httpResponse, handler);
    }

    public void getCurrentUser(HttpServletRequest httpRequest){
        String toekn = httpRequest.getHeader("Authorization");
        String username = TokenUtil.getUsername(toekn);
        User user = new User();
        user.setUsername(username);
        BaseContextHandler.setUser(user);
    }

    public void removeCookie(HttpServletResponse response){
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    private CompletableFuture<Integer> getStatusCode(String token, String resource){
        logger.info("请求的token是:"+token+"请求的资源是:"+resource);
        return CompletableFuture.supplyAsync(() -> {
            HttpResponse response = HttpRequest.get(authHost + "/userVerify").query("token", token).query("resource", resource)
                    .send();
            int code = response.statusCode();
            logger.info("相应的状态馬:"+code);
            return code;
        });
    }
}
