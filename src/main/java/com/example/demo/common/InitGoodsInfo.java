package com.example.demo.common;

import com.example.demo.domain.dao.OrderDAO;
import com.example.demo.domain.dao.SeckillGoodsDAO;
import com.example.demo.domain.entity.SeckillGoodsDO;
import com.example.demo.redis.RedisPrefixKey;
import com.example.demo.redis.ValueOperations;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author: flyboy
 * @Date: 22/03/2021 12:49
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Service
public class InitGoodsInfo implements InitializingBean {
    @Autowired
    private SeckillGoodsDAO seckillGoodsDAO;

    public void afterPropertiesSet() throws Exception {
        SeckillGoodsDO seckillGoodsDO = new SeckillGoodsDO();
        seckillGoodsDO.setGoodsName("iphone");
        seckillGoodsDO.setGoodsStock(100);
        seckillGoodsDO.setGoodsPrice(BigDecimal.valueOf(7999));
        seckillGoodsDO.setSeckillPrice(BigDecimal.valueOf(6999));
        seckillGoodsDO.setGoodsImg("/img/iphone.jpg");
        seckillGoodsDO.setGoodsDetail("this iphone is very good");
        seckillGoodsDO.setGoodsTitle("iphone x");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        // 秒杀正在进行
        String startDate = "2021/01/01 10:00:00";
        seckillGoodsDO.setStartDate(sdf.parse(startDate));
        Date endDate = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(endDate);
        calendar.add(Calendar.DATE,2);
        seckillGoodsDO.setEndDate(calendar.getTime());
        seckillGoodsDAO.addGoodsByName(seckillGoodsDO,"iphone");

        SeckillGoodsDO seckillGoodsDO2 = new SeckillGoodsDO();
        seckillGoodsDO2.setGoodsImg("/img/xiaomi.jpg");
        seckillGoodsDO2.setGoodsPrice(BigDecimal.valueOf(3999));
        seckillGoodsDO2.setSeckillPrice(BigDecimal.valueOf(2999));
        seckillGoodsDO2.setGoodsName("xiao mi");
        seckillGoodsDO2.setGoodsDetail("xiao mi is very good");
        seckillGoodsDO2.setGoodsTitle("xiao mi x");
        seckillGoodsDO2.setGoodsStock(300);
        seckillGoodsDO2.setStartDate(sdf.parse(startDate));
        // 秒杀已结束
        seckillGoodsDO2.setEndDate(sdf.parse("2021/01/02 10:00:00"));
        seckillGoodsDAO.addGoodsByName(seckillGoodsDO2,"xiao mi");

        SeckillGoodsDO seckillGoodsDO3 = new SeckillGoodsDO();
        seckillGoodsDO3.setGoodsPrice(BigDecimal.valueOf(4999));
        seckillGoodsDO3.setSeckillPrice(BigDecimal.valueOf(3999));
        seckillGoodsDO3.setGoodsImg("/img/oppo.jpg");
        seckillGoodsDO3.setGoodsName("oppo");
        seckillGoodsDO3.setGoodsDetail("oppo is very good");
        seckillGoodsDO3.setGoodsTitle("oppo x");
        seckillGoodsDO3.setGoodsStock(400);
        Date startDate1 = new Date();
        calendar.setTime(startDate1);
        calendar.add(Calendar.MINUTE,3);
        // 秒杀还未开始
        seckillGoodsDO3.setStartDate(calendar.getTime());
        calendar.add(Calendar.DATE,1);
        seckillGoodsDO3.setEndDate(calendar.getTime());
        seckillGoodsDAO.addGoodsByName(seckillGoodsDO3,"oppo");
    }
}