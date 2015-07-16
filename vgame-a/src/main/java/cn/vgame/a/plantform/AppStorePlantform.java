package cn.vgame.a.plantform;

public class AppStorePlantform implements Plantform {

	@Override
	public TokenChecker getChecker() {
		return new VcTokenChecker();
	}

	@Override
	public String getRechargeSheetName() {
		return "recharge-appstore";
	}

}
