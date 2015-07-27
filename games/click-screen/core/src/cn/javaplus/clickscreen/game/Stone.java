package cn.javaplus.clickscreen.game;

import org.javaplus.clickscreen.bullet.HitListener.Target;
import org.javaplus.game.common.actions.ShakeAction;
import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.assets.Assets.Loader;
import org.javaplus.game.common.util.Space;

import cn.javaplus.clickscreen.App;
import cn.javaplus.clickscreen.game.HpLabel.Hpable;
import cn.javaplus.clickscreen.hp.Hp;
import cn.javaplus.clickscreen.script.Scripts;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Stone extends Group implements Target, Hpable {
	private static final int HP_MAX = 5000;

	private Image stoneImage;
	private HpLabel label;
	private Hp hp = new Hp(HP_MAX);

	private int X;

	private int Y;

	private Space addHpSpace;

	private int level;
	private double addEverySpace;

	public Stone(int x, int y) {
		X = x;
		Y = y;
		setPosition(x, y);
		Loader e = Assets.getSd();
		stoneImage = new Image(e.findRegion("data/game.txt", "rpgTile056"));
		addActor(stoneImage);
		setSize(stoneImage.getWidth(), stoneImage.getHeight());
		label = new HpLabel(this);
		addActor(label);

		level = 1;
		updateAddHpController();
		addHpSpace.addAction(new Runnable() {

			public void run() {
				hp.add(addEverySpace);
			}
		});
	}

	private void updateAddHpController() {
		
		String script = Scripts.get("stone.js");
		App.getScript().call(script, "updateAddHpController", this);
	}
	
	public void setAddEverySpace(double addEverySpace) {
		this.addEverySpace = addEverySpace;
	}
	
	public void setAddHpSpace(Space addHpSpace) {
		this.addHpSpace = addHpSpace;
	}

	public int getLevel() {
		return level;
	}

	@Override
	public void act(float delta) {
		addHpSpace.update(delta);
	}

	public double getHp() {
		return hp.getValue();
	}

	public void reduceHp(double damage) {
		if (isDeath()) {
			return;
		}
		hp.reduce(damage);
		if (isDeath()) {
			removeActor(stoneImage);
		}
	}

	public void shake() {
		ShakeAction s = new ShakeAction(this, -2, 0, X, Y);
		addAction(s);
	}

	@Override
	public Actor toActor() {
		return this;
	}

	@Override
	public double getHpMax() {
		return HP_MAX;
	}

	@Override
	public boolean isDeath() {
		return hp.isDeath();
	}

}
