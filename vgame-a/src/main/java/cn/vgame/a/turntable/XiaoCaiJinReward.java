package cn.vgame.a.turntable;

/**
 * 小彩金奖励
 * 
 * @author Administrator
 * 
 */
public class XiaoCaiJinReward {
	private String roleId;
	private long countThisTime;
	private double percent;
	private long receive;

	/**
	 * 这次压了多少注
	 * 
	 * @return
	 */
	public long getCountThisTime() {
		return countThisTime;
	}

	public void setCountThisTime(long countThisTime) {
		this.countThisTime = countThisTime;
	}

	/**
	 * 角色ID
	 * 
	 * @return
	 */
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	/**
	 * 获得彩金比例
	 * 
	 * @return
	 */
	public double getPercent() {
		return percent;
	}

	public void setReceive(long receive) {
		this.receive = receive;

	}

	/**
	 * 最终获得了好多彩金
	 * 
	 * @return
	 */
	public long getReceive() {
		return receive;
	}

}