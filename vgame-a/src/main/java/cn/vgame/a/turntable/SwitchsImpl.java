package cn.vgame.a.turntable;

import cn.vgame.a.turntable.swt.ISwitchs;


public class SwitchsImpl implements ISwitchs {

	private final CommitMySwitchsAction a;

	@Override
	public String toString() {
		return TurntableUtil.toString(this);
	}
	public SwitchsImpl(CommitMySwitchsAction a) {
		this.a = a;
	}

	public int getA() {
		return a.getA();
	}

	public int getB() {
		return a.getB();
	}

	public int getC() {
		return a.getC();
	}

	public int getD() {
		return a.getD();
	}

	public int getE() {
		return a.getE();
	}

	public int getF() {
		return a.getF();
	}

	public int getG() {
		return a.getG();
	}

	public int getH() {
		return a.getH();
	}

	public int getI() {
		return a.getI();
	}

	public int getJ() {
		return a.getJ();
	}

	public int getK() {
		return a.getK();
	}

	public int getL() {
		return a.getL();
	}


}
