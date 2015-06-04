package cn.javaplus.math;
/**
 * 范围
 */
public class Scope {
	private long min;
	private long max;
	
	public Scope(long min, long max) {
		this.setMin(min);
		this.setMax(max);
	}

	/**
	 * 判断x是不是在这个范围当中, 包含了最小值, 不包含最大值
	 * @param x
	 * @return
	 */
	public boolean contains(long x){
		return x >= this.getMin() && x < this.getMax();
	}

	/**
	 * 判断x是否比该范围小,
	 * 数轴左侧
	 * @param x
	 * @return
	 */
	public boolean isLeft(long x) {
		return x < this.getMin();
	}

	/**
	 * 判断x是否比该范围大,
	 * 数轴右侧
	 * @param x
	 * @return
	 */
	public boolean isRight(long x) {
		return x >= this.getMax();
	}
	
	public long getMin() {
		return min;
	}
	public long getMax() {
		return max;
	}
	public void setMin(long min) {
		this.min = min;
	}
	public void setMax(long max) {
		this.max = max;
	}
	@Override
	public String toString() {
		return "max = " + max+ ", min = " + min;
	}
}