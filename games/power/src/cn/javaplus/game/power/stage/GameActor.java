package cn.javaplus.game.power.stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.javaplus.game.power.Game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.IActor;

public class GameActor extends Actor implements IActor {

	private GameModel game;
	private Map<Integer, Texture> map;

	
	public GameActor(GameModel game) {
		this.game = game;
		
		map = new HashMap<Integer, Texture>();
		put(2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192);
	}

	private void put(int...i) {
		for (int j : i) {
			Texture t = Game.getAssetsManager().getTexture(j + ".png");
			map.put(j, t);
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		List<Grid> grids = game.getGrids(false);
		for (Grid grid : grids) {
			draw(grid, batch);
		}
	}

	private void draw(Grid grid, Batch batch) {
		int value = grid.getV();
		Texture t = map.get(value);
		int x = grid.getXIndex() * t.getWidth();
		int y = (game.getWidth() - grid.getYIndex() - 1) * t.getHeight();
		batch.draw(t, x, y);
	}
}
