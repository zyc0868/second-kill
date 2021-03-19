package com.example.demo.dto.goodsDto;

import com.example.demo.domain.entity.SeckillGoodsDO;
import com.example.demo.domain.entity.UserDO;
import com.example.demo.dto.BaseResponse;
import lombok.*;

/**
 * @author: flyboy
 * @Date: 18/03/2021 11:04
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDetailResponse extends BaseResponse {
    private static final long serialVersionUID = -248249487347434377L;

    private SeckillGoodsDO seckillGoods;

    private UserDO user;

    private Integer remainSeconds;

    private Integer seckillStatus;
}
