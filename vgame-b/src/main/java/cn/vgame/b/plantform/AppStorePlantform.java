package cn.vgame.b.plantform;

import cn.vgame.plantform.Plantform;
import cn.vgame.plantform.TokenChecker;

public class AppStorePlantform implements Plantform {

	@Override
	public TokenChecker getChecker() {
		return new AppStoreTokenChecker();
	}

	@Override
	public String getRechargeSheetName() {
		return "recharge-" + getName();
	}

	@Override
	public String getName() {
		return "appstore";
	}

}
