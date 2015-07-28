package cn.javaplus.twolegs.android;

import org.javaplus.game.common.gps.GpsLocation;

import android.location.Location;

public class GpsLocationAdaptor implements GpsLocation {

	private Location location;

	public GpsLocationAdaptor(Location location) {
		this.location = location;
	}

	public double getLatitude() {
		return location.getLatitude();
	}

	public double getLongitude() {
		return location.getLongitude();
	}

}
