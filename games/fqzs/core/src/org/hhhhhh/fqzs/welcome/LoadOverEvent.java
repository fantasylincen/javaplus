package org.hhhhhh.fqzs.welcome;

import org.hhhhhh.fqzs.config.GameConfig;

public class LoadOverEvent {

	private GameConfig config;

	public LoadOverEvent(GameConfig config) {
		this.config = config;
	}

	public GameConfig getConfig() {
		return config;
	}
}
