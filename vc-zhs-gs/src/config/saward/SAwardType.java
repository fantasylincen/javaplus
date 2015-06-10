package config.saward;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统奖励 类型
 * @author DXF
 */
public enum SAwardType {
	
	/** VIP信息 */
	VIP(0),

	/** 每日登陆 奖励水晶 */
	LAG(1),
	
	/** 每日登陆 奖励金币 */
	LAM(2),
	
	/** 每日登陆 奖励体力 */
	LAS(3),
	
	/** 每日转发腾讯或者新浪微博 */
	LAT(4),
	
	/** 连续登录奖励 */
	CI(5),
	
	/** 充值奖励 */
	TU(6),
	
	/** 升级奖励 */
	UG(7),
	
	/** 普通副本通关奖励 */
	AFC(8),
	
	/** 精英副本通关奖励 */
	AFE(9),
	
	/** 好友邀请到达30级 奖励 */
	IM30(10),
	
	/** 好友邀请到达40级 奖励 */
	IM40(11),
	
	/** 好友邀请到达50级 奖励 */
	IM50(12),
	
	/** 好友邀请到达60级 奖励 */
	IM60(13),
	
	/** 好友邀请到达70级 奖励 */
	IM70(14),
	
	/** 好友邀请到达80级 奖励 */
	IM80(15),
	
	/** 好友邀请到达100级 奖励 */
	IM100(16),
	
	/** 每日领取月卡 */
	LAMC(17),
	
	/** 每日领取月卡 */
	LAMC1(18),
	
	/** 每日领取月卡 */
	LAMC2(19),
	
	/**	水晶抽卡一次 */
	STC(20),
	
	/**	友情抽卡一次 */
	YTC(21),

	/**	购买体力 */
	POP(22),
	
	/**	完成10次普通副本*/
	CCE1(23),
	
	/** 完成20次普通副本 */
	CCE2(24),
	
	/**	完成40次普通副本 */
	CCE3(25),
	
	/**	完成80次普通副本 */
	CCE4(26),
	
	/**	完成10次精英副本 */
	CEE1(27),
	
	/**	完成20次精英副本 */
	CEE2(28),
	
	/**	完成40次精英副本 */
	CEE3(29),
	
	/**	完成80次精英副本 */
	CEE4(30),
	
	/**	绑定账号 */
	BDA(31),
	;
	
	
	
	
	
	private final byte number;
	SAwardType(int n) {
		number = (byte) n;
	}
	private static final Map<Byte, SAwardType> intToEnum = new HashMap<Byte, SAwardType>();
	static {
		for (SAwardType a : values()) {
			intToEnum.put(a.number, a);
		}
	}
	public static SAwardType fromNumber(int n) {
		return intToEnum.get((byte)n);
	}
	public byte toNumber() {
		return number;
	}
}
