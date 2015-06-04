package cn.mxz.daji;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import message.S;

import org.joda.time.DateTime;

import cn.javaplus.random.WeightFetcher;
import cn.javaplus.time.colddown.ColdDown;
import cn.javaplus.util.Util;
import cn.javaplus.util.Util.Random;
import cn.mxz.CustodianGuardLibraryTemplet;
import cn.mxz.CustodianGuardLibraryTempletConfig;
import cn.mxz.CustodianLibraryTemplet;
import cn.mxz.CustodianLibraryTempletConfig;
import cn.mxz.CustodianMapTemplet;
import cn.mxz.CustodianMapTempletConfig;
import cn.mxz.VipPrivilegeTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.battle.Battle;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.city.FunctionOpenManager.ModuleType;
import cn.mxz.coutinuous.ContinuousManager;
import cn.mxz.coutinuous.ContinuousType;
import cn.mxz.mission.old.PrizeImpl;
import cn.mxz.util.cd.CDKey;
import cn.mxz.util.cd.CDManager;
import cn.mxz.util.counter.CounterKey;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import define.D;

public class DajiUserData {
	
	class ColdTimeTool{
		public ColdTimeTool() {
//			user.getUserCounter().set(CounterKey.DAJI_COLD_DOWN, 0 );
		}
		/**
		 * 冷却时间是否已经到了
		 * true		处于冰冻状态，冷却时间未到
		 * false	冷却时间已经到了
		 * @return
		 */
		boolean isFreezing(){
			return getRemainSencond() > 0;
		}
		
		int getRemainSencond(){
			int remainSecond = (int) (getEndSecond() - System.currentTimeMillis() / 1000);
			
			return Math.max( 0, remainSecond );
		}
		
		int getRemainingMin(){
			return getRemainSencond() / 60;
		}
		
		int getEndSecond(){
			return user.getUserCounter().get(CounterKey.DAJI_COLD_DOWN );
		}
		/**
		 * 
		 * @param coldMSecond 冷却的豪秒数
		 */
		void setEndSecond( long coldMSecond ){
			int endSecond = (int) ((System.currentTimeMillis() + coldMSecond) / 1000);
			user.getUserCounter().set(CounterKey.DAJI_COLD_DOWN, endSecond );
		}
	}

//	private static final int	PROTECT_COUNT2	= 8;
//	private static final int	PROTECT_COUNT1	= 3;
	private City				user;
	// private ColdDownTime cd;
	private ColdDown			cd;
	private ContinuousManager	C;
	private CDManager			cdManager;
	
	private ColdTimeTool		coldTool;
	/**
	 * 保存驱赶成功的奖励
	 */
	private List<Prize>			prizes			= new ArrayList<Prize>();

	DajiUserData(City user) {
		super();
		this.user = user;
		C = user.getContinuousManager();
		cdManager = user.getCDManager();
		coldTool = new ColdTimeTool();
//		setColdDownTime();
	}

	/**
	 * 获取玩家今日是否保护过了
	 *
	 * @return
	 */
	boolean isProtectToday() {
		//
		return user.getUserCounter().get(CounterKey.DAJI_TODAY_PROTECT_COUNT) == 1;
	}

	private void setProtectToday() {
		user.getUserCounter().set(CounterKey.DAJI_TODAY_PROTECT_COUNT, 1);
	}

	/**
	 * 获取当前的保护阶段
	 *
	 * @return
	 */
	int getProtectPhase() {
		int phase = user.getUserCounter().get(CounterKey.DAJI_PROTECT_PHASE);
		if (phase == 0) {
			phase = 1;
		}
		return phase;
	}

	/**
	 * 设置当前的保护阶段
	 *
	 * @return
	 */
	int setProtectPhase(int phase) {
		return user.getUserCounter().get(CounterKey.DAJI_PROTECT_PHASE, phase);
	}

	/**
	 * 获取玩家连续的保护次数,数据在0-8之间循环,超过8之后按如下规则
	 *
	 * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 . . . . . . . . . . . . . . . .
	 *
	 * 8=>8 9=>4 10=>5 11=>6 12=>7 13=>8 14=>4 15=>5 16=>6 n=>n - 4 % 5 + 4
	 *
	 *
	 * @return 0：从没登陆过
	 */
	int getAllProtectCount() {

		int count = C.getContinuous(ContinuousType.DAJI);// 连续登陆天数
		int space = C.getSpaceDays(ContinuousType.DAJI);// 中断天数

		if (space > 0) {// 有中断的情况，此时不用考虑连续，中断和连续式互斥的

			if (space > D.DAJI_PROTECT_COUNT2) {// 中断天数大于8，则从头开始计数
				setProtectPhase(1);// 自动进入第1阶段
				return 0;
			} else {// 小于8天的情况
				if (getProtectPhase() == 1) {
					count = 0;
				} else {
					count = D.DAJI_PROTECT_COUNT1;// 从第二阶段的第0天开始计时
				}
			}
			return count;
		}

		if (count > D.DAJI_PROTECT_COUNT2) {// 连续次数大于8天,从4,5,6,7,8开始重复，详细见注释
			count -= 4;
			count %= 5;
			count += 4;
			return count;
		}

		return count;

	}

