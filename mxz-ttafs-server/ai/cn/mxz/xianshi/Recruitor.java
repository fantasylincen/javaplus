package cn.mxz.xianshi;



public interface Recruitor {

	/**
	 * @param discount 折扣   0-1
	 * @return
	 */
	RecruitResault recruit(double discount, boolean noJia);

	int getRemainingSec();

	int getFreeTimes();

	/**
	 * 历史招募次数
	 * @return
	 */
	int getTimes();

	void check(double discount, int times);

	/**
	 * 10次万里寻仙所需寻仙令
	 * @param disCount
	 * @return
	 */
	int getNeed(double disCount);
}
