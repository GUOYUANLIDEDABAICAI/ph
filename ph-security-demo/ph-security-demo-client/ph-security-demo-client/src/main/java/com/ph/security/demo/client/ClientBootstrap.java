package com.ph.security.demo.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan({"com.ph.security.demo.client","com.ph.security.common"})
@EnableJpaRepositories("com.ph.security.common.repository")
@EntityScan("com.ph.security.common.entity")
public class ClientBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ClientBootstrap.class).web(true).run(args);    }
}
