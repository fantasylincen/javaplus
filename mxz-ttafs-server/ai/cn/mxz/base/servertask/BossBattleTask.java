package cn.mxz.base.servertask;

import cn.mxz.bossbattle.BossBattleActivity;
import cn.mxz.util.debuger.Debuger;


public class BossBattleTask extends TaskSafetyLogToFile {

	private int	type;

	/**
	 * 事件类型
	 * 1、上午活动开始
	 * 2、上午活动结束
	 * 3、下午活动开始
	 * 4、下午活动结束
	 */


	public BossBattleTask(int type) {
		this.type = type;


	}

	@Override
	public void runSafty() {
		Debuger.debug("================================");
		Debuger.debug();
		Debuger.debug( "runSafty " + type );
		Debuger.debug();
		Debuger.debug("================================");

		switch( type ){
		case 1:
			BossBattleActivity.INSTANCE.start();
			break;
		case 2:
			BossBattleActivity.INSTANCE.end(  );
			break;
		case 3:
			BossBattleActivity.INSTANCE.start(  );
			break;
		case 4:
			BossBattleActivity.INSTANCE.end(  );
			break;
		case 5://
			BossBattleActivity.INSTANCE.genBoss();
			break;
		}

	}

}
