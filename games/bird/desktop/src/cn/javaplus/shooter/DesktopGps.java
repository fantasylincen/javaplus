package cn.javaplus.shooter;

import cn.javaplus.shooter.gps.GpsListener;

public class DesktopGps implements Gps {

	@Override
	public void requestLocation(GpsListener gpsListener) {
		
	}

	@Override
	public boolean isOpen() {
		return false;
	}


}
