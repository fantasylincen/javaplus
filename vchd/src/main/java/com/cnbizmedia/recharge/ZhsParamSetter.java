package com.cnbizmedia.recharge;

import scala.collection.mutable.StringBuilder;
import cn.javaplus.log.Log;
import cn.javaplus.util.Util;

import com.cnbizmedia.config.ServerNode;
import com.cnbizmedia.gen.dto.MongoGen.OrderDto;
import com.ning.http.client.RequestBuilder;

public class ZhsParamSetter implements ParameterSetter {

	@Override
	public void addParameters(RequestBuilder b, OrderDto order, ServerNode node) {
		long time = System.currentTimeMillis() / 1000;

		b.addParameter("did", node.getString("rechargePar-did"));
		b.addParameter("uid", order.getUserId());
		b.addParameter("goodsid", "-1");
		b.addParameter("money", getMoney(order));
		b.addParameter("pt", time + "");
		b.addParameter("tcd", order.getId());
		b.addParameter("st", "1");
		b.addParameter("sign", buildSign(order, node, time));
	}

	private String getMoney(OrderDto order) {
		return order.getCount() + ".0";
	}

	private String buildSign(OrderDto order, ServerNode node, long time) {
		StringBuilder sb = new StringBuilder();
		sb.append("did=" + node.getString("rechargePar-did"));
		sb.append("&uid=" + order.getUserId());
		sb.append("&goodsid=" + "-1");
		sb.append("&money=" + order.getCount() + ".0");
		sb.append("&pt=" + time + "");
		sb.append("&tcd=" + order.getId());
		sb.append("&st=1");
		
		String md5 = Util.Secure.md5(sb.toString() + "LINGCHENGDENGXINFENG");
		Log.d("request", node.getString("rechargeUrl") + "?" + sb + "&sign=" + md5);
		return md5;
	}

}
