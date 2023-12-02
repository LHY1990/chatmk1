package com.lhy.springLettuce.redis;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class RedisYmlConfig {
    @Value("${spring.data.redis.host}")
    private String host;
    @Value("${spring.data.redis.password}")
    private String password;
    @Value("${spring.data.redis.port}")
    private int port;
}
