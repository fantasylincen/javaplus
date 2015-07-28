package org.javaplus.game.common;

import org.javaplus.game.common.gps.GpsListener;

public interface Gps {

	void requestLocation(GpsListener gpsListener);

	boolean isOpen();
}
