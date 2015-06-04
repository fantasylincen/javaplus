package cn.mxz.xianshi;

public class AddHeroResult {

	private boolean hasCreateNewHero;
	private int spiriteCount;

	public AddHeroResult(boolean hasCreateNewHero, int spiriteCount) {
		this.hasCreateNewHero = hasCreateNewHero;
		this.spiriteCount = spiriteCount;
	}

	public boolean hasCreateNewHero() {
		return hasCreateNewHero;
	}

	public int getSpiriteCount() {
		return spiriteCount;
	}

}
