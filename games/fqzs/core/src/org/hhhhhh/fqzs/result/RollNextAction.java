package org.hhhhhh.fqzs.result;

import org.hhhhhh.fqzs.result.PlayResult.Result;

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class RollNextAction extends SequenceAction {

	private Roller roller;
	private Result result;

	public RollNextAction(Result result, Roller roller) {
		this.result = result;
		this.roller = roller;
	}

	public Result getResult() {
		return result;
	}

	public Roller getRoller() {
		return roller;
	}
}