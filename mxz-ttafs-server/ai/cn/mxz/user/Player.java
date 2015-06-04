package cn.mxz.user;
import cn.javaplus.math.Fraction;
import cn.javaplus.serchengine.SearchAble;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.user.builder.PlayerBase;

public interface Player extends SearchAble, PlayerBase {


	/**
	 * 经验
	 * @return
	 */
	Fraction getExp();

	/**
	 * 英雄信息
	 * @return
	 */
	Fraction getHeroInfo();

	/**
	 * 体力
	 * @return
	 */
	Fraction getPhysical();

	/**
	 * 精力
	 * @return
	 */
	Fraction getPower();

	/**
	 * 增加经验
	 * @param count
	 */
	void addExp(int count);

	/**
	 * 获得玩家某个属性的值
	 *
	 * @param property	来自#PlayerProperty
	 * @return
	 */
	int get(PlayerProperty property);

	/**
	 * 增加玩家某个属性的值
	 * @param playerProperty	来自#PlayerProperty
	 * @param add
	 */
	void add(PlayerProperty playerProperty, int add);

	/**
	 * 扣除玩家某个属性的值
	 * @param playerProperty	来自#PlayerProperty
	 * @param reduce
	 */
	void reduce(PlayerProperty playerProperty, int reduce);

	/**
	 * 设置玩家某项属性值
	 * @param p
	 * @param v
	 */
	void set(PlayerProperty p, int v);

	/**
	 * 最后登陆时间, 距现在的秒
	 * @return
	 */
	int getLastLoginSec();

	/**
	 * 重置帐号昵称
	 * @param newNick
	 */
	void setNick(String newNick);

	/**
	 * 设置邀请码
	 * @param code
	 */
	void setInvitationCode(String code);

	/**
	 * 邀请码
	 * @return
	 */
	String getInvitationCode();

	/**
	 * 判断是否是第三方平台的玩家
	 * @return
	 */
	boolean isThirdPartyPlayer();

	/**
	 * 保存第三方平台的ID
	 * @param userId
	 */
	void saveThirdPartyId(String userId);

	/**
	 * 保存玩家客户端类型
	 * @param clientType
	 */
	void saveClientType(int clientType);

	/**
	 * 第三方平台的帐号
	 *    不是角色ID, 不是 Player.getId()
	 * @return
	 */
	String getThirdPartyId();

	/**
	 * 扣元宝, 先扣系统赠送的元宝, 再扣充值的元宝
	 * @param value
	 */
	void reduceGold(int value);

	/**
	 * 扣除金锭或者元宝. 优先扣除金锭
	 * @param value
	 */
	void reduceGoldOrJinDing(int value);

	/**
	 * 金锭 + 元宝总数
	 * @return
	 */
	int getGoldAndJinDing();

	/**
	 * 系统赠送的元宝 + 充值元宝
	 * @return
	 */
	int getGold();

	/**
	 * 增加点券
	 * @param count
	 */
	void addGiftGold(int count);

	/**
	 * 充值而来的元宝, 只能有充值这一个地方调用这个方法, 其他地方不要调用!
	 *
	 * @param add
	 */
	void addGold(int add);

	/**
	 * 最大体力值
	 * @return
	 */
	int getPhysicalMax();

	/**
	 * 精力最大值
	 * @return
	 */
	int getPowerMax();

	City getCity();


	void updateGoldFromThirdParty();


	/**
	 * 玩家客户端类型
	 * 客户端类型:1 ios;2 android;
	 * @return
	 */
	int getClientType();

	int getGiftGold();

	int getRechargeGold();

}
