package com.example.demo.dto.goodsDto;

import com.example.demo.dto.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: flyboy
 * @Date: 18/03/2021 11:06
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Getter
@Setter
@ToString
public class GoodsDetailRequest extends BaseRequest {
    private static final long serialVersionUID = 1701552734844460673L;

    private Long goodsId;
}
