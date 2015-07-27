package cn.javaplus.shhz.components.buildings;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class BuildingSelectEffect extends RepeatAction {

	private ColorAction action1;
	private ColorAction action2;

	public BuildingSelectEffect(Building building) {
		action1 = new ColorAction();
		action1.setDuration(0.5f);

		action2 = new ColorAction();
		action2.setDuration(0.5f);

		SequenceAction sa = new SequenceAction();
		sa.addAction(action1);
		sa.addAction(action2);

		setCount(RepeatAction.FOREVER);
		setAction(sa);
	}

	public void setEndColor1(Color endColor1) {
		action1.setEndColor(endColor1);
	}

	public void setEndColor2(Color endColor2) {
		action2.setEndColor(endColor2);
	}

	public void toUnStandableEffect() {
		setEndColor1(new Color(0.6f, 0.5f, 0.5f, 1));
		setEndColor2(Color.RED);
	}

	public void toStandableEffect() {
		setEndColor1(Color.GRAY);
		setEndColor2(Color.WHITE);
	}

}
