package cn.mxz.dogz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import message.S;
import cn.javaplus.util.Util;
import cn.mxz.DogzLevelTemplet;
import cn.mxz.DogzLevelTempletConfig;
import cn.mxz.DogzTemplet;
import cn.mxz.DogzTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.events.Events;
import cn.mxz.events.dogz.DogzGoodLuckLevelUpEvent;
import cn.mxz.events.dogz.DogzLevelUpEvent;
import cn.mxz.script.Script;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

class DogzManagerImpl implements DogzManager {

	private List<Dogz> all;
	private City city;

	public DogzManagerImpl(City city) {
		this.city = city;
	}

	@Override
	public Map<Integer, Dogz> getDogzAll() {

		Map<Integer, Dogz> m = new HashMap<Integer, Dogz>();

		for (Dogz d : all) {

			m.put(d.getTypeId(), d);
		}

		return m;
	}


	@Override
	public Dogz getFighting() {

		for (Dogz d : getDogzAll().values()) {

			if (d.isFighting()) {

				return d;
			}
		}

		return null;
	}

	public void setAll(List<Dogz> all) {
		this.all = all;
	}

	@Override
	public Dogz getDogz(int dogzTypeId) {

		Dogz dogz = getDogzAll().get(dogzTypeId);

		return dogz;
	}

	@Override
	public void addDogz(Dogz d) {

		all.add(d);
	}

	@Override
	public int getShenJia() {
		final Dogz dogz = getFighting();
		if (dogz == null) {
			return 0;
		}

		DogzLevelTemplet temp = DogzLevelTempletConfig.get(dogz.getLevel());
		if (temp == null) {
			return 0;
		}
		return temp.getSocial();
	}

	protected Dogz add(int id) {
		return DogzFactory.createNewDogz(city, id);
	}

	protected DogzTemplet getTemplet(int id) {
		return DogzTempletConfig.get(id);
	}

	@Override
	public void zhuHun(int dogzId) {
		Dogz dogz = getDogz(dogzId);
		check(dogz);
		reduce(dogz);
		up(dogz);// 提升神兽
	}

	private void check(Dogz dogz) {
		int l = dogz.getLevel();
		if (l >= DogzLevelTempletConfig.getMaxKey()) {
			throw new OperationFaildException(S.S10212);
		}
	}

	private void up(Dogz dogz) {
		if (goodLuck(dogz) /*|| city.isTester()*/) {
			levelUp(dogz);
			setHunLi(0);
			Events.getInstance().dispatch(new DogzGoodLuckLevelUpEvent(dogz, city));
		} else {
			int level = dogz.getLevel();
			
			addHunLi(dogz);
			int l2 = dogz.getLevel();
			
			if(l2 != level) {
				Events.getInstance().dispatch(new DogzLevelUpEvent(dogz, city));
			}
		}
	}

	private void addHunLi(Dogz dogz) {
		DogzLevelTemplet t = getLevelTemp(dogz);
		float p = t.getSingleSoulPower() * t.getSoulNumber();
		addHunLiMaybeLevelUp(dogz, p);
	}

	private void addHunLiMaybeLevelUp(Dogz dogz, float p) {
		addHunLi(p);

		while (true) {
			float hunLi = getHunLi();
			DogzLevelTemplet t = getLevelTemp(dogz);
			int need = t.getSoulPower();
			if (hunLi < need) {
				break;
			}

			levelUp(dogz);
			

			setHunLi(getHunLi() - need);

		}
	}

	private void levelUp(Dogz dogz) {
		int level = dogz.getLevel() + 1;
		level = Math.min(DogzLevelTempletConfig.getMaxKey(), level);
		setLevel(level);
	}

	private boolean goodLuck(Dogz dogz) {
		DogzLevelTemplet temp = getLevelTemp(dogz);
		// 直接升级的概率：基础概率+0.1-0.5*当前升级所差魂（也就是差值）/升级所需魂

		float ug = temp.getUpgrade();

		int needAll = temp.getSoulPower();
		int need = (int) (temp.getSoulPower() - getHunLi());
		float p = (float) Script.Dogz.getLevelUpProbability(ug, need, needAll);
		return Util.Random.isHappen(p);
	}

	private DogzLevelTemplet getLevelTemp(Dogz dogz) {
		DogzLevelTemplet temp = DogzLevelTempletConfig.get(dogz.getLevel());
		return temp;
	}

	private void reduce(Dogz dogz) {
		DogzLevelTemplet temp = getLevelTemp(dogz);
		int n = temp.getSoulNumber();
		city.getPlayer().reduce(PlayerProperty.SHOU_HUN, n);
	}

	@Override
	public int getLevel() {
		UserCounter his = getCounter();

		int lv = his.get(CounterKey.DOGZ_LEVEL);
		if (lv < 1) {
			lv = 1;
			his.set(CounterKey.DOGZ_LEVEL, lv);
		}
		return lv;
	}

	@Override
	public void setLevel(int level) {
		UserCounter his = getCounter();
		his.set(CounterKey.DOGZ_LEVEL, level);
	}

	@Override
	public float getHunLi() {
		UserCounter c = getCounter();
		return c.get(CounterKey.HUN_LI) / 100;
	}

	@Override
	public void setHunLi(float hunLi) {
		UserCounter c = getCounter();
		c.set(CounterKey.HUN_LI, (int) (hunLi * 100));
	}

	@Override
	public void addHunLi(float p) {
		setHunLi(getHunLi() + p);
	}

	private UserCounter getCounter() {
		UserCounter his = city.getUserCounterHistory();
		return his;
	}

	@Override
	public int getHunLiNow() {
		return (int) getHunLi();
	}

	@Override
	public int getHunLiMax() {
		DogzLevelTemplet t = DogzLevelTempletConfig.get(getLevel());
		return t.getSoulPower();
	}

//	@Override
//	public void ensureHasFirst() {
//		int countMax = DogzConfig.getCountMax(0, city.getLevel());
//		if(countMax == 1 && all.isEmpty()) {
//
//			//开启第一个神兽
//			DogzFactory.createNewDogz(city, D.ID_BAI_HU);
//		}
//	}

}
