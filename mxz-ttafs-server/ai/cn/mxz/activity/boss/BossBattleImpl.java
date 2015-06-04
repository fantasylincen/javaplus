//package cn.mxz.activity.boss;
//
//import java.util.List;
//
//import cn.mxz.FighterExpPrize;
//import cn.mxz.battle.AbstractBattle;
//import cn.mxz.battle.BattleExpPrize;
//import cn.mxz.enemy.EmptyExpPrize;
//import cn.mxz.formation.PlayerCamp;
//import cn.mxz.mission.old.PropPrize;
//import cn.mxz.user.City;
//
//import com.google.common.collect.Lists;
//
//class BossBattleImpl extends AbstractBattle implements BossBattle {
//
//	private Boss	boss;
//
//	private City	city;
//
//	public BossBattleImpl(City city, PlayerCamp selected, Boss boss, int mapId) {
//
//		super(selected, new BossCamp(boss));
//
//		this.city = city;
//
//		this.boss = boss;
//	}
//
//	@Override
//	public BossReward generateReward() {
//
//		return new BossRewardImpl();
//	}
//
//	@Override
//	protected FighterExpPrize getMapTemplet() {
//		return new EmptyExpPrize();
//	}
//
//	private class BossRewardImpl implements BossReward {
//
//		@Override
//		public double getMvp() {
//
//			if (!boss.isDeath()) {
//
//				return 0;
//			}
//
//			if (boss.getMvp().getId().equals(city.getId())) {
//
//				// return D.BOSS_MVP_ADDITION;
//				return 1;
//			}
//
//			return 0;
//		}
//
//		@Override
//		public double getFriendAddition() {
//
//			if (!boss.isDeath()) {
//
//				return 0;
//			}
//
//			if (hasFriend(boss.getBossChallengers(), city)) {
//
//				// return D.BOSS_FRIEND_ADDITION;
//				return 1;
//			}
//
//			return 0;
//		}
//
//		/**
//		 * 在Boss挑战者列表中, 是否有好友存在
//		 *
//		 * @param bossChallengers
//		 * @param city
//		 * @return
//		 */
//		private boolean hasFriend(List<BossChallenger> bossChallengers, City city) {
//
//			// for (BossChallenger bc : bossChallengers) {
//			//
//			// FriendManager fm = city.getActivityFriendManager();
//			//
//			// if (fm.isFriend(bc.getId())) {
//			//
//			// return true;
//			// }
//			// }
//
//			return false;
//		}
//
//		@Override
//		public int getDamageReward() {
//
//			return BossRewardConfig.get(1, boss).getAwardEvent();
//		}
//
//		@Override
//		public double getContinuous() {
//
//			// if (boss.getBossChallengers().size() >
//			// D.BOSS_CONTINUOUS_CHALLENGER_COUNT) {
//			//
//			// return D.BOSS_CONTINUOUS_ADDITION;
//			// }
//
//			return 0;
//		}
//
//		@Override
//		public int getAttackReward() {
//
//			if (!boss.isDeath()) {
//
//				return 0;
//			}
//
//			return BossRewardConfig.get(4, boss).getAwardEvent();
//		}
//
//		@Override
//		public String getMVPOrJMVP() {
//
//			boolean isJmvp = false;
//
//			BossChallenger jMvp = boss.getJMvp();
//
//			if (jMvp != null && jMvp.getCity() == city) {
//
//				isJmvp = true;
//			}
//
//			return isJmvp ? "JMVP" : "MVP";
//		}
//
//		@Override
//		public int getFriendCount() {
//
//			// List<BossChallenger> bs = boss.getBossChallengers();
//			//
//			// ActivityFriendManager fm = city.getActivityFriendManager();
//			//
//			int count = 0;
//			//
//			// for (BossChallenger bc : bs) {
//			//
//			// if (fm.isFriend(bc.getId())) {
//			//
//			// count++;
//			// }
//			// }
//
//			return count;
//		}
//
//		@Override
//		public int getChallengerCount() {
//
//			return boss.getBossChallengers().size();
//		}
//
//		@Override
//		public int getScoreRewardId() {
//
//			// ActivityFriendManager fm = city.getActivityFriendManager();
//			//
//			// int score = fm.findNextScore();
//			//
//			// if (score == -1) {
//			//
//			// return -1;
//			// }
//			//
//			// Player player = city.getPlayer();
//			//
//			// int s = player.get(PlayerProperty.ACTIVITY_SCORE);
//			//
//			// if (s >= score) {
//			//
//			// return score;
//			// }
//
//			return -1;
//		}
//
//	}
//
//	@Override
//	public List<PropPrize> getPropPrize() {
//		return Lists.newArrayList();
//	}
//
//	@Override
//	public List<BattleExpPrize> getExpPrize() {
//		return Lists.newArrayList();
//	}
//}
