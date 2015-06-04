package cn.mxz.fighter;

import cn.javaplus.util.Util;
import cn.mxz.fighter.Inherit.InheritBean;

class InheritBeanImpl implements InheritBean {

	private boolean	isInheritYuanShen;
	private float	expPercent;
	private int		propNeed;

	//
	// /**
	// * 传承 : 130030:80% 经验:不传元神, 130032:100%经验:不传元神, 130033:100%经验:要传元神
	// */
	// public static final String INHERIT_EXP =
	// "130030:0.8:0,130032:1:0,130033,1,1";

	InheritBeanImpl(String string) {
		try {
			String[] split = string.split(":");
			propNeed = new Integer(split[0]);
			expPercent = new Float(split[1]);
			isInheritYuanShen = split[2].equals("1");
		} catch (Exception e) {
			System.err.println(string);
			throw Util.Exception.toRuntimeException(e);
		}
	}

	@Override
	public boolean isInheritYuanShen() {
		return isInheritYuanShen;
	}

	@Override
	public float getExpPercent() {
		return expPercent;
	}

	@Override
	public int getPropNeed() {
		return propNeed;
	}

}
