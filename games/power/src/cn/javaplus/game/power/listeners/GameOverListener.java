package cn.javaplus.game.power.listeners;

import cn.javaplus.game.power.events.GameOverEvent;
import cn.mxz.events.Listener;

public class GameOverListener implements Listener<GameOverEvent> {

	@Override
	public void onEvent(GameOverEvent e) {
		System.out.println("游戏结束");
	}

}
