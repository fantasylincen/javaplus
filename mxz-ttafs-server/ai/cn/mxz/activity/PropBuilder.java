package cn.mxz.activity;

import cn.mxz.bossbattle.Prize;
import cn.mxz.protocols.user.PropP.PropPro;

public class PropBuilder {

	public PropPro build(int id, int count) {

		PropPro.Builder b = PropPro.newBuilder();

		b.setTypeId(id);

		b.setType("");

		b.setCount(count);

		return b.build();
	}

	public PropPro build(Prize p) {
		cn.mxz.protocols.user.PropP.PropPro.Builder b = PropPro.newBuilder();
		b.setCount( p.getCount() );
		b.setTypeId( p.getId() );
		b.setType("");
		return b.build();
	}
}
