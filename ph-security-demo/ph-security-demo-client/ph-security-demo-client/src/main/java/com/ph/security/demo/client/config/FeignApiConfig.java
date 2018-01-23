package com.ph.security.demo.client.config;

import com.ph.security.agent.interceptor.ClientAuthInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignApiConfig {
    @Value("${auth.client.clientId}")
    private String clientId;
    @Value("${auth.client.secret}")
    private String secret;
    @Value("${auth.client.authHeader}")
    private String authHeader;
    @Value("${auth.client.authHost}")
    private String authHost;
    @Value("${auth.client.tokenHead}")
    private String tokenHead;

    @Bean
    public ClientAuthInterceptor authenticationInterceptor() {
        return new ClientAuthInterceptor(clientId, secret, authHeader, authHost, tokenHead);
    }

}
