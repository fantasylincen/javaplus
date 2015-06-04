package cn.mxz;

public interface Addition2 {

	/**
	 * 阵首属性类型（0气血 1物攻 2法攻 3物防 4法防 5速度 6暴击 7闪避 8格挡 9抗暴 10 命中 11破格 12会心）
	 */
	int getAdditionType();

	/**
	 * 阵首属性数值（百分比）
	 */
	float getAdditionPercent();

	/**
	 * 阵首属性数值（固定值）
	 */
	int getAdditionValue();

}