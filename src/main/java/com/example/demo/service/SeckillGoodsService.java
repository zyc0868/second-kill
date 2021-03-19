package com.example.demo.service;

import com.example.demo.domain.entity.SeckillGoodsDO;

import java.util.List;

/**
 * @author: flyboy
 * @Date: 12/03/2021 19:49
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
public interface SeckillGoodsService {
    List<SeckillGoodsDO> getSeckillGoodsList();
    SeckillGoodsDO getGoodsById(long goodsId);
    boolean reduceStock(long goodsId);
}
