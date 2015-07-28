package cn.javaplus.shhz;

import cn.javaplus.game.shhz.Events;
import cn.javaplus.shhz.events.GameStartEvent;
import cn.javaplus.shhz.input.GameInputProcessor;

import com.badlogic.gdx.Gdx;

public class App extends com.badlogic.gdx.Game {


	private GameInputProcessor processor;

	@Override
	public void create() {
		processor = new GameInputProcessor();
		Gdx.input.setInputProcessor(processor);
		Events.dispatch(new GameStartEvent(this));
		Game.onCreate(this);
	}

	GameInputProcessor getProcessor() {
		return processor;
	}

}
