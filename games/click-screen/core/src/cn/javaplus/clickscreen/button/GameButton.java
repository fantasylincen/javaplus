package cn.javaplus.clickscreen.button;

import org.javaplus.clickscreen.button.IButtonListener;
import org.javaplus.game.common.assets.Assets;

import cn.javaplus.clickscreen.App;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GameButton extends ImageButton {

	public class ButtonNormalAction extends InputListener {

	}

	private static BitmapFont font;
	private CharSequence text;

	private void generateFont() {
		FreeTypeFontGenerator generator = App.getAssets().getGenerator();
		FreeTypeFontParameter p = new FreeTypeFontParameter();
		p.characters = "0.123456789wertyuiop[]asdfghjkl;'zxcvbnm,/QWERTYUIOPASDFGHJKL:ZXCVBNM<>?";
		p.size = 32;
		font = generator.generateFont(p);
	}

	public void setText(CharSequence text) {
		this.text = text;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		font.setColor(Color.WHITE);
		font.draw(batch, text, getX(), getY());
	}

	public GameButton(TextureRegion region) {
		super(create(region), create(region));
		addListener(new ButtonNormalAction());
		if (font == null)
			generateFont();
	}

	public GameButton(String name) {
		this(Assets.getSd().getTextureAtlas("data/game.txt").findRegion(name));
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
