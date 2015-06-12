package cn.vgame.a.gm.test;

import cn.vgame.a.account.IRole;
import cn.vgame.a.turntable.Turntable;

public class TestRole implements IRole {

	private static final String TEST_ROLE = "XXX";
	private long coin;

	public void setCoin(long coin) {
		this.coin = coin;
	}

	@Override
	public String getId() {
		return TEST_ROLE;
	}

	@Override
	public String getOwnerId() {
		return TEST_ROLE;
	}

	@Override
	public String getNick() {
		return TEST_ROLE;
	}

	@Override
	public long getCoin() {
		return coin;
	}

	@Override
	public void reduceCoin(long reduce) {
		coin -= reduce;
	}

	@Override
	public void addCoin(long add) {
		coin += add;
	}

	@Override
	public long getCd() {
		return Turntable.getInstance().getCd();
	}

	@Override
	public boolean hasFengHao() {
		return false;
	}

	@Override
	public boolean hasJinYan() {
		return false;
	}

	@Override
	public long getLaBa() {
		return 0;
	}

	@Override
	public long getBankCoin() {
		return 0;
	}

	@Override
	public void toBank(long coin) {

	}

	@Override
	public void addCoinLog(long coin, Object from, String dsc) {
		
	}

}