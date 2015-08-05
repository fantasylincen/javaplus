package cn.vgame.a.plantform;

import cn.vgame.plantform.Plantform;
import cn.vgame.plantform.TokenChecker;

public class AppStorePlantform implements Plantform {
	private final String plantform;

	public AppStorePlantform(String plantform) {
		this.plantform = plantform;
	}

	@Override
	public String getName() {
		return plantform;
	}
	@Override
	public TokenChecker getChecker() {
		return new VcTokenChecker();
	}

	@Override
	public String getRechargeSheetName() {
		return "recharge-appstore";
	}


}
