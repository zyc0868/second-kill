package com.example.demo.interceptor;


import com.example.demo.domain.entity.UserDO;
import com.example.demo.redis.RedisPrefixKey;
import com.example.demo.redis.RedisTimeUtil;
import com.example.demo.redis.RedisValueOperations;
import com.example.demo.util.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: flyboy
 * @Date: 22/03/2021 14:47
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */


@Component
public class UserSessionExpire implements Filter {
    @Autowired
    RedisValueOperations redisValueOperations;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        // 判断是否已登录
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        if(StringUtils.isNotEmpty(loginToken)){
            String user = (String) redisValueOperations.get(RedisPrefixKey.USER_NAME.getPrefix()+loginToken);
            if(user != null){
                //重置session 过期时间
                redisValueOperations.set(RedisPrefixKey.USER_NAME.getPrefix() + loginToken ,user, RedisTimeUtil.USER_SESSION_EXPIRE);
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }


    @Override
    public void destroy() {

    }
}
