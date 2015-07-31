package org.hhhhhh.fqzs.result;

import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;

public class EndAction extends RunnableAction {

	private Roller roller;

	public EndAction(final Roller roller) {
		this.roller = roller;
		setRunnable(new Runnable() {
			public void run() {
				roller.onRollEnd();
			}
		});
	}

	public Roller getRoller() {
		return roller;
	}
}