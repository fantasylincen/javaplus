package cn.javaplus.jigsaw.game;

import org.javaplus.game.common.assets.Assets;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class HelpPanel extends Image {

	public HelpPanel() {
		super(Assets.getInternal().getTextureAtlas("data/game.txt").findRegion("helpcontent"));
	}
}
