package cn.mxz.equipment;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.mxz.DanRewardTemplet;
import cn.mxz.DanRewardTempletConfig;
import cn.mxz.FighterTempletConfig;
import cn.mxz.PropTemplet;
import cn.mxz.SnatchTemplet;
import cn.mxz.SnatchTempletConfig;
import cn.mxz.city.City;
import cn.mxz.fighter.Fighter;
import cn.mxz.prop.PropTempletFactory;
import cn.mxz.protocols.user.UserBaseP.UserBasePro;
import cn.mxz.protocols.user.equipment.EquipmentP.EquipmentSnatchListPro;
import cn.mxz.protocols.user.equipment.EquipmentP.EquipmentSnatchListPro.Builder;
import cn.mxz.protocols.user.equipment.EquipmentP.SnatchUserPro;
import cn.mxz.user.builder.UserBaseBuilder;
import cn.mxz.user.team.god.Hero;

import com.google.common.collect.Lists;

class EquipmentSnatchListBuilder {

	// public EquipmentSnatchListPro build(List<City> ls) {
	//
	// Builder b = EquipmentSnatchListPro.newBuilder();
	//
	// for (City c : ls) {
	//
	// b.addUsers(buildSnatchUser(c));
	// }
	//
	// return b.build();
	// }

	private SnatchUserPro buildSnatchUser(FightingUser c, float probablility, int stuffId) {
		
		SnatchUserPro.Builder b = SnatchUserPro.newBuilder();

		UserBasePro build = new UserBaseBuilder().build(c.getPlayer());

		b.setUser(build);

		b.setPvpDanId(c.getDanId());

		DanRewardTemplet temp = DanRewardTempletConfig.get(c.getDanId());
		b.setPvpDanLv(temp.getDanLv());

		int value = (int) (probablility * 100);

		b.setIsRobot(c.isRobot()); // 是否是机器人

		b.setProbability(value);
		
		SnatchTemplet s = getTemp(stuffId, c.isRobot());

		b.setProbabilityId(s.getCodeId());

		b.setProbabilityText(s.getText());

		List<Hero> fighters = Lists.newArrayList(c.getCamp().getFighters());

		Comparator<Fighter> com = new Comparator<Fighter>() {

			@Override
			public int compare(Fighter o1, Fighter o2) {
				int a = o1.getTypeId();
				int b = o2.getTypeId();
				if ((a + "").startsWith("3")) {
					a -= 1000000;
				}

				if ((b + "").startsWith("3")) {
					b -= 1000000;
				}
				return a - b;
			}
		};

		Collections.sort(fighters, com);

		for (Fighter hero : fighters) {
			// System.out.print(hero.getTypeId() + " ");
			int tId = hero.getTypeId();
			if (FighterTempletConfig.get(tId).getCategory() == 3) {
				continue;
			}
			b.addHead(new HeadBuilder().build(hero));
		}

		// System.out.println("-----");

		return b.build();
	}

private SnatchTemplet getTemp(int stuffId, boolean robot) {
	PropTemplet t = PropTempletFactory.get(stuffId);
	int q = t.getQuality();
	SnatchTemplet temp = SnatchTempletConfig.get(q + "," + (robot ? 1 : 2));
	return temp;
}

//	private String getProbabilityText(int value) {
//		List<SnatchProbabilityTemplet> all = SnatchProbabilityTempletConfig.getAll();
//		for (SnatchProbabilityTemplet t : all) {
//			if(contains(t, value)) {
//				return t.getText();
//			}
//		}
//		throw new RuntimeException(value + "");
//	}
//
//	private boolean contains(SnatchProbabilityTemplet t, int value) {
//		String scope = t.getScope();
//		List<Integer> is = Util.Collection.getIntegers(scope);
//		Integer min = is.get(0);
//		Integer max = is.get(1);
//		return min <= value && value <= max;
//	}
//
//	private int getProbabilityId(int value) {
//		List<SnatchProbabilityTemplet> all = SnatchProbabilityTempletConfig.getAll();
//		for (SnatchProbabilityTemplet t : all) {
//			if(contains(t, value)) {
//				return t.getId();
//			}
//		}
//		throw new RuntimeException(value + "");
//	}

	public EquipmentSnatchListPro build(City city, List<FightingUser> users,
			int stuffId) {

		Builder b = EquipmentSnatchListPro.newBuilder();

		for (FightingUser c : users) {

			float probability = c.getProbability(city, stuffId);
			b.addUsers(buildSnatchUser(c, probability, stuffId));
		}

		return b.build();
	}

}
