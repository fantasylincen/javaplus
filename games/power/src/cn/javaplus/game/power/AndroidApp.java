package cn.javaplus.game.power;

import android.os.Bundle;
import cn.mxz.events.Events;

import com.badlogic.gdx.backends.android.AndroidApplication;

public class AndroidApp extends AndroidApplication {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		Events.getInstance().loadListeners("cn.javaplus.game.power.listeners");
		super.onCreate(savedInstanceState);
		initialize(new Game(""), false);
	}
}

