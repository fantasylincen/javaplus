package cn.javaplus.crazy.main;

import cn.javaplus.crazy.pocker.Place;
import cn.javaplus.crazy.protocol.Protocols.AbstractJdzAction;
import cn.javaplus.crazy.protocol.Protocols.ProtocolContext;

public class JdzAction extends AbstractJdzAction {

	@Override
	protected void excute(ProtocolContext ctx, boolean isJ) {
		Place place = ctx.getUser().getPlace();
		place.sayDz(isJ, true);
	}

}
