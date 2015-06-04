package cn.vgame.a.turntable;

import com.alibaba.fastjson.JSON;

import cn.javaplus.log.Log;
import cn.vgame.a.account.JsonActionAfterRoleEnterGame;
import cn.vgame.a.turntable.GetAllSwitchsAction.GetTableDataResult;
import cn.vgame.a.turntable.swt.ISwitchs;

/**
 * 同步数据
 * 
 * -----------------
 * 
 * 返回: 同 GetAllSwitchs
 * 
 */
public class SynchronizeDataAction extends JsonActionAfterRoleEnterGame {

	public class SwitchsImpl2 implements ISwitchs {

		private final SynchronizeDataAction a2;

		public SwitchsImpl2(SynchronizeDataAction a) {
			a2 = a;
		}

		public int getA() {
			return a2.getA();
		}

		public int getB() {
			return a2.getB();
		}

		public int getC() {
			return a2.getC();
		}

		public int getD() {
			return a2.getD();
		}

		public int getE() {
			return a2.getE();
		}

		public int getF() {
			return a2.getF();
		}

		public int getG() {
			return a2.getG();
		}

		public int getH() {
			return a2.getH();
		}

		public int getI() {
			return a2.getI();
		}

		public int getJ() {
			return a2.getJ();
		}

		public int getK() {
			return a2.getK();
		}

		public int getL() {
			return a2.getL();
		}

		@Override
		public String toString() {
			return JSON.toJSONString(this);
		}
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

	/**
	 * A 2 飞禽
	 */
	public int getA() {
		return a;
	}

	/**
	 * B 24 银鲨鱼
	 */
	public int getB() {
		return b;
	}

	/**
	 * C 48 金鲨鱼
	 */
	public int getC() {
		return c;
	}

	/**
	 * D 2 走兽
	 */
	public int getD() {
		return d;
	}

	/**
	 * E 6 燕子
	 */
	public int getE() {
		return e;
	}

	/**
	 * F 8 鸽子
	 */
	public int getF() {
		return f;
	}

	/**
	 * G 8 孔雀
	 */
	public int getG() {
		return g;
	}

	/**
	 * H 12 老鹰
	 */
	public int getH() {
		return h;
	}

	/**
	 * I 12 狮子
	 */
	public int getI() {
		return i;
	}

	/**
	 * J 8 熊猫
	 */
	public int getJ() {
		return j;
	}

	/**
	 * K 8 猴子
	 */
	public int getK() {
		return k;
	}

	/**
	 * L 6 兔子
	 */
	public int getL() {
		return l;
	}

	public void setA(int a) {
		this.a = a;
	}

	public void setB(int b) {
		this.b = b;
	}

	public void setC(int c) {
		this.c = c;
	}

	public void setD(int d) {
		this.d = d;
	}

	public void setE(int e) {
		this.e = e;
	}

	public void setF(int f) {
		this.f = f;
	}

	public void setG(int g) {
		this.g = g;
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setI(int i) {
		this.i = i;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public void setK(int k) {
		this.k = k;
	}

	public void setL(int l) {
		this.l = l;
	}

	public class ErrorJson {

		private final String error;

		public ErrorJson(String error) {
			this.error = error;
		}

		public String getError() {
			return error;
		}
	}

	private static final long serialVersionUID = -6099859675509539457L;

	@Override
	public Object run() {
		ISwitchs s = new SwitchsImpl2(this);
		Turntable.getInstance().commitUserSwitchs(role.getId(), s);
//		Log.d(s);
		return new GetTableDataResult(role);
	}
}
