package com.example.demo.util;
import org.springframework.util.DigestUtils;
import java.nio.charset.StandardCharsets;

/**
 * @author: flyboy
 * @Date: 18/03/2021 14:51
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */

public class MD5Util {

    public static String md5(String src) {
        return DigestUtils.md5DigestAsHex(src.getBytes(StandardCharsets.UTF_8));
    }

    private static final String salt = "9d5b364d";

    public static String inputPassToFormPass(String inputPass) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + inputPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String salt) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDbPass(String inputPass, String saltDB) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }
}