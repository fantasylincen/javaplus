package util.taskscheduling;

import define.SystemCfg;
import util.SystemTimer;
import util.UtilBase;

public enum T {
	
	/**
	 * 每1秒
	 */
	OMNI_SECOND {
		@Override
		public long getDelay() {
			return 0;
		}
		
		@Override
		public long getPeriod() {
			// 这里当然是每秒
			return 1000L;
		}
	},
	
	/**
	 * 每分钟
	 */
	OMNI_MINUTE {
		@Override
		public long getDelay() {
			// 先得到当前整点的时间 然后加上 60000就是一分钟的时间 就变成下一个小时的时间
			long earliestDtae		= SystemTimer.getEarliestDate( -1, -1, -1, 0 ) + 60000L;
			// 用下一个小时的时间 减当前时间 就变成延期时间
			return earliestDtae - SystemTimer.currentTimeMillis();
		}
		
		@Override
		public long getPeriod() {
			// 这里当然是每分钟 1000 * 60
			return 60000L;
		}
	},
	
	/**
	 * 每10分钟
	 */
	OMNI_TEN_MINUTE {
		@Override
		public long getDelay() {
			// 一秒后开始
			return 1000L;
		}
		
		@Override
		public long getPeriod() {
			// 这里当然是每分钟 1000 * 60 * 10
			return 600000L;
		}
	},
	/**
	 * 每小时
	 */
	OMNI_HORA {
		@Override
		public long getDelay() {
			// 先得到当前整点的时间 然后加上3600000就是一个小时的时间 就变成下一个小时的时间
			long earliestDtae		= SystemTimer.getEarliestDate( -1, -1, 0, 0 ) + 3600000L;
			// 用下一个小时的时间 减当前时间 就变成延期时间
			return earliestDtae - SystemTimer.currentTimeMillis();
		}

		@Override
		public long getPeriod() {
			// 这里当然是每小时刷新
			return 3600000L;
		}
	},
	
	/**
	 * 每日
	 */
	EVERY_DAY {
		@Override
		public long getDelay() {
			// 得到00:00:00的时间
			long earliestDtae		= SystemTimer.getEarliestDate( -1, 23, 59, 59 );
			// 用 00:00:00的时间 减去当前时间 就是延期时间
			return earliestDtae - SystemTimer.currentTimeMillis();
		}

		@Override
		public long getPeriod() {
			// 这里当然是每24小时刷新 1000 * 60 * 60 * 24 - 1000
			return 86399000L;
		}
	},
	
	/**
	 * 每日初始 日志
	 */
	LOG_INIT {
		@Override
		public long getDelay() {
			// 得到00:00:00的时间
			long earliestDtae		= SystemTimer.getEarliestDate( -1, 24, 0, 1 );
			// 用 00:00:00的时间 减去当前时间 就是延期时间
			return earliestDtae - SystemTimer.currentTimeMillis();
		}

		@Override
		public long getPeriod() {
			// 这里当然是每24小时刷新 1000 * 60 * 60 * 24 + 1000
			return 86401000L;
		}
	},
	
	/**
	 * 每周 前
	 */
	EVERY_WEEK_FRONT {
		@Override
		public long getDelay() {
			// 得到00:00:00的时间
			long earliestDtae		= SystemTimer.getEarliestDate( -1, 24, 0, 1 );
			// 用 00:00:00的时间 减去当前时间 就是延期时间
			long delayTime			= earliestDtae - SystemTimer.currentTimeMillis();
			// 得到还差多久到星期日
			long earliestDayTime	= UtilBase.getEarliestDayTimeToWeek( 6 );
			if( earliestDayTime == 0 ) return 1;
			
			return delayTime + (earliestDayTime - 1) * 86400000L;
		}

		@Override
		public long getPeriod() {
			// 这里当然是每24*7小时刷新 1000 * 60 * 60 * 24  * 7
			return 604800000L;
		}
	},
	
	/**
	 * 每周 后
	 */
	EVERY_WEEK_BACK {
		@Override
		public long getDelay() {
			// 得到00:00:00的时间
			long earliestDtae		= SystemTimer.getEarliestDate( -1, 24, 0, 1 );
			// 用 00:00:00的时间 减去当前时间 就是延期时间
			long delayTime			= earliestDtae - SystemTimer.currentTimeMillis();
			// 得到还差多久到星期日
			long earliestDayTime	= UtilBase.getEarliestDayTimeToWeek( 7 );
			if( earliestDayTime == 0 ) return delayTime;
			return delayTime + earliestDayTime * 86400000L;
		}

		@Override
		public long getPeriod() {
			// 这里当然是每24*7小时刷新 1000 * 60 * 60 * 24  * 7
			return 604800000L;
		}
	},
	
	/**
	 * 	每日12点-14点    每日晚18点-20点
	 */
	EVERY_12 {
		@Override
		public long getDelay() {
			long earliestDtae		= SystemTimer.getEarliestDate( -1, 12, 0, 1 );
			long delayTime			= earliestDtae - SystemTimer.currentTimeMillis();
			if( delayTime < 0 ) return 1;
			return delayTime;
		}
		@Override
		public long getPeriod() {
			// 这里当然是每24*7小时刷新 1000 * 60 * 60 * 24
			return 86401000L;
		}
	},
	
	/**
	 * 	每日12点-14点    每日晚18点-20点
	 */
	EVERY_14 {
		@Override
		public long getDelay() {
			long earliestDtae		= SystemTimer.getEarliestDate( -1, 14, 0, 1 );
			long delayTime			= earliestDtae - SystemTimer.currentTimeMillis();
			if( delayTime < 0 ) return 1;
			return delayTime;
		}
		@Override
		public long getPeriod() {
			// 这里当然是每24*7小时刷新 1000 * 60 * 60 * 24
			return 86401000L;
		}
	},
	
	/**
	 * 	每日12点-14点    每日晚18点-20点
	 */
	EVERY_18 {
		@Override
		public long getDelay() {
			long earliestDtae		= SystemTimer.getEarliestDate( -1, 18, 0, 1 );
			long delayTime			= earliestDtae - SystemTimer.currentTimeMillis();
			if( delayTime < 0 ) return 1;
			return delayTime;
		}
		@Override
		public long getPeriod() {
			// 这里当然是每24*7小时刷新 1000 * 60 * 60 * 24
			return 86401000L;
		}
	},
	
	/**
	 * 	每日12点-14点    每日晚18点-20点
	 */
	EVERY_20 {
		@Override
		public long getDelay() {
			long earliestDtae		= SystemTimer.getEarliestDate( -1, 20, 0, 1 );
			long delayTime			= earliestDtae - SystemTimer.currentTimeMillis();
			if( delayTime < 0 ) return 1;
			return delayTime;
		}
		@Override
		public long getPeriod() {
			// 这里当然是每24*7小时刷新 1000 * 60 * 60 * 24
			return 86401000L;
		}
	},
	
	/**
	 * 从开区起3天
	 */
	OPEN_AREA_THREE_DAY  {
		@Override
		public long getDelay() {
			// 得到3天后的时间
			long earliestDtae		= SystemCfg.START_SERVER_T + 259200000L;
			// 然后用3天后的时间减去现在的时间 那么就是 差值
			return earliestDtae - SystemTimer.currentTimeMillis();
		}

		@Override
		public long getPeriod() {
			// 这里不用重复
			return 0;
		}
	};
	
	/**
	 * 获得延期时间
	 * @return
	 */
	public abstract long getDelay();
	
	/**
	 * 获得周期时间
	 * @return
	 */
	public abstract long getPeriod();
}
