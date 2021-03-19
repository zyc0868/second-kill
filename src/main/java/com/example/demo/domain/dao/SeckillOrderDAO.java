package com.example.demo.domain.dao;

import com.example.demo.domain.entity.SeckillOrderDO;
import com.example.demo.domain.repository.SeckillOrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: flyboy
 * @Date: 18/03/2021 15:10
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Repository
public class SeckillOrderDAO {
    private SeckillOrderRepository repository;

    public SeckillOrderDAO(SeckillOrderRepository repository) {
        this.repository = repository;
    }

    public boolean existById(long id){
        return repository.existsById(id);
    }

    public SeckillOrderDO findByUserIdAndGoodsId(long userId, long goodsId){
        List<SeckillOrderDO> list = repository.findByUserIdAndGoodsId(userId,goodsId);
        if (list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    public SeckillOrderDO findById(long id){
        return repository.findById(id).get();
    }

    public SeckillOrderDO findByOrderId(long orderId){
        return repository.findByOrderId(orderId);
    }

    public SeckillOrderDO insert(SeckillOrderDO orderDO){
        return repository.saveAndFlush(orderDO);
    }
}
