package cn.mxz.mission.old;

import cn.mxz.INeedsInExcel;
import cn.mxz.util.needs.Deductor;

class DeductorFactory {

	/**
	 * 尽量多的扣除消耗, 不够扣
	 * @param temp
	 * @return
	 */
	static Deductor getDeductorAsMuch(INeedsInExcel temp) {

		return new DeductorAsMuchImpl(temp);
	}

}
