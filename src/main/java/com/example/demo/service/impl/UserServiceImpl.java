package com.example.demo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.example.demo.domain.dao.UserDAO;
import com.example.demo.domain.entity.UserDO;
import com.example.demo.dto.MessageRequest;
import com.example.demo.dto.MessageResponse;
import com.example.demo.dto.userDto.LoginRequest;
import com.example.demo.dto.userDto.LoginResponse;
import com.example.demo.dto.userDto.LogoutRequest;
import com.example.demo.dto.userDto.LogoutResponse;
import com.example.demo.service.UserService;
import com.example.demo.util.ErrorConstants;
import com.example.demo.util.MD5Util;
import io.netty.util.internal.StringUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: flyboy
 * @Date: 12/03/2021 19:54
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */

@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public MessageResponse<LoginResponse> login(MessageRequest<LoginRequest> request) {
        LoginRequest loginRequest = request.getData();
        String phone = loginRequest.getPhone();
        String password = loginRequest.getPassword();

        UserDO user = userDAO.findByPhone(phone);
        if (user == null){
            return request.response(ErrorConstants.PHONE_NUMBER_NOT_FOUND,new LoginResponse());
        }

        String databasePassword = user.getPassword();
        String databaseSalt = user.getSalt();
        String mixPassword = MD5Util.formPassToDBPass(password,databaseSalt);
        if (!StringUtils.equals(databasePassword,mixPassword)){
            return request.response(ErrorConstants.PASSWORD_ERROR,new LoginResponse());
        }
        user.setLoginCount(user.getLoginCount()+1);
        user.setLastLoginDate(new Date());
        userDAO.updateInfoAfterLogin(user);
        // 防止泄密
        user.setPassword("");
        LoginResponse response = LoginResponse.builder()
                .userDO(user)
                .build();
        return request.response(response);
    }

    @Override
    public MessageResponse<LogoutResponse> logout(MessageRequest<LogoutRequest> request) {

        return null;
    }


}
