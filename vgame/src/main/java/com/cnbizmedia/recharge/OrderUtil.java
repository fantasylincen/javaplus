package com.cnbizmedia.recharge;

import com.cnbizmedia.config.GameServersXml;
import com.cnbizmedia.config.ServerNode;
import com.cnbizmedia.gen.dto.MongoGen.OrderDto;

public class OrderUtil {

	/**
	 * 值多少V币
	 * @param o
	 * @return
	 */
	public static int getVbNeed(OrderDto o) {
		int c = o.getCount();
		ServerNode n = GameServersXml.getServer(o.getServerId());
		double s = n.getDouble("scale");
		return (int) (c / s);
	}

}
