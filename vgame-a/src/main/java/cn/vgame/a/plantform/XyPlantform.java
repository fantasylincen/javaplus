package cn.vgame.a.plantform;

public class XyPlantform implements Plantform {

	private final String plantform;

	public XyPlantform(String plantform) {
		this.plantform = plantform;
	}

	@Override
	public String getName() {
		return plantform;
	}

	@Override
	public TokenChecker getChecker() {
		return new XyTokenChecker();
	}

	@Override
	public String getRechargeSheetName() {
		return "recharge-xy";
	}

}
