package com.example.demo.util;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @author: flyboy
 * @Date: 12/03/2021 23:29
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
public class Utils {
    private Utils() {

    }

    public static boolean isNull(final Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(final Object obj) {
        return obj != null;
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(final Collection<?> co) {
        return !isEmpty(co);
    }

    public static boolean isEmpty(final Collection<?> co) {
        return co == null || co.isEmpty();
    }

    public static boolean isNotEmpty(final Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(final Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(final byte[] data) {
        return !isEmpty(data);
    }

    public static boolean isEmpty(final byte[] data) {
        return data == null || data.length == 0;
    }

    public static boolean allNull(final Object... values) {
        return ObjectUtils.firstNonNull(values) == null;
    }

    public static boolean allNotNull(final Object... values) {
        return ObjectUtils.allNotNull(values);
    }

    public static boolean anyNotNull(final Object... values) {
        return ObjectUtils.anyNotNull(values);
    }

    public static boolean anyNull(final Object... values) {
        return !allNotNull();
    }
}