	// private void setAllProtectCount( int count ){
	// user.getUserCounterHistory().set( CounterKey.DAJI_ALL_PROTECT_COUNT,
	// count );
	// }

	List<Prize> getPrizes() {
		return prizes;
	}

	void setPrizes(List<Prize> prizes) {
		this.prizes = prizes;
	}

	int protect() {

		// UserCounter his = user.getUserCounter();
		// his.add(CounterKey.PROTECT_DAJI_TIMES, 1);

		if (isProtectToday()) {
			System.err.println(user.getId() + "今日已经保护过了");
			return -1;
		}

		setProtectToday();
		C.doIt(ContinuousType.DAJI);
		// recordProtectTime();
		//getProtectPrize();
		return getAllProtectCount();
	}

	//
	// /**
	// * 记录上次的保护时间
	// */
	// private void recordProtectTime() {
	// int now = (int) (System.currentTimeMillis() / 1000);
	// user.getUserCounterHistory().set( CounterKey.DAJI_LAST_PROTECT_TIME, now
	// );
	//
	// }

	/**
	 * 驱赶
	 *
	 * @param user
	 * @return
	 */
	Battle drift() {

		if (!isProtectToday()) {
			throw new OperationFaildException(S.S10216);
		}
		if (coldTool.isFreezing()) {
			throw new OperationFaildException(S.S10177);
		}

		int todayBattleCount = getTodayDriftCount();
		CustodianMapTemplet temp = CustodianMapTempletConfig.getAll().get(todayBattleCount);

		int monsterCount = user.getFormation().getMaxCount();
		Battle b = new ProtectDaJiBattle(user.getFormation().getSelected(), temp, monsterCount, user );
		b.fighting();
		if (b.isWin()) {
			calcDriftPrize();

			this.user.getUserCounter().add(CounterKey.PROTECT_DAJI_TIMES, 1);
			//System.err.println( "保护妲己1次");

			// data.addCurrentMission();
			// ColdDownTime cd = new ColdDownTime();
			// data.setCd( cd );
		}

		return b;
	}

	/**
	 * 今日已经战斗次数
	 *
	 * @return
	 */
	public int getTodayDriftCount() {
		int count = user.getUserCounter().get(CounterKey.DAJI_TODAY_BATTLE_COUNT);
		// System.out.println( "今日战斗次数" + count );
		return count;

	}

	/**
	 * 增加今日已经战斗次数
	 *
	 * @return
	 */
	private void addTodayBattleCount() {
		user.getUserCounter().add(CounterKey.DAJI_TODAY_BATTLE_COUNT, 1);
		// int count = user.getUserCounter().get(
		// CounterKey.DAJI_TODAY_BATTLE_COUNT);
		// System.out.println( "今日战斗次数" + count );
	}

	/**
	 * 计算奖励情况,请策划确保数值正确，不会抛出异常
	 *
	 * @param user
	 * @param currentMission
	 * @return
	 */
	private void calcDriftPrize() {

		// List<Prize> all = new ArrayList<Prize>();
		int todayBattleCount = getTodayDriftCount();
		CustodianMapTemplet ct = CustodianMapTempletConfig.getAll().get(todayBattleCount);
		String[] s = ct.getDriveAward().split(",");

		int propId = Integer.parseInt(s[0]);
		int count = Integer.parseInt(s[1]);
		Prize p = new PrizeImpl(propId, count);
		prizes.add(p);

		// int index = Random.getRandomIndex(
		// CustodianLibraryTempletConfig.getArrayByWeight() );
		// CustodianLibraryTemplet t = CustodianLibraryTempletConfig.get(
		// CustodianLibraryTempletConfig.getKeys().get(index) );

		WeightFetcher<CustodianLibraryTemplet> weightAble = new WeightFetcher<CustodianLibraryTemplet>() {

			@Override
			public Integer get(CustodianLibraryTemplet t) {
				return t.getWeight();
			}
		};
		
		List<CustodianLibraryTemplet> list = Lists.newArrayList();//过滤玩家等级以下的奖励
		for( CustodianLibraryTemplet t : CustodianLibraryTempletConfig.getAll() ){
			if( user.getFunctionOpenManager().isOpen( ModuleType.getType(t.getModulesId() ) ) ){
				list.add(t);
			}
		}
		
		CustodianLibraryTemplet t = Util.Random.getRandomOneByWeight(list, weightAble);

		s = t.getAwards().split(":");

		propId = Integer.parseInt(s[0]);
		s = s[1].split(",");
		int min = Integer.parseInt(s[0]);
		int max = Integer.parseInt(s[1]);

		count = Random.get(min, max);
		p = new PrizeImpl(propId, count);
		prizes.add(p);

	}

