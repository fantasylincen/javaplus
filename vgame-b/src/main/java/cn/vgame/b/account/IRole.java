package cn.vgame.b.account;

public interface IRole {

	String getId();

	String getOwnerId();

	String getNick();

	long getCoin();

	void reduceCoin(long reduce);

	void addCoin(long add);

	boolean hasFengHao();
	boolean hasJinYan();
}