package com.example.demo.dto.userDto;

import com.example.demo.domain.entity.UserDO;
import com.example.demo.dto.BaseResponse;
import lombok.*;

/**
 * @author: flyboy
 * @Date: 12/03/2021 19:52
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse extends BaseResponse {
    private static final long serialVersionUID = -3038659181250587472L;

    private UserDO userDO;
}
