package com.example.demo.domain.dao;

import com.example.demo.domain.entity.SeckillGoodsDO;
import com.example.demo.domain.repository.SeckillGoodsRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author: flyboy
 * @Date: 14/03/2021 22:14
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */

@Repository
public class SeckillGoodsDAO {
    private SeckillGoodsRepository goodsRepository;

    public SeckillGoodsDAO(SeckillGoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public List<SeckillGoodsDO> getAllGoods(){
        return goodsRepository.findAll();
    }

    public SeckillGoodsDO getGoodsById(Long goodsId){
        Optional<SeckillGoodsDO> optional = goodsRepository.findById(goodsId);
        return optional.get();
    }

    public boolean existById(Long goodsId){
        return goodsRepository.existsById(goodsId);
    }

    @Transactional
    public boolean decreaseStocks(Long goodsId){
        SeckillGoodsDO seckillGoodsDO = goodsRepository.findById(goodsId).get();
        if (seckillGoodsDO.getGoodsStock() >= 1){
            seckillGoodsDO.setGoodsStock(seckillGoodsDO.getGoodsStock() - 1);
            goodsRepository.saveAndFlush(seckillGoodsDO);
            return true;
        }
        return false;
    }

    public void addGoods(SeckillGoodsDO seckillGoodsDO){
        goodsRepository.saveAndFlush(seckillGoodsDO);
    }

    // 这里并不严谨，但是为了启动项目时多次插入数据，所以采用了先找手机名字，再判断是否插入的方法
    public void addGoodsByName(SeckillGoodsDO seckillGoodsDO,String name){
        SeckillGoodsDO goodsDO = goodsRepository.findByGoodsName(name);
        if (goodsDO != null){
            goodsDO.setStartDate(seckillGoodsDO.getStartDate());
            goodsDO.setEndDate(seckillGoodsDO.getEndDate());
            goodsRepository.saveAndFlush(goodsDO);
        }else {
            goodsRepository.saveAndFlush(seckillGoodsDO);
        }

    }
}
