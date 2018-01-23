package com.ph.security.auth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class AuthConfiguration {

    @Autowired
    RedisProperties redisProperties;

    @Bean
    public Jedis jedis(){
        return new Jedis(redisProperties.getHost(),redisProperties.getPort());
    }
}
