package cn.vgame.a.plantform;

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
