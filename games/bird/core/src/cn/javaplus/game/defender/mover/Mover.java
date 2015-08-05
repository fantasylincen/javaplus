package cn.javaplus.game.defender.mover;

public interface Mover extends Moveable {
	float getY();
	float getX();
	void setPosition(float x, float y);	
}
