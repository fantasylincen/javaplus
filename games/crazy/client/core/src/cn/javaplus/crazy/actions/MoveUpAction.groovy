package cn.javaplus.crazy.actions;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class MoveUpAction extends ParallelAction {

	public MoveUpAction(Actor menu) {
		MoveToAction mt = new MoveToAction();

		mt = new MoveToAction();

		mt.setY(-menu.getHeight());
		mt.setX(menu.getX());
		mt.setDuration(0);
		addAction(mt);

		mt.setY(0);
		mt.setX(menu.getX());
		mt.setDuration(1f);
		addAction(mt);

		SequenceAction sa = new SequenceAction();
		AlphaAction ab = new AlphaAction();
		ab.setDuration(0);
		ab.setAlpha(0);
		sa.addAction(ab);

		AlphaAction aa = new AlphaAction();
		aa.setDuration(0.09f);
		aa.setAlpha(1f);
		sa.addAction(aa);

		addAction(sa);
	}
}
