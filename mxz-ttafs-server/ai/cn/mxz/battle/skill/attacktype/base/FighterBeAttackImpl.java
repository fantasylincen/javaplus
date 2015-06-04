package cn.mxz.battle.skill.attacktype.base;

import java.util.ArrayList;
import java.util.List;

import cn.mxz.Attribute;
import cn.mxz.battle.skill.FighterBeAttack;
import cn.mxz.fighter.Fighter;

public class FighterBeAttackImpl implements FighterBeAttack {

	private Fighter			target;

	private float			decay;

	private Attribute		attributeOld;

	private List<Integer>	bufferIdsOld	= new ArrayList<Integer>();

	private boolean			isHit			= true;

	private boolean			isCrit;

	private boolean			isBlock;

	@Override
	public Fighter getTarget() {
		return target;
	}

	@Override
	public float getDecay() {
		return decay;
	}

	@Override
	public void setTarget(Fighter target) {
		this.target = target;
	}

	@Override
	public void setAttributeOld(Attribute attributeOld) {
		this.attributeOld = attributeOld;
	}

	@Override
	public void setDecay(float decay) {
		this.decay = decay;
	}

	@Override
	public Attribute getOld() {
		return attributeOld;
	}

	@Override
	public boolean addBufferId(Integer bufferId) {
		return bufferIdsOld.add(bufferId);
	}

	@Override
	public List<Integer> getBufferIdsOld() {
		return bufferIdsOld;
	}

	@Override
	public boolean isCrit() {
		return isCrit;
	}

	@Override
	public boolean isHit() {
		return isHit;
	}

	@Override
	public boolean isBlock() {
		return isBlock;
	}

	@Override
	public void setBlock(boolean isBlock) {
		this.isBlock = isBlock;
	}

	@Override
	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}

	@Override
	public void setCrit(boolean isCrit) {
		this.isCrit = isCrit;
	}
}
