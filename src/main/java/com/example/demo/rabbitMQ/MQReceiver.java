package com.example.demo.rabbitMQ;

import com.example.demo.domain.entity.SeckillGoodsDO;
import com.example.demo.domain.entity.SeckillOrderDO;
import com.example.demo.domain.entity.UserDO;
import com.example.demo.redis.RedisValueOperations;
import com.example.demo.service.SeckillGoodsService;
import com.example.demo.service.SeckillOrderService;
import com.example.demo.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author: flyboy
 * @Date: 18/03/2021 16:58
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Service
public class MQReceiver {

		private static Logger log = LoggerFactory.getLogger(MQReceiver.class);
		@Autowired
		RedisValueOperations redisService;
		
		@Autowired
		SeckillGoodsService goodsService;
		
		@Autowired
		SeckillOrderService seckillOrderService;
		
		@RabbitListener(queues= com.example.demo.rabbitMQ.MQConfig.MIAOSHA_QUEUE)
		public void receive(String message) {
			log.info("receive message:"+message);
			SeckillMessage mm  = JsonUtil.toBean(message, SeckillMessage.class);
			UserDO user = mm.getUser();
			long goodsId = mm.getGoodsId();

			SeckillGoodsDO goods = goodsService.getGoodsById(goodsId);
	    	int stock = goods.getGoodsStock();
	    	if(stock <= 0) {
	    		return;
	    	}
	    	//判断是否已经秒杀到了
	    	SeckillOrderDO order = seckillOrderService.getSeckillOrderByUserIdGoodsId(user.getId(), goodsId);
	    	if(order != null) {
	    		return;
	    	}
	    	//减库存 下订单 写入秒杀订单
			seckillOrderService.insert(user, goods);
		}
}
