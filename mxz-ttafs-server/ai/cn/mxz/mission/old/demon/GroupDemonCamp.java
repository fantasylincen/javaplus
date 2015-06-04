package cn.mxz.mission.old.demon;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import cn.mxz.dogz.Dogz;
import cn.mxz.fighter.Fighter;

/**
 * 
 * 怪物阵容
 * 
 * @author 林岑
 * 
 */
class GroupDemonCamp extends AbstractDemonGroup {

	GroupDemonCamp(List<Demon> demons) {
		super(demons);
	}

	@Override
	public int getPosition(Fighter f) {
		
		Set<Entry<Integer, Demon>> s = demons.entrySet();

		for (Entry<Integer, Demon> entry : s) {
			Demon value = entry.getValue();
			if (value.equals(f)) {
				return entry.getKey();
			}
		}

		throw new IllegalArgumentException("不在阵形中!" + f);
	}
	
	@Override
	public int getShenJia() {
		int a = 0;
		for (Demon d : demons.values()) {
			a += d.getShenJia();
		}
		return a;
	}

	@Override
	public Dogz getDogz() {
		return null;
	}

}
