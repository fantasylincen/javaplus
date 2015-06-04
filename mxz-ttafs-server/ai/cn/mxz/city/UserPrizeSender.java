package cn.mxz.city;

import java.util.List;

import cn.mxz.bossbattle.Prize;

//PrizeSenderFactory.getPrizeSender1()
public interface UserPrizeSender {

	/**
	 * 发送奖励
	 * @param prize
	 */
	void send(String prize);

	/**
	 * 构建奖励
	 * @param prize
	 * @return
	 */
	List<Prize> buildPrizes(String prize);

}
