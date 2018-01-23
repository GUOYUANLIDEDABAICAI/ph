package com.ph.security.zuul;

import com.ph.security.zuul.properties.ZuulProperties;
import com.ph.security.zuul.properties.auth.AuthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableEurekaClient
@SpringBootApplication
@EnableZuulProxy
@EnableFeignClients
public class GateBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(GateBootstrap.class, args);
    }
}
