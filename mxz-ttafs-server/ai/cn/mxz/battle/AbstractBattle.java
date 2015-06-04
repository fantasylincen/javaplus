package cn.mxz.battle;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.mxz.Attribute;
import cn.mxz.FighterExpPrize;
import cn.mxz.FighterTempletConfig;
import cn.mxz.IFighterTemplet;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.battle.buffer.Buffer;
import cn.mxz.battle.skill.FighterBeAttack;
import cn.mxz.city.City;
import cn.mxz.dogz.Dogz;
import cn.mxz.events.BeAttackEvent;
import cn.mxz.events.Events;
import cn.mxz.events.FightEndEvent;
import cn.mxz.events.FightingLoseEvent;
import cn.mxz.events.FightingStartEvent;
import cn.mxz.events.FightingWinEvent;
import cn.mxz.fighter.BattleUnionSkill;
import cn.mxz.fighter.Fighter;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import define.D;

public abstract class AbstractBattle implements Battle {

	private static final Events ET = Events.getInstance();

	/**
	 * 连携技能 记录是否出过招
	 * 
	 * @author 林岑
	 * 
	 */
	class UnionSkillManager {

		Map<Integer, List<Fighter>> map = Maps.newHashMap();

		public boolean hasTrigerThisRound(Fighter f) {
			List<Fighter> ls = map.get(getRound());
			if (ls == null) {
				return false;
			}

			return ls.contains(f);
		}

		public void saveAttack(List<Fighter> fighters) {
			int r = getRound();

			List<Fighter> list = map.get(r);
			if (list != null) {
				list.addAll(fighters);
			} else {
				map.put(r, fighters);
			}

		}

	}

	public static class MissionPrize {

		private boolean isEmpty;
		private int id;
		private int count;
		private float probaility;

		public void setIsEmpty(boolean isEmpty) {
			this.isEmpty = isEmpty;

		}

		public void setId(int id) {
			this.id = id;

		}

		public void setCount(int count) {
			this.count = count;
		}

		public boolean isEmpty() {
			return isEmpty;
		}

		public int getId() {
			return id;
		}

		public int getCount() {
			return count;
		}

		public float getProbability() {
			return probaility;
		}

		public void setProbaility(float probaility) {
			this.probaility = probaility;
		}

		@Override
		public String toString() {
			return "MissionPrize [isEmpty=" + isEmpty + ", id=" + id
					+ ", count=" + count + ", probaility=" + probaility + "]";
		}

	}

	/**
	 * 上面的阵营
	 */
	private BattleCamp upper;

	/**
	 * 下面的阵营
	 */
	private BattleCamp under;

	/**
	 * 战况信息
	 */
	private WarSituationImpl situation;

	private byte currentRound = 0;

	private AttackSelector selector;

	private UnionSkillManager unionSkillManager = new UnionSkillManager();

	private PlayerCamp underPlayer;

	private PlayerCamp upperPlayer;

	protected AbstractBattle(PlayerCamp under, Camp<? extends Fighter> upper) {

		underPlayer = under;

		if (upper instanceof PlayerCamp) {
			upperPlayer = (PlayerCamp) upper;
		}

		this.under = new BattleCampImpl(under, isUpHp());

		this.upper = new BattleCampImpl(upper, isUpHp());

		if (this.under.getLivesCount() < 1) {

			Debuger.error("AbstractBattle.AbstractBattle() 攻击方死完了!");

			throw new SureIllegalOperationException("攻击方死完了");
		}

		if (this.upper.getLivesCount() < 1) {

			throw new SureIllegalOperationException("-守方战士已经死完");
		}

//		for (Fighter f : upper.getFighters()) {
//			Debuger.debug("AbstractBattle.AbstractBattle()" + f.getHpNow());
//		}
//		
		init();

		initWarsituation();
	}

	protected boolean isUpHp() {
		return true;
	}

	@Override
	public PlayerCamp getUpperPlayerCamp() {
		return upperPlayer;
	}

	@Override
	public PlayerCamp getUnderPlayerCamp() {

		return underPlayer;
	}

	@Override
	public BattleCamp getUnder() {
		return under;
	}

	@Override
	public boolean isWin() {

		return situation.isWin();
	}

