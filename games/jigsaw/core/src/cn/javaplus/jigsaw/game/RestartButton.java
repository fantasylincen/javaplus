package cn.javaplus.jigsaw.game;

import org.javaplus.game.common.assets.Assets;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class RestartButton extends Image {

	public RestartButton(final GameStage gameStage) {
		super(Assets.getInternal().getTextureAtlas("data/game.txt").findRegion("restartbutton"));
		addListener(new ActorGestureListener() {

			@Override
			public void tap(InputEvent event, float x, float y, int count, int button) {
				gameStage.restart();
			}
		});
	}

}
