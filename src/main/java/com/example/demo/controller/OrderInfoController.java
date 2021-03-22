package com.example.demo.controller;

import com.example.demo.common.AccessLimit;
import com.example.demo.domain.entity.OrderInfoDO;
import com.example.demo.domain.entity.SeckillGoodsDO;
import com.example.demo.domain.entity.UserDO;
import com.example.demo.dto.MessageRequest;
import com.example.demo.dto.MessageResponse;
import com.example.demo.dto.orderDto.OrderDetailRequest;
import com.example.demo.dto.orderDto.OrderDetailResponse;
import com.example.demo.redis.RedisValueOperations;
import com.example.demo.service.SeckillGoodsService;
import com.example.demo.service.SeckillOrderService;
import com.example.demo.util.CookieUtil;
import com.example.demo.util.ErrorConstants;
import com.example.demo.util.JsonUtil;
import com.example.demo.redis.RedisPrefixKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: flyboy
 * @Date: 18/03/2021 17:51
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Controller
@RequestMapping("/order")
public class OrderInfoController {
    @Autowired
    private SeckillGoodsService seckillGoodsService;

    @Autowired
    private RedisValueOperations redisValueOperations;

    @Autowired
    private SeckillOrderService seckillOrderService;

    @AccessLimit
    @PostMapping("/detail")
    @ResponseBody
    public MessageResponse<OrderDetailResponse> orderInfo(@RequestBody MessageRequest<OrderDetailRequest> request, HttpServletRequest httpServletRequest){
        String token = CookieUtil.readLoginToken(httpServletRequest);
        String jsonUser = (String) redisValueOperations.get(RedisPrefixKey.USER_NAME.getPrefix()+token);
        UserDO userDO = JsonUtil.json2Obj(jsonUser,UserDO.class);
        if (userDO == null){
            return request.response(ErrorConstants.USER_NOT_LOGIN,new OrderDetailResponse());
        }
        OrderDetailRequest data = request.getData();
        long orderId = data.getOrderId();
        OrderInfoDO orderInfoDO = seckillOrderService.getOrderInfo(orderId);
        if (orderInfoDO == null){
            return  request.response(ErrorConstants.ORDER_NOT_FOUND,new OrderDetailResponse());
        }
        long goodsId = orderInfoDO.getGoodsId();
        SeckillGoodsDO goodsDO = seckillGoodsService.getGoodsById(goodsId);
        OrderDetailResponse response = OrderDetailResponse.builder()
                .order(orderInfoDO)
                .goods(goodsDO)
                .user(userDO)
                .build();
        return request.response(response);
    }
}
