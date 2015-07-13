package cn.vgame.a.plantform;


public class DomainPlantform implements Plantform {

	@Override
	public TokenChecker getChecker() {
		return new DomainTokenChecker();
	}

	@Override
	public String getRechargeSheetName() {
		return "recharge-zfb";
	}

}
