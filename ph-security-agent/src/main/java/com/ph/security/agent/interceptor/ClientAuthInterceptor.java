package com.ph.security.agent.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ph.security.agent.exception.AuthenticationServerErrorException;
import com.ph.security.agent.exception.AuthenticationVerifyFailException;
import com.ph.security.agent.util.TokenUtil;
import com.ph.security.common.util.CookiesUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class ClientAuthInterceptor implements RequestInterceptor {

    private String clientId;
    private String secret;
    private String authHeader;
    private static String authHost;
    private String tokenHead;

    Logger logger = LoggerFactory.getLogger(getClass());

    public ClientAuthInterceptor(String clientId, String secret, String header, String authHost, String tokenHead) {
        this.clientId = clientId;
        this.secret = secret;
        this.authHeader = header;
        this.authHost = authHost;
        this.tokenHead = tokenHead;
        getTokenStrategy = new AutoKeepAliveStrategy();
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        logger.info("客户端请求拦截器feignInterceptor");
        String token = getTokenStrategy.getAccessToken(clientId, secret);
        requestTemplate.header(authHeader,tokenHead+" "+token);
    }

    private static String getToken(String clientId,String secret) throws AuthenticationServerErrorException, AuthenticationVerifyFailException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("clientId",clientId);
        jsonObject.put("secret",secret);
        HttpResponse response = HttpRequest.post(authHost + "/auth").contentType("application/json").body(jsonObject.toJSONString())
                .send();
        if (response.statusCode() == 200) {
           String token = JSON.parseObject(response.body()).getString("token");
            //容错
            if (StringUtils.isBlank(token)) {
                throw new AuthenticationServerErrorException(JSON.toJSONString(response));
            }
            return token;
        } else if (response.statusCode() == 401) {
            throw new AuthenticationVerifyFailException(JSON.toJSONString(response));
        } else {
            throw new AuthenticationServerErrorException(JSON.toJSONString(response));
        }
    }

    private interface GetTokenStrategy {
        String getAccessToken(String appId, String appSecret) throws AuthenticationVerifyFailException, AuthenticationServerErrorException;
    }


    private class AutoKeepAliveStrategy implements GetTokenStrategy {

        private class ClientInfo {
            private final String clientId;
            private final String secret;

            ClientInfo(String clientId, String secret) {
                this.clientId = clientId;
                this.secret = secret;
            }
        }

        private ClientInfo clientInfo;
        private final AtomicReference<ScheduledThreadPoolExecutor> executor = new AtomicReference<ScheduledThreadPoolExecutor>();
        private final AtomicReference<String> accessToken = new AtomicReference<String>();

        @Override
        public String getAccessToken(String clientId, String secret) throws AuthenticationVerifyFailException, AuthenticationServerErrorException {
            clientInfo = new ClientInfo(clientId, secret);
            String token = accessToken.get();
            logger.info("token判断==============="+token);
            if(token == null || TokenUtil.isTokenExpired(token)){
                token = getToken(clientId, secret);
               // accessToken.set(token);
                executor.compareAndSet(null, scheduledExecutor());
            }
            return token;
        }

        private ScheduledThreadPoolExecutor scheduledExecutor() {
            ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
            executor.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    try {
                        accessToken.set(getToken(clientInfo.clientId, clientInfo.secret));
                    } catch (AuthenticationVerifyFailException e) {
                        e.printStackTrace();
                    } catch (AuthenticationServerErrorException e) {
                        e.printStackTrace();
                    }
                }
            }, 0, iacKeepalive, TimeUnit.MINUTES);
            return executor;
        }
    }

    private GetTokenStrategy getTokenStrategy;
    private static long iacKeepalive = 30L;

}
