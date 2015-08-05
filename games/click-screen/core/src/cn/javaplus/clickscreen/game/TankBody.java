package cn.javaplus.clickscreen.game;

import org.javaplus.clickscreen.excel.Row;
import org.javaplus.clickscreen.excel.Sheet;
import org.javaplus.game.common.assets.Assets;

import cn.javaplus.clickscreen.App;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class TankBody extends Image {

	public TankBody(int tankId) {
		super(Assets.getSd().findRegion("data/game.txt", getAsset(tankId)));
	}

	private static String getAsset(int tankId) {
		Sheet b = App.getStaticData().get("tanks");
		Row r = b.find("id", tankId);
		return r.get("body");
	}
}
