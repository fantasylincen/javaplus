package cn.mxz.fighter;

import cn.javaplus.util.Util;
import cn.mxz.Attribute;
import cn.mxz.ExclusiveTemplet;
import cn.mxz.battle.buff.AttributeSingle;
import cn.mxz.formation.AdditionType;

public class TianMingAddition extends AttributeSingle implements Attribute {

	public TianMingAddition(Attribute base, ExclusiveTemplet temp) {
		super(getType(temp), getAddition(base, temp));
	}

	private static AdditionType getType(ExclusiveTemplet temp) {
		return AdditionType.fromNum(temp.getNatureType());
	}

	private static int getAddition(Attribute base, ExclusiveTemplet temp) {
		
		int value = (int) temp.getNatureFixed();
		AdditionType t = getType(temp);
		
		Util.Exception.checkNull(t, temp.getNatureType() + ", " + temp.getId());
		 
		int i = (int) (t.get(base) * temp.getNaturePar());
		
		return value + i;
	}


}
