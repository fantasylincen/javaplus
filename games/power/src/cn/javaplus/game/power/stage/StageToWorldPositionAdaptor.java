package cn.javaplus.game.power.stage;

import com.badlogic.gdx.math.Vector2;

public class StageToWorldPositionAdaptor extends Vector2 {

	private static final long serialVersionUID = -7679418902246879871L;

	public StageToWorldPositionAdaptor(Vector2 stagePosition) {
		this.setX(WorldToStagePositionAdaptor.getStageToWorld(stagePosition.getX()));
		this.setY(WorldToStagePositionAdaptor.getStageToWorld(stagePosition.getY()));
	}

	public StageToWorldPositionAdaptor(float x, float y) {
		this(new Vector2(x, y));
	}


}
