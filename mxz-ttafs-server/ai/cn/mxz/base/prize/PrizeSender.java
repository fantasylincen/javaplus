package cn.mxz.base.prize;

import java.util.List;

import cn.mxz.bossbattle.Prize;
import cn.mxz.user.Player;

/**
 *
 * 奖励发放器
 *
 * @author 林岑
 *
 */
public interface PrizeSender {

	/**
	 * 构造奖励列表
	 *
	 * @param prize
	 * @return
	 */
	List<Prize> buildPrizes(Player player, String prize);

	/**
	 * 发送奖励
	 *
	 * @param player
	 * @param prize
	 * @return
	 */
	List<Prize> send(Player player, String prize);

}
