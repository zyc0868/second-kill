package com.example.demo.redis.operation;

import lombok.Getter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author: flyboy
 * @Date: 14/03/2021 20:54
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
public class RedisValueOperations<K, V> {

    @Getter
    private final RedisTemplate<String, V> redisTemplate;

    @Getter
    private final ValueOperations<String, V> valueOperations;


    public RedisValueOperations() {

        redisTemplate = new RedisTemplate<>();
        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.setDefaultSerializer(RedisSerializer.string());
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory();
        connectionFactory.afterPropertiesSet();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.afterPropertiesSet();

        valueOperations = redisTemplate.opsForValue();
    }

    protected String key(K key) {
        return key.toString();
    }

    public void set(K key, V value) {
        valueOperations.set(key(key), value);
    }

    public void set(K key, V value, Duration duration) {
        valueOperations.set(key(key), value, duration);
    }

    public void set(K key, V value, long timeout, TimeUnit unit) {
        valueOperations.set(key(key), value, timeout, unit);
    }

    public Boolean setIfAbsent(K key, V value, Duration duration) {
        return valueOperations.setIfAbsent(key(key), value, duration);
    }

    public Boolean setIfAbsent(K key, V value, long timeout, TimeUnit unit) {
        return valueOperations.setIfAbsent(key(key), value, timeout, unit);
    }

    public V get(K key) {
        return valueOperations.get(key(key));
    }

    public V getAndSet(K key, V value) {
        return valueOperations.getAndSet(key(key), value);
    }

    public List<V> multiGet(Collection<K> keys) {
        Collection<String> collection = keys.stream().map(this::key).collect(Collectors.toList());
        return valueOperations.multiGet(collection);
    }

    public Long size(K key) {
        return valueOperations.size(key(key));
    }

    public Boolean delete(K key) {
        return redisTemplate.delete(key(key));
    }

    public Long delete(Collection<K> keys) {
        Collection<String> collection = keys.stream().map(this::key).collect(Collectors.toList());
        return redisTemplate.delete(collection);
    }

    public Long increment(K key) {
        return valueOperations.increment(key(key));
    }

    public Long decrement(K key) {
        return valueOperations.decrement(key(key));
    }

    public Boolean expire(K key, Duration duration) {
        return redisTemplate.expire(key(key), duration);
    }

    public Long getExpire(K key) {
        return redisTemplate.getExpire(key(key));
    }

    public Boolean hasKey(K key) {
        return redisTemplate.hasKey(key(key));
    }

}