	public int getProtectPrize() {

		int count = getAllProtectCount();

//		if (count == D.DAJI_PROTECT_COUNT1) {
//			PresentTemplet t = PresentTempletConfig.get(2);
//			PrizeSenderFactory.getPrizeSender().send(user.getPlayer(), t.getAwards());
//
//			setProtectPhase(2);// 自动进入第二阶段
//		} else if (count == D.DAJI_PROTECT_COUNT2) {
//			PresentTemplet t = PresentTempletConfig.get(3);
//			PrizeSenderFactory.getPrizeSender().send(user.getPlayer(), t.getAwards());
//		}
		Prize prize;
		boolean isFirst = false;
		if (count == D.DAJI_PROTECT_COUNT1 && firstGodPrize() ) {
			setFirstGotPrize();
			isFirst = true;
			prize = new PrizeImpl( calcProtectPrize( isFirst ), 1 );
		}
		else{
			prize = new  PrizeImpl( calcProtectPrize( isFirst ), 1 );
		}
		if (count == D.DAJI_PROTECT_COUNT1 ){
			setProtectPhase(2);// 自动进入第二阶段
		}
		prize.award( user.getPlayer() );


		//user.getMessageSender().send( S.S90001 );
		return prize.getId();

	}

	/**
	 * 计算奖品id
	 * @param isFirst		是否第一次，如果是第一次有个伪随机的问题
	 * @return
	 */
	private int calcProtectPrize(boolean isFirst) {
		List<CustodianGuardLibraryTemplet> list = Lists.newArrayList();;
		if( isFirst ){
			
			for(CustodianGuardLibraryTemplet t : CustodianGuardLibraryTempletConfig.getAll() ){
				if( t.getType() == 2 && t.getStep() == 4 ){
					list.add(t);
				}
			}
		}
		else{
			for( CustodianGuardLibraryTemplet t : CustodianGuardLibraryTempletConfig.getAll() ){//过滤玩家等级以下的奖励
				if( user.getFunctionOpenManager().isOpen( ModuleType.getType(t.getModulesId() ) ) ){
					list.add(t);
				}
			}
			list = CustodianGuardLibraryTempletConfig.getAll();
		}

		WeightFetcher<CustodianGuardLibraryTemplet> weightAble = new WeightFetcher<CustodianGuardLibraryTemplet>() {

			@Override
			public Integer get(CustodianGuardLibraryTemplet t) {
				return t.getWeight();
			}
		};
		return Random.getRandomOneByWeight( list, weightAble).getId();

	}

	private void setFirstGotPrize() {
		user.getUserCounterHistory().set(CounterKey.DAJI_FIRST_GOT_PRIZE, 1 );

	}

	private boolean firstGodPrize() {
		return user.getUserCounterHistory().get(CounterKey.DAJI_FIRST_GOT_PRIZE ) == 1;
	}

	void getDriftPrize() {
		for (Prize p : prizes) {
			p.award(user.getPlayer());
		}

		prizes.clear();
		CustodianMapTemplet ct = CustodianMapTempletConfig.getAll().get(getTodayDriftCount());
		int coldSecond = ct.getCoolTime();
		// cd = new ColdDownTime(coldSecond);
		setColdDownTime();
		//getCd().add();// 开始冷却计时
		addTodayBattleCount();
		user.getMessageSender().send(S.S10147);
		// setAllProtectCount();
	}

