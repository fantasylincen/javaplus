package cn.mxz;

/**
 * Excel中配置的奖励
 * @author 	林岑
 * @since	2012年12月6日 11:17:27
 */
public interface IRewardsInExcel {

	/**
	 * 奖励说明
	 */
	String getExplain();
	
	/**
	 * 所有奖励序列0,10|1,30|20,200003,1|0,30|20,100001,1..... 按照这种格式排列
	 */
	String getAwards();
}
