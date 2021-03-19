package com.example.demo.service.impl;

import com.example.demo.domain.dao.SeckillGoodsDAO;
import com.example.demo.domain.entity.SeckillGoodsDO;
import com.example.demo.service.SeckillGoodsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: flyboy
 * @Date: 14/03/2021 22:12
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */

@Service
public class SeckillGoodsServiceImpl implements SeckillGoodsService {

    private final SeckillGoodsDAO seckillGoodsDAO;
    public SeckillGoodsServiceImpl(SeckillGoodsDAO seckillGoodsDAO) {
        this.seckillGoodsDAO = seckillGoodsDAO;
    }

    @Override
    public List<SeckillGoodsDO> getSeckillGoodsList() {
        return seckillGoodsDAO.getAllGoods();
    }

    @Override
    public SeckillGoodsDO getGoodsById(long goodsId) {
        return seckillGoodsDAO.getGoodsById(goodsId);
    }

    @Override
    public boolean reduceStock(long goodsId) {
        return seckillGoodsDAO.decreaseStocks(goodsId);
    }
}
