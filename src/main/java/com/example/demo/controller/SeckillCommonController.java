package com.example.demo.controller;

import com.example.demo.common.AccessLimit;
import com.example.demo.domain.entity.SeckillOrderDO;
import com.example.demo.domain.entity.UserDO;
import com.example.demo.dto.MessageRequest;
import com.example.demo.dto.MessageResponse;
import com.example.demo.dto.orderDto.PurchaseResultRequest;
import com.example.demo.dto.orderDto.PurchaseResultResponse;
import com.example.demo.dto.seckillCommonDto.CreatePathRequest;
import com.example.demo.dto.seckillCommonDto.CreatePathResponse;
import com.example.demo.dto.seckillCommonDto.PurchaseRequest;
import com.example.demo.dto.seckillCommonDto.PurchaseResponse;
import com.example.demo.rabbitMQ.MQSender;
import com.example.demo.rabbitMQ.SeckillMessage;
import com.example.demo.redis.RedisValueOperations;
import com.example.demo.service.SeckillGoodsService;
import com.example.demo.service.SeckillOrderService;
import com.example.demo.util.CookieUtil;
import com.example.demo.util.ErrorConstants;
import com.example.demo.util.JsonUtil;
import com.example.demo.redis.RedisPrefixKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: flyboy
 * @Date: 18/03/2021 10:41
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Controller
@RequestMapping("/seckill")
public class SeckillCommonController {

    @Autowired
    private SeckillGoodsService seckillGoodsService;

    @Autowired
    private RedisValueOperations redisValueOperations;

    @Autowired
    private SeckillOrderService seckillOrderService;

    @Autowired
    MQSender mqSender;

    // 下单
    @PostMapping("/{path}/seckill")
    @ResponseBody
    public MessageResponse<PurchaseResponse> purchase(@RequestBody MessageRequest<PurchaseRequest> request, HttpServletRequest httpServletRequest){
        String token = CookieUtil.readLoginToken(httpServletRequest);
        String userJson = (String) redisValueOperations.get(RedisPrefixKey.USER_NAME.getPrefix()+token);
        UserDO user = JsonUtil.json2Obj(userJson,UserDO.class);

        PurchaseRequest data = request.getData();
        long goodsId = data.getGoodsId();
        String path = data.getPath();

        if (user == null){
            request.response(ErrorConstants.USER_NOT_LOGIN,new PurchaseResponse());
        }
        // 验证路径
        boolean pathValid = seckillOrderService.checkPath(user,goodsId,path);
        if (!pathValid){
            request.response(ErrorConstants.REQUEST_PATH_ILLEGAL,new PurchaseResponse());
        }

        // 判断是否已经秒杀过
        SeckillOrderDO seckillOrder = seckillOrderService.getSeckillOrderByUserIdGoodsId(user.getId(), goodsId);
        if (seckillOrder != null){
            return request.response(ErrorConstants.GOODS_REPEATED_PURCHASE,new PurchaseResponse());
        }

        // 判断库存 预减库存
        long stock = redisValueOperations.decrement(RedisPrefixKey.GOODS_STOCK.getPrefix()+goodsId);
        if (stock < 0){
            request.response(ErrorConstants.GOODS_SOLD_OUT,new PurchaseResponse());
        }

        // 入队
        SeckillMessage mm = new SeckillMessage();
        mm.setUser(user);
        mm.setGoodsId(goodsId);
        mqSender.sendSeckillMessage(mm);

        return request.response(new PurchaseResponse(0));
    }

    /**
     * 客户端轮询查询是否下单成功
     * orderId：成功
     * -1：秒杀失败
     * 0： 排队中
     */
    @PostMapping("/result")
    @ResponseBody
    public MessageResponse<PurchaseResultResponse> purchaseResult(@RequestBody MessageRequest<PurchaseResultRequest> request, HttpServletRequest httpServletRequest){
        String token = CookieUtil.readLoginToken(httpServletRequest);
        String userJson = (String) redisValueOperations.get(RedisPrefixKey.USER_NAME.getPrefix()+token);
        UserDO user = JsonUtil.json2Obj(userJson,UserDO.class);
        PurchaseResultRequest data = request.getData();
        long goodsId = data.getGoodsId();
        if (user == null){
            return request.response(ErrorConstants.USER_NOT_LOGIN,new PurchaseResultResponse());
        }
        long result = seckillOrderService.getSeckillResult(user.getId(),goodsId);
        return request.response(new PurchaseResultResponse(result));
    }

    @AccessLimit(seconds = 3,limit = 10)
    @PostMapping("/path")
    @ResponseBody
    public MessageResponse<CreatePathResponse> getSeckillPath(@RequestBody MessageRequest<CreatePathRequest> request, HttpServletRequest httpServletRequest){
        String token = CookieUtil.readLoginToken(httpServletRequest);
        String userJson = (String) redisValueOperations.get(RedisPrefixKey.USER_NAME.getPrefix()+token);
        UserDO user = JsonUtil.json2Obj(userJson,UserDO.class);
        if (user == null){
            return request.response(ErrorConstants.USER_NOT_LOGIN,new CreatePathResponse());
        }
        MessageResponse<CreatePathResponse> response = seckillOrderService.createSeckillPath(request, user.getId());
        return response;
    }
}
