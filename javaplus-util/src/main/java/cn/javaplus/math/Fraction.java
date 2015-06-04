package cn.javaplus.math;

/**
 * 分数
 * @author 	林岑
 * @since	2012年9月14日 10:49:33
 *
 */
public class Fraction {

	/**
	 * 分子
	 */
	private int numerator = 0;

	/**
	 * 分母
	 */
	private int denominator = 100;

	/**
	 * 初始化一个分式
	 * @param numerator		分子
	 * @param denominator	分母
	 */
	public Fraction(int numerator, int denominator) {
		setValue(numerator, denominator);
	}

	/**
	 * @param numerator2
	 * @param denominator2
	 * @param i				分子分母同时放大倍数
	 */
	public Fraction(int numerator2, int denominator2, int i) {
		this(numerator2, denominator2);
		this.numerator *= i;
		this.denominator *= i;
	}

	/**
	 * 判断该分数是不是真分数(值小于1为真分数)
	 * @return
	 */
	public boolean isProper() {
		return numerator < denominator;
	}

	/**
	 * 给该分式设置
	 * @param numerator
	 * @param denominator
	 */
	public void setValue(int numerator, int denominator) {
//		if(denominator <= 0 || numerator < 0 ) {
//
//			throw new IllegalArgumentException("分母必须大于0, 分子必须不小于零" + numerator + "/" + denominator);
//		}

		this.numerator = numerator;
		this.denominator = denominator;
	}

	/**
	 * 分子
	 * @return
	 */
	public int getNumerator() {
		return numerator;
	}

	/**
	 * 分母
	 * @return
	 */
	public int getDenominator() {
		return denominator;
	}

	@Override
	public String toString() {
		return numerator + "/" + denominator;
	}
}
