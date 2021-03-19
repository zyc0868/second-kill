package com.example.demo.dto.orderDto;

import com.example.demo.dto.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: flyboy
 * @Date: 18/03/2021 17:41
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Getter
@Setter
@ToString
public class PurchaseResultRequest extends BaseRequest {
    private static final long serialVersionUID = 6934490949311893362L;

    private Long goodsId;
}
