package cn.javaplus.twolegs.game;

import org.javaplus.game.common.assets.Assets;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class RankingListButton extends ImageButton {

	public RankingListButton() {
		super(style());
	}

	public static ImageButtonStyle style() {
		ImageButtonStyle style = new ImageButtonStyle();
		TextureAtlas atlas = Assets.getInternal().getTextureAtlas("data/robot.txt");
		Skin skin = new Skin(atlas);
		style.imageUp = skin.getDrawable("rankingListButtonUp");
		style.imageDown = skin.getDrawable("rankingListButtonDown");
		return style;
	}
}
