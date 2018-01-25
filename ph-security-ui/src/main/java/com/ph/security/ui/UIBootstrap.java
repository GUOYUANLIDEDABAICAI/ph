package com.ph.security.ui;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UIBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(UIBootstrap.class).web(true).run(args);    }
}
