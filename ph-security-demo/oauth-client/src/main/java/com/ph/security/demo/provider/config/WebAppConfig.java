package com.ph.security.demo.provider.config;

import com.ph.security.agent.interceptor.ClientTokenCheckInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {
    @Value("${auth.client.authHost}")
    private String authHost;
    @Value("${auth.client.authHeader}")
    private String authHeader;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ClientTokenCheckInterceptor(authHost,authHeader)).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
