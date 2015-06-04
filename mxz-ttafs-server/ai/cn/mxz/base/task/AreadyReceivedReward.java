package cn.mxz.base.task;

/**
 * 已经领取过了的任务奖励
 * @author 林岑
 *
 */
public class AreadyReceivedReward extends TaskException {

	private int	id;

	AreadyReceivedReward(int id) {
		super("id = " + id);
		this.id = id;
	}

	private static final long	serialVersionUID	= 7923400397350541644L;

	public int getId() {
		return id;
	}
}
