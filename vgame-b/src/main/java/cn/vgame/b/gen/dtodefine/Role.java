package cn.vgame.b.gen.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Index;
import cn.javaplus.db.mongo.Key;
import cn.vgame.b.gen.dto.MongoGen.MongoMap;

@Dao
interface Role {

	@Key
	String getId();

	/**
	 * 这个角色所属帐号
	 * 
	 * @return
	 */
	@Index
	String getOwnerId();

	/**
	 * 是否是机器人, 如果是机器人, 则系统自动帮忙玩, 且玩家不可登陆
	 */
	boolean getIsRobot();
    /**
     * 人物角色的名称
     * @return
     */
	String getNick();
	
     /**
      * 角色的头像
      * @return
      */
	
	int getHead();
	
	/**
	 * 角色金币
	 * @return
	 */
	long getCoin();

   

	long getCreateTime();

	/**
	 * @return 角色砖石
	 */
	long getMasonry();
	
	/**
	 * 累计充值量
	 * @return
	 */
	long getRechargeHistory();

	/**
	 * 是否禁言
	 */
	boolean getHasJinYan();
	
	/**
	 * 是否在线
	 */
	boolean getIsOnline();

	/**
	 * 是否封号
	 */
	boolean getHasFengHao();
	
	
	/**
	 * 最大关卡数
	 */
	int getMaxMissionId();
	
	/**
	 * 角色的体力
	 */
	int getPhysical();
	
	/**
	 * 角色的体力倒计时
	 */
	int getPhysicalCd();
	
	/**
	 * 键： 关卡ID
	 * 值: 关卡数据
	 */
	MongoMap<MissionData> getMissionData();

	MongoMap<String> getKeyValueDaily();

	MongoMap<String> getKeyValueForever();
}
