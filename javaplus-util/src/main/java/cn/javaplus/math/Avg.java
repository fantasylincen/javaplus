package cn.javaplus.math;

/**
 * 平均值
 */
public class Avg {

	public double getValue() {
		return all / count;
	}

	int count;
	
	private double all;

	public void add(double up) {
		all += up;
		count++;
	}

	public int getCount() {
		return count;
	}

	public void reset() {
		count = 0;
		all = 0;
	}

}
