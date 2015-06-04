package cn.mxz.corona;

import java.util.Collection;
import java.util.List;

import cn.mxz.activity.PropBuilder;
import cn.mxz.bossbattle.Prize;
import cn.mxz.protocols.user.PropP.PropPro;
import cn.mxz.protocols.user.PropP.PropsPro;

import com.google.common.collect.Lists;

public class PropsBuilder {

	public Iterable<PropPro> build(Collection<Prize> list) {

		List<PropPro> ls = Lists.newArrayList();

		for( Prize p : list ){

			ls.add(build(p));
		}

		return ls;
	}

	public PropsPro buildProps(Collection<Prize> list) {
		PropsPro.Builder b = PropsPro.newBuilder();
		b.addAllProps(build(list));
		return b.build();
	}

	private PropPro build(Prize p) {

		return new PropBuilder().build(p.getId(), p.getCount());
	}

	public static void main(String[] args) {
		PropsBuilder p = new PropsBuilder();
		List<Prize> l = null;
		p.build( l );
	}
}
