package com.example.demo.controller;

import com.example.demo.domain.entity.UserDO;
import com.example.demo.dto.MessageRequest;
import com.example.demo.dto.MessageResponse;
import com.example.demo.dto.userDto.LoginRequest;
import com.example.demo.dto.userDto.LoginResponse;
import com.example.demo.redis.RedisTimeUtil;
import com.example.demo.redis.ValueOperations;
import com.example.demo.service.UserService;
import com.example.demo.util.CookieUtil;
import com.example.demo.util.ErrorConstants;
import com.example.demo.util.JsonUtil;
import com.example.demo.redis.RedisPrefixKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author: flyboy
 * @Date: 12/03/2021 22:11
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */

@Controller
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private ValueOperations redisValueOperations;

    @RequestMapping("login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/loginAuthentication")
    @ResponseBody
    public MessageResponse<LoginResponse> login(HttpServletResponse response, HttpSession session,@RequestBody MessageRequest<LoginRequest> request) throws Exception {
        MessageResponse<LoginResponse> loginResponse = userService.login(request);
        if (loginResponse.getCode() == ErrorConstants.MESSAGE_STATUS_OK.getCode()){
            CookieUtil.writeLoginToken(response,session.getId());
            LoginResponse responseData = loginResponse.getData();
            UserDO userDO = responseData.getUserDO();

            String userJson = JsonUtil.getJsonString(userDO);
            redisValueOperations.set(RedisPrefixKey.USER_NAME.getPrefix() + session.getId() ,userJson, RedisTimeUtil.USER_SESSION_EXPIRE);
        }
        return loginResponse;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        String token = CookieUtil.readLoginToken(request);
        CookieUtil.delLoginToken(request,response);
        redisValueOperations.delete(RedisPrefixKey.USER_NAME.getPrefix() + token);
        return "login";
    }
}
