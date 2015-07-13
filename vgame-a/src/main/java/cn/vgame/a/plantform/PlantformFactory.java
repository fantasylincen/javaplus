package cn.vgame.a.plantform;

public class PlantformFactory {

	public static Plantform createPlantform(String plantform) {
		if ("xy".equals(plantform)) {
			return new XyPlantform();
		}
		if ("domain".equals(plantform)) {
			return new DomainPlantform();
		}
		if ("appstore".equals(plantform)) {
			return new AppStorePlantform();
		}
		return new VcPlantform();
	}

}
