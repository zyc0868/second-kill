package com.example.demo.domain.repository;

import com.example.demo.domain.entity.SeckillGoodsDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: flyboy
 * @Date: 12/03/2021 18:13
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Repository
public interface SeckillGoodsRepository extends JpaRepository<SeckillGoodsDO, Long> {
    boolean existsById(Long id);
    List<SeckillGoodsDO> findAll();
}