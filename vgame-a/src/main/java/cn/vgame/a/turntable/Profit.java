package cn.vgame.a.turntable;

public class Profit {

	private long coin;
	private String roleId;
	private String nick;

	public Profit(IProfit o) {
		long add = o.getAdd();
		long reduce = o.getReduce();
		long caiJin = o.getCaiJin();

		coin = add + caiJin;
		roleId = o.getRoleId();
		nick = o.getNick();
	}

	public long getCoin() {
		return coin;
	}

	public String getRoleId() {
		return roleId;
	}

	public String getNick() {
		return nick;
	}

	@Override
	public String toString() {
		return getNick() + " add:" + getCoin();
	}
}
