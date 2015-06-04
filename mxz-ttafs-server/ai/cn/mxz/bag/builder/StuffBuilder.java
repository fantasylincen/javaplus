package cn.mxz.bag.builder;

import cn.mxz.prop.Stuff;
import cn.mxz.protocols.user.god.StuffP.StuffPro;


public class StuffBuilder{

	public StuffPro build(Stuff s) {

		StuffPro.Builder b = StuffPro.newBuilder();

		b.setCount(s.getCount());

		b.setTypeId(s.getTypeId());

		return b.build();
	}

}
