package cn.mxz.system;

import java.util.Collection;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import cn.mxz.FighterTempletConfig;
import cn.mxz.PresentTemplet;
import cn.mxz.PresentTempletConfig;
import cn.mxz.base.prize.PrizeSenderFactory;
import cn.mxz.base.service.AbstractService;
import cn.mxz.city.City;
import cn.mxz.handler.SystemService;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.protocols.user.god.FighterP.FightersPro;
import cn.mxz.team.builder.FightersBuilder;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.debuger.Debuger;

@Component("systemService")
@Scope("prototype")
public class SystemServiceImpl extends AbstractService implements SystemService {

	@Override
	public void recharge(int count) {
//		if (Debuger.isDebug()) {
//			if (count <= 0) {
//				return;
//			}
//
//			City user = getCity();
//			UserCounter his = user.getUserCounterHistory();
//
//			boolean isFirstRecharge = his.get(CounterKey.TOTAL_RECHARGE_GOLD_COUNT) == 0;
//			if (isFirstRecharge) {
//				his.set(CounterKey.FIRST_GOLD_COUNT, count);//
//				// count *= 2;
//				// firstRecharge();
//
//			}
//
//			his.add(CounterKey.TOTAL_RECHARGE_GOLD_COUNT, count);//
//			user.getUserCounter().add(CounterKey.TOTAL_RECHARGE_GOLD_COUNT, count);//
//
//			Player player = user.getPlayer();
//			if (isFirstRecharge) {
//				player.add(PlayerProperty.GOLD, count * D.FIRST_RECHARGE_X);
//			} else {
//				player.add(PlayerProperty.GOLD, count);
//			}
//			sendGift(user, count);
//		} else {
//			throw new SureIllegalOperationException("非调试模式, 无法充值!");
//		}
	}

//	private void sendGift(City city, int count) {
//
//		int count2 = find(count);
//		city.getPlayer().addGiftGold(count2);
//	}
//
//	private int find(int count) {
//		List<PayTemplet> findBySycee = PayTempletConfig.findByAndroidSycee(count);
//		if (findBySycee.isEmpty()) {
//			return 0;
//		}
//		return findBySycee.get(0).getAndroidSyceeGet();
//	}

	@Override
	public Boolean getRechargeAward() {
		City user = getCity();
		
		boolean hasReceive = user.getUserCounterHistory().isMark(CounterKey.HAS_RECEIVE_FIRST_RECHARGE_REWARD);
		if(hasReceive) {
			return false;
		}
		
		PresentTemplet t = PresentTempletConfig.get(1);
		String awards = t.getAwards();
		Debuger.debug("SystemServiceImpl.getRechargeAward()领取首冲礼包:" + awards);
		PrizeSenderFactory.getPrizeSender().send(user.getPlayer(), awards);
		user.getUserCounterHistory().mark(CounterKey.HAS_RECEIVE_FIRST_RECHARGE_REWARD);
		
		updateFighter(400102, user);//首冲送妲己，刷新此id，如果配置表修改，这里需要做相应修改
		
		
		
		return true;

	}
	
	private void updateFighter(int propId, City user ) {
		if(FighterTempletConfig.get(propId) != null)  {

			Hero hero = user.getTeam().get(propId);
			Collection<Hero> fighters = Lists.newArrayList(hero);
			
			FightersPro fs = new FightersBuilder().build(fighters);
			
			MessageFactory.getFighter().fightersUpdate( user.getSocket(), fs);
		}
	}

	@Override
	public void beatToServer() {

	}
}
