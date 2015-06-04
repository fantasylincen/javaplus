package cn.vgame.a.turntable;

import cn.vgame.a.account.JsonActionAfterRoleEnterGame;
import cn.vgame.a.turntable.swt.ISwitchs;

/**
 * 获取开奖结果
 * -----------------
 * 
 * A.正常情况:
 * 	{
 * 		coin: 玩家金币(结算后的金币),
 * 
 * 		caiJin: 得了多少彩金,
 * 
 * 		results:[
 * 			{
 * 				type 对应到 weights表里面的type
 * 				id 对应到 weights表里面的id
 * 			},
 * 			{
 * 				type 对应到 weights表里面的type
 * 				id 对应到 weights表里面的id
 * 			},
 * 			{
 * 				type 对应到 weights表里面的type
 * 				id 对应到 weights表里面的id
 * 			},
 * 			{
 * 				type 对应到 weights表里面的type
 * 				id 对应到 weights表里面的id
 * 			}
 * 		]
 * 	}
 * 
 * B.错误:
 *  标准错误 
 */
public class GetPlayResultAction extends JsonActionAfterRoleEnterGame implements ISwitchs{

	private static final long serialVersionUID = -5251001762611598388L;
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

	@Override
	public Object run() {
		return Turntable.getInstance().getPlayResult(role, this);
	}

}
