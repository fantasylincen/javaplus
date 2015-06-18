package cn.vgame.a.account;

public interface IRole {

	String getId();

	String getOwnerId();

	String getNick();

	long getCoin();

	void reduceCoin(long reduce);

	void addCoin(long add);

	boolean hasFengHao();
	boolean hasJinYan();
	
	/**
	 * 开奖CD
	 * 
	 * @return
	 */
	long getCd();

	long getLaBa();

	long getBankCoin();

	void toBank(long coin);

	void addCoinLog(long coin, Object from, String dsc);

	long getJiangQuan();

}