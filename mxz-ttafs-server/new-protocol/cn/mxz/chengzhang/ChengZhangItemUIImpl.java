package cn.mxz.chengzhang;

public class ChengZhangItemUIImpl implements ChengZhangItemUI {

	private ChengZhangBox c;

	public ChengZhangItemUIImpl(ChengZhangBox c) {
		this.c = c;
	}

	@Override
	public boolean getHasReceive() {
		return c.hasReceive();
	}

	@Override
	public boolean getCanReceive() {
		return c.canReceive();
	}

	@Override
	public int getLevel() {
		return c.getLevel();
	}

	@Override
	public int getId() {
		return c.getId();
	}

	@Override
	public int getGold() {
		return c.getGold();
	}

}
