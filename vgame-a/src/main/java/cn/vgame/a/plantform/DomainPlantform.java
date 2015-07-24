package cn.vgame.a.plantform;


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
