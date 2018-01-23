package com.ph.security.agent.interceptor;

import com.ph.security.agent.annotation.ClientSecurity;
import com.ph.security.agent.exception.AuthenticationServerErrorException;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.CompletableFuture;

public class ClientTokenCheckInterceptor extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(getClass());

    private String authHost;
    private String tokenHead;

    /**
     * @param tokenHead 认证信息，默认access-token
     */
    public ClientTokenCheckInterceptor(String authHost, String tokenHead) {
        this.authHost = authHost;
        this.tokenHead = tokenHead;
    }

    /**
     * 默认access-token
     */
    public ClientTokenCheckInterceptor(String authHost) {
        this.authHost = authHost;
        this.tokenHead = "access-token";
    }

    @Override
    public boolean preHandle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        ClientSecurity methodAnnotation = handlerMethod.getBeanType().getAnnotation(ClientSecurity.class);
        if (methodAnnotation == null)
            methodAnnotation = handlerMethod.getMethodAnnotation(ClientSecurity.class);
        String token = httpRequest.getHeader(tokenHead);
        if (methodAnnotation != null) {
            int statuCode = getStatusCode(token,httpRequest.getRequestURI() + ":" + httpRequest.getMethod()).get();
            if (statuCode == 200) {
                return super.preHandle(httpRequest, httpResponse, handler);
            } else if (statuCode == 401) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return false;
            } else {
                throw new AuthenticationServerErrorException("鉴权其他异常！");
            }
        }
        return super.preHandle(httpRequest, httpResponse, handler);
    }

    private CompletableFuture<Integer> getStatusCode(String token, String resource){
        return CompletableFuture.supplyAsync(() -> {
            HttpResponse response = HttpRequest.get(authHost + "/verify").query("token", token).query("resource", resource)
                    .send();
            int code = response.statusCode();
            return code;
        });
    }
}
