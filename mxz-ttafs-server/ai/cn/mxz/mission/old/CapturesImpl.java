package cn.mxz.mission.old;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.javaplus.util.Util.Random;
import cn.mxz.FighterTempletConfig;
import cn.mxz.GodQurlityTemplet;
import cn.mxz.GodQurlityTempletConfig;
import cn.mxz.IFighterTemplet;
import cn.mxz.battle.Camp;
import cn.mxz.city.City;
import cn.mxz.fighter.Fighter;
import cn.mxz.user.mission.Captures;
import cn.mxz.user.mission.FighterCapture;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


public class CapturesImpl implements Captures {

	private static Map<String, List<FighterCapture>> all = Maps.newHashMap();

	private City	city;

	CapturesImpl(City city) {
		this.city = city;
	}

	@Override
	public Iterator<FighterCapture> iterator() {
		return getAll().iterator();
	}

	@Override
	public List<Hero> captureByGanLu() {

		List<Hero> hs = new ArrayList<Hero>();

		for (FighterCapture fc : this) {

			IFighterTemplet temp = FighterTempletConfig.get(fc.getId());

			capture(fc.getId(), 0, hs);
		}

		getAll().clear();

		return hs;
	}

	@Override
	public List<Hero> captureByXianLu() {

		List<Hero> hs = new ArrayList<Hero>();

		for (FighterCapture fc : this) {

			capture(fc.getId(), 1, hs);
		}

		getAll().clear();

		return hs;
	}

	/**
	 * 捕获这个神将, 如果捕获成功, 将捕获到的神将让如hs中
	 * @param id
	 * @param catchPro 捕获成功的几率
	 * @param hs
	 */
	private void capture(Integer id, float catchPro, List<Hero> hs) {

		if(Random.isHappen(catchPro)) {

			IFighterTemplet temp = FighterTempletConfig.get(id);

			Hero h = city.getTeam().createNewHero(id);

			hs.add(h);

			IFighterTemplet temp2 = FighterTempletConfig.get(h.getTypeId());

//			toBeAlternate(temp2.getSubdueFighterId(), h);
//
//			Debuger.debug("CapturesImpl.capture() 捕捉到:" + id + ":" + temp.getName());
		}
	}


//	/**
//	 * 让h成为subdueFighterId的替补
//	 * @param subdueFighterId
//	 * @param h
//	 */
//	private void toBeAlternate(int subdueFighterId, Hero h) {
//
//		Collection<Hero> values = city.getTeam().getFightersInTeam().values();
//
//		for (Hero hero : values) {
//
//			int typeId = hero.getTypeId();
//
//			if(subdueFighterId == typeId) {
//
////				h.setInTeam(true);
//
//				if(city.getFormation().getSelected().contains(hero)) { //如果在阵上
//
//					city.getFormation().setAlternate(subdueFighterId, h);
//				}
//
//
//			}
//
//		}
//
//	}

	@Override
	public void generate(Camp<? extends Fighter> camp) {

		getAll().clear();

		for (Fighter fighter : camp.getFighters()) {

			IFighterTemplet temp = FighterTempletConfig.get(fighter.getTypeId());

			float pro = 0;

			boolean happen = Random.isHappen(pro);

			if(happen) {

				int id = fighter.getTypeId();

				int position = camp.getPosition(fighter);
				int quality = fighter.getQuality();
				GodQurlityTemplet t = GodQurlityTempletConfig.get(quality);
				int step2 = t.getStep();

				FighterCapture kv = new FighterCaptureImpl(id, position, step2);

				getAll().add(kv);

				Debuger.debug("CapturesImpl.generate() 生成可捕获神将  id = " + id + " position = " + position + " step = " + step2);
			}
		}
	}

	/**
	 * 获得我捕获到的所有
	 * @return
	 */
	private List<FighterCapture> getAll() {

		List<FighterCapture> list = all.get(city.getId());

		if(list == null) {

			list = Lists.newArrayList();

			all.put(city.getId(), list);
		}

		return list;
	}

}
