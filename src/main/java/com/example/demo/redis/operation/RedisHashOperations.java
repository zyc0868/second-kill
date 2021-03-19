package com.example.demo.redis.operation;

import lombok.Getter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: flyboy
 * @Date: 14/03/2021 20:54
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */

public class RedisHashOperations<K, HK, HV> {

    @Getter
    private final RedisTemplate<String, HV> redisTemplate;

    @Getter
    private final HashOperations<String, String, HV> hashOperations;


    public RedisHashOperations() {
        redisTemplate = new RedisTemplate<>();
        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.setDefaultSerializer(RedisSerializer.string());
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory();
        connectionFactory.afterPropertiesSet();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.afterPropertiesSet();
        hashOperations = redisTemplate.opsForHash();
    }


    protected String key(K key) {
        return key.toString();
    }

    protected String hashKey(HK hashKey) {
        return hashKey.toString();
    }

    public void put(K key, HK hashKey, HV hashValue) {
        hashOperations.put(key(key), hashKey(hashKey), hashValue);
    }

    public boolean hasKey(K key, HK hashKey) {
        return hashOperations.hasKey(key(key), hashKey(hashKey));
    }

    public Boolean hasKey(K key) {
        return redisTemplate.hasKey(key(key));
    }

    public HV get(K key, HK hashKey) {
        return hashOperations.get(key(key), hashKey(hashKey));
    }

    public void delete(K key, HK hashKey) {
        hashOperations.delete(key(key), hashKey(hashKey));
    }

    public Map<String, HV> entries(K key) {
        return hashOperations.entries(key(key));
    }

    public void putAll(K key, Map<String, HV> map) {
        hashOperations.putAll(key(key), map);
    }

}
