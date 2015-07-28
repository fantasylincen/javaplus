package cn.javaplus.twolegs.game;

import org.javaplus.game.common.game.Marsking;

import cn.javaplus.twolegs.define.D;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class MarskingFullScreen extends Marsking {

	public MarskingFullScreen(Actor actor) {
		super(actor);

		setSize(D.STAGE_W, D.STAGE_H);
		setPosition(-D.STAGE_W / 2, -D.STAGE_H / 2);
	}

}
