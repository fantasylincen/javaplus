package cn.mxz.base;

import cn.mxz.AdditionMultiplierSetable;

/**
 * 天赋
 * 
 * @author 林岑
 * @time 2013-3-23
 */
public class TalentEmpty implements Talent, AdditionMultiplierSetable {
	private float	hpPar;

	@Override
	public float getHpPar() {
		return hpPar;
	}

	@Override
	public void setHpPar(float value) {
		this.hpPar = value;
	}

	private float	attackPar;

	@Override
	public float getAttackPar() {
		return attackPar;
	}

	@Override
	public void setAttackPar(float value) {
		this.attackPar = value;
	}

	private float	mAttackPar;

	@Override
	public float getMAttackPar() {
		return mAttackPar;
	}

	@Override
	public void setMAttackPar(float value) {
		this.mAttackPar = value;
	}

	private float	defendPar;

	@Override
	public float getDefendPar() {
		return defendPar;
	}

	@Override
	public void setDefendPar(float value) {
		this.defendPar = value;
	}

	private float	mDefendPar;

	@Override
	public float getMDefendPar() {
		return mDefendPar;
	}

	@Override
	public void setMDefendPar(float value) {
		this.mDefendPar = value;
	}

	private float	speedPar;

	@Override
	public float getSpeedPar() {
		return speedPar;
	}

	@Override
	public void setSpeedPar(float value) {
		this.speedPar = value;
	}

	private float	critPar;

	@Override
	public float getCritPar() {
		return critPar;
	}

	@Override
	public void setCritPar(float value) {
		this.critPar = value;
	}

	private float	dodgePar;

	@Override
	public float getDodgePar() {
		return dodgePar;
	}

	@Override
	public void setDodgePar(float value) {
		this.dodgePar = value;
	}

	private float	blockPar;

	@Override
	public float getBlockPar() {
		return blockPar;
	}

	@Override
	public void setBlockPar(float value) {
		this.blockPar = value;
	}

	private float	rCritPar;

	@Override
	public float getRCritPar() {
		return rCritPar;
	}

	@Override
	public void setRCritPar(float value) {
		this.rCritPar = value;
	}

	private float	hitPar;

	@Override
	public float getHitPar() {
		return hitPar;
	}

	@Override
	public void setHitPar(float value) {
		this.hitPar = value;
	}

	private float	rBlockPar;

	@Override
	public float getRBlockPar() {
		return rBlockPar;
	}

	@Override
	public void setRBlockPar(float value) {
		this.rBlockPar = value;
	}

	private float	critAdditionPar;

	@Override
	public float getCritAdditionPar() {
		return critAdditionPar;
	}

	@Override
	public void setCritAdditionPar(float value) {
		this.critAdditionPar = value;
	}

	private float	magic;
	@Override
	public float getMagicPar() {
		return magic;
	}

	@Override
	public void setMagic(float value) {
		magic = value;		
	}

}
