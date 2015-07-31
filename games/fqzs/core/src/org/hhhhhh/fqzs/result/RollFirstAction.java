package org.hhhhhh.fqzs.result;

import java.util.List;

import org.hhhhhh.fqzs.core.App;
import org.hhhhhh.fqzs.result.PlayResult.Result;
import org.javaplus.game.common.log.Log;

import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class RollFirstAction extends SequenceAction {

	private Result result;
	private Roller roller;

	public RollFirstAction(Result result, Roller roller) {
		this.result = result;
		this.roller = roller;
		
		addActions(Roller.FIRST_ROLL_SPACES_A);	//第1阶段
		addActionsOther();						//填充阶段
		addActions(Roller.FIRST_ROLL_SPACES_B);	//第2阶段
		addActions(Roller.FIRST_ROLL_SPACES_C);	//第3阶段
		
		addActionDelayOnRollEnd();
	}

	private void addActionDelayOnRollEnd() {
		double stopSecOnRollEnd = App.getProperties().getDouble("stopSecOnRollEnd");
		DelayAction delayAction = new DelayAction();
		delayAction.setDuration((float) stopSecOnRollEnd);
		addAction(delayAction);
	}
	
	private void addActions(SpaceDefine space) {
		List<Integer> spaces = space.getSpaces();
		for (int delay : spaces) {
			addRollAction(delay);
		}
	}

	private void addActionsOther() {
		int count = getOtherCount();
		for (int i = 0; i < count; i++) {
			int delay = Roller.NEXT_ROLL_SPACE;
			addRollAction(delay);
		}
	}

	private void addRollAction(int delay) {
		addAction(new RollOnceAction(roller));
		addAction(new DelayAction(delay / 1000f));
		
//		Log.d("add action", delay);
	}

	static final int LIGHT_COUNT = 28;
	
	private int getOtherCount() {

		int times = roller.getSpace(result.getId() + "");//需要旋转多少次才能到达目的地
		
		int count = 0;
		
		count += Roller.FIRST_ROLL_SPACES_A.getSpaces().size();
		count += Roller.FIRST_ROLL_SPACES_B.getSpaces().size();
		count += Roller.FIRST_ROLL_SPACES_C.getSpaces().size();

		count %= LIGHT_COUNT;
		
		int cc = times - count;
		if(cc < 0) {
			cc += LIGHT_COUNT;
		}
		return cc;
	}

	public Result getResult() {
		return result;
	}
	
	public Roller getRoller() {
		return roller;
	}

}
