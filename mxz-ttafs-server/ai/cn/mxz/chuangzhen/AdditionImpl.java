package cn.mxz.chuangzhen;

import db.domain.ChuangZhen;

public class AdditionImpl implements Addition {

	private ChuangZhen	dto;

	public AdditionImpl(ChuangZhen dto) {
		this.dto = dto;
	}

	@Override
	public float getAttack() {
		return dto.getAttack();
	}

//	@Override
//	public float getMAttack() {
//		return dto.getMAttack();
//	}

	@Override
	public float getDefend() {
		return dto.getDefend();
	}

//	@Override
//	public float getMDefend() {
//		return dto.getMDefend();
//	}

	@Override
	public float getHp() {
		return dto.getHp();
	}

	@Override
	public float getSpeed() {
		return dto.getSpeed();
	}

}
