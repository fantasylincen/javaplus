package cn.javaplus.game.power.listeners;

import cn.javaplus.game.power.events.GameStartEvent;
import cn.javaplus.game.power.stage.GameInputProcessor;
import cn.javaplus.game.power.stage.GameModel;

import com.badlogic.gdx.InputAdapter;

import cn.mxz.events.Listener;
public class InitControlerByKey implements Listener<GameStartEvent> {

	public class Controller extends InputAdapter {

		private GameModel game;

		public Controller(GameModel game) {
			this.game = game;
		}


		@Override
		public boolean keyTyped(char x) {
			//w 上 
			//s 下 
			//a 左 
			//d 右 
			
			if(x == '8') {
				game.up();
			} else if(x == '4') {
				game.left();
			} else if(x == '5') {
				game.down();
			} else if(x == '6') {
				game.right();
			}
			return true;
		}

	}

	@Override
	public void onEvent(GameStartEvent e) {
		GameModel game = e.getGameModel();
		GameInputProcessor input = e.getInput();
		input.add(new Controller(game));
	}

}
