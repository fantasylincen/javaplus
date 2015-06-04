package cn.mxz.xianshi;

import java.util.List;

import cn.javaplus.util.Util;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.GodTypeTemplet;
import cn.mxz.city.City;
import cn.mxz.util.debuger.Debuger;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

class QFinder extends FinderBean {


	public QFinder(City city, String string, GodTypeTemplet temp, boolean noJia) {

		super(city, string, temp, noJia);
	}

	@Override
	public int findOneTime() {

		List<FighterTemplet> q = findBy();

		new PlayerFilter().filter(q);

		filterGodBank(q);

		FighterTemplet randomOne = cn.javaplus.util.Util.Collection.getRandomOne(q);

		return randomOne.getType();
	}

	/**
	 * 寻找所有品阶为step的战士
	 *
	 * @return
	 */
	private List<FighterTemplet> findBy() {

		List<FighterTemplet> all = FighterTempletConfig.getAll();

		Predicate<FighterTemplet> predicate = new Predicate<FighterTemplet>() {

			@Override
			public boolean apply(FighterTemplet t) {
				return t.getStep() == type && godTypeEqual(t);
			}

			private boolean godTypeEqual(FighterTemplet t) {
				String bk = temp.getGodBank();
				List<Integer> is = Util.Collection.getIntegers(bk);
				return is.contains(new Integer(t.getGodType()));
			}
		};
		Debuger.debug("QFinder.findBy().新建 Predicate() {...}.apply()" + type);

		return Lists.newArrayList(Collections2.filter(all, predicate));
	}

}
