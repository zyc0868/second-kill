package com.example.demo.rabbitMQ;

import com.example.demo.domain.entity.UserDO;

/**
 * @author: flyboy
 * @Date: 18/03/2021 16:51
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
public class SeckillMessage {
	private UserDO user;
	private long goodsId;
	public UserDO getUser() {
		return user;
	}
	public void setUser(UserDO user) {
		this.user = user;
	}
	public long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}
}
