package cn.vgame.a.turntable.generator;

import cn.vgame.a.turntable.swt.ISwitchs;
import cn.vgame.a.turntable.swt.SwitchAll;

public class SwitchWithOutRobot implements ISwitchs {

	private final SwitchAll switchs;

	public SwitchWithOutRobot(SwitchAll switchs) {
		this.switchs = switchs;
	}

	public int getA() {
		return switchs.getByTypeWithOutRobot("A");
	}

	public int getB() {
		return switchs.getByTypeWithOutRobot("B");
	}

	public int getC() {
		return switchs.getByTypeWithOutRobot("C");
	}

	public int getD() {
		return switchs.getByTypeWithOutRobot("D");
	}

	public int getE() {
		return switchs.getByTypeWithOutRobot("E");
	}

	public int getF() {
		return switchs.getByTypeWithOutRobot("F");
	}

	public int getG() {
		return switchs.getByTypeWithOutRobot("G");
	}

	public int getH() {
		return switchs.getByTypeWithOutRobot("H");
	}

	public int getI() {
		return switchs.getByTypeWithOutRobot("I");
	}

	public int getJ() {
		return switchs.getByTypeWithOutRobot("J");
	}

	public int getK() {
		return switchs.getByTypeWithOutRobot("K");
	}

	public int getL() {
		return switchs.getByTypeWithOutRobot("L");
	}

	
}
