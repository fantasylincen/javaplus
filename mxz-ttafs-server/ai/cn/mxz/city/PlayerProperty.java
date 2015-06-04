package cn.mxz.city;

import define.D;

/**
 * 玩家属性
 *
 * @author 林岑
 *
 */
public enum PlayerProperty {

	/** 活跃度1 */
	LIVENESS {
		@Override
		public int getId() {
			return -1;
		}
	},

	/** 金币2 */
	CASH {
		@Override
		public int getId() {
			return D.ID_CASH;
		}
	},

	/** 服装4 */
	CLOTHES {
		@Override
		public int getId() {
			return -1;
		}
	},

	/** 体力 5*/
	PHYSICAL {
		@Override
		public int getId() {
			return D.ID_PHYSICAL;
		}
	},

	/** 修行 6*/
	CULTIVATION {
		@Override
		public int getId() {
			return D.ID_CULTIVATION;
		}
	},

	/** 声望7 */
	REPUTATION {
		@Override
		public int getId() {
			return D.ID_REPUTATION;
		}
	},

	/** 精力8 */
	POWER {
		@Override
		public int getId() {
			return D.ID_POWER;
		}
	},

	/** 积分 9*/
	POINTS {
		@Override
		public int getId() {
			return D.ID_POINTS;
		}
	},

	/** 地仙令10 */
	DI_XIAN_LING {
		@Override
		public int getId() {
			return D.ID_DI_XIAN_LING;
		}
	},

	/** 天仙令11 */
	TIAN_XIAN_LING {
		@Override
		public int getId() {
			return D.ID_TIAN_XIAN_LING;
		}
	},

	/** 金仙令12 */
	JIN_XIAN_LING {
		@Override
		public int getId() {
			return D.ID_JIN_XIAN_LING;
		}
	},

	/** 活动币 14*/
	ACTIVITY1 {
		@Override
		public int getId() {
			return D.ID_ACTIVITY1;
		}
	},

	/** 活动币 15*/
	ACTIVITY2 {
		@Override
		public int getId() {
			return D.ID_ACTIVITY2;
		}
	},

	/** 活动币 16*/
	ACTIVITY3 {
		@Override
		public int getId() {
			return D.ID_ACTIVITY3;
		}
	},

	/** 活动币 17*/
	ACTIVITY4 {
		@Override
		public int getId() {
			return D.ID_ACTIVITY4;
		}
	},

	/** 活动币 18*/
	ACTIVITY5 {
		@Override
		public int getId() {
			return D.ID_ACTIVITY5;
		}
	},

	/** Boss活动积分19 */
	ACTIVITY_SCORE {
		@Override
		public int getId() {
			return -1;
		}
	},

	/** 魅力值 20*/
	CHARM {
		@Override
		public int getId() {
			return -1;
		}
	},

	/** 累计获得的元宝 21*/
	GOLD_HISTORY {
		@Override
		public int getId() {
			return -1;
		}
	},

	/** 最后登陆秒 22*/
	LAST_LOGIN_TIME_DEPRECATED {
		@Override
		public int getId() {
			return -1;
		}
	},

	/**
	 * 历史登陆次数23
	 */
	LOGIN_TIMES_HISTORY {
		@Override
		public int getId() {
			return -1;
		}
	},

	/**
	 * 聚魂值24
	 */
	JU_HUN {
		@Override
		public int getId() {
			return -1;
		}
	},

	/**
	 * 缘分值25
	 */
	LOT {
		@Override
		public int getId() {
			return D.ID_LOT;
		}
	},

	/**
	 * 兽魂26
	 */
	SHOU_HUN {
		@Override
		public int getId() {
			return D.ID_SHOU_HUN;
		}
	},

	/**
	 * 身价缓存28
	 */
	SHEN_JIA_CACHE {
		@Override
		public int getId() {
			return -1;
		}
	},

	/**
	 * 用户创建时间(秒)29   (北京时间)
	 */
	CREATE_SEC {
		@Override
		public int getId() {
			return -1;
		}
	},

	/**
	 * 金锭
	 */
	NEW_GOLD {
		@Override
		public int getId() {
			return D.ID_NEW_GOLD;
		}
	}, 
	
	/**
	 * 玩家等级缓存
	 */
	USER_LEVEL_CACHE {
		@Override
		public int getId() {
			return -1;
		}
	}, 
	
	RONG_YU {
		@Override
		public int getId() {
			return BaseRewards.BaseRewardId.RongYu_110024;
		}
	},
	

	;

	public int getValue() {

		return ordinal();
	}

	public abstract int getId();

	public static PlayerProperty value(String value) {

		for (PlayerProperty e : values()) {

			if (e.toString().equals(value)) {

				return e;
			}
		}

		throw new RuntimeException("无法识别的值" + value);
	}
}
