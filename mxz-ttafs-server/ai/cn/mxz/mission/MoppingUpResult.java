package cn.mxz.mission;

import java.util.List;

import cn.mxz.bossbattle.Prize;

/**
 * 扫荡结果
 * @author 林岑
 *
 */
public class MoppingUpResult {

	private boolean	isPlayerLevelUp;
	private List<Prize>	prize;
	private final boolean isTriggerShenmo;

	public MoppingUpResult(List<Prize> prize, boolean isPlayerLevelUp,boolean isTriggerShenmo) {
		this.prize = prize;
		this.isPlayerLevelUp = isPlayerLevelUp;
		this.isTriggerShenmo  = isTriggerShenmo;
	}

	public List<Prize> getPrizes() {
		return prize;
	}

	public boolean isPlayerLevelUp() {
		return isPlayerLevelUp;
	}

	/**
	 * @return isTriggerShenmo
	 */
	public boolean isTriggerShenmo() {
		return isTriggerShenmo;
	}

	
	
	


}
