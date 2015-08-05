package cn.vgame.a.plantform;

import cn.vgame.plantform.Plantform;
import cn.vgame.plantform.TokenChecker;


public class DomainPlantform implements Plantform {
	private final String plantform;

	public DomainPlantform(String plantform) {
		this.plantform = plantform;
	}

	@Override
	public String getName() {
		return plantform;
	}
	@Override
	public TokenChecker getChecker() {
		return new DomainTokenChecker();
	}

	@Override
	public String getRechargeSheetName() {
		return "recharge-zfb";
	}

}
