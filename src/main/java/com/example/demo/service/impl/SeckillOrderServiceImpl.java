package com.example.demo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.example.demo.domain.dao.OrderDAO;
import com.example.demo.domain.dao.SeckillGoodsDAO;
import com.example.demo.domain.dao.SeckillOrderDAO;
import com.example.demo.domain.dao.UserDAO;
import com.example.demo.domain.entity.OrderInfoDO;
import com.example.demo.domain.entity.SeckillGoodsDO;
import com.example.demo.domain.entity.SeckillOrderDO;
import com.example.demo.domain.entity.UserDO;
import com.example.demo.dto.MessageRequest;
import com.example.demo.dto.MessageResponse;
import com.example.demo.dto.seckillCommonDto.CreatePathRequest;
import com.example.demo.dto.seckillCommonDto.CreatePathResponse;
import com.example.demo.redis.operation.ValueOperations;
import com.example.demo.service.SeckillGoodsService;
import com.example.demo.service.SeckillOrderService;
import com.example.demo.util.ErrorConstants;
import com.example.demo.util.MD5Util;
import com.example.demo.util.RedisPrefixKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

/**
 * @author: flyboy
 * @Date: 18/03/2021 14:43
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Slf4j
@Service
public class SeckillOrderServiceImpl implements SeckillOrderService {

    @Autowired
    SeckillOrderDAO seckillOrderDAO;

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    SeckillGoodsService goodsService;

    @Autowired
    UserDAO userDAO;

    @Autowired
    ValueOperations redisValueOperations;

    @Autowired
    SeckillGoodsDAO goodsDAO;


    @Override
    public SeckillOrderDO getSeckillOrderByUserIdGoodsId(long userId, long goodsId) {
        return seckillOrderDAO.findByUserIdAndGoodsId(userId,goodsId);
    }

    @Override
    public long getSeckillResult(long userId, long goodsId) {
        SeckillOrderDO order = seckillOrderDAO.findByUserIdAndGoodsId(userId,goodsId);
        if (order != null){
            return order.getOrderId();
        }
        boolean over = getGoodsOver(goodsId);
        if (over){
            return -1;
        }
        return 0;
    }

    @Transactional
    @Override
    public OrderInfoDO insert(UserDO user, SeckillGoodsDO goodsBo) {
        boolean success = goodsService.reduceStock(goodsBo.getId());
        if (success){
            OrderInfoDO orderInfoDO = new OrderInfoDO();
            orderInfoDO.setUserId(user.getId());
            orderInfoDO.setGoodsId(goodsBo.getId());
            orderInfoDO.setAddress(user.getAddress());
            orderInfoDO.setGoodsName(goodsBo.getGoodsName());
            orderInfoDO.setGoodsCount(1);
            orderInfoDO.setGoodsPrice(goodsBo.getSeckillPrice());
            orderInfoDO.setStatus(0);
            orderInfoDO.setPayDate(new Date());
            OrderInfoDO DBOrderInfo = orderDAO.insert(orderInfoDO);

            log.info("order id :"+DBOrderInfo.getId());
            SeckillOrderDO seckillOrderDO =  new SeckillOrderDO();
            seckillOrderDO.setOrderId(DBOrderInfo.getId());
            seckillOrderDO.setGoodsId(goodsBo.getId());
            seckillOrderDO.setUserId(user.getId());

            seckillOrderDAO.insert(seckillOrderDO);
            return DBOrderInfo;
        }
        setGoodsOver(goodsBo.getId());
        return null;
    }

    @Override
    public OrderInfoDO getOrderInfo(long orderId) {
        if (!seckillOrderDAO.existById(orderId)){
            return null;
        }
        SeckillOrderDO seckillOrderDO = seckillOrderDAO.findById(orderId);
        return orderDAO.findById(seckillOrderDO.getOrderId());
    }

    @Override
    public boolean checkPath(UserDO user, long goodsId, String path) {
        if (user == null || path == null){
            return false;
        }
        String existPath = redisValueOperations.get(RedisPrefixKey.PATH_CHECK.getPrefix() +user.getId()+"_"+goodsId);
        return StringUtils.equals(existPath,path);
    }


    @Override
    public MessageResponse<CreatePathResponse> createSeckillPath(MessageRequest<CreatePathRequest> request,long userId) {
        CreatePathRequest data = request.getData();
        long goodsId = data.getGoodsId();
        if (!userDAO.existById(userId)){
            return request.response(ErrorConstants.USER_NOT_FOUND,new CreatePathResponse());
        }
        if (!goodsDAO.existById(goodsId)){
            return request.response(ErrorConstants.REQUEST_GOODS_NOT_FOUND,new CreatePathResponse());
        }

        String path = MD5Util.md5(UUID.randomUUID()+"abcd1234");
        redisValueOperations.set(RedisPrefixKey.PATH_CHECK.getPrefix()+userId+"_"+goodsId,path);
        CreatePathResponse response = CreatePathResponse.builder()
                .path(path)
                .build();
        return request.response(response);
    }

    private void setGoodsOver(long goodsId){
        redisValueOperations.set(RedisPrefixKey.GOODS_OVER.getPrefix()+goodsId,"true");
    }

    private boolean getGoodsOver(long goodsId){
        String over = redisValueOperations.get(RedisPrefixKey.GOODS_OVER.getPrefix()+goodsId);
        if (StringUtils.equals(over,"true")){
            return true;
        }
        return false;
    }
}

