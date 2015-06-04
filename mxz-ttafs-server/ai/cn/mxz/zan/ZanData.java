package cn.mxz.zan;

public class ZanData {
	public int getCount() {
		return count;
	}

	public int getTodayGold() {
		return todayGold;
	}

	public int getTomorrowGold() {
		return tomorrowGold;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setTodayGold(int todayGold) {
		this.todayGold = todayGold;
	}

	public void setTomorrowGold(int tomorrowGold) {
		this.tomorrowGold = tomorrowGold;
	}

	/**
	 * 服务器的总的点赞数
	 */
	private int totalCount;
	
	/**
	 * 玩家点赞的总数
	 */
	private int count;
	
	/**
	 * 今日获取的元宝数
	 */
	private int todayGold;
	
	/**
	 * 明日获取的元宝数
	 */
	private int tomorrowGold;
	
	
}
