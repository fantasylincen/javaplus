package cn.mxz.chuangzhen;

import cn.mxz.CopterBuffTemplet;

public class AdditionImpl2 implements Addition2 {

	private CopterBuffTemplet	temp;

	public AdditionImpl2(CopterBuffTemplet temp) {
		this.temp = temp;
	}
//
//	@Override
//	public float getAttack() {
//		int f = temp.getFront();
//
//		float frontPar = temp.getFrontPar();
//
//		if (f == 1)
//			return frontPar;
//
//		return 0;
//	}
//
//	@Override
//	public float getMAttack() {
//		int f = temp.getFront();
//
//		float frontPar = temp.getFrontPar();
//
//		if (f == 2)
//			return frontPar;
//
//		return 0;
//	}
//
//	@Override
//	public float getDefend() {
//		int f = temp.getFront();
//
//		float frontPar = temp.getFrontPar();
//
//		if (f == 3)
//			return frontPar;
//
//		return 0;
//	}
//
//	@Override
//	public float getMDefend() {
//		int f = temp.getFront();
//
//		float frontPar = temp.getFrontPar();
//
//		if (f == 4)
//			return frontPar;
//
//		return 0;
//	}
//
//	@Override
//	public float getHp() {
//		int f = temp.getFront();
//
//		float frontPar = temp.getFrontPar();
//
//		if (f == 0)
//			return frontPar;
//
//		return 0;
//	}
//
//	@Override
//	public float getSpeed() {
//		int f = temp.getFront();
//
//		float frontPar = temp.getFrontPar();
//
//		if (f == 5)
//			return frontPar;
//
//		return 0;
//	}

	@Override
	public int getId() {
		return temp.getId();
	}

	@Override
	public float getAdd() {
		return temp.getFrontPar();
	}

}
