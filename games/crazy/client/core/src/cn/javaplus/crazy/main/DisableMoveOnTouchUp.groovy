package cn.javaplus.crazy.main;

import cn.javaplus.crazy.events.Listener;
import cn.javaplus.crazy.input.TouchUpEvent;

public class DisableMoveOnTouchUp implements Listener<TouchUpEvent> {

	@Override
	public void onEvent(TouchUpEvent e) {
		// Log.d("DisableMoveOnTouchUp.onEvent ");
		SelectCardListener.cardMoveable = false;
	}

}