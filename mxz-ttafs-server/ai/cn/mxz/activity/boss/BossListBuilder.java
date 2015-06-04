package cn.mxz.activity.boss;

import java.util.List;

import cn.mxz.city.City;
import cn.mxz.protocols.user.boss.BossP.BossListPro;

/**
 * Boss列表构建器
 * 
 * @author 林岑
 * 
 */

public class BossListBuilder {

	public BossListPro build(List<Boss> bossList, City city) {

		BossListPro.Builder b = BossListPro.newBuilder();

		for (Boss boss : bossList) {

			b.addList(new BossLabelBuilder().build(boss, city));
		}

		return b.build();
	}

}
