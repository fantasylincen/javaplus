package org.hhhhhh.fqzs.result;

import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;

public class RollOnceAction extends RunnableAction {
	public RollOnceAction(final Roller roller) {
		setRunnable(new Runnable() {
			public void run() {
				roller.rollOnce();
			}
		});
	}
}
