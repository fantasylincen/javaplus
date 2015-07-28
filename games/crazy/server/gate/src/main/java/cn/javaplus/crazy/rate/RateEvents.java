package cn.javaplus.crazy.rate;

import java.util.Timer;
import java.util.TimerTask;

import cn.javaplus.crazy.events.Events;
import cn.javaplus.crazy.events.GateStartEvent;
import cn.javaplus.crazy.events.Listener;

public class RateEvents implements Listener<GateStartEvent> {

	@Override
	public void onEvent(GateStartEvent e) {
		Timer timer = new Timer();
		schedule(timer, 5);
	}

	private void schedule(Timer timer, final int sec) {
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				Events.dispatch(new RateEvent(sec));
			}
		}, sec * 1000);
	}

}
