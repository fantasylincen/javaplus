package cn.javaplus.game.defender.collision;

import com.badlogic.gdx.math.IRectangle;

public class CollisionDetectorImpl implements CollisionDetector {

	private IRectangle target;

	public CollisionDetectorImpl(IRectangle target) {
		this.target = target;
	}

	@Override
	public boolean isHalfInto(IRectangle actor) {
		
		float x = target.getX();
		float y = target.getY();
		
		float halfWidth = target.getWidth() / 2;
		float halfHeight = target.getHeight() / 2;
		
		if(x + halfWidth < actor.getX()) {
			return false;
		}
		
		if(x + halfWidth > actor.getX() + actor.getWidth()) {
			return false;
		}
		
		if(y + halfHeight < actor.getY()) {
			return false;
		}
		
		if(y + halfHeight > actor.getY() + actor.getHeight()) {
			return false;
		}
		
		return true;
		}

}
