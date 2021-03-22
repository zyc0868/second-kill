package com.example.demo.common;

import com.example.demo.domain.dao.SeckillGoodsDAO;
import com.example.demo.domain.dao.UserDAO;
import com.example.demo.domain.entity.SeckillGoodsDO;
import com.example.demo.domain.entity.UserDO;
import com.example.demo.redis.RedisPrefixKey;
import com.example.demo.redis.ValueOperations;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author: flyboy
 * @Date: 22/03/2021 12:27
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Service
public class InitUserInfo implements InitializingBean {
    @Autowired
    private UserDAO userDAO;

    public void afterPropertiesSet() throws Exception {
        List<UserDO> userDOList = new ArrayList<>();
        String[] addressList = new String[]{"北京市北京大学","北京市清华大学","上海市复旦大学","上海市上海交通大学",
                "杭州市浙江大学","合肥市中国科学技术大学","南京市南京大学"};
        String[] nameList = new String[]{"赵先生","钱女士","孙先生","李女士"};
        String phoneNumberPrefix = "138666";
        String phoneNumberSuffix = "60000";
        Random random = new Random();
        for (int i=0;i<1000;i++){
            UserDO userDO = new UserDO();
            int randomName = random.nextInt(nameList.length);
            int randomAddress = random.nextInt(addressList.length);
            userDO.setUserName(nameList[randomName]);
            userDO.setAddress(addressList[randomAddress]);
            String number = phoneNumberPrefix + (Integer.parseInt(phoneNumberSuffix)+i);
            userDO.setPhone(number);
            userDO.setPassword("ae2fe40a6242ef07a35a30da2232e10a");
            userDO.setSalt("9d5b364d");
            userDO.setLoginCount(0);
            userDO.setLastLoginDate(new Date());
            userDOList.add(userDO);
            userDAO.createByPhoneNumber(userDO,number);
        }
    }
}