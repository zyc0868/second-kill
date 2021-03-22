package com.example.demo.controller;

import com.example.demo.domain.entity.SeckillGoodsDO;
import com.example.demo.domain.entity.UserDO;
import com.example.demo.dto.MessageRequest;
import com.example.demo.dto.MessageResponse;
import com.example.demo.dto.goodsDto.GoodsDetailRequest;
import com.example.demo.dto.goodsDto.GoodsDetailResponse;
import com.example.demo.redis.RedisValueOperations;
import com.example.demo.service.SeckillGoodsService;
import com.example.demo.util.CookieUtil;
import com.example.demo.util.ErrorConstants;
import com.example.demo.util.JsonUtil;
import com.example.demo.redis.RedisPrefixKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: flyboy
 * @Date: 14/03/2021 22:35
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Controller
@RequestMapping("/goods")
public class SeckillGoodsController {
    @Autowired
    SeckillGoodsService seckillGoodsService;

    @Autowired
    private RedisValueOperations redisValueOperations;

    @RequestMapping("/list")
    public String goodsList(Model model){
        List<SeckillGoodsDO> goodsDOList = seckillGoodsService.getSeckillGoodsList();
        model.addAttribute("goodsList",goodsDOList);
        return "goods_list";
    }

    @PostMapping(value = "/detail/{goodsId}")
    @ResponseBody
    public MessageResponse<GoodsDetailResponse> detail(@RequestBody MessageRequest<GoodsDetailRequest> request, HttpServletRequest httpServletRequest){
        GoodsDetailRequest request1 = request.getData();
        Long goodsId = request1.getGoodsId();
        SeckillGoodsDO seckillGoodsDO = seckillGoodsService.getGoodsById(goodsId);
        if (seckillGoodsDO == null){
            return request.response(ErrorConstants.REQUEST_GOODS_NOT_FOUND,new GoodsDetailResponse());
        }
        long startDate = seckillGoodsDO.getStartDate().getTime();
        long endDate = seckillGoodsDO.getEndDate().getTime();
        // 秒杀正在进行中
        int seckillStatus = 1, remainSeconds = 0;
        // 秒杀未开始
        if (System.currentTimeMillis() < startDate){
            seckillStatus = 0;
            remainSeconds = (int)((startDate - System.currentTimeMillis())/1000);
        } else if (System.currentTimeMillis() > endDate){
            // 秒杀已结束
            seckillStatus = 2;
            remainSeconds = -1;
        }

        String token = CookieUtil.readLoginToken(httpServletRequest);
        // 此处应使用redis获得用户信息
        String jsonUser = (String) redisValueOperations.get(RedisPrefixKey.USER_NAME.getPrefix()+token);
        UserDO user = JsonUtil.json2Obj(jsonUser,UserDO.class);
        GoodsDetailResponse response = GoodsDetailResponse.builder()
                .seckillGoods(seckillGoodsDO)
                .user(user)
                .seckillStatus(seckillStatus)
                .remainSeconds(remainSeconds)
                .build();
        return request.response(response);
    }

}
