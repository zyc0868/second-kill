package com.example.demo.domain.repository;

import com.example.demo.domain.entity.OrderInfoDO;
import com.example.demo.domain.entity.SeckillOrderDO;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: flyboy
 * @Date: 12/03/2021 18:13
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderInfoDO, Long> {
    boolean existsById(Long id);
}
