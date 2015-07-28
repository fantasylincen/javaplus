package org.javaplus.game.common;

import java.util.Map;



public interface Logger {
	void onCountEvent(Object id);
	void onEvent(Object id, Map<String, String> kvs);
	void onResume();
	void onPause();
}
