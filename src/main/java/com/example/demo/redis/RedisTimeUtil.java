package com.example.demo.redis;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author: flyboy
 * @Date: 22/03/2021 12:09
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
public interface RedisTimeUtil {
    Duration USER_SESSION_EXPIRE = Duration.ofMinutes(30);
    Duration GOODS_ID = Duration.ofMinutes(2);
    Duration GOODS_LIST = Duration.ofMinutes(1);
}
