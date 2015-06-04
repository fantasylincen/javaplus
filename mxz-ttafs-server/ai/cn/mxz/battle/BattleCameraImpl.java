package cn.mxz.battle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.Attribute;
import cn.mxz.battle.buffer.Buffer;
import cn.mxz.battle.skill.FighterBeAttack;
import cn.mxz.dogz.Dogz;
import cn.mxz.fighter.AttributeCalculator;
import cn.mxz.fighter.AttributeSetable;
import cn.mxz.fighter.Fighter;

@Component("battleCamera")
@Scope("prototype")
public class BattleCameraImpl implements BattleCamera {

	private SkillAction										action;

//	private DoubleKeyMap<Boolean, Integer, List<Integer>>	buffersOld	= new DoubleKeyMapImpl<Boolean, Integer, List<Integer>>();

	@Override
	public void snapshot(Fighter att, Battle battle, int skillId) {

		initAction(att, battle, skillId);
	}


	@Override
	public AttackAction contrast(List<FighterBeAttack> bs, Battle battle,
			List<Buffer> removes) {
		List<FighterRecord> allChanged = getFighterChangedAll(bs, battle);

		for (FighterRecord f : allChanged) {
			action.addSkillPoint(f);
		}

//		contrastBuffers(battle);
		addBufferRemoves(removes);
		setDogzAngryValue(battle);
		return action;
	}

	private void addBufferRemoves(List<Buffer> removes) {
		for (Buffer buffer : removes) {
			action.addDisappearBuffers(new BuffDisappearImpl(buffer.isUnder(), buffer.getPosition(), buffer.getId()));		
		}
	}


	private void setDogzAngryValue(Battle battle) {

		{
			Dogz d = battle.getUnderPlayerCamp().getCity().getDogzManager().getFighting();

			if (d != null) {
				action.setUnderDogzAngryValue(d.getAngryValue());
			}
		}

		{
			Camp<? extends Fighter> upper = battle.getUpper();

			if(upper instanceof BattleCamp) {

				Dogz dogz = ((BattleCamp) upper).getDogz();

				if (dogz != null) {

					action.setUpperDogzAngryValue(dogz.getAngryValue());
				}
			}

		}
	}

//	/**
//	 * 对比技能前后的Buffer
//	 *
//	 * @param battle
//	 */
//	private void contrastBuffers(Battle battle) {
//		contrastBuffers(battle.getUnder(), true);
//		contrastBuffers(battle.getUpper(), false);
//	}

//	private void contrastBuffers(Camp<? extends Fighter> camp, boolean isUnder) {
//		for (Fighter f : camp.getFighters()) {
//			BufferManager bm = f.getBufferManager();
//			int position = camp.getPosition(f);
//			List<Buffer> buffers = bm.getBuffers();
//			contrastDisappear(isUnder, position, buffers); // 对比出消失了的Buffer
//		}
//	}

//	/**
//	 * 
//	 * @param isUnder
//	 * @param position
//	 * @param buffers 当前这个战士身上的Buff
//	 */
//	private void contrastDisappear(boolean isUnder, int position, List<Buffer> buffers) {
//
//		List<Integer> oldBuffs = buffersOld.get(isUnder, position);
//
//		if (oldBuffs == null) {
//			oldBuffs = Lists.newArrayList();
//		}
//
//		for (Integer id : oldBuffs) {
//			if (!contains(id, buffers)) {
//				action.addDisappearBuffers(new BuffDisappearImpl(isUnder, position, id));
////				Debuger.debug("logic -- communication : BattleCameraImpl.contrastDisappear() 增加一个失效Buff " + BuffTempletConfig.get(id).getName());
//			}
//		}
//		
//	}

	private boolean contains(Integer id, List<Buffer> buffers) {
		for (Buffer buffer : buffers) {
			if (buffer.getId() == id) {
				return true;
			}
		}
		return false;
	}

	private void initAction(Fighter att, Battle battle, int skillId) {
		Camp<? extends Fighter> friend = battle.getFriends(att);
		boolean isUnder = friend == battle.getUnder();
		action = new SkillAction(isUnder, battle.getRound(), friend.getPosition(att), skillId, att instanceof Dogz);
//		saveBuffer(battle.getUnder(), true);
//		saveBuffer(battle.getUpper(), false);
	}

//	private void saveBuffer(Camp<? extends Fighter> camp, boolean isUnder) {
//		for (Fighter f : camp.getFighters()) {
//			List<Buffer> buffers = f.getBufferManager().getBuffers();
//			int position = camp.getPosition(f);
//			buffersOld.put(isUnder, position, build(buffers));
//		}
//	}

	private List<Integer> build(List<Buffer> buffers) {

		List<Integer> list = new ArrayList<Integer>();

		for (Buffer buffer : buffers) {

			list.add(buffer.getId());
		}

		return list;
	}

	/**
	 * 所有属性发生变化了的战士
	 *
	 * @param beAttacks
	 * @param battle
	 * @return
	 */
	private List<FighterRecord> getFighterChangedAll(List<FighterBeAttack> beAttacks, Battle battle) {

		List<FighterRecord> changed = new ArrayList<FighterRecord>();

		for (FighterBeAttack b : beAttacks) {

			changed.add(build(b, battle));
		}

		return changed;
	}

	private FighterRecord build(FighterBeAttack b, Battle battle) {

		Fighter fighter = b.getTarget();

		FighterRecord c = new FighterRecord();

		c.setOld(b.getOld());

		AttributeSetable copy = AttributeCalculator.copy(fighter.getAttribute());

		copy.setHp(fighter.getHpNow());

		c.setNow(copy);

		c.setNewBufferId(getNewBufferId(b, fighter));

		Camp<? extends Fighter> friends = battle.getFriends(fighter);

		c.setPosition(friends.getPosition(fighter));

		c.setUnder(battle.getUnder() == friends);

		c.setBlock(b.isBlock());
		c.setHit(b.isHit());
		c.setCrit(b.isCrit());

		return c;
	}

	private int getNewBufferId(FighterBeAttack b, Fighter fighter) {

		List<Buffer> buffers = fighter.getBufferManager().getBuffers();

		for (Buffer buffer : buffers) {

			if (!b.getBufferIdsOld().contains(buffer.getId())) {

				return buffer.getId();
			}
		}

		return -1;
	}

	/**
	 * 战士记录
	 *
	 * @author 林岑
	 *
	 */
	class FighterRecord {

		private boolean				isUnder;

		private int					position;

		private Attribute			old;

		private AttributeSetable	now;

		private int					newBufferId;

		private boolean				isCrit;

		private boolean				isBlock;

		private boolean				isHit;

		public void setCrit(boolean isCrit) {
			this.isCrit = isCrit;
		}

		public void setBlock(boolean isBlock) {
			this.isBlock = isBlock;
		}

		public void setHit(boolean isHit) {
			this.isHit = isHit;
		}

		public void setUnder(boolean isUnder) {
			this.isUnder = isUnder;
		}

		public void setNow(AttributeSetable now) {
			this.now = now;
		}

		public void setNewBufferId(int newBufferId) {
			this.newBufferId = newBufferId;
		}

		public void setOld(Attribute old) {
			this.old = old;
		}

		public void setPosition(int position) {
			this.position = position;
		}

		public boolean isUnder() {
			return isUnder;
		}

		public int getPosition() {
			return position;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + (isUnder ? 1231 : 1237);
			result = prime * result + position;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			FighterRecord other = (FighterRecord) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (isUnder != other.isUnder)
				return false;
			if (position != other.position)
				return false;
			return true;
		}

		private BattleCameraImpl getOuterType() {
			return BattleCameraImpl.this;
		}

		public int getAttackAdd() {
			return now.getAttack() - old.getAttack();
		}

		public int getDefendAdd() {
			return now.getDefend() - old.getDefend();
		}

		public int getMAttackAdd() {
			return now.getMAttack() - old.getMAttack();
		}

		public int getMDefendAdd() {
			return now.getMDefend() - old.getMDefend();
		}

		public int getCritAdd() {
			return now.getCrit() - old.getCrit();
		}

		public int getDodgeAdd() {
			return now.getDodge() - old.getDodge();
		}

		public int getSpeedAdd() {
			return now.getSpeed() - old.getSpeed();
		}

		public int getHpNow() {
			return now.getHp();
		}

		/**
		 * 新生成的BufferID
		 *
		 * @return
		 */
		public int getNewBufferId() {
			return newBufferId;
		}

		public boolean isCrit() {
			return isCrit;
		}

		public boolean isBlock() {
			return isBlock;
		}

		public boolean isHit() {
			return isHit;
		}
	}
}
