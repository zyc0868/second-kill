package com.example.demo.redis;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: flyboy
 * @Date: 18/03/2021 16:09
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */

@Getter
public enum RedisPrefixKey {
    USER_NAME("user_"),
    USER_TOKEN("token_"),

    GOODS_STOCK("goods_stock_"),
    GOODS_OVER("goods_over_"),

    PATH_CHECK("path_"),

    ACCESS_LIMIT("access_")
    ;
    private final String prefix;

    private static final Map<String, RedisPrefixKey> EXCEPTION_MAP = new HashMap<>();

    RedisPrefixKey(String prefix) {
        this.prefix = prefix;
    }
}
