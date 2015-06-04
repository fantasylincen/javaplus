package cn.mxz.xianshi;

import java.util.Iterator;
import java.util.List;

import cn.javaplus.util.Util;
import cn.mxz.FighterTemplet;
import cn.mxz.GodTypeTemplet;
import cn.mxz.city.City;

abstract class FinderBean {

	private int					probability;

	protected int				type;

	protected GodTypeTemplet	temp;

	public FinderBean(City city, String string, GodTypeTemplet temp, boolean noJia) {

		this.temp = temp;

		String[] split = string.split(":");

		type = new Integer(split[1]);
		
		if(noJia && (type == 4 || type == 5) ) {
			type = 3;
		}

		probability = new Integer(split[2]);
	}

	public int getProbability() {

		return probability;
	}

	public abstract int findOneTime();

	protected void filterGodBank(List<FighterTemplet> fs) {

		Iterator<FighterTemplet> it = fs.iterator();

		while (it.hasNext()) {
			FighterTemplet ft = (FighterTemplet) it.next();
			String t = ft.getGodType();
			if (!contains(t, temp.getGodBank())) {
				it.remove();
			}
		}
	}

	private boolean contains(String t, String godBank) {

		List<Integer> l1 = Util.Collection.getIntegers(godBank);
		List<Integer> l2 = Util.Collection.getIntegers(t);

		for (Integer tt : l2) {
			if (l1.contains(tt)) {
				return true;
			}
		}
		return false;
	}

}