package cn.javaplus.plugins.generator.protocol.util;

import org.eclipse.jface.preference.IPreferenceStore;

import cn.javaplus.plugins.generator.protocol.Activator;
import cn.javaplus.plugins.generator.protocol.preferences.D.Paths;

public class Store {

	private Store() {
	}

	public static String getString(Paths key) {
		return getStore().getString(key + "");
	}

	public static IPreferenceStore getStore() {
		return Activator.getDefault().getPreferenceStore();
	}

	public static void put(String key, String value) {

		getStore().putValue(key, value);
	}
}
