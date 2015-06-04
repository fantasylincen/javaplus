package com.taobao.protobuf.util;

import org.eclipse.jface.preference.IPreferenceStore;

import com.taobao.protobuf.Activator;
import com.taobao.protobuf.editors.D.Paths;

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
