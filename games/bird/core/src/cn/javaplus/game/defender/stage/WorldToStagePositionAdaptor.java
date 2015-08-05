package cn.javaplus.game.defender.stage;

import cn.javaplus.common.util.Util;

import com.badlogic.gdx.math.Vector2;

public class WorldToStagePositionAdaptor extends Vector2 {

	private static final long	serialVersionUID	= -7679418902246879871L;


	public WorldToStagePositionAdaptor(Vector2 worldPosition) {
		this.setX(Util.getWorldToStage(worldPosition.getX()));
		this.setY(Util.getWorldToStage(worldPosition.getY()));
	}

}
