package cn.javaplus.crazy.main;

import java.util.List;

import cn.javaplus.crazy.pocker.Place;
import cn.javaplus.crazy.protocol.Protocols.AbstractCpAction;
import cn.javaplus.crazy.protocol.Protocols.ProtocolContext;
import cn.javaplus.util.Util;

public class CpAction extends AbstractCpAction {

	@Override
	protected void excute(ProtocolContext ctx, String cardIds) {
		List<Integer> ids = Util.Collection.getIntegers(cardIds);
		Place place = ctx.getUser().getPlace();
		place.checkCurrent();
		place.cp(ids);
	}

}
