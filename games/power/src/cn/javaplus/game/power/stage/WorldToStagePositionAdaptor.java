package cn.javaplus.game.power.stage;

import com.badlogic.gdx.math.Vector2;

public class WorldToStagePositionAdaptor extends Vector2 {

	private static final long serialVersionUID = -7679418902246879871L;

	public WorldToStagePositionAdaptor(Vector2 worldPosition) {
		this.setX(getWorldToStage(worldPosition.getX()));
		this.setY(getWorldToStage(worldPosition.getY()));
	}

	// 900像素: 228毫米
	private static final float S = 200;

	static float getStageToWorld(float v) {
		return v / S;
	}

	static float getWorldToStage(float v) {
		return v * S;
	}

}
