package cn.javaplus.clickscreen.game;

import org.javaplus.clickscreen.button.IButtonListener;

public interface TankButtonData extends IButtonListener{

	String getButtonImageName();

	boolean isTouchable();

	CharSequence getText();

}
