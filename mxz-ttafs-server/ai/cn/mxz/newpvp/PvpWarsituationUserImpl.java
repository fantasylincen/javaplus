package cn.mxz.newpvp;

import java.util.List;

import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.protocols.user.battle.WarSituationP.BattleFighterPro;
import cn.mxz.protocols.user.battle.WarSituationP.CampPro;

public class PvpWarsituationUserImpl implements PvpWarsituationUser {

	private CampPro camp;

	public PvpWarsituationUserImpl(CampPro camp) {
		this.camp = camp;
	}

	@Override
	public String getNick() {
		return camp.getNick() == null ? "" : camp.getNick();
	}

	@Override
	public boolean isMan() {
		List<BattleFighterPro> fs = camp.getFightersList();
		for (BattleFighterPro f : fs) {
			FighterTemplet c = FighterTempletConfig.get(f.getId());
			int cat = c.getCategory();
			if(cat == 3) {
				return c.getSex() == 1;
			}
 		}
		return false;
	}

	@Override
	public int getLevel() {
		try {
			City user = CityFactory.getCity(getUserId());
			return user.getLevel();
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	@Override
	public int getFighterType() {
		List<BattleFighterPro> fs = camp.getFightersList();
		for (BattleFighterPro f : fs) {
			FighterTemplet c = FighterTempletConfig.get(f.getId());
			int cat = c.getCategory();
			if(cat == 3) {
				return c.getId();
			}
 		}
		return FighterTempletConfig.findByCategory(3).get(0).getId();
	}

	@Override
	public String getUserId() {
		return camp.getUserId();
	}

}
