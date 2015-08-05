package cn.vgame.a.plantform;

import cn.vgame.plantform.Plantform;

public class PlantformFactory {

	public static Plantform createPlantform(String plantform) {
		if ("xy".equals(plantform)) {
			return new XyPlantform(plantform);
		}
		if ("domain".equals(plantform)) {
			return new DomainPlantform(plantform);
		}
		if ("appstore".equals(plantform)) {
			return new AppStorePlantform(plantform);
		}
		return new VcPlantform(plantform);
	}

}
