package cn.vgame.a.plantform;

public class VcPlantform implements Plantform {

	@Override
	public TokenChecker getChecker() {
		return new VcTokenChecker();
	}

	@Override
	public String getRechargeSheetName() {
		return "recharge-zfb";
	}

}
