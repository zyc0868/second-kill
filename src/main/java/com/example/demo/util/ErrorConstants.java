package com.example.demo.util;

import lombok.Getter;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: flyboy
 * @Date: 12/03/2021 15:30
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */

@Getter
public enum ErrorConstants {

    /**
     * MESSAGE Common Status
     */
    MESSAGE_STATUS_OK(0, "ok"),

    /**
     * user error
     */
    PHONE_NUMBER_NOT_FOUND(1000,"phone number not found"),
    PASSWORD_ERROR(1001,"password not right"),
    USER_NOT_LOGIN(1002,"user not login"),
    USER_NOT_FOUND(1003,"user not found"),
    /**
     * Database Exception
     */
    DB_GENERAL_EXCEPTION(2000, "General Database exception"),

    /**
     * Goods
     */
    GOODS_SOLD_OUT(3000,"goods had been sold out"),
    GOODS_REPEATED_PURCHASE(3001,"goods have been purchased"),
    ORDER_NOT_FOUND(3002,"order not found"),

    /**
     * Message Common Status
     */
    REQUEST_GOODS_NOT_FOUND(4001,"request goods not found"),
    REQUEST_PATH_ILLEGAL(4002,"request path illegal"),
    ACCESS_LIMIT(4003,"too many requests, please visit later")
    ;
    private final int code;
    private final String msg;

    private static final Map<Integer, ErrorConstants> COALASMART_EXCEPTION_MAP = new HashMap<>();

    ErrorConstants(int errorCode, String errorMessage) {
        this.code = errorCode;
        this.msg = errorMessage;
    }

    static {
        for (ErrorConstants cause : ErrorConstants.values()) {
            COALASMART_EXCEPTION_MAP.put(cause.getCode(), cause);
        }
    }

    public static ErrorConstants valueOf(@NonNull Integer errorCode) {
        return COALASMART_EXCEPTION_MAP.get(errorCode);
    }
}

