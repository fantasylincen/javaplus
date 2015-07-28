package cn.javaplus.twolegs.game;

import org.javaplus.game.common.assets.Assets;

import cn.javaplus.twolegs.define.D;
import cn.javaplus.twolegs.define.Events;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ExitButton extends ImageButton {
	public ExitButton() {
		super(new TextureRegionDrawable(Assets
				.getInternal().getTextureAtlas("data/robot.txt").findRegion("exit")));
		setSize(100, 50);
		setPosition(D.STAGE_W / 2 - getWidth(), D.STAGE_H / 2 - getHeight());
		
		
		
		addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Events.dispatch(new ExitEvent());
			}
		});

	}
}
