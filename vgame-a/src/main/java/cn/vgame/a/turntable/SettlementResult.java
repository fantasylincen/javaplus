package cn.vgame.a.turntable;

public class SettlementResult implements IProfit {
	long reduce;
	long add;

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.vgame.a.turntable.IProfit#getReduce()
	 */
	@Override
	public long getReduce() {
		return reduce;
	}

	public void setReduce(long reduce) {
		this.reduce = reduce;
	}

	@Override
	public String toString() {
		return getNick() + " add:" + (getAdd() - getReduce() + getCaiJin());
	}

	@Override
	public long getAdd() {
		return add;
	}

	public void setAdd(long add) {
		this.add = add;
	}

	private long caiJin;
	private String caiJinRoleId;
	private String roleId;
	private String nick;
	private long xiaoCaiJinAdd;

	/**
	 * 是否结算了彩金
	 * 
	 * @return
	 */
	public boolean isSettlementCaiJin() {
		return caiJin > 0;
	}

	public void setSettlementCaiJin(long caiJin) {
		this.caiJin = caiJin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.vgame.a.turntable.IProfit#getCaiJin()
	 */
	@Override
	public long getCaiJin() {
		return caiJin;
	}

	public String getCaiJinRoleId() {
		return caiJinRoleId;
	}

	public void setCaiJinRoleId(String caiJinRoleId) {
		this.caiJinRoleId = caiJinRoleId;
	}

	@Override
	public int compareTo(IProfit o) {
		return getCompare(o) - getCompare(this);
	}

	private int getCompare(IProfit o) {
		long add = o.getAdd();
		long reduce = o.getReduce();
		long caiJin = o.getCaiJin();

		long l = add + caiJin /*- reduce*/;
		return (int) l;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@Override
	public String getRoleId() {
		return roleId;
	}

	@Override
	public String getNick() {
		return nick;
	}

	public void setXiaoCaiJinAdd(long xiaoCaiJinAdd) {
		this.xiaoCaiJinAdd = xiaoCaiJinAdd;
		
	}
	
	public long getXiaoCaiJinAdd() {
		return xiaoCaiJinAdd;
	}

}