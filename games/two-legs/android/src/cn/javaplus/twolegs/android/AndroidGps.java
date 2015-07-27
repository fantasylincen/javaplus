package cn.javaplus.twolegs.android;

import org.javaplus.game.common.Gps;
import org.javaplus.game.common.gps.GpsListener;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;

public class AndroidGps implements Gps {

	public class LocationListenerImpl implements LocationListener {

		private GpsListener listener;

		public LocationListenerImpl(GpsListener listener) {
			this.listener = listener;
		}

		/**
		 * 位置信息变化时触发
		 */
		public void onLocationChanged(Location location) {
			listener.onChanged(new GpsLocationAdaptor(location));
		}

		/**
		 * GPS状态变化时触发
		 */
		public void onStatusChanged(String provider, int status, Bundle extras) {
			switch (status) {
			case LocationProvider.AVAILABLE:
				listener.onAvalilable();
				break;
			case LocationProvider.OUT_OF_SERVICE:
				listener.onOutOfService();
				break;
			case LocationProvider.TEMPORARILY_UNAVAILABLE:
				listener.onTemporarilyUnavailable();
				break;
			}
		}

		/**
		 * GPS开启时触发
		 */
		public void onProviderEnabled(String provider) {
			listener.onProviderEnabled();
		}

		/**
		 * GPS禁用时触发
		 */
		public void onProviderDisabled(String provider) {
			listener.onProviderDisabled();
		}

	};

	private LocationManager manager;

	public AndroidGps(Context context) {
		Object service = context.getSystemService(Context.LOCATION_SERVICE);
		manager = (LocationManager) service;
	}

	public boolean isOpen() {
		return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}

	@Override
	public void requestLocation(final GpsListener gpsListener) {

		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE); // 高精度
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW); // 低功耗

		String provider = manager.getBestProvider(criteria, true); // 获取GPS信息
		Location location = manager.getLastKnownLocation(provider); // 通过GPS获取位置
		
		manager.requestLocationUpdates(provider, 100 * 1000, 500,
				new LocationListenerImpl(gpsListener));
	}

}
