//package cn.mxz.mission.old;
//
//import java.util.List;
//
//import cn.mxz.mission.old.demon.MapDemonImpl;
//import cn.mxz.user.mission.MissionMark;
//
///**
// * 副本地图
// * @author 彭超
// *
// */
//public interface MissionMap{
//
//	/**
//	 * 终点坐标
//	 */
//	MapNode getEnd();
//
//	/**
//	 * 地图ID
//	 * @return
//	 */
//	int getId();
//
//	/**
//	 * 起点坐标
//	 */
//	MapNode getStart();
//
//	/**
//	 * 所有的大石头坐标
//	 */
//	List<MapNode> getBigStones();
//
//	/**
//	 * 所有的小石头坐标
//	 */
//	List<MapNode> getSmallStones();
//
//	/**
//	 * 所有的怪物
//	 */
////	List<MapDemonImpl> getBoss();
//
//	List<MapDemonImpl> getStaticDemons();
//
//	List<MapBoxImpl> getStaticBoxes();
//
//	List<MapRandomEventImpl> getStaticEvents();
//
//	List<MapMoneyImpl> getStaticMapMoney();
//
//	List<MapDemonImpl> getStaticGod();
//
//	List<MapStoryImpl> getStaticStory();
//
//
//	/**
//	 * 所有的怪物
//	 */
//	List<MapDemonImpl> getDynamicDemons();
//
//	/**
//	 * 所有的宝箱
//	 */
//	List<MapBoxImpl> getDynamicBoxes();
//
//	List<MapDemonImpl> getDynamicGod();
//
//	List<MapMoneyImpl> getDynamicMapMoney();
//
//	/**
//	 * 随机事件
//	 */
//	List<MapRandomEventImpl> getDynamicEvents();
//
//	/**
//	 * 当前地图完成度
//	 */
//	float getCompleteness();
//
//	/**
//	 * 人
//	 */
//	Person getPerson();
//
//	/**
//	 * 碰到的宝箱
//	 * @return
//	 */
//	MapBox getBoxTouched();
//
//	/**
//	 * 碰到的问号
//	 * @return
//	 */
//	MapRandomEvent getRandomTouched();
//
//	/**
//	 * 碰到的钱袋
//	 * @return
//	 */
//	MapMoneyImpl getMapMoneyTouched();
//
//	/**
//	 * 获得指定编号的路段
//	 * @param pathDst
//	 * @return
//	 */
//	MapPath getPath(int path);
//
//	/**
//	 * 人物所占的地图节点
//	 * @return
//	 */
//	MapNode getPersonNode();
//
//	/**
//	 * 移除某个物体
//	 */
//	void remove(LocationAble l);
//
//	/**
//	 * 地图是否被Person给走完了
//	 * @return
//	 */
//	boolean isEnd();
//
//	/**
//	 * 副本通关标记
//	 * @return
//	 */
//	MissionMark getMark();
//
//	/**
//	 * 获得指定位置的怪物
//	 * @param path	怪物所在路段
//	 * @param index	怪物所在路段索引位置
//	 * @return
//	 */
//	MapDemon getDemon(int path, int index);
//
//	List<MapDemonImpl> getDemons();
//
//	List<MapBoxImpl> getBoxes();
//
//	List<MapRandomEventImpl> getEvents();
//
//	List<MapDemonImpl> getGod();
//
//	List<MapMoneyImpl> getMapMoney();
//}
