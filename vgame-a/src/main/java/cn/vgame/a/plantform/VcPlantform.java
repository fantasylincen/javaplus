package cn.vgame.a.plantform;

import cn.vgame.plantform.Plantform;
import cn.vgame.plantform.TokenChecker;

public class VcPlantform implements Plantform {
	private final String plantform;

	public VcPlantform(String plantform) {
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
		return "recharge-zfb";
	}


}
