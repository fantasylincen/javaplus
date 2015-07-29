package cn.javaplus.clickscreen.game;

import java.util.List;

import org.javaplus.game.common.assets.Assets;

import cn.javaplus.clickscreen.button.GameButton;
import cn.javaplus.clickscreen.define.D;
import cn.javaplus.clickscreen.tank.Tank;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class TankMenu extends Group {
	private Table table;
	private ScrollPane pane;

	public TankMenu(Tank tank) {
		table = new Table();

//		background(new TextureRegionDrawable(region));
		
		TankInfoPane info = new TankInfoPane(tank);
		info.setSize(D.STAGE_W / 4, D.STAGE_H / 2);
		
		pane = new ScrollPane(table);
		pane.setSize(D.STAGE_W - info.getWidth(), info.getHeight());
		pane.setPosition(info.getX() + info.getWidth(), info.getY());
		pane.setOverscroll(true, false);

		List<GameButton> buttons = tank.getButtonManager().getButtons();
		for (GameButton bt : buttons) {
			table.add(bt);
			addSpace();
		}

		TextureAtlas bc = Assets.getSd().getTextureAtlas("data/game.txt");
		TextureRegion region = bc.findRegion("mask");
		Image background = new Image(region);
		background.setSize(D.STAGE_W, D.STAGE_H / 2);

		addActor(background);
		addActor(pane);
		addActor(info);
	}

	private void addSpace() {
		Actor as = new Actor();
		as.setWidth(100);
		table.add(as);
	}
}
