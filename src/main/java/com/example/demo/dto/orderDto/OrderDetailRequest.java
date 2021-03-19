package com.example.demo.dto.orderDto;

import com.example.demo.dto.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: flyboy
 * @Date: 18/03/2021 23:47
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Getter
@Setter
@ToString
public class OrderDetailRequest extends BaseRequest {
    private static final long serialVersionUID = 9006202173906475509L;

    private long orderId;
}
