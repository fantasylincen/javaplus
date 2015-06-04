package cn.vgame.a.gen.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Index;
import cn.javaplus.db.mongo.Key;
import cn.vgame.a.gen.dto.MongoGen.MongoMap;

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

	String getNick();

	long getCoin();

	/**
	 * 银行密码
	 */
	String getBankPassword();

	long getCreateTime();

	/**
	 * @return 银行中的金豆
	 */
	long getBankCoin();
	
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
	
	MongoMap<String> getKeyValueDaily();

	MongoMap<String> getKeyValueForever();
}
