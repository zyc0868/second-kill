package com.example.demo.domain.repository;

import com.example.demo.domain.entity.SeckillOrderDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: flyboy
 * @Date: 12/03/2021 18:14
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Repository
public interface SeckillOrderRepository extends JpaRepository<SeckillOrderDO, Long> {
    List<SeckillOrderDO> findByUserIdAndGoodsId(long userId, long goodsId);
    SeckillOrderDO findByOrderId(long orderId);
    SeckillOrderDO findByGoodsId(long goodsId);
    SeckillOrderDO findByUserId(long userId);
}
