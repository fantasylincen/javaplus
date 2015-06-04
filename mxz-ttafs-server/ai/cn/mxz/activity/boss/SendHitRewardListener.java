//package cn.mxz.activity.boss;
//
//import java.util.List;
//
//import cn.javaplus.common.db.DAO2;
//import cn.javaplus.common.util.math.Fraction;
//import cn.mxz.base.prize.PrizeSender;
//import cn.mxz.battle.FightingEndEvent;
//import cn.mxz.city.PlayerProperty;
//import cn.mxz.event.Listen;
//import cn.mxz.event.Listener;
//import cn.mxz.event.ServerEvent;
//import cn.mxz.formation.PlayerCamp;
//import cn.mxz.friend.Friend;
//import cn.mxz.user.City;
//import cn.mxz.util.Factory;
//import db.dao.factory.DaoFactory;
//import db.domain.BossDamageData;
//import db.domain.BossScoreShared;
//
//@Listen(eventClass = FightingEndEvent.class)
//class SendHitRewardListener implements Listener {
//
//	private Boss		boss;
//
//	private Fraction	oldHp;
//
//	SendHitRewardListener(Boss boss, Fraction oldHp) {
//
//		this.boss = boss;
//
//		this.oldHp = oldHp;
//	}
//
//	@Override
//	public void onEvent(ServerEvent e) {
//
//		BossBattle battle = (BossBattle) e.getSource();
//
//		int damage = oldHp.getNumerator() - boss.getHp().getNumerator();
//
//		PlayerCamp under = battle.getUnder();
//
//		City city = under.getCity();
//
//		saveDamage(boss, city, damage); // 记录我对Boss的伤害值
//
//		sendHitReward(battle, boss, damage, city); // 赠送Boss攻击奖励
//	}
//
//	/**
//	 * 将分数分享给好友
//	 * 
//	 * @param score
//	 * @param city
//	 */
//	private void addScoreToFriendAll(int score, City city) {
//
//		List<Friend> all = city.getActivityFriendManager().getAll();
//
//		for (Friend friend : all) {
//
//			DAO2<String, String, BossScoreShared> DAO = DaoFactory.getBossScoreSharedDAO();
//
//			BossScoreShared bs = DAO.get(city.getId(), friend.getFriendId());
//
//			if (bs == null) {
//
//				bs = new BossScoreShared();
//
//				bs.setUanme(city.getId());
//
//				bs.setShareId(friend.getFriendId());
//
//				DAO.add(bs);
//			}
//
//			bs.addScore((int) (score * 0.1f));
//
//			DAO.update(bs);
//		}
//	}
//
//	/**
//	 * 记录city对boss造成的伤害值
//	 * 
//	 * @param boss
//	 * @param city
//	 * @param damage
//	 *            伤害值
//	 */
//	private void saveDamage(Boss boss, City city, int damage) {
//
//		DAO2<Integer, String, BossDamageData> DAO = DaoFactory.getBossDamageDataDAO();
//
//		BossDamageData b = DAO.get(boss.getId(), city.getId());
//
//		if (b == null) {
//
//			b = new BossDamageData();
//
//			b.setBossId(boss.getId());
//
//			b.setChallengerId(city.getId());
//
//			DAO.add(b);
//		}
//
//		b.addDamage(damage);
//
//		DAO.update(b);
//	}
//
//	/**
//	 * 发放攻击奖励
//	 * 
//	 * @param b
//	 * @param boss
//	 * @param damage
//	 * @param city
//	 */
//	private void sendHitReward(BossBattle b, Boss boss, int damage, City city) {
//
//		// 积分奖励
//		sendScore(b, city);
//
//		// MVP奖励
//		sendMvpReward(boss, city);
//	}
//
//	private void sendMvpReward(Boss boss, City city) {
//
//		PrizeSender ps = PrizeSenderFactory.getPrizeSender();
//
//		ps.send(city.getPlayer(), new BossMvpReward(boss, city));
//	}
//
//	private void sendScore(BossBattle b, City city) {
//
//		BossReward reward = b.generateReward();
//
//		city.getBossMission().saveBossReward(reward);
//
//		int score = 0;
//
//		score += reward.getDamageReward();
//
//		score += reward.getAttackReward();
//
//		score = (int) (score * (1 + reward.getContinuous()));
//
//		score = (int) (score * (1 + reward.getFriendAddition()));
//
//		score = (int) (score * (1 + reward.getMvp()));
//		
//		
//		city.getPlayer().add(PlayerProperty.ACTIVITY_SCORE, score);
//
//		addScoreToFriendAll(score, city);
//	}
//
//}
