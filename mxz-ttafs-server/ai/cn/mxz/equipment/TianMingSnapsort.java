package cn.mxz.equipment;

import java.util.List;

import cn.mxz.events.Events;
import cn.mxz.events.equipment.TianMingChangeEvent;
import cn.mxz.user.team.god.Hero;

import com.google.common.collect.Lists;

public class TianMingSnapsort {

	private Hero hero;
	private List<Integer> htm;
	private List<Integer> stm;
	private List<Integer> etm;

	public TianMingSnapsort(Hero hero) {
		this.hero = hero;
	}

	public void snapsort() {

		if (htm == null) {
			init();
		} else {
			dispatchChangeEvent();
		}
	}

	private void dispatchChangeEvent() {
		List<Integer> htmAdd = Lists.newArrayList(hero.getTianMing()
				.getTianMingIds());
		List<Integer> stmAdd = Lists.newArrayList(hero.getSkillTianMing()
				.getIds());
		List<Integer> etmAdd = Lists.newArrayList(hero.getEquipmentTianMing()
				.getTianMingIds());

		htmAdd.removeAll(this.htm);
		stmAdd.removeAll(this.stm);
		etmAdd.removeAll(this.etm);

		List<Integer> htmRemove = Lists.newArrayList(this.htm);
		List<Integer> stmRemove = Lists.newArrayList(this.stm);
		List<Integer> etmRemove = Lists.newArrayList(this.etm);

		htmRemove.removeAll(hero.getTianMing().getTianMingIds());
		stmRemove.removeAll(hero.getSkillTianMing().getIds());
		etmRemove.removeAll(hero.getEquipmentTianMing().getTianMingIds());

		if (!htmAdd.isEmpty() || !etmAdd.isEmpty() || !stmAdd.isEmpty()
				|| !etmRemove.isEmpty() || !stmRemove.isEmpty()
				|| !htmRemove.isEmpty()) {
			Events.getInstance().dispatch(
					new TianMingChangeEvent(hero.getCity(), htmAdd, stmAdd,
							etmAdd, htmRemove, stmRemove, etmRemove));
		}
	}

	private void init() {
		htm = hero.getTianMing().getTianMingIds();
		stm = hero.getSkillTianMing().getIds();
		etm = hero.getEquipmentTianMing().getTianMingIds();
	}

}
