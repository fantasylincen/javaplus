package cn.javaplus.twolegs;

import org.javaplus.game.common.Gps;
import org.javaplus.game.common.gps.GpsListener;

public class DesktopGps implements Gps {

	@Override
	public void requestLocation(GpsListener gpsListener) {
		
	}

	@Override
	public boolean isOpen() {
		return false;
	}


}