	@Override
	public void fighting() {

		ET.dispatch(new FightingStartEvent(this, underPlayer, upperPlayer));

		while (!needEnd()) {

			List<Buffer> removes = newRound();
			
			removes = Lists.newArrayList(removes);
			
			while (!needEnd() && selector.hasNext()) {

				final Fighter attacker = selector.next();

				boolean death = attacker.isDeath();

				boolean canHit = attacker.getBufferManager().canHit();

				if (!death && canHit) {

					attacker.getBufferManager().beforeSkill(this);

					BattleUnionSkill us = attacker.createUnionSkill(this);

					SkillInBattle skill;

					
					
					
					final BattleCamera bc = new BattleCameraImpl();
					
					
					List<FighterBeAttack> beAttacks;
					if (us != null) {
						skill = us;
						bc.snapshot(attacker, this, skill.getId()); // 战场快照
						beAttacks = us.fire();

						unionSkillManager.saveAttack(us.getFighters());
					} else {
						skill = attacker.createSkill(this);
						
						bc.snapshot(attacker, this, skill.getId()); // 战场快照
						beAttacks = skill.fire();
					}
					
					AttackAction action = bc.contrast(beAttacks, this, removes);
					getWarSituation().add(action);// 与之前快照进行对比
					
					

					tryToCounterAttack(beAttacks, attacker);

					removes.clear();
					
					ET.dispatch(new BeAttackEvent(beAttacks, attacker, this, skill));
					
				}
			}
		}

		markWarSituationResult();

		init();

		City city = underPlayer.getCity();
		if (isWin()) {

			FightingWinEvent e = new FightingWinEvent(this, currentRound, city,
					upper);
			ET.dispatch(e);

		} else {

			ET.dispatch(new FightingLoseEvent(this, city));
		}

		ET.dispatch(new FightEndEvent(this, city));
	}

	/**
	 * 尝试反击
	 * 
	 * @param beAttacks
	 * @param attacker
	 */
	private void tryToCounterAttack(List<FighterBeAttack> beAttacks,
			Fighter attacker) {
		if (attacker.isDeath() || attacker instanceof Dogz) {
			// Debuger.debug("AbstractBattle.tryToCounterAttack() 攻击方死了, 不反击!");
			return;
		}

		for (FighterBeAttack beAttack : beAttacks) {

			Fighter target = beAttack.getTarget(); //反击者

			if (target instanceof Dogz) {
				continue;
			}

			if(!target.getBufferManager().canHit()) {
				continue;
			}
			
			if (!target.isDeath() && beAttack.isBlock()
					&& isEnemy(beAttack, attacker) && isFront(beAttack)) {
				SkillInBattle skill = new CounterAttackSkillImpl(this, target,
						attacker);
				skill.fire();
			}
		}
	}

	private boolean isFront(FighterBeAttack beAttack) {
		BattleCamp friends = getFriends(beAttack.getTarget());
		int position = friends.getPosition(beAttack.getTarget());
		return position == 1;
	}

	/**
	 * 判断两者是否是敌人
	 * 
	 * @param beAttack
	 * @param attacker
	 * @return
	 */
	private boolean isEnemy(FighterBeAttack beAttack, Fighter attacker) {
		Fighter beA = beAttack.getTarget();
		boolean a = upper.contains(beA);
		boolean b = upper.contains(attacker);
		return a != b;
	}

	private List<Buffer> newRound() {

		currentRound++;

		selector.reset();

//		Debuger.debug("logic: AbstractBattle.newRound() round = " + getRound());
		
		return allBufferToNewRound();
	}

	private List<Buffer> allBufferToNewRound() {

		List<Buffer> ls = Lists.newArrayList();
		for (Fighter f : upper.getFighters()) {

			List<Buffer> removes = f.getBufferManager().newRound(situation, currentRound);
			ls.addAll(removes);
		}

		for (Fighter f : under.getFighters()) {

			List<Buffer> removes = f.getBufferManager().newRound(situation, currentRound);
			ls.addAll(removes);
		}
		return ls;
	}

	/**
	 * 战斗是否该结束了
	 * 
	 * @return
	 */
	private boolean needEnd() {

		final boolean isToRoundLimit = currentRound > getMaxRound();

		final boolean underDeathAll = under.isAllDeath();

		final boolean upperDeathAll = upper.isAllDeath();

		final boolean needEnd = isToRoundLimit || underDeathAll
				|| upperDeathAll;

		// if (needEnd) {
		//
		// Debuger.debug("AbstractBattle.needEnd() 战斗结束:" + " isToRoundLimit:" +
		// isToRoundLimit + ", underDeathAll:" + underDeathAll +
		// ", upperDeathAll:" + upperDeathAll);
		// }

		return needEnd;
	}

