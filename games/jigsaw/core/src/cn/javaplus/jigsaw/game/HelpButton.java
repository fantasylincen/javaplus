package cn.javaplus.jigsaw.game;

import org.javaplus.game.common.assets.Assets;

import cn.javaplus.jigsaw.gameover.PopAction;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class HelpButton extends Image {

	public HelpButton(final GameStage gameStage) {
		super(Assets.getInternal().getTextureAtlas("data/game.txt").findRegion("help"));
		addListener(new ActorGestureListener() {

			@Override
			public void tap(InputEvent event, float x, float y, int count,
					int button) {
				HelpPanel s = new HelpPanel();
				s.setScale(0.1f, 0.1f);
				s.setPosition(300, 600);
				s.setColor(new Color(1, 1, 1, 0));
				s.addAction(new PopAction(s));
				
				AlphaAction aa = new AlphaAction();
				aa.setDuration(0.1f);
				aa.setAlpha(1);
				s.addAction(aa);
				gameStage.addActor(new Marsking(s));
			}
		});
	}

}
