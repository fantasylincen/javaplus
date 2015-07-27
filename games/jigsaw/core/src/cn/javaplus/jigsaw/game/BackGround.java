package cn.javaplus.jigsaw.game;

import org.javaplus.game.common.assets.Assets;

import cn.javaplus.jigsaw.define.D;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BackGround extends Image {

	public BackGround() {
		super(Assets.getInternal().getTextureAtlas("data/game.txt").findRegion("background"));
		setSize(D.STAGE_W, D.STAGE_H);
	}

}
