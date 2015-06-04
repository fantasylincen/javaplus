package cn.mxz.equipment;

import java.util.List;

import com.google.common.collect.Lists;

public class EquipmentHasUIImpl implements EquipmentHasUI {

	private final class EquipmentResultImpl implements EquipmentResult {
		private final boolean	containsKey;
		private final Integer	id;

		private EquipmentResultImpl(boolean containsKey, Integer id) {
			this.containsKey = containsKey;
			this.id = id;
		}

		@Override
		public boolean isExist() {
			return containsKey;
		}

		@Override
		public int getEquipmentId() {
			return id;
		}
	}

	private List<EquipmentResult>	list	= Lists.newArrayList();

	@Override
	public List<EquipmentResult> getResult() {
		return list;
	}

	public void add(final Integer id, final boolean containsKey) {
		list.add(new EquipmentResultImpl(containsKey, id));
	}

}
