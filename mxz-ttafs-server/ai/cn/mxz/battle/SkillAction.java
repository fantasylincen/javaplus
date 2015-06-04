package cn.mxz.battle;

import java.util.ArrayList;
import java.util.List;

import cn.mxz.battle.BattleCameraImpl.FighterRecord;

/**
 *
 * 技能作用效果
 *
 * @author 林岑
 *
 */
class SkillAction implements AttackAction {

	private boolean isUnder;

	private final List<SkillPoint> points = new ArrayList<SkillPoint>();

	private int round;

	private int position;

	private int skillId;

	private List<BuffDisappear>	buffDisappears = new ArrayList<BuffDisappear>();

	private int	partnerPosition = -1;

	private boolean	isDogz;

	private int	underDogzAngryValue;

	private int	upperDogzAngryValue;

	private boolean	isCounterAttack;


	SkillAction(final boolean isUnder, final int round, final int position, final int skillId, boolean isDogz) {

		this.isUnder = isUnder;

		this.round = round;

		this.position = position;

		this.skillId = skillId;
		this.isDogz = isDogz;
		
	}

	@Override
	public List<SkillPoint> getSkillPoints() {
		return points;
	}

	@Override
	public boolean isAttackerUnder() {
		return isUnder;
	}

	@Override
	public int getSkillId() {
		return skillId;
	}

	@Override
	public int getRound() {

		return round;
	}

	@Override
	public int getPosition() {

		return position;
	}

	public void addSkillPoint(final FighterRecord f) {

		points.add(new SkillPoint() {

			@Override
			public int getPosition() {

				return f.getPosition();
			}

			@Override
			public int getHpEnd() {

				return f.getHpNow();
			}

			@Override
			public boolean isUnder() {

				return f.isUnder();
			}

			@Override
			public int getAttack() {
				return f.getAttackAdd();
			}

			@Override
			public int getDefend() {
				return f.getDefendAdd();
			}

			@Override
			public int getMAttack() {
				return f.getMAttackAdd();
			}

			@Override
			public int getMDefend() {
				return f.getMDefendAdd();
			}

			@Override
			public int getCrit() {
				return f.getCritAdd();
			}

			@Override
			public int getDodge() {
				return f.getDodgeAdd();
			}

			@Override
			public int getSpeed() {
				return f.getSpeedAdd();
			}

			@Override
			public int getBufferId() {
				return f.getNewBufferId();
			}

			@Override
			public boolean isCrit() {
				return f.isCrit();
			}

			@Override
			public boolean isBlock() {
				return f.isBlock();
			}

			@Override
			public boolean isHit() {
				return f.isHit();
			}
		});
	}

	@Override
	public List<BuffDisappear> getBuffDisappears() {

		return buffDisappears;
	}

	public void addDisappearBuffers(BuffDisappear buffDisappearImpl) {

		buffDisappears.add(buffDisappearImpl);
	}

	@Override
	public int getPartnerPosition() {
		return partnerPosition;
	}

	@Override
	public boolean isDogz() {
		return isDogz;
	}

	@Override
	public int getUnderDogzAngryValue() {
		return underDogzAngryValue;
	}

	@Override
	public int getUpperDogzAngryValue() {
		return upperDogzAngryValue;
	}

	@Override
	public boolean isCounterAttack() {
		return isCounterAttack;
	}

	public void setUnderDogzAngryValue(int underDogzAngryValue) {
		this.underDogzAngryValue = underDogzAngryValue;
		
	}

	public void setUpperDogzAngryValue(int upperDogzAngryValue) {
		this.upperDogzAngryValue = upperDogzAngryValue;
	}

	@Override
	public void setIsCounterAttack(boolean isCounterAttack) {
		this.isCounterAttack = isCounterAttack;
	}
}