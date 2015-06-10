package config.skill.accord;

/**
 * 确定被攻击的人
 * @author DXF
 * 2013-6-18 下午3:55:44
 */
public enum ChooseFighters {
	
	/** 正常攻击 */
	NORMAL_ATTACK {
		@Override
		public int getCount() {
			return 1;
		}
	},
	
	/** 对自己 */
	SELF {
		@Override
		public int getCount() {
			return 1;
		}
	},
	
	/** 对行 */
	ROW {
		@Override
		public int getCount() {
			return 3;
		}
	},
	
	/** 对列 */
	COL {
		@Override
		public int getCount() {
			return 2;
		}
	},
	
	/** 对血量最少的一个 */
	MIN_HP {
		@Override
		public int getCount() {
			return 1;
		}
	}, 
	
	/** 对血量最少的两个 */
	MIN_HP2 {
		@Override
		public int getCount() {
			return 2;
		}
	}, 
	
	/** 对血量最少的三个 */
	MIN_HP3 {
		@Override
		public int getCount() {
			return 3;
		}
	}, 
	
	/** 对敌方所有人 */
	ALL {
		@Override
		public int getCount() {
			return 4;
		}
	},
	
	/**  随机一个 */
	RANDOM_1 {
		@Override
		public int getCount() {
			return 1;
		}
	},
	
	/**  随机两个 */
	RANDOM_2 {
		@Override
		public int getCount() {
			return 2;
		}
	},
	
	/**  随机三个 */
	RANDOM_3 {
		@Override
		public int getCount() {
			return 3;
		}
	},
	
	/** 对敌方前排 */
	FRONTROW {
		@Override
		public int getCount() {
			return 3;
		}
	},
	
	/** 对敌方后排 */
	BACKROW {
		@Override
		public int getCount() {
			return 3;
		}
	},
	
	/** 对下一个出招英雄 */
	NEXT {
		@Override
		public int getCount() {
			return 1;
		}
	};

	/**
	 * 返回每个能针对几个人
	 * @return
	 */
	abstract public int getCount() ;
}
