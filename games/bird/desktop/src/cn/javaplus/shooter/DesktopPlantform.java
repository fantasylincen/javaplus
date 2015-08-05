package cn.javaplus.shooter;

import cn.javaplus.shooter.Plantform;

public class DesktopPlantform implements Plantform {

	@Override
	public Gps getGps() {
		return new DesktopGps();
	}

}
