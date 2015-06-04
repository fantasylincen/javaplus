package cn.mxz.newpvp;

import cn.javaplus.math.Fraction;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.user.builder.PlayerBase;

public interface PvpFightUser {

	int getDanId();

	int getPower();

	Fraction getPractice();

	/**
	 * 上一次晋级的战况信息ID
	 * @return
	 */
	int getWarsituationId();

	int getRankInAll();

	PlayerCamp getCamp();

	PlayerBase getPlayer();

	/**
	 * 主角战士 品阶
	 * @return
	 */
	int getStep();

	/**
	 * 胜利次数
	 * @return
	 */
	int getWinTimes();

	/**
	 * 失败次数
	 * @return
	 */
	int getLoseTimes();

	boolean isRobot();
}
