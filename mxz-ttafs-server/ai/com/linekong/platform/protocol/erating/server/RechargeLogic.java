package com.linekong.platform.protocol.erating.server;

import java.util.Date;
import java.util.List;

import cn.mxz.PayTemplet;
import cn.mxz.PayTempletConfig;
import cn.mxz.city.City;
import cn.mxz.events.Events;
import cn.mxz.listeners.heishi.ChongZhi;
import cn.mxz.user.Player;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import cn.mxz.util.debuger.Debuger;
import define.D;

public class RechargeLogic {

	private final City	user;
	private final int	clientType;

	public RechargeLogic(City user) {
		this.user = user;
		clientType = user.getPlayer().getClientType();
//		clientType = 1;
	}

	public void recharge(int count) {
		if (count <= 0) {
			return;
		}

		

		UserCounter his = user.getUserCounterHistory();

		boolean isFirstRecharge = his.get(CounterKey.TOTAL_RECHARGE_GOLD_COUNT) == 0;
		if (isFirstRecharge) {
			his.set(CounterKey.FIRST_GOLD_COUNT, count);//
		}

		his.add(CounterKey.TOTAL_RECHARGE_GOLD_COUNT, count);//
		his.add(CounterKey.VIP_GROWTH, count);
		his.add(CounterKey.MONTH_RECHARGE, count, new MonthRechargeKeyBuilder().getCurrentMonthKey());

		user.getUserCounter().add(CounterKey.TOTAL_RECHARGE_GOLD_COUNT, count);// 今日充值数量

		Player player = user.getPlayer();
		
		Debuger.debug( user.getPlayer().getNick() +  " 本次充值数目:" + count );
		Debuger.debug( user.getPlayer().getNick() +  " 充值后元宝数：" + user.getPlayer().getGold() );
		
		
		player.updateGoldFromThirdParty();
		
	

		PayTemplet templet = getTemplet(count);
//		Debuger.debug( user.getPlayer().getNick() +  " 在 " + templet.getSycee() + "档次是否充过值:" + hasRecharged(templet) );
		if(D.LANGUAGE == 1 ){
			calcReward(player, templet);
		}
		else{
			calcRewardTW(player, templet, isFirstRecharge);
		}
//		Debuger.debug( user.getPlayer().getNick() +  " 充值后并且加入额外返利的元宝数：" + user.getPlayer().getGold() );
		
		Events.getInstance().dispatch(new RechargeEvent(user, count));
		
		new ChongZhi().onXXX(user, count);
		
//		if (getTemplet(count).getMonthcard() == 1) {// 处理月卡相关
//			processMonthCard();
//		}
	}

	
	private void calcReward(Player player, PayTemplet templet) {
		if (!hasRecharged(templet)) {
			player.addGiftGold(calcFirstGold(templet));// 首冲
			setFirstRecharge(templet);
		}else{
			int calcGiftGold = calcGiftGold( templet);
			player.addGiftGold(calcGiftGold);// 额外返利
			Debuger.debug(user.getPlayer().getNick() + ";e wai ho de;" + calcGiftGold);// 额外返利
		}
	}
	
	/**
	 * 台湾的充值奖励	
	 * 
	 * @param player			玩家
	 * @param templet			充值的模板
	 * @param isFirstRecharge	是否玩家整个充值历史上第一次充值（），主要用于台湾版
	 */
	private void calcRewardTW(Player player, PayTemplet templet, boolean isFirstRecharge) {
		if (isFirstRecharge) {
			player.addGiftGold(calcFirstGold(templet));// 首冲
//			setFirstRecharge(templet);
		}else{
			int calcGiftGold = calcGiftGold( templet);
			player.addGiftGold(calcGiftGold);// 额外返利
//			Debuger.debug(user.getPlayer().getNick() + ";e wai ho de;" + calcGiftGold);// 额外返利
		}
	}



