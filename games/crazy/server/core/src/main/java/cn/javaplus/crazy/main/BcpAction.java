package cn.javaplus.crazy.main;

import cn.javaplus.crazy.pocker.Place;
import cn.javaplus.crazy.protocol.Protocols.AbstractBcpAction;
import cn.javaplus.crazy.protocol.Protocols.ProtocolContext;

public class BcpAction extends AbstractBcpAction {

	@Override
	protected void excute(ProtocolContext ctx) {
		Place place = ctx.getUser().getPlace();
		place.checkCurrent();
		place.pass();
	}
}
