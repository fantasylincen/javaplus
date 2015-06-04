package cn.mxz.prompt;

public interface PromptManager {

	/**
	 * 有新伙伴
	 */
	public static final int	YXHB	= 3;
//	public static final int	SLYXXX	= 2;//私聊有新消息
	public static final int	LMYXXX	= 1;//联盟有新消息
	public static final int	SJYXXX	= 0;//世界有新消息

	/**
	 * 魂魄可升星
	 *
	 * @return
	 */
	boolean getHpksx();

	/**
	 * 魂魄可招募
	 *
	 * @return
	 */
	boolean getHpkzm();

	/**
	 * 神兽可开启
	 *
	 * @return
	 */
	boolean getSsksj();

	/**
	 * 有剩余可挑战次数
	 *
	 * @return
	 */
	boolean getYsyktzcs();

	/**
	 * 竞技奖励未领取
	 *
	 * @return
	 */
	boolean getJjjlwlq();

	/**
	 * 有装备材料、技能残卷、阵法残片可夺宝
	 *
	 * @return
	 */
	boolean getKdb();

	/**
	 * 有魔神可挑战
	 *
	 * @return
	 */
	boolean getYmsktz();

	/**
	 * 有魔神奖励可领取
	 *
	 * @return
	 */
	boolean getYmsjlklq();

	/**
	 * 有夺宝新消息
	 *
	 * @return
	 */
	boolean getYdbxxx();

	/**
	 * 有对战新消息
	 *
	 * @return
	 */
	boolean getYdzxxx();

	/**
	 * 有系统新消息
	 *
	 * @return
	 */
	boolean getYxtxxx();

	/**
	 * 有好友留言
	 *
	 * @return
	 */
	boolean getYhyly();

	/**
	 * 世界有新消息
	 *
	 * @return
	 */
	boolean getSjyxxx();

	/**
	 * 联盟有新消息
	 *
	 * @return
	 */
	boolean getLmyxxx();

	/**
	 * 私聊有新消息
	 *
	 * @return
	 */
	boolean getSlyxxx();

	/**
	 * 有已完成目标可领取奖励(目标可领取)
	 *
	 * @return
	 */
	boolean getMbklq();

	/**
	 * 有已完成成就可领取奖励（对应的章节图标上也要显示标识）
	 *
	 * @return
	 */
	boolean getCjklq();

	/**
	 * 3种招募方式任一方式有免费招募机会
	 *
	 * @return
	 */
	boolean getKzm();

	/**
	 * 招魂活动有开启招募
	 *
	 * @return
	 */
	boolean getZmhd();

	/**
	 * 移除消息提示标记
	 *
	 * @param type PromptManager 内部的常量
	 */
	void removeMessageMark(int type);

	/**
	 * @param type PromptManager 内部的常量
	 */
	void markMessage(int type);

	/**
	 * 背包里面是否有宝箱可以打开
	 *
	 * @return
	 */
	boolean getBbybx();

	/**
	 * 有新伙伴
	 *
	 * @return
	 */
	boolean getYxhb();

	/**
	 * 奇遇
	 * @return
	 */
	boolean getQiYuButtons();

	/**
	 * 开服礼包
	 * @return
	 */
	boolean getKflb();

	/**
	 * 开服礼包按钮是否开启
	 * @return
	 */
	boolean getKflbOpen();

	/**
	 * 客服有新消息
	 * @return
	 */
	boolean getKfyxxx();

	/**
	 * 目标宝箱可领取
	 * @return
	 */
	boolean getMmbxklq();
}
