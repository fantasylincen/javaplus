package cn.mxz.bossbattle;


/**
 * 点击鼓舞等按钮后，刷新ui2的一些相关内容，仅仅是一部分，前端不能采用全部刷新的办法
 * @author Administrator
 *
 */
public interface ISubBossUI2 {
	
	/**
	 * 下一次鼓舞士气所需要的元宝
	 * @return
	 */
	int getInspireGold();
	
	/**
	 * 极限鼓舞士气所需要的元宝
	 * @return
	 */
	int getMaxInspireGold();
	
	/**
	 * 本次战斗已经使用的元宝数
	 * @return
	 */
	int getCurUseInspireGold();
	
	
	/**
	 * 已经使用的元宝
	 * @return
	 */
	int getUseGold();
	
	/**
	 * 属性加成
	 * @return
	 */
	float getAddtionPercent();
	
	
	/**
	 * 是否使用过浴火重生
	 * @return
	 */
	boolean isRibirthInTurn();
	
	/**
	 * 获取使用元宝换取的培养丹
	 * @return
	 */
	int getPeiyangdanFromGold();
	
	/**
	 * 获取战斗的冷却时间
	 * @return
	 */
	int getBattleCodeDown();

	int getRibirthGold();
	
	int getGold();
	
	
}
