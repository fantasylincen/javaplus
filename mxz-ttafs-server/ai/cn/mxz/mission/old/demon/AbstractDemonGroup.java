package cn.mxz.mission.old.demon;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.mxz.battle.AbstractCamp;
import cn.mxz.fighter.Fighter;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public abstract class AbstractDemonGroup extends AbstractCamp<Demon> {

	protected Map<Integer, Demon>	demons;

	public AbstractDemonGroup(List<Demon> demons) {
		build(demons);

		if(demons.isEmpty()) {
			throw new IllegalArgumentException("demons列表为空!");
		}
	}

	@Override
	public String getUserId() {
		return null;
	}
	
	private void build(List<Demon> ds) {
		demons = Maps.newHashMap();
		List<Integer> ls = Lists.newArrayList(1, 3, 4, 5, 7);
		cn.javaplus.util.Util.Collection.upset(ls);

		Iterator<Integer> itPosition = ls.iterator();
		Iterator<Demon> it = ds.iterator();
		while (it.hasNext()) {
			Demon demon = (Demon) it.next();
			Integer position = itPosition.next();

			demons.put(position, demon);
		}
	}

	public void setVid(int vId) {

	}

	@Override
	public List<Demon> getFighters() {
		return Lists.newArrayList(demons.values());
	}

	@Override
	public Fighter getMainFighter() {
		return demons.get(0);
	}

}