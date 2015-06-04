package cn.vgame.a.turntable;

public interface IProfit extends Comparable<IProfit>{

	public abstract long getReduce();

	public abstract long getAdd();

	public abstract long getCaiJin();

	public abstract String getRoleId();

	public abstract String getNick();

	public int compareTo(IProfit o);
}