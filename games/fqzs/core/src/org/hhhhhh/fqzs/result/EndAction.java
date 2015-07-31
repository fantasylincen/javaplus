package org.hhhhhh.fqzs.result;

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class EndAction extends SequenceAction {

	private Roller roller;

	public EndAction(Roller roller) {
		this.roller = roller;
	}

	public Roller getRoller() {
		return roller;
	}
}