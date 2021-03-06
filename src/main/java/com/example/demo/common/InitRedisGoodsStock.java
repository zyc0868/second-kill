package com.example.demo.common;

import com.example.demo.domain.dao.SeckillGoodsDAO;
import com.example.demo.domain.entity.SeckillGoodsDO;
import com.example.demo.redis.RedisTimeUtil;
import com.example.demo.redis.ValueOperations;
import com.example.demo.redis.RedisPrefixKey;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: flyboy
 * @Date: 18/03/2021 20:30
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Service
public class InitRedisGoodsStock implements InitializingBean {
    @Autowired
    private SeckillGoodsDAO seckillGoodsDAO;
    @Autowired
    private ValueOperations redisValueOperations;

    public void afterPropertiesSet() throws Exception {
        List<SeckillGoodsDO> goodsDOList = seckillGoodsDAO.getAllGoods();
        for (SeckillGoodsDO goodsDO : goodsDOList){
            redisValueOperations.set(RedisPrefixKey.GOODS_STOCK.getPrefix()+goodsDO.getId(),String.valueOf(goodsDO.getGoodsStock()));
        }
    }
}
