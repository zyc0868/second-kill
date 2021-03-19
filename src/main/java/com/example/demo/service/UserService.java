package com.example.demo.service;

import com.example.demo.dto.MessageRequest;
import com.example.demo.dto.MessageResponse;
import com.example.demo.dto.userDto.LoginRequest;
import com.example.demo.dto.userDto.LoginResponse;
import com.example.demo.dto.userDto.LogoutRequest;
import com.example.demo.dto.userDto.LogoutResponse;

/**
 * @author: flyboy
 * @Date: 12/03/2021 19:48
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
public interface UserService {
    MessageResponse<LoginResponse> login(MessageRequest<LoginRequest> request);
    MessageResponse<LogoutResponse> logout(MessageRequest<LogoutRequest> request);
}
