package com.example.demo.dto.userDto;

import com.example.demo.dto.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author: flyboy
 * @Date: 12/03/2021 19:51
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Getter
@Setter
@ToString
public class LoginRequest extends BaseRequest {

    @NotNull(message = "phone can't be null")
    private String phone;

    @NotNull(message = "password can't be null")
    private String password;
}
