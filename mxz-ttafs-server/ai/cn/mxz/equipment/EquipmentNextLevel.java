package cn.mxz.equipment;


class EquipmentNextLevel extends EquipmentImpl {

	EquipmentNextLevel(Equipment e) {
		super(((EquipmentImpl)e).data);
	}

	@Override
	public int getLevel() {
		return super.getLevel() + 1;
	}
}