	/**
	 * 获取相应的模板
	 * 
	 * @param gold
	 * @return
	 */
	private PayTemplet getTemplet(int gold) {
//		PayTemplet templet = null;
		List<PayTemplet> list = PayTempletConfig.findByIosOrAndroid( clientType );
		for( PayTemplet t : list ){
			if( t.getSycee() <= gold ){
//				System.out.println( t );
				return t;
			}
		}
		return null;
//		
//		if (clientType == 1) {
//			List<PayTemplet> list = PayTempletConfig.findBySycee(gold);
//			if( list.size() > 0 ){
//				templet = list.get(0);
//			}
//		} else {
//			List<PayTemplet> list = PayTempletConfig.findByAndroidSycee(gold);
//			if( list.size() > 0 ){
//				templet = list.get(0);
//			}
//		}
//
//		if (templet == null) {// 找不到对应的数据项，就必须找一个小于充值金额的最近似的数据
//		
//			if (clientType == 1) {
//
//				for (PayTemplet t : PayTempletConfig.findByIosOrAndroid(clientType)) {
//					if (t.getIosSycee() < gold) {
//						return t;
//						
//					}
//				}
//			} else {
//				for (PayTemplet t : PayTempletConfig.findByIosOrAndroid(clientType)) {
//					if (t.getAndroidSycee() < gold) {
//						return t;
//					}
//				}
//			}
//		}
//		return templet;
	}

	/**
	 * 计算充值额外获得的返利元宝(非首冲)
	 * 
	 * @return
	 */
	private int calcGiftGold(PayTemplet templet) {
		
			return templet.getSyceeGet();
	}

	/**
	 * 计算首冲的返利元宝
	 * 
	 * @return
	 */
	private int calcFirstGold(PayTemplet templet) {
		return templet.getRebate();
	}
	
	/**
	 * 判断这个档次的是否充过值(经过首冲了)
	 * true:  已经冲过了
	 * false  还没冲过
	 * @param gold
	 * @return
	 */
	private boolean hasRecharged( PayTemplet templet ){
		//PayTemplet templet = getTemplet(gold);
		int actGold = templet.getSycee();
		//System.out.println( "user.getUserCounterHistory().get(CounterKey.RECHARGE, " +actGold+")" + user.getUserCounterHistory().get(CounterKey.RECHARGE, actGold) );
		
		return user.getUserCounterHistory().isMark(CounterKey.RECHARGE, actGold);
	}
	
	/**
	 * 设置这个档次的是否第一次充值的标记
	 * @param gold
	 * @return
	 */
	private void setFirstRecharge(PayTemplet templet ){
		int actGold = templet.getSycee();
		user.getUserCounterHistory().mark(CounterKey.RECHARGE,actGold);
//		System.out.println("user.getUserCounterHistory().mark(CounterKey.RECHARGE," + actGold + ");" + user.getUserCounterHistory().isMark(CounterKey.RECHARGE,actGold));
	}
	
	/**
	 * 返回玩家所有档次充值的首冲情况
	 * @return
	 */
	public String getFirshtRechargeStr(){
		String ret = "";
		for( PayTemplet t: PayTempletConfig.findByIosOrAndroid( clientType ) ){
//			if( t.getSycee() == 1 ){
//				System.out.println( "1这一档是否充值 " + !isFirstRecharge(t) );
//			}
			if( hasRecharged(t) ){
				ret += t.getSycee();
				ret += ",";
			}
		}
		if( ret.length() > 1 ){
			ret = ret.substring( 0 , ret.length() - 1 );
		}
		Debuger.debug( user.getPlayer().getNick() +  "的客户端类型:" + (clientType == 1? "正版苹果" : "android或破解苹果") );
		Debuger.debug( user.getPlayer().getNick() +  "的首冲情况:" + ret );
		return ret;
	}
	
	public static void main(String[] args) {
		System.out.println(new Date(1417465979*1000l));
		//RechargeLogic logic = new RechargeLogic( null );
		//PayTemplet templet = logic.getTemplet( 5 );
//		System.out.println( "首冲返利：" + templet.getRebate() );
//		System.out.println( "其他返利：" + templet.getSyceeGet() );
	}

}
