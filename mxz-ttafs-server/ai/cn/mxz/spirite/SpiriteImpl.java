package cn.mxz.spirite;

import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.GodQurlityTemplet;
import cn.mxz.GodQurlityTempletConfig;
import cn.mxz.IFighterTemplet;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.debuger.Debuger;

class SpiriteImpl implements Spirite {

	private db.domain.Spirite	spirite;
	private SpiriteManagerImpl sm;

	SpiriteImpl(db.domain.Spirite spirite, SpiriteManagerImpl sm) {
		this.spirite = spirite;
		this.sm = sm;
	}

	@Override
	public int getCount() {
		return spirite.getCount();
	}

	@Override
	public int getStep() {
		
		int typeId = getTypeId();
		
		IFighterTemplet temp = FighterTempletConfig.get(typeId);
		int q = temp.getQuality();
		GodQurlityTemplet gqt = GodQurlityTempletConfig.get(q);
		return gqt.getStep();
	}

	@Override
	public int getTypeId() {
		return spirite.getTypeId();
	}

	@Override
	public boolean hasFighter() {
		City city = CityFactory.getCity(spirite.getUname());
		Hero hero = city.getTeam().get(getTypeId());
		return hero != null;
	}

	@Override
	public boolean isFull() {
		int count = getCount();
		int countMax = getCountMax();
		return count >= countMax;
	}

	@Override
	public int getCountMax() {

//		City city = CityFactory.getCity(spirite.getUname());
//
//		Hero hero = city.getTeam().get(getTypeId());
//
//		return ScriptOld.spirite.getCountMax(getTypeId(), hero);
		
		FighterTemplet temp = FighterTempletConfig.get(getTypeId());
		float soul = temp.getSoul();
		Debuger.debug("SpiriteImpl.getCountMax() " + temp.getName() + soul);
		return (int) soul;
	}

	@Override
	public db.domain.Spirite getDto() {
		return spirite;
	}

	@Override
	public boolean canLevelUp() {
		return sm.canLevelUp(getTypeId());
	}

}
