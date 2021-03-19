package com.example.demo.service;

import com.example.demo.domain.entity.OrderInfoDO;
import com.example.demo.domain.entity.SeckillGoodsDO;
import com.example.demo.domain.entity.SeckillOrderDO;
import com.example.demo.domain.entity.UserDO;
import com.example.demo.dto.MessageRequest;
import com.example.demo.dto.MessageResponse;
import com.example.demo.dto.seckillCommonDto.CreatePathRequest;
import com.example.demo.dto.seckillCommonDto.CreatePathResponse;

/**
 * @author: flyboy
 * @Date: 12/03/2021 19:49
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
public interface SeckillOrderService {

    SeckillOrderDO getSeckillOrderByUserIdGoodsId(long userId , long goodsId);

    long getSeckillResult(long userId, long goodsId);

    OrderInfoDO insert(UserDO user , SeckillGoodsDO goodsBo);

    OrderInfoDO getOrderInfo(long orderId);

    boolean checkPath(UserDO user, long goodsId, String path);

    MessageResponse<CreatePathResponse> createSeckillPath(MessageRequest<CreatePathRequest> request,long userId);
}
