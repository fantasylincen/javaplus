package cn.javaplus.clickscreen.game;

import org.javaplus.clickscreen.excel.Row;
import org.javaplus.clickscreen.excel.Sheet;
import org.javaplus.game.common.assets.Assets;

import cn.javaplus.clickscreen.App;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Barrel extends Image {

	private static final int X = 29;
	private static final int Y = 25;
	private static final int ORIGIN = 8;

	public Barrel(int tankId) {
		super(Assets.getSd().findRegion("data/game.txt", getBarrle(tankId)));
		setOrigin(ORIGIN, ORIGIN);
		setPosition(X, Y);
	}
	
	private static String getBarrle(int tankId) {
		Sheet b = App.getStaticData().get("tanks");
		Row r = b.find("id", tankId);
		String string = r.get("barrel");
		return string;
	}

	public void shake() {
		double d = Math.toRadians(getRotation()+90);
		double sy = Math.sin(d);
		double sx = Math.cos(d);

		double l = 10;

		setPosition((float) (X - l * sx), (float) (Y - l * sy));
		MoveToAction a = Actions.moveTo(X, Y);
		a.setDuration(0.2f);
		addAction(a);
	}
}
