package com.example.demo.interceptor;

import com.example.demo.common.AccessLimit;
import com.example.demo.domain.entity.UserDO;
import com.example.demo.dto.BaseResponse;
import com.example.demo.dto.MessageRequest;
import com.example.demo.dto.MessageResponse;
import com.example.demo.redis.RedisPrefixKey;
import com.example.demo.redis.RedisTimeUtil;
import com.example.demo.redis.RedisValueOperations;
import com.example.demo.util.CookieUtil;
import com.example.demo.util.ErrorConstants;
import com.example.demo.util.JsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: flyboy
 * @Date: 22/03/2021 14:54
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Aspect
@Component
public class AccessLimitAspect {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisValueOperations redisValueOperations;

    @Pointcut("execution(* com.example.demo.controller.*.*(..)) && @annotation(accessLimit)")
    public void pointcut(AccessLimit accessLimit){

    }

    @Before("pointcut(accessLimit)")
    public void before(JoinPoint joinPoint, AccessLimit accessLimit) throws IOException {
        long limit = accessLimit.limit();
        long seconds = accessLimit.seconds();
        Duration DurationSeconds = Duration.ofSeconds(seconds);
        boolean needLogin = accessLimit.needLogin();
        String key = request.getRequestURI();
        String loginToken = CookieUtil.readLoginToken(request);
        UserDO userDO = null;
        if (loginToken != null){
            String userJson = (String) redisValueOperations.get(RedisPrefixKey.USER_NAME.getPrefix()+loginToken);
            userDO = JsonUtil.json2Obj(userJson, UserDO.class);
        }

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
        response.setContentType("application/json;charset=UTF-8");
        if (needLogin){
            if (userDO == null){
                ServletOutputStream output = response.getOutputStream();
                MessageResponse<BaseResponse> messageResponse = new MessageResponse<>(ErrorConstants.USER_NOT_LOGIN);
                output.write(JsonUtil.getJsonString(messageResponse).getBytes());
                output.flush();
                output.close();
                return;
            }
            key += "_" +userDO.getId();
        }
        Integer count = null;
        String res = (String) redisValueOperations.get(RedisPrefixKey.ACCESS_LIMIT.getPrefix()+key);
        if (res != null){
            count = Integer.valueOf(res);
        }
        if (count == null){
            redisValueOperations.set(RedisPrefixKey.ACCESS_LIMIT.getPrefix()+key,"1", DurationSeconds);
        }else if (count < limit){
            redisValueOperations.increment(RedisPrefixKey.ACCESS_LIMIT.getPrefix()+key);

        }else {
            ServletOutputStream output = response.getOutputStream();
            MessageResponse<BaseResponse> messageResponse = new MessageResponse<>(ErrorConstants.ACCESS_LIMIT);
            output.write(JsonUtil.getJsonString(messageResponse).getBytes());
            output.flush();
            output.close();
            return;
        }

    }

}


