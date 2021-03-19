package com.example.demo.dto.orderDto;

import com.example.demo.dto.BaseResponse;
import lombok.*;

/**
 * @author: flyboy
 * @Date: 18/03/2021 17:41
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResultResponse extends BaseResponse {
    private static final long serialVersionUID = -2689579249901315211L;

    private Long result;
}
