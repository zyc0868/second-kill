package com.example.demo.dto.seckillCommonDto;

import com.example.demo.dto.BaseResponse;
import lombok.*;

/**
 * @author: flyboy
 * @Date: 18/03/2021 14:30
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePathResponse extends BaseResponse {
    private static final long serialVersionUID = 5305080156386928176L;

    private String path;
}
