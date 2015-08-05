package cn.javaplus.shhz.listeners.button;

import cn.javaplus.game.shhz.R;
import cn.javaplus.shhz.Game;
import cn.javaplus.shhz.components.buildings.Home;
import cn.javaplus.shhz.events.Listener;
import cn.javaplus.shhz.events.components.ButtonTapEvent;
import cn.javaplus.shhz.stage.GameStage;

public class ButtonTapHandler implements Listener<ButtonTapEvent> {

	@Override
	public void onEvent(ButtonTapEvent e) {
		String id = e.getId();
		if (id.equals(R.ResourceXNPng)) {
			System.exit(0);
		} else if (id.equals(R.BuildingTownhouseBTh01Png)) {
			createHomeBuilding();
		}

	}

	private void createHomeBuilding() {
			GameStage stage = Game.getGameStage();
			Home home = new Home();
			stage.addBuilding(home);
	}

}
