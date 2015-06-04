package cn.mxz.battle.buff;

import java.util.concurrent.atomic.AtomicLong;

import cn.mxz.Attribute;
import cn.mxz.BuffTemplet;
import cn.mxz.BuffTempletConfig;
import cn.mxz.battle.Battle;
import cn.mxz.battle.BuffEffect;
import cn.mxz.battle.WarSituation;
import cn.mxz.battle.buffer.Buffer;
import cn.mxz.fighter.AttributeEmpty;
import cn.mxz.fighter.AttributeSetable;
import cn.mxz.fighter.Fighter;
import cn.mxz.team.Skill;
import cn.mxz.util.debuger.Debuger;

public class BufferImpl implements Buffer {

	private BuffTemplet temp;

	/**
	 * Buff持续回合数
	 */
	private int round = 0;

	private Fighter target;

	private long idf;

	private int position;

	private boolean isUnder;

	private static AtomicLong ids = new AtomicLong();

	public BufferImpl(int id, Fighter target, Skill skill, boolean isUnder,
			int position) {
		this.isUnder = isUnder;
		this.position = position;
		this.temp = BuffTempletConfig.get(id);
		if (temp == null) {
			throw new NullPointerException(id + "");
		}
		this.target = target;
		idf = ids.addAndGet(1);
	}

	/**
	 * 唯一标识符
	 */
	@Override
	public long getIdfen() {
		return idf;
	}

	@Override
	public int getId() {
		return temp.getId();
	}

	@Override
	public boolean canHit() {
		return temp.getCanHit() == 1;
	}

	@Override
	public boolean isLose() {
		int r = getRound();
		int rounds = temp.getRounds();
		return r >= rounds;
	}

	@Override
	public void newRound(WarSituation situation, int currentRound) {
		this.round++;
		int hpReduce = temp.getHpReduce() + (int) (temp.getHpReduceScale() * target
				.getBaseAttribute().getHp());
		if (hpReduce > 0 && !target.isDeath()) {
			target.reduceHp(hpReduce);
//			if(target.isDeath()) {
//				Debuger.debug("BufferImpl.newRound() 被Buff流血死了: " + target.getName());
//			}
			addBuffEfect(currentRound, situation);
		}
	}

	private void addBuffEfect(int currentRound, WarSituation situation) {

		BuffEffect e = new BuffEffect();
		Attribute a = target.getAttribute();

		e.setAttack(a.getAttack());
		e.setBufferId(temp.getId());
		e.setCrit(a.getCrit());
		e.setDefend(a.getDefend());
		e.setDodge(a.getDodge());
		e.setHp(target.getHpNow());
		e.setmAttack(a.getMAttack());
		e.setmDefend(a.getMDefend());
		e.setPosition(position);
		e.setRound(currentRound);
		e.setSpeed(a.getSpeed());
		e.setUnder(isUnder);
		
		int hpNow = target.getHpNow();
		
		if(hpNow <= 0) {
			Debuger.debug("BufferImpl.addBuffEfect() 流血身亡:" + target.getName());
		}

		situation.addEffect(e);
	}

	public int getRound() {
		return round;
	}

	@Override
	public String toString() {
		return "[ round = " + round + ", idf = " + idf + ", name = "
				+ temp.getName() + "]";
	}

	@Override
	public Attribute getAddition() {

		AttributeSetable a = new AttributeEmpty();
		int w = temp.getWaterEffect();

		// 降低双防
		a.setDefend(a.getDefend() - temp.getLowerDefense());
		a.setMDefend(a.getMDefend() - temp.getLowerDefense());
		float par = temp.getLowerDefensePar();
		if (Math.abs(par) < 0.00001) {
			a.setDefend((int) (a.getDefend() - a.getDefend() * par));
			a.setMDefend((int) (a.getMDefend() - a.getMDefend() * par));
		}

		// 格挡闪避为0 最后出手
		if (w == 1) {
			a.setBlock(-1000000);
			a.setDodge(-1000000);
			a.setSpeed(-1000000);
		}

		return a;
	}

	@Override
	public void beforeSkill(Battle battle) {
//		reduceHp(); // 每回合掉血
	}

//	private void reduceHp() {
//		target.reduceHp(temp.getHpReduce());
//		target.reduceHp((int) (temp.getHpReduceScale() * target
//				.getBaseAttribute().getHp()));
//	}

	@Override
	public boolean isNormalAttackOnly() {
		return temp.getReleaseSkill() == 1;
	}

	@Override
	public boolean isUnder() {
		return isUnder;
	}

	@Override
	public int getPosition() {
		return position;
	}
}
