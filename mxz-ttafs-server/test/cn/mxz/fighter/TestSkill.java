//package cn.mxz.fighter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import cn.javaplus.common.util.Util;
//import cn.mxz.battle.ActiveSkill;
//import cn.mxz.battle.AttackAction;
//import cn.mxz.battle.Battle;
//import cn.mxz.battle.Camp;
//import cn.mxz.battle.SkillPoint;
//import cn.mxz.debug.Debuger;
//
///**
// *
// * 用于测试的一个技能
// *
// * @author 林岑
// *
// */
//public class TestSkill implements ActiveSkill {
//
//	private class TestAction implements AttackAction {
//
//		private boolean isUnder;
//
//		public TestAction(final boolean isUnder, final int round,
//				final int position) {
//
//			this.isUnder = isUnder;
//
//			this.round = round;
//
//			this.position = position;
//		}
//
//		private final List<SkillPoint> points = new ArrayList<SkillPoint>();
//
//		private int round;
//
//		private int position;
//
//		@Override
//		public List<SkillPoint> getSkillPoints() {
//			return points;
//		}
//
//		@Override
//		public boolean isAttackerUnder() {
//			return isUnder;
//		}
//
//		@Override
//		public int getSkillId() {
//			return TestSkill.this.getId();
//		}
//
//		public void addSkillPoint(final int hp, final int sp,
//				final int position, final boolean isUnder) {
//
//			points.add(new SkillPoint() {
//
//				@Override
//				public int getSpEnd() {
//
//					return sp;
//				}
//
//				@Override
//				public int getPosition() {
//
//					return position;
//				}
//
//				@Override
//				public int getHpEnd() {
//
//					return hp;
//				}
//
//				@Override
//				public boolean isUnder() {
//
//					return isUnder;
//				}
//			});
//		}
//
//		@Override
//		public int getRound() {
//
//			return round;
//		}
//
//		@Override
//		public int getPosition() {
//
//			return position;
//		}
//
//	}
//
//	private TestAction testAction;
//
//	private Fighter attacker;
//
//	private Battle battle;
//
//	public TestSkill(final Fighter fighter, final Battle battle) {
//
//		this.attacker = fighter;
//
//		this.battle = battle;
//
//		final Camp<? extends Fighter> under = battle.getUnder();
//
//		final boolean isUnder = under.contains(getFighter());
//
//		int position;
//
//		if (isUnder) {
//
//			position = under.getPosition(getFighter());
//
//		} else {
//
//			position = battle.getUpper().getPosition(getFighter());
//		}
//
//		testAction = new TestAction(isUnder, battle.getRound(), position);
//	}
//
//	@Override
//	public Fighter getFighter() {
//		return attacker;
//	}
//
//	@Override
//	public void releaseToEnemy() {
//
//		final Camp<? extends Fighter> enemy = battle.getEnemy(attacker);
//
//		final List<Fighter> all = new ArrayList<>(enemy.getLives());
//
//		Debuger.debug("TestSkill.releaseToEnemy() 施法方:"
//				+ (battle.getUnder() == enemy ? "Upper" : "Under") + " 施法者:"
//				+ attacker);
//
//		randomFilter(all);
//
//		for (Fighter f : all) {
//
//			int damage = attacker.getAttribute().getAttack()
//					- f.getAttribute().getDefend();
//
//			if (damage < 0) {
//
//				damage = Util.getRandomIntByMaxAndMin(10, 90);
//			}
//
//			f.reduceHp(damage);
//
//			int position = enemy.getPosition(f);
//
//			testAction.addSkillPoint(f.getHp(), f.getSp(), position,
//					battle.getUnder() == enemy);
//
//			Debuger.debug("		TestSkill.releaseToEnemy() 被打:" + attacker
//					+ " 位置:" + position + " 伤害值:" + damage + " 剩余血量:"
//					+ f.getHp());
//		}
//	}
//
//	private void randomFilter(final List<Fighter> all) {
//
//		final int count = Util.getRandomIntByMaxAndMin(1, 2);
//
//		Util.upset(all);
//
//		while (all.size() > count) {
//
//			all.remove(0);
//		}
//	}
//
//	@Override
//	public void releaseToFriend() {
//
//	}
//
//	@Override
//	public AttackAction getAttackAction() {
//
//		return testAction;
//	}
//
//	@Override
//	public int getId() {
//
//		// 10000 普通攻击
//		// 10001 加血
//
////		int[] a = new int[] { 10016, 10017, 10018};
//
//		int[] a = new int[] { 10000, /*10002, 10003, 10004, 10005,*/ 10006,
//				10007, 10008, 10009/*, 10010, 10011, 10012, 10013*/, 10014, /*10015,
//				10016, 10017*/};
//
//		return a[Util.R.nextInt(a.length)];
//	}
//
//	@Override
//	public int getLevel() {
//
//		return 1;
//	}
//
//}
