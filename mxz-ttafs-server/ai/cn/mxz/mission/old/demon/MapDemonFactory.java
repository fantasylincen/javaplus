package cn.mxz.mission.old.demon;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.util.Util;
import cn.mxz.DemonGroupTemplet;
import cn.mxz.DemonGroupTempletConfig;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.MapTemplet;
import cn.mxz.city.City;
import cn.mxz.mission.old.MapDemon;

import com.google.common.collect.Lists;

class MapDemonFactory {

	static AbstractDemonGroup createRandomCamp(City city, MapTemplet temp, MapDemonImpl demon) {

		final List<Demon> demons = randomDemons(city, temp, demon);

		if(containsBoss(demons, temp)) {

			return new DemonCampContainsBoss(demons, temp);

		} else {

			return new DemonCamp(demons);
		}
	}



	private static boolean containsBoss(List<Demon> demons, MapTemplet temp) {

		String mapIds = temp.getBossId().trim();

		if(mapIds.isEmpty()) {

			return false;
		}

		for (Demon d : demons) {

			if(mapIds.contains(d.getTypeId() + "")) {

				return true;
			}
		}

		return false;
	}

	static AbstractDemonGroup createGroupCamp(int vId, MapTemplet mapTemp, City city) {

		DemonGroupTemplet demonGroupTemplet = DemonGroupTempletConfig.get(vId);

		String[] groups = demonGroupTemplet.getGroup().split("\\|");

		final List<Demon> demons = new ArrayList<Demon>();

		FighterTemplet ft = null;

		for(String g : groups) {

			ft = FighterTempletConfig.get(Integer.parseInt(g.split(",")[0]));

			demons.add(new DemonImpl(ft, mapTemp, false, false, city));
		}

		AbstractDemonGroup demonCamp = new GroupDemonCamp(demons);

		demonCamp.setVid(vId);

		return demonCamp;
	}


	/**
	 * 随机怪物列表
	 * @param city
	 * @param temp
	 * @param mapId	地图ID
	 * @param demon	地图上碰到的怪物
	 * @return
	 */
	private static List<Demon> randomDemons(City city, MapTemplet temp, MapDemon demon) {

		int c = 5;

		final int count = c - 1;

		final List<Demon> demons = new ArrayList<Demon>();

		int demonId = demon.getId();

		FighterTemplet ft = FighterTempletConfig.get(demonId);

		if(ft == null) {

			throw new NullPointerException("demonId:" + demonId);
		}

		demons.add(new DemonImpl(ft, temp, false, false, city));

		for (int i = 0; i < count; i++) {

			if(temp.getMonsterId().trim().isEmpty()) {

				break;
			}

			Demon dm = getRandomDemon(temp, city);

			demons.add(dm);
		}

		return demons;
	}

	private static Demon getRandomDemon(MapTemplet temp, City city) {

		String[] split = temp.getMonsterId().split(",");

		List<FighterTemplet> all = Lists.newArrayList();

		for (String text : split) {

			if (!text.isEmpty()) {

				Integer id = new Integer(text.trim());

				all.add(FighterTempletConfig.get(id));
			}
		}

		if(all.isEmpty()) {

			throw new RuntimeException("地图ID: " + temp.getId() + " : " + temp.getClass().getSimpleName());
		}

		final FighterTemplet f = all.get(Util.R.nextInt(all.size()));

		DemonImpl demonImpl = new DemonImpl(f, temp, false, false, city);

		return demonImpl;
	}
}
