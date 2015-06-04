package cn.mxz;

public interface MapTemplet extends DemonParameter {

	/**
	 * Boss物品掉落
	 *
	 * @return
	 */
	String getBossDropOut();

	String getLineMonsterDropOut();

	/**
	 * 支线Boss掉落
	 *
	 * @return
	 */
	String getLineBossDropOut();

	/**
	 * 小怪
	 *
	 * @return
	 */
	String getMonsterDropOut();

	/**
	 * 场景ID
	 */
	int getId();

	/**
	 * 前剧情1 id
	 */
	int getPlotBegin1();

	/**
	 * 前剧情2 id
	 */
	int getPlotBegin2();

	/**
	 * 后剧情1 id
	 */
	int getPlotEnd1();

	/**
	 * 后剧情2 id
	 */
	int getPlotEnd2();

	/**
	 * 是否新手引导
	 */
	int getIsNew();

	/**
	 * 所属章节
	 */
	int getChapterId();

	/**
	 * 所属副本
	 */
	int getSceneId();

	/**
	 * 场景名称
	 */
	String getName();

	/**
	 * 地图宽度
	 */
	int getWidth();

	/**
	 * 地图高度
	 */
	int getHeight();

	/**
	 * 每条路空点所占比例
	 */
	float getIgnoreScale();

	/**
	 * 每条路宝箱数量所占比例
	 */
	float getBoxScale();

	/**
	 * 每条路怪物数量所占比例
	 */
	float getDemonScale();

	/**
	 * 每条路问号事件所占比例
	 */
	float getAskScale();

	/**
	 * boss怪物id
	 */
	String getBossId();

//	/**
//	 * 战斗一次单个神将获得经验
//	 */
//	int getSingleExp();

	/**
	 * 地图资源ID（人物行走的资源）
	 */
	int getPictype();

	/**
	 * 小怪怪物id
	 */
	String getMonsterId();

	public abstract float getLineWilsonParam();

	/**
	 * 小怪怪物数量
	 *
	 * @return
	 */
	int getMonsterNumber();

	float getBossParam();

	float getLineBossParam();

	int getHurtMin();

}