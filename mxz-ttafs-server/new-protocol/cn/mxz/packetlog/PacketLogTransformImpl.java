package cn.mxz.packetlog;

import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.city.City;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

public class PacketLogTransformImpl implements PacketLogTransform {

	private City	city;

	@Override
	public void add(String packetName) {
		if (city == null) {
			throw new SureIllegalOperationException("客户端未接入!");
		}
		UserCounter his = city.getUserCounterHistory();
		his.add(CounterKey.PACKET_LOG, 1, packetName);
	}

	@Override
	public void setUser(City city) {
		this.city = city;
	}
}
