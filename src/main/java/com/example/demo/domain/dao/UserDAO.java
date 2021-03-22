package com.example.demo.domain.dao;

import com.example.demo.domain.entity.UserDO;
import com.example.demo.domain.repository.UserRepository;
import lombok.Getter;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: flyboy
 * @Date: 12/03/2021 19:58
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */

@Repository
public class UserDAO {
    private final UserRepository repository;

    public UserDAO(UserRepository repository) {
        this.repository = repository;
    }

    public UserDO findByPhone(String phone){
        return repository.findByPhone(phone);
    }

    public boolean existById(long id){
        return repository.existsById(id);
    }

    public UserDO updateInfoAfterLogin(UserDO userDO){
        return repository.saveAndFlush(userDO);
    }

    public void createBatchUser(List<UserDO> userDOList){
        repository.saveAll(userDOList);
    }

    public void createByPhoneNumber(UserDO userDO,String phoneNumber){
        UserDO existing = findByPhone(phoneNumber);
        if (existing == null){
            repository.saveAndFlush(userDO);
        }
    }
}
