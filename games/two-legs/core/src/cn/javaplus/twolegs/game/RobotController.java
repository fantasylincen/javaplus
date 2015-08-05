package cn.javaplus.twolegs.game;

import cn.javaplus.twolegs.App;

public class RobotController {

	private Robot robot;

	private boolean isLeft;

	public RobotController(GameStage stage, Robot robot) {
		this.robot = robot;
	}

	public Robot getRobot() {
		return robot;
	}

	public void touchDown() {
		if (robot.isDeath()) {
			App.getApp().restart();
			return;
		}
		robot.legOut();
		if (isLeft) {
			robot.getRight().moveBehind();
			robot.getLeft().moveFront();
		} else {
			robot.getRight().moveFront();
			robot.getLeft().moveBehind();
		}
		robot.addTouchTimes();
		isLeft = !isLeft;
	}

}
