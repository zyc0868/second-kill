package com.example.demo.domain.repository;

import com.example.demo.domain.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: flyboy
 * @Date: 12/03/2021 18:13
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */

@Repository
public interface UserRepository extends JpaRepository<UserDO, Long> {
    UserDO findByPhone(String phone);

}
