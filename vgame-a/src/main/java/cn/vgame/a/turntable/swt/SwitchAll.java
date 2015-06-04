package cn.vgame.a.turntable.swt;

import java.util.Map;
import java.util.Set;

import cn.javaplus.collections.map.Maps;
import cn.vgame.a.turntable.TurntableUtil;

public class SwitchAll implements ISwitchs {

	Map<String, ISwitchs> ss = Maps.newConcurrentMap();
	

	public ISwitchs get(String id) {
		return ss.get(id);
	}

	public void clear() {
		ss.clear();
	}

	public void save(String id, ISwitchs s) {

		ss.put(id, s);
		
	}

	private int getByType(String type) {
		int sum = 0;
		for (ISwitchs a : ss.values()) {
			int byType = TurntableUtil.getByType(a, type);
			sum += byType;
		}
		return sum;
	}

	/**
	 * A 2 飞禽
	 */
	public int getA() {
		return getByType("A");
	}

	/**
	 * B 24 银鲨鱼
	 */
	public int getB() {
		return getByType("B");
	}

	/**
	 * C 48 金鲨鱼
	 */
	public int getC() {
		return getByType("C");
	}

	/**
	 * D 2 走兽
	 */
	public int getD() {
		return getByType("D");
	}

	/**
	 * E 6 燕子
	 */
	public int getE() {
		return getByType("E");
	}

	/**
	 * F 8 鸽子
	 */
	public int getF() {
		return getByType("F");
	}

	/**
	 * G 8 孔雀
	 */
	public int getG() {
		return getByType("G");
	}

	/**
	 * H 12 老鹰
	 */
	public int getH() {
		return getByType("H");
	}

	/**
	 * I 12 狮子
	 */
	public int getI() {
		return getByType("I");
	}

	/**
	 * J 8 熊猫
	 */
	public int getJ() {
		return getByType("J");
	}

	/**
	 * K 8 猴子
	 */
	public int getK() {
		return getByType("K");
	}

	/**
	 * L 6 兔子
	 */
	public int getL() {
		return getByType("L");
	}


	public ISwitchs remove(String id) {
		return ss.remove(id);
	}

	public Set<String> keySet() {
		return ss.keySet();
	}
}