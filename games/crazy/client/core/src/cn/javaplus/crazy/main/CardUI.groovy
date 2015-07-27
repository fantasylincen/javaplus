package cn.javaplus.crazy.main;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public interface CardUI {

	Image getSmallColor(int c);

	Image getBigColor(int c);

	Image getValue(boolean black, int v);

	Image getBackGround(int v);

}
