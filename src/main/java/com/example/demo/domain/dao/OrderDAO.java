package com.example.demo.domain.dao;

import com.example.demo.domain.entity.OrderInfoDO;
import com.example.demo.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author: flyboy
 * @Date: 18/03/2021 19:33
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Repository
public class OrderDAO {
    private OrderRepository repository;

    public OrderDAO(OrderRepository repository) {
        this.repository = repository;
    }

    public OrderInfoDO insert(OrderInfoDO orderInfoDO){
        return repository.saveAndFlush(orderInfoDO);
    }

    public OrderInfoDO findById(long id){
        return repository.findById(id).get();
    }
}
