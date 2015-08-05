package cn.javaplus.twolegs.share;

import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.share.ButtonListener;

import cn.javaplus.twolegs.App;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ShareButton extends ImageButton {

	public ShareButton() {
		super(style());
		addShareListener();
	}

	public static ImageButtonStyle style() {
		ImageButtonStyle style = new ImageButtonStyle();
		TextureAtlas atlas = Assets.getInternal().getTextureAtlas("data/robot.txt");
		Skin skin = new Skin(atlas);
		style.imageUp = skin.getDrawable("shareButtonUp");
		style.imageDown = skin.getDrawable("shareButtonDown");
		return style;
	}

	private void addShareListener() {
		addListener(new ButtonListener() {

			@Override
			public void click(ChangeEvent event, Actor actor) {
				App.getShare().share();
			}

		});
	}

}
