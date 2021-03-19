package com.example.demo.dto.userDto;

import com.example.demo.dto.BaseRequest;
import lombok.*;

/**
 * @author: flyboy
 * @Date: 12/03/2021 22:30
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
public class LogoutRequest extends BaseRequest {
    private static final long serialVersionUID = 4814672432272588331L;
}
