package org.hhhhhh.fqzs.result;

import org.hhhhhh.fqzs.result.PlayResult.Result;

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class PlayResultAction extends SequenceAction {

	private Result next;

	public PlayResultAction(Result next) {
		this.next = next;
	}


}
