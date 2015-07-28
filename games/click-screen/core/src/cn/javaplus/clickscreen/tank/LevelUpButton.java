package cn.javaplus.clickscreen.tank;

import org.javaplus.clickscreen.button.IButtonListener;
import org.javaplus.game.common.util.Util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

import cn.javaplus.clickscreen.App;
import cn.javaplus.clickscreen.button.GameButton;
import cn.javaplus.clickscreen.game.Marsking;

public class LevelUpButton extends GameButton implements IButtonListener {

	private Tank tank;

	public LevelUpButton(Tank tank) {
		super("tankRed_outline");
		this.tank = tank;
		addButtonListener(this);
		setText("level up");
	}

	private boolean moneyEnouph() {
		return App.getDb().getCoin() >= tank.getOpenNeed();
	}

	@Override
	public void onException(Exception e) {

	}

	@Override
	public void action(ChangeEvent event, Actor actor) {
		tank.levelUp();
		tank.showLevelUpEffect();
	}
}
