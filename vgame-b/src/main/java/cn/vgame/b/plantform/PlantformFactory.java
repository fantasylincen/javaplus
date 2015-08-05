package cn.vgame.b.plantform;

import cn.vgame.plantform.Plantform;

public class PlantformFactory {

	public static Plantform createPlantform(String plantform) {
		if("appstore".equals(plantform)) {
			return new AppStorePlantform();
		}
		throw new RuntimeException("unknow plantform:" + plantform);
	}

}
