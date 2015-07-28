package cn.javaplus.game.defender.collision;

import com.badlogic.gdx.math.IRectangle;
import com.badlogic.gdx.scenes.scene2d.IActor;

public interface CollisionDetector {

	boolean isHalfInto(IRectangle actor);

}
