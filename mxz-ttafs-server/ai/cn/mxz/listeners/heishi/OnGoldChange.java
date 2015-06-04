//package cn.mxz.listeners.heishi;
//
//import message.S;
//import cn.javaplus.util.Util;
//import cn.mxz.ActivityTemplet;
//import cn.mxz.ActivityTempletConfig;
//import cn.mxz.activity.ActivityIds;
//import cn.mxz.city.City;
//import cn.mxz.events.Listener;
//import cn.mxz.events.PlayerPropertyChangeEvent;
//import cn.mxz.util.counter.CounterKey;
//import define.D;
//
///**
// * 当玩家元宝发生变化时 执行
// * @author 林岑
// *
// */
//public class OnGoldChange implements Listener<PlayerPropertyChangeEvent> {
//
//	@Override
//	public void onEvent(PlayerPropertyChangeEvent e) {
//		if(e.hasGoldChanged()) {
//			
//			if( D.LANGUAGE != 2 ){//仅限于台湾版本
//				return;
//			}
//			ActivityTemplet temp = ActivityTempletConfig.get(ActivityIds.XianShiHeiShi_14);
//			
//			boolean in = Util.Time.isIn(temp.getTime());
//			if( !in){//不在活动中
//				return;
//			}
//			onGoldChange(e.getCity(), e.getOldValue(), e.getNewValue());
//		}
//	}
//
//	/**
//	 * 玩家金币发生变化时
//	 * @param user
//	 * @param oldValue 消耗钱金币
//	 * @param newValue 消耗后金币
//	 * 
//	 * 2014/11/20~2014/11/27每消耗100元寶獲得  100:110026,1";
//	 * 2014/11/20~2014/11/27累積充值滿10000元寶 10000:110023,1"; 
//	 */
//	private void onGoldChange(City user, int oldValue, int newValue) {
//
//		if( oldValue > newValue ){//消耗了元宝
//			int reduceGold = oldValue - newValue;//本次消费的元宝数
//			
//			int needGold = Integer.parseInt( D.HEI_SHI_SHUI_JING_JIANG_LI_2.split(":")[0] );//得奖门槛
//			int remainGold = user.getUserCounterHistory().get(CounterKey.HEISHI_REDUCE_REMAIN_GOLD);//上次兑换水晶后剩余的元宝数
//			
//			int allRemainGold = reduceGold + remainGold;//可供计算兑换的总的元宝数
//			
//			if( allRemainGold < needGold ){//不到兑换门槛
//				user.getUserCounterHistory().add(CounterKey.HEISHI_REDUCE_REMAIN_GOLD, reduceGold );
//			}else{
//				String str = D.HEI_SHI_SHUI_JING_JIANG_LI_2.split(":")[1];
//				
//				int templet_propId = Integer.parseInt( str.split(",")[0]);
//				int templet_count = Integer.parseInt( str.split(",")[1]); 
//				
//				int count = allRemainGold / needGold;//达到兑换条件多少次
//				user.getPrizeCenter().addPrize(5, templet_propId + "," + count *templet_count , S.STR60293, S.STR60292);
//				
//				remainGold = allRemainGold - count * needGold;//扣除已经兑换的元宝 
//				user.getUserCounterHistory().set(CounterKey.HEISHI_REDUCE_REMAIN_GOLD, remainGold );
//
//			}
//			
//		}
//		else{//充值了元宝
//			
//			int rechargeGold = newValue - oldValue;//本次充值的元宝数
//			
//			
//			int needGold = Integer.parseInt( D.HEI_SHI_SHUI_JING_JIANG_LI_3.split(":")[0] );//得奖门槛
//			
//			int remainGold = user.getUserCounter().get(CounterKey.HEISHI_RECHARGE_REMAIN_GOLD);//上次充值元宝后，黑市活动兑换奖励之后，剩余的元宝数
//			
//			user.getUserCounter().add(CounterKey.HEISHI_RECHARGE_REMAIN_GOLD,rechargeGold);//更新每日充值记录
//			int allRemainGold = rechargeGold + remainGold;//今日充值的总的元宝数
//			
//			if( allRemainGold >= needGold && !user.getUserCounter().isMark(CounterKey.HEISHI_RECHARGE_GOT_PRIZE )){//兑换门槛
//				
//				String str = D.HEI_SHI_SHUI_JING_JIANG_LI_3.split(":")[1];
//				
//				int templet_propId = Integer.parseInt( str.split(",")[0]);
//				int templet_count = Integer.parseInt( str.split(",")[1]); 
//				
//				//int count = allRemainGold / needGold;//达到兑换条件多少次
//				user.getPrizeCenter().addPrize(5, templet_propId + "," + templet_count , S.STR60297, S.STR60296);
//				
//				//remainGold = allRemainGold - needGold;//扣除已经兑换的元宝 
//				user.getUserCounter().mark(CounterKey.HEISHI_RECHARGE_GOT_PRIZE );
//
//			}
//			
//		}
//		
//	}
//	
//
//}
