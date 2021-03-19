package com.example.demo.dto.seckillCommonDto;

import com.example.demo.dto.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: flyboy
 * @Date: 18/03/2021 14:38
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Getter
@Setter
@ToString
public class PurchaseRequest extends BaseRequest {
    private static final long serialVersionUID = 1994895407288357907L;

    private String path;

    private Long goodsId;
}
