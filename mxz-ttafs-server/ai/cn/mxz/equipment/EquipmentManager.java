package cn.mxz.equipment;

import java.util.List;
import java.util.Map;

import cn.mxz.city.City;
import cn.mxz.protocols.user.equipment.EquipmentP.SnatchResultPro;
import cn.mxz.user.team.god.Hero;

/**
 * 装备管理器
 *
 * @author 林岑
 *
 */
public interface EquipmentManager {



	/**
	 * 仓库里面的所有装备, 以及队伍中所有的装备
	 *
	 * @return
	 */
	Map<Integer, EquipmentImpl> getAll();

	/**
	 * 当前装备的所有装备
	 *
	 * @return
	 */
	Map<Integer, EquipmentImpl> getEquippedAll();

	/**
	 * 更换装备
	 *
	 * @param id1
	 *            装备ID1
	 * @param id2
	 *            装备ID2
	 *
	 */
	List<Equipment> change(int id1, int id2);

	/**
	 * 强化装备 如果levelAdd超限, 自动强化到最高等级
	 *
	 * @param id
	 *            装备ID1
	 * @param levelAdd
	 *            强化等级
	 *
	 */
	void levelUp(int id, int levelAdd);


	/**
	 * 抢夺
	 *
	 * @param userId
	 *            被抢夺者ID
	 *
	 * @param stuffTempletId
	 *            抢夺的材料ID
	 *
	 */
	SnatchResultPro snatch(String userId, int stuffTempletId);

	/**
	 * 免战
	 *
	 *
	 *
	 */
	void protect();

	/**
	 * 拿到某个英雄身上的所有装备
	 *
	 * @param h
	 * @return
	 */
	Map<Integer, EquipmentImpl> getEquipmentAll(Hero h);

	/**
	 * 随机生成抢夺玩家列表
	 *
	 * 对手寻找规则： 筛选拥有材料的玩家 未处于新手保护阶段的玩家
	 *
	 * 3）按以下顺序寻找对应数据的玩家作为挑战对象（以下数值均为参考值
	 * ，此表仅攻表述规则）目的在于在寻找对手时，不给玩家造成不良体验情况下，给玩家制造挑战难度，促使玩家消费欲望
	 * 需要后端对玩家是否付费进行记录，充值金额大于1000元的玩家被记为1级付费玩家
	 * ；充值金额在500元~999元间的玩家被记为2级付费玩家；充值金额在200元
	 * ~499元间的玩家被记为3级付费玩家；充值金额在50元~199元的玩家将被记为4级付费玩家
	 * ；充值金额大于0元~49元的玩家被记为5级付费玩家；免费玩家被记为6级玩家。
	 *
	 * 按以下规则为玩家寻找夺宝对手： 1级付费玩家，战斗力100%~90%之间，同段位无级别要求
	 * 3级付费玩家，战斗力100%~95%之间，同段位无级别要求 2级付费玩家，战斗力100%~95%之间，同段位无级别要求
	 * 4级付费玩家，战斗力100%~95%之间，同段位无级别要求 5级付费玩家，战斗力110%~95%之间，同段位无级别要求
	 * 6级付费玩家，战斗力120%~100%之间，同段位无级别要求
	 *
	 * 3级付费玩家，战斗力105%~90%之间，同段位无级别要求 2级付费玩家，战斗力105%~90%之间，同段位无级别要求
	 * 4级付费玩家，战斗力105%~90%之间，同段位无级别要求 5级付费玩家，战斗力115%~90%之间，同段位无级别要求
	 * 6级付费玩家，战斗力125%~95%之间，同段位无级别要求
	 *
	 * 3级付费玩家，战斗力110%~85%之间，同段位无级别要求 2级付费玩家，战斗力110%~85%之间，同段位无级别要求
	 * 4级付费玩家，战斗力110%~85%之间，同段位无级别要求 5级付费玩家，战斗力120%~85%之间，同段位无级别要求
	 * 6级付费玩家，战斗力130%~90%之间，同段位无级别要求 （依次类推，每次上限+5%，下限-5%）
	 * @param stuffId
	 *
	 * @return
	 */
	List<City> getUsersWhoHas(int stuffId);

	/**
	 * 出售装备
	 *
	 * @param equipmentId 装备ID
	 *
	 */
	Equipment sell(int equipmentId);


	/**
	 * 卸下装备
	 *
	 * @param equipmentId 装备ID
	 *
	 */
	Equipment takeOff(int equipmentId);

	/**
	 * 移除所有夺宝日志
	 */
	void removeAllSnatchLog();

	/**
	 * 随机夺宝，查找一个id
	 * @param userId
	 * @return
	 */
	int snatchRamdomId(String userId);

	/**
	 * 将某个装备添加到战士身上
	 * @param id
	 * @param fighterTypeId
	 */
	Equipment add(int id, int fighterTypeId);

	void createNewEquipment(int... ids);

	void createNewEquipment(List<Integer> all);

	/**
	 * @param srcEquipmentId	作为材料的装备唯一ID
	 * @param templetId	目标装备类型ID
	 */
	Equipment generate2(int srcEquipmentId, int templetId);

	/**
	 * 装备合成
	 * @param stuffId 材料ID
	 * @return
	 */
	Equipment generate3(int stuffId);


}
