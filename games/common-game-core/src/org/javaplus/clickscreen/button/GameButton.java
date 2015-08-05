package org.javaplus.clickscreen.button;

import org.javaplus.game.common.assets.Assets;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GameButton extends ImageButton {

	public class ButtonNormalAction extends InputListener {

	}

	protected BitmapFont font;
	private CharSequence text;

	public void setText(CharSequence text) {
		this.text = text;
	}
	
	public void setFont(BitmapFont font) {
		this.font = font;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		
		if(font != null && text != null) {
			font.draw(batch, text, getX(), getY());
		}
	}

	public GameButton(TextureRegion region) {
		super(create(region), create(region));
		addListener(new ButtonNormalAction());
	}

	public GameButton(String name) {
		this(Assets.getDefaultLoader().getTextureAtlas("data/game.txt").findRegion(name));
	}

	private static TextureRegionDrawable create(TextureRegion region) {
		return new TextureRegionDrawable(region);
	}

	public void addButtonListener(final IButtonListener bt) {

		ChangeListener bb = new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				try {
					bt.action(event, actor);
				} catch (Exception e) {
					e.printStackTrace();
					bt.onException(e);
				}
			}

		};
		addListener(bb);
	}

	public void setTouchable(boolean isTouchable) {
		setTouchable(isTouchable ? Touchable.enabled : Touchable.disabled);
	}

}
