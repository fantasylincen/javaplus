package cn.javaplus.shhz.components;

import cn.javaplus.shhz.log.Log;
import cn.javaplus.shhz.stage.GameStage;
import cn.javaplus.shhz.util.VectorInt;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class GridDebuger extends InputListener {

//	private GameStage stage;
//	private GridManager gm;

	public GridDebuger(GameStage stage, GridManager gm) {
//		this.stage = stage;
//		this.gm = gm;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
//		float sx = event.getStageX();
//		float sy = event.getStageY();
//		VectorInt v = GridManager.getGridPositionOnStage((int) sx, (int) sy);
//		stage.addPoint(v);
		return true;
	}

	@Override
	public boolean mouseMoved(InputEvent event, float x, float y) {

		float sx = event.getStageX();
		float sy = event.getStageY();
		VectorInt v = GridManager.getGridIndex((int) sx, (int) sy);
		Log.d("GridDebuger.mouseMoved ", v);
		return true;
	}
}
