package cn.mxz.activity.boss;

import cn.mxz.AdditionMultiplier;

public class AdditionMultiplierImpl implements AdditionMultiplier {

	private double	attributeX;

	public AdditionMultiplierImpl(double attributeX) {
		this.attributeX = attributeX;
	}

	@Override
	public float getAttackPar() {
		return (float) getBase();
	}

	private double getBase() {
		return attributeX;
	}

	@Override
	public float getDefendPar() {
		return (float) getBase();
	}

	@Override
	public float getHpPar() {
		return (float) getBase();
	}

	@Override
	public float getBlockPar() {
		return (float) getBase();
	}

	@Override
	public float getDodgePar() {
		return (float) getBase();
	}

	@Override
	public float getCritPar() {
		return (float) getBase();
	}

	@Override
	public float getMDefendPar() {
		return (float) getBase();
	}

	@Override
	public float getMAttackPar() {
		return (float) getBase();
	}

	@Override
	public float getSpeedPar() {
		return (float) getBase();
	}

	@Override
	public float getRBlockPar() {
		return (float) getBase();
	}

	@Override
	public float getHitPar() {
		return (float) getBase();
	}

	@Override
	public float getRCritPar() {
		return (float) getBase();
	}

	@Override
	public float getCritAdditionPar() {
		return (float) getBase();
	}

	@Override
	public float getMagicPar() {
		return (float) getBase();
	}

}
