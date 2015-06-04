package cn.mxz.market;

import com.alibaba.fastjson.JSON;

import cn.mxz.prop.PropTempletFactory;

public class Goods {

	private int	toolId;
	private int	count;
	private int	price;

	public Goods(int toolId, int count, int price) {
		this.toolId = toolId;
		this.count = count;
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public int getToolId() {
		return toolId;
	}

	public int getPrice() {
		return price;
	}

	public String getName() {
		return PropTempletFactory.get(toolId).getName();
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
