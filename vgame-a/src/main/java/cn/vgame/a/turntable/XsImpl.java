package cn.vgame.a.turntable;

import cn.vgame.a.turntable.GetAllSwitchsAction.Xs;

public class XsImpl implements Xs {

	/**
	 * 
	 */
	private final Turntable turntable;

	/**
	 * @param turntable
	 */
	XsImpl(Turntable turntable) {
		this.turntable = turntable;
	}

	@Override
	public int getA() {
		return this.turntable.getX("A");
	}

	@Override
	public int getB() {
		return this.turntable.getX("B");
	}

	@Override
	public int getC() {
		return this.turntable.getX("C");
	}

	@Override
	public int getD() {
		return this.turntable.getX("D");
	}

	@Override
	public int getE() {
		return this.turntable.getX("E");
	}

	@Override
	public int getF() {
		return this.turntable.getX("F");
	}

	@Override
	public int getG() {
		return this.turntable.getX("G");
	}

	@Override
	public int getH() {
		return this.turntable.getX("H");
	}

	@Override
	public int getI() {
		return this.turntable.getX("I");
	}

	@Override
	public int getJ() {
		return this.turntable.getX("J");
	}

	@Override
	public int getK() {
		return this.turntable.getX("K");
	}

	@Override
	public int getL() {
		return this.turntable.getX("L");
	}

}