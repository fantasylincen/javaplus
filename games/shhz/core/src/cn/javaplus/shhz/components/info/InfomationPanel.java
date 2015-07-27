package cn.javaplus.shhz.components.info;

import cn.javaplus.shhz.stage.GameStage;

import com.badlogic.gdx.scenes.scene2d.Group;

public class InfomationPanel extends Group {

	public InfomationPanel(GameStage stage) {
		addActor(new GoldField());
		addActor(new WoodField());
		addActor(new PersonSizeField());
	}

}
