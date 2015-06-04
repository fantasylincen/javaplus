package cn.mxz.base.config;

/**
 * 数据库key_value表配置定义
 * @author 林岑
 *
 */
public enum KeyValueDefine {

	/**
	 * 开服时间
	 */
	SERVER_OPEN_TIME {

		@Override
		public Object getInitValue() {

			return System.currentTimeMillis();
		}
	},

	/**
	 * 服务操作次数记录
	 */
	SERVICE{

		@Override
		public Object getInitValue() {

			return "0|0";// 操作次数|累计耗时 毫秒
		}

	},

	/**
	 * 消费点记录
	 */
	GOLD_REDUCE,

	/**
	 * 历史在线最大人数
	 */
	ONLINE_SIZE_MAX_HISTORY,

	/**
	 * 今日最大在线人数
	 */
	ONLINE_SIZE_MAX_TODAY,

	/**
	 * 历史获得最大星星数量
	 */

	MAX_STAR_HISTORY,


	/**
	 * 此次闯关 丢失的星星数量
	 */
	STAR_MISS_THIS_TIME,

	/**
	 * 最后一次更新天数
	 */
	PVP_TOPS_UPDATE_DAY,

	/**
	 * 当前在线人数
	 */
	ONLINE_SIZE_NOW
	;



	/**
	 * 初始化数值
	 * @return
	 */
	public Object getInitValue() {

		return 0;
	}

}
