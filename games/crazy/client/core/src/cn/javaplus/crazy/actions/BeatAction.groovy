package cn.javaplus.crazy.actions;

import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class BeatAction extends SequenceAction {
	public BeatAction() {
		ScaleToAction st = new ScaleToAction();
		st.setDuration(0.07f);
		st.setScale(1.05f);

		addAction(st);

		st = new ScaleToAction();
		st.setDuration(0.07f);
		st.setScale(1f);

		addAction(st);
	}
}
