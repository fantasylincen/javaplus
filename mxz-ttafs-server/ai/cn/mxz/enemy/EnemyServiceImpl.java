package cn.mxz.enemy;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import message.S;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.javaplus.exception.SQLRuntimeException;
import cn.javaplus.util.Util;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.service.AbstractService;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.friend.FriendManager;
import cn.mxz.friend.Request;
import cn.mxz.handler.EnemyService;
import cn.mxz.protocols.user.enemy.EnemyP.EnemyWarSituationPro;
import cn.mxz.protocols.user.enemy.EnemyP.SystemItemsPro;
import cn.mxz.protocols.user.enemy.EnemyP.SystemItemsPro.Builder;
import cn.mxz.user.team.Formation;

@Component("enemyService")
@Scope("prototype")
public class EnemyServiceImpl extends AbstractService implements EnemyService {

	@Override
	public cn.mxz.protocols.user.enemy.EnemyP.EnemysPro getEnemys(int index, int count) {
		EnemyManager em = getCity().getEnemyManager();
		List<Enemy> ls = em.getAll();

		ls = ls.subList(index, ls.size());

		ls = cn.javaplus.util.Util.Collection.sub(ls, count);

		return new EnemysBuilder().build(ls);
	}

	@Override
	public void addToEnemy(String userId) {
		
		//机器人不能加入仇敌.
		if(CityFactory.getCity(userId) == null) {
			return;
		}
		
		City city = getCity();
		EnemyManager em = city.getEnemyManager();
		
		if (getId().equals(userId)) {
			throw new OperationFaildException(S.S10243);
		}

		removeFriend(city.getId(), userId);
		removeFriend(userId, city.getId());
		
		try {
			em.add(userId);
		} catch (SQLRuntimeException e) {
			System.err.println("已是仇敌");
		}
	}

	private void removeFriend(String id1, String id2) {
		City city = CityFactory.getCity(id1);
		city.getFriendManager().remove(id2);		
	}

	@Override
	public void removeEnemy(String userId) {
		EnemyManager em = getCity().getEnemyManager();
		em.remove(userId);
	}

	@Override
	public EnemyWarSituationPro revenge(String userId) {
		City c = getCity();
		Formation formation = c.getFormation();
		PlayerCamp s = formation.getSelected();
		EnemyBattle battle = new EnemyBattleImpl(s, userId);
		battle.fighting();
		return new EnemyWarSituationBuilder().build(battle);
	}

	@Override
	public EnemyWarSituationPro attack(String userId) {
		City c = getCity();
		Formation formation = c.getFormation();
		PlayerCamp s = formation.getSelected();
		EnemyBattle battle = new EnemyBattleImpl(s, userId);
		battle.fighting();
		return new EnemyWarSituationBuilder().build(battle);
	}

	@Override
	public SystemItemsPro getSystemList(int indexStart, int count) {

//		if(getCity().isTester()) {
//			return new SystemItemsBuilder().buildDev();
//		}

		City c = getCity();

		FriendManager fm = c.getFriendManager();

		List<Request> all = fm.getRequests();

		all = all.subList(indexStart, all.size());

		all = Util.Collection.sub(all, count);

		Builder b = SystemItemsPro.newBuilder();

		Comparator<Request> cc = new Comparator<Request>() {

			@Override
			public int compare(Request o1, Request o2) {
				return (int) (o2.getRequestTime() - o1.getRequestTime());
			}
		};

		Collections.sort(all, cc);

		for (Request u : all) {
			b.addItems(new SystemItemBuilder().buildFriend(u));
		}


//		UserCounterSetter his = getCity().getUserCounterHistory();
//		his.clear(CounterKey.HAS_NEW_SNATCH_MESSAGE);

		return b.build();
	}

}
