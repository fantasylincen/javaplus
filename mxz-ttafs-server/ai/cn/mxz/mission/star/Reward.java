package cn.mxz.mission.star;

public class Reward {

	public static class Status {

		/** 30 不可领取 */
		public static final int	PERCENT_30_X	= 0;
		/** 30 可领取 */
		public static final int	PERCENT_30_O	= 1;
		/** 60 不可领取 */
		public static final int	PERCENT_60_X	= 2;
		/** 60 可领取 */
		public static final int	PERCENT_60_O	= 3;
		/** 100 不可领取 */
		public static final int	PERCENT_100_X	= 4;
		/** 100 可领取 */
		public static final int	PERCENT_100_O	= 5;
		/** 全部领取完成 */
		public static final int	COMPLETE		= 6;
	}

	public static class TYPE {
		public static final int	PERCENT_30	= 1;
		public static final int	PERCENT_60	= 2;
		public static final int	PERCENT_100	= 3;
	}

}
