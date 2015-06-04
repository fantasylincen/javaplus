package cn.mxz.battle;


/**
 * 战场制造器
 * @author 	林岑
 * @since	2013年6月6日 17:34:49
 *
 */

public class BattleFactory {

	BattleFactory() {
	}

	/**
	 * 战斗结果
	 * @param key 
	 * @param result
	 * @return
	 */
	public static WarResult createWarResult(final String result) {
		
		
		return new WarResult() {
			
			@Override
			public boolean isWin() {
				return true;
			}
			
			@Override
			public boolean isLose() {
				return false;
			}
		};
	}

}
