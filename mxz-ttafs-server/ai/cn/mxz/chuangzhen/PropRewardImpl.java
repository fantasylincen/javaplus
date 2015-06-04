package cn.mxz.chuangzhen;



public class PropRewardImpl implements PropReward {

	private int	type;
	private int	count;

	public PropRewardImpl(int type, int count) {
		this.type = type;
		this.count = count;
	}

	@Override
	public int getId() {
		return type;
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" [type=");
		builder.append(type);
		builder.append(", count=");
		builder.append(count);
		builder.append("]");
		return builder.toString();
	}

}
