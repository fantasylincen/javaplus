package org.hhhhhh.fqzs.result;

import org.hhhhhh.fqzs.result.PlayResult.Result;

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class RollFirstAction extends SequenceAction {

	private Result result;
	private Roller roller;

	public RollFirstAction(Result result, Roller roller) {
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
