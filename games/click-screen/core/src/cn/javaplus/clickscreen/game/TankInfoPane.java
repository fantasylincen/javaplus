package cn.javaplus.clickscreen.game;

import cn.javaplus.clickscreen.App;
import cn.javaplus.clickscreen.actor.MonitorLabel;
import cn.javaplus.clickscreen.actor.MonitorLabel.IMonitor;
import cn.javaplus.clickscreen.tank.Tank;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class TankInfoPane extends Group {

	public class AttackMonitor implements IMonitor {
		private double lastTankAttack;

		@Override
		public boolean hasChanged() {
			return lastTankAttack != tank.getAttack();
		}

		@Override
		public CharSequence getText() {
			return "Att " + tank.getLevel();
		}

		@Override
		public void onChanged() {
			lastTankAttack = tank.getAttack();
		}

	}

	public class LevelMonitor implements IMonitor {

		private int lastTankLevel;

		@Override
		public boolean hasChanged() {
			return lastTankLevel != tank.getLevel();
		}

		@Override
		public CharSequence getText() {
			return "Lv " + tank.getLevel();
		}

		@Override
		public void onChanged() {
			lastTankLevel = tank.getLevel();
		}

	}

	private Tank tank;
	private MonitorLabel tankLevel;
	private MonitorLabel tankAttack;

	public TankInfoPane(Tank tank) {
		this.tank = tank;
		LabelStyle style = App.getAssets().getLabelStyle2();

		tankLevel = new MonitorLabel(new LevelMonitor(), style);
		addActor(tankLevel);

		tankAttack = new MonitorLabel(new AttackMonitor(), style);
		addActor(tankAttack);
	}

}
