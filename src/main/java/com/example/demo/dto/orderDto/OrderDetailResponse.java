package com.example.demo.dto.orderDto;

import com.example.demo.domain.entity.OrderInfoDO;
import com.example.demo.domain.entity.SeckillGoodsDO;
import com.example.demo.domain.entity.UserDO;
import com.example.demo.dto.BaseResponse;
import lombok.*;

/**
 * @author: flyboy
 * @Date: 18/03/2021 23:48
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse extends BaseResponse {
    private static final long serialVersionUID = 1699856627467580964L;
    private SeckillGoodsDO goods;
    private OrderInfoDO order;
    private UserDO user;
}
