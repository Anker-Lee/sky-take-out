package com.sky.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
@Configuration
public class RedisConfiguration {
    /**
     * 当前配置类不是必须的，因为 Spring Boot 框架会自动装配 RedisTemplate 对象，但是默认的key序列化器为
     * JdkSerializationRedisSerializer，导致我们存到Redis中后的数据和原始数据有差别，故设置为
     * StringRedisSerializer序列化器。
     */
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("开始创建 Redis 模板对象...");
        RedisTemplate redisTemplate = new RedisTemplate();
        // 设置连接工厂对象
        redisTemplate.setConnectionFactory(redisConnectionFactory); // Spring Data Redis 提供的一个接口，
        // 用于创建与 Redis 数据库的连接。它是 Redis 操作的核心组件之一，负责管理与 Redis 服务器的底层连接。
        // 设置 Redis key 的序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置value序列化⽅式
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        //设置hash中field字段序列化⽅式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //设置hash中value的序列化⽅式
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        //5.初始化参数设置
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
