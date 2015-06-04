package cn.mxz.chuangzhen;

import cn.mxz.AdditionMultiplier;

public class ChuangZhenAdditionAdaptor implements AdditionMultiplier {

	private ChuangZhenPlayer	player;

	public ChuangZhenAdditionAdaptor(ChuangZhenPlayer player) {
		this.player = player;
	}

	@Override
	public float getAttackPar() {
		return player.getAddition().getAttack() + 1;
	}

	@Override
	public float getDefendPar() {
		return player.getAddition().getDefend() + 1;
	}

	@Override
	public float getHpPar() {
		return player.getAddition().getHp() + 1;
	}

	@Override
	public float getBlockPar() {
		return 1;
	}

	@Override
	public float getDodgePar() {
		return 1;
	}

	@Override
	public float getCritPar() {
		return 1;
	}
	@Override
	public float getMAttackPar() {
		return player.getAddition().getAttack() + 1;
	}

	@Override
	public float getMDefendPar() {
		return player.getAddition().getDefend() + 1;
	}

	@Override
	public float getSpeedPar() {
		return player.getAddition().getSpeed() + 1;
	}

	@Override
	public float getRBlockPar() {
		return 1;
	}

	@Override
	public float getHitPar() {
		return 1;
	}

	@Override
	public float getRCritPar() {
		return 1;
	}

	@Override
	public float getCritAdditionPar() {
		return 1;
	}

	@Override
	public float getMagicPar() {
		return 1;
	}

}
