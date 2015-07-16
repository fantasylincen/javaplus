package cn.vgame.a.plantform;


public class XyPlantform implements Plantform {

	@Override
	public TokenChecker getChecker() {
		return new XyTokenChecker();
	}

	@Override
	public String getRechargeSheetName() {
		return "recharge-xy";
	}

}
