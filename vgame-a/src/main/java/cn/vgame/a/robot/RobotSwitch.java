package cn.vgame.a.robot;

import java.util.List;

import cn.vgame.a.turntable.TurntableUtil;
import cn.vgame.a.turntable.swt.ISwitchs;

public class RobotSwitch implements ISwitchs {

	public void add(String type, int coin) {
		if ("A".equals(type)) {
			a += coin;
		}
		if ("B".equals(type)) {
			b += coin;
		}
		if ("C".equals(type)) {
			c += coin;
		}
		if ("D".equals(type)) {
			d += coin;
		}
		if ("E".equals(type)) {
			e += coin;
		}
		if ("F".equals(type)) {
			f += coin;
		}
		if ("G".equals(type)) {
			g += coin;
		}
		if ("H".equals(type)) {
			h += coin;
		}
		if ("I".equals(type)) {
			i += coin;
		}
		if ("J".equals(type)) {
			j += coin;
		}
		if ("K".equals(type)) {
			k += coin;
		}
		if ("L".equals(type)) {
			l += coin;
		}
	}

	@Override
	public String toString() {
		return TurntableUtil.toString(this);
	}

	int a;
	int b;
	int c;
	int d;

	int e;
	int f;
	int g;
	int h;

	int i;
	int j;
	int k;
	int l;

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public int getE() {
		return e;
	}

	public void setE(int e) {
		this.e = e;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}

	public void add(ISwitchs s) {
		List<String> ts = TurntableUtil.getAllTypes();
		for (String type : ts) {
			int add = TurntableUtil.getByType(s, type);
			add(type, add);
		}
	}
}
