package cn.javaplus.game.power.camera;

import cn.javaplus.game.power.mover.Mover;

import com.badlogic.gdx.graphics.ICamera;
import com.badlogic.gdx.scenes.scene2d.EventListener;

public interface GameCamera extends ICamera, Mover {
	float getX();
	float getY();
	boolean isVisible(VisibleObject o);
	void addListener(EventListener listener);
	boolean removeListener(EventListener listener);
}
