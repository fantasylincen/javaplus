package cn.javaplus.crazy.main;

import cn.javaplus.crazy.pocker.Place;
import cn.javaplus.crazy.protocol.Protocols.AbstractQdzAction;
import cn.javaplus.crazy.protocol.Protocols.ProtocolContext;

public class QdzAction extends AbstractQdzAction {

	@Override
	protected void excute(ProtocolContext ctx, boolean isQ) {
		Place place = ctx.getUser().getPlace();
		place.sayDz(isQ, false);
	}

}
