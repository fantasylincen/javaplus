package cn.mxz.equipment;

import java.util.List;

import message.S;
import cn.mxz.EquipmentTemplet;
import cn.mxz.EquipmentTempletConfig;
import cn.mxz.base.exception.OperationFaildException;


/**
 * 进阶后的装备
 * @author 林岑
 *
 */
class EquipmentAfterLevelUp extends EquipmentImpl implements Equipment {

	private int typeId;

	EquipmentAfterLevelUp(EquipmentImpl e) {
		super(e.data);

		List<EquipmentTemplet> findBySpot = EquipmentTempletConfig.findBySpot(e.getTemplet().getSpot());

		for (EquipmentTemplet equipmentTemplet : findBySpot) {

			int quality = equipmentTemplet.getQuality();

			if (quality == e.getStep() + 1) {

				this.typeId = equipmentTemplet.getId();

				return;
			}
		}
		throw new OperationFaildException(S.S10178);
	}

	@Override
	public int getTypeId() {

		return typeId;
	}
}
