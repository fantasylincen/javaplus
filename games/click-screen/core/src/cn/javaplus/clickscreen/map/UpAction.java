package cn.javaplus.clickscreen.map;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class UpAction extends SequenceAction {

	public UpAction(final Actor ac) {

		MoveToAction mt = new MoveToAction();

		mt = new MoveToAction();

		mt.setY(ac.getY() + 200);
		mt.setX(ac.getX());
		mt.setDuration(0.2f);
		addAction(mt);

		AlphaAction ab = new AlphaAction() {
			@Override
			protected void end() {
				ac.remove();
			}
		};
		ab.setDuration(0.5f);
		ab.setAlpha(0);
		addAction(ab);
	}

}
