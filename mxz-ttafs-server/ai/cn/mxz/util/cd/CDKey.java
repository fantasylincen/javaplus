package cn.mxz.util.cd;

import cn.javaplus.time.Time;
import cn.javaplus.util.Util;
import cn.mxz.GodTypeTempletConfig;
import define.D;

public enum CDKey {

	// 亲密示爱冷却时间 (在数据库中的索引, 每次操作增加的秒, CD冻结时间)

	QMSA(D.QMSA_CD),

	CORONA(600 * 1000),

	/**
	 * 寻仙1冷却时间
	 */
	RECRUIT1((int)(getR(1) * Time.MILES_ONE_MIN)),
	/**
	 * 寻仙2冷却时间
	 */
	RECRUIT2((int)(getR(2) * Time.MILES_ONE_MIN)),
	/**
	 * 寻仙3冷却时间
	 */
	RECRUIT3((int)(getR(3) * Time.MILES_ONE_MIN)),
	
	

	DAJI0(300*1000),
	DAJI1(600*1000),
	DAJI2(900*1000),
	DAJI3(1200*1000),
	DAJI4(1500*1000),
	DAJI5(1800*1000),
	DAJI6(2100*1000),
	DAJI7(2400*1000),
	DAJI8(2700*1000),
	DAJI9(3000*1000),
	DAJI10(3300*1000),
	DAJI11(3600*1000),
	DAJI12(3900*1000),
	DAJI13(4200*1000),
	DAJI14(4500*1000),
	DAJI15(4800*1000),
	DAJI16(5100*1000),
	DAJI17(5400*1000),
	DAJI18(5700*1000),
	DAJI19(6000*1000),
	DAJI20(6300*1000),
	DAJI21(6600*1000),
	DAJI22(6900*1000),
	DAJI23(7200*1000),
	DAJI24(7500*1000),
	DAJI25(7800*1000),
	DAJI26(8100*1000),
	DAJI27(8400*1000),
	DAJI28(8700*1000),
	DAJI29(9000*1000),
	DAJI30(9300*1000),
	DAJI31(9600*1000),
	DAJI32(9900*1000),
	DAJI33(10200*1000),
	DAJI34(10500*1000),
	DAJI35(10800*1000),
	DAJI36(11100*1000),
	DAJI37(11400*1000),
	DAJI38(11700*1000),
	DAJI39(12000*1000),
	DAJI40(12300*1000),
	DAJI41(12600*1000),
	DAJI42(12900*1000),
	DAJI43(13200*1000),
	DAJI44(13500*1000),
	DAJI45(13800*1000),
	DAJI46(14100*1000),
	DAJI47(14400*1000),
	DAJI48(14700*1000),
	DAJI49(15000*1000),
	DAJI50(15300*1000),
	DAJI51(15600*1000),
	DAJI52(15900*1000),
	DAJI53(16200*1000),
	DAJI54(16500*1000),
	DAJI55(16800*1000),
	DAJI56(17100*1000),
	DAJI57(17400*1000),
	DAJI58(17700*1000),
	DAJI59(18000*1000),
	
	DAJI_VIP(30 *60 *1000),


	
	MOPPING_UP((int)(5 * Util.Time.MILES_ONE_MIN), (int)(600 * Util.Time.MILES_ONE_MIN)), 
	PVP_CHALLENGE(D.PVP_CHALLENGE_CD * 1000);
	;

	private int	addEvery;

	private int	freezingTime;

	/**
	 *
	 * @param addEvery		每次操作增加的冷却时间
	 * @param freezingTime	总冷却时间
	 */

	CDKey (int addEvery, int freezingTime) {

		this.addEvery = addEvery;

		this.freezingTime = freezingTime;

	}

	/**
	 * @param time	冷却时间  毫秒
	 */
	CDKey (int time) {

		this.addEvery = time;

		this.freezingTime = time;

	}

	private static int getR(int rType) {
		return GodTypeTempletConfig.get(rType).getTimes();
	}

	public int getId() {

		return ordinal();
	}

	public int getAddEvery() {

		return addEvery;
	}

	public int getFreezingTime() {

		return freezingTime;
	}
}