	protected int getMaxRound() {
		return D.MAX_ROUND;
	}

	private void markWarSituationResult() {

		final boolean upperDeath = upper.isAllDeath();

		situation.setIsWin(upperDeath);
	}

	private void init() {

		under.init();

		upper.init();

		selector = new SelectorBySpeedOnly();
	}

	private void initWarsituation() {

		situation = new WarSituationImpl(this);

		situation.saveFormation(getUnderPlayerCamp(), upper);
	}

	/**
	 * @return 防御方阵营
	 */
	@Override
	public BattleCamp getUpper() {
		return upper;
	}

	/**
	 * 获得敌方阵营
	 * 
	 * @param f
	 * @return
	 */
	@Override
	public BattleCamp getEnemy(Fighter f) {
		return under.contains(f) ? upper : under;
	}

	/**
	 * 获得友方阵营
	 * 
	 * @param f
	 * @return
	 */
	@Override
	public BattleCamp getFriends(Fighter f) {
		return under.contains(f) ? under : upper;
	}

	@Override
	public final WarSituation getWarSituation() {
		return situation;
	}

	@Override
	public int getRound() {
		return currentRound;
	}

	protected abstract FighterExpPrize getMapTemplet();

	/**
	 * 该地图是否包含Boss
	 * 
	 * @return
	 */
	protected boolean containsBoss() {

		Camp<? extends Fighter> up = getUpper();

		FighterExpPrize mapTemplet = getMapTemplet();

		for (Fighter f : up.getFighters()) {

			if (mapTemplet.getBossId().contains(f.getTypeId() + "")) {

				return true;
			}
		}

		return false;
	}

	/**
	 * 完全按照速度来决定出手顺序的出手顺序 比较器
	 * 
	 * 黄必生喊改的(留证据)
	 * 
	 * @author 林岑
	 * 
	 */
	private class SelectorBySpeedOnly implements AttackSelector {

		private Iterator<Fighter> iterator;
		private Dogz upperDogz;
		private Dogz underDogz;

		public SelectorBySpeedOnly() {
			reset();

			upperDogz = upper.getDogz();
			underDogz = under.getDogz();
		}

		@Override
		public void reset() {
			List<Fighter> fighters;
			fighters = Lists.newArrayList();
			fighters.addAll(buildFighters(under));
			fighters.addAll(buildFighters(upper));

			sort(fighters);
			
			iterator = fighters.iterator();

		}

		private Collection<? extends Fighter> buildFighters(
				final Camp<? extends Fighter> upper) {
			List<? extends Fighter> fighters = Lists.newArrayList(upper
					.getFighters());

			Collections.sort(fighters, new Comparator<Fighter>() {

				@Override
				public int compare(Fighter o1, Fighter o2) {
					return upper.getPosition(o2) - upper.getPosition(o1);
				}
			});
			return fighters;
		}

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public Fighter next() {

			if (underDogz != null && underDogz.isAngry()) {
				return underDogz;
			}
			if (upperDogz != null && upperDogz.isAngry()) {
				return upperDogz;
			}

			return next(iterator);
		}

		/**
		 * 取得下一个活着的战士, 如果没有了, 就返回null
		 */
		private Fighter next(Iterator<Fighter> it) {

			Fighter f = null;

			while (it.hasNext()) {

				f = it.next();

				if (!f.isDeath() && !unionSkillManager.hasTrigerThisRound(f)) {

					break;
				}
			}

			return f;
		}
	}

	private void sort(List<Fighter> fs) {

		Collections.sort(fs, new Comparator<Fighter>() {

			@Override
			public int compare(Fighter o1, Fighter o2) {

				Attribute attribute = o1.getAttribute();
				if (attribute == null) {
					throw new NullPointerException(o1.getClass() + "");
				}
				int speed1 = attribute.getSpeed();
				int speed2 = o2.getAttribute().getSpeed();

				speed1 += getD(o1);
				speed2 += getD(o2);

				return speed2 - speed1;
			}

			private int getD(Fighter o) {
				IFighterTemplet t = FighterTempletConfig.get(o.getTypeId());
				int c = t.getCategory();

				if (c == 2 || c == 3 || c == 4) { // 主角 Boss 伙伴 优先于其他战士出手
					return 100000;
				}
				return 0;

			}
		});
	}
}