	public void setColdDownTime() {
		int todayBattleCount = getTodayDriftCount();
		

		int coldTimeSecond = 0;
		switch (todayBattleCount) {
		case 0:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI0).getTimeFreezing());   break;
		case 1:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI1).getTimeFreezing());   break;
		case 2:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI2).getTimeFreezing());   break;
		case 3:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI3).getTimeFreezing());   break;
		case 4:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI4).getTimeFreezing());   break;
		case 5:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI5).getTimeFreezing());   break;
		case 6:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI6).getTimeFreezing());   break;
		case 7:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI7).getTimeFreezing());   break;
		case 8:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI8).getTimeFreezing());   break;
		case 9:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI9).getTimeFreezing());   break;
		case 10:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI10).getTimeFreezing());   break;
		case 11:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI11).getTimeFreezing());   break;
		case 12:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI12).getTimeFreezing());   break;
		case 13:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI13).getTimeFreezing());   break;
		case 14:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI14).getTimeFreezing());   break;
		case 15:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI15).getTimeFreezing());   break;
		case 16:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI16).getTimeFreezing());   break;
		case 17:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI17).getTimeFreezing());   break;
		case 18:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI18).getTimeFreezing());   break;
		case 19:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI19).getTimeFreezing());   break;
		case 20:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI20).getTimeFreezing());   break;
		case 21:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI21).getTimeFreezing());   break;
		case 22:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI22).getTimeFreezing());   break;
		case 23:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI23).getTimeFreezing());   break;
		case 24:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI24).getTimeFreezing());   break;
		case 25:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI25).getTimeFreezing());   break;
		case 26:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI26).getTimeFreezing());   break;
		case 27:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI27).getTimeFreezing());   break;
		case 28:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI28).getTimeFreezing());   break;
		case 29:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI29).getTimeFreezing());   break;
		case 30:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI30).getTimeFreezing());   break;
		case 31:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI31).getTimeFreezing());   break;
		case 32:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI32).getTimeFreezing());   break;
		case 33:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI33).getTimeFreezing());   break;
		case 34:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI34).getTimeFreezing());   break;
		case 35:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI35).getTimeFreezing());   break;
		case 36:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI36).getTimeFreezing());   break;
		case 37:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI37).getTimeFreezing());   break;
		case 38:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI38).getTimeFreezing());   break;
		case 39:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI39).getTimeFreezing());   break;
		case 40:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI40).getTimeFreezing());   break;
		case 41:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI41).getTimeFreezing());   break;
		case 42:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI42).getTimeFreezing());   break;
		case 43:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI43).getTimeFreezing());   break;
		case 44:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI44).getTimeFreezing());   break;
		case 45:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI45).getTimeFreezing());   break;
		case 46:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI46).getTimeFreezing());   break;
		case 47:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI47).getTimeFreezing());   break;
		case 48:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI48).getTimeFreezing());   break;
		case 49:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI49).getTimeFreezing());   break;
		case 50:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI50).getTimeFreezing());   break;
		case 51:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI51).getTimeFreezing());   break;
		case 52:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI52).getTimeFreezing());   break;
		case 53:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI53).getTimeFreezing());   break;
		case 54:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI54).getTimeFreezing());   break;
		case 55:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI55).getTimeFreezing());   break;
		case 56:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI56).getTimeFreezing());   break;
		case 57:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI57).getTimeFreezing());   break;
		case 58:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI58).getTimeFreezing());   break;
		case 59:   coldTool.setEndSecond(cdManager.get(CDKey.DAJI59).getTimeFreezing());   break;

		default:
			coldTool.setEndSecond(cdManager.get(CDKey.DAJI59).getTimeFreezing());
			break;

		}
		
		int vipPrivilege = VipPrivilegeTempletConfig.get( (byte) user.getPlayer().getVipLevel() ).getCustodianCD();
		if( vipPrivilege == 1 && coldTool.getRemainingMin() > 30){
			coldTool.setEndSecond(cdManager.get(CDKey.DAJI_VIP).getTimeFreezing());
		}
		
//		if(cd.isFreezing()) {
//			 cd.setEndTime(System.currentTimeMillis());
//		}
		
//		int endSecond = (int) ((System.currentTimeMillis() + cd.getTimeFreezing()) / 1000);
		
//		LocalDate compare = new LocalDate( endSecond );
//		LocalDate now = new LocalDate();
//        if( !now.equals( compare ) ){//冷却时间会翻过午夜0点
//        	endSecond = (int) (now.toDateMidnight().getMillis() / 1000);
//        	
//        	coldTool.setEndSecond(endSecond);
//        }
		
	}

	public static void main(String[] args) {
		System.out.println( new DateTime(1404891181 * 1000l) );
		Map<Integer, Integer> map = Maps.newTreeMap();
		// for( int i = 1; i < 9; i++ ){
		// map.put( i, i );
		// }

		for (int i = 1; i < 110; i++) {
			int key = i;
			int value = key - 4;
			value %= 5;
			value += 4;
			map.put(key, value);
		}
		System.out.println(map);
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}

//	/**
//	 * @return cd
//	 */
	ColdTimeTool getCd() {
		return coldTool;
	}
//
//	/**
//	 * @param cd
//	 *            要设置的 cd
//	 */
//	void .getTimeFreezing());(ColdDown cd) {
//		this.cd = cd;
//	}
	
	/**保护妲己的感叹号, 神马时候显示
	 * 	驱赶可点，领取可点，保护可点
	 * @return
	 */
	public boolean getHasTips() {
 		if (!isProtectToday()) {//今日可保护
			return true;
		}
		if (!coldTool.isFreezing()) {
			return true;
		}
		
		if( prizes.size() > 0 ){
			return true;
		}
		
		return false;
	}

}
