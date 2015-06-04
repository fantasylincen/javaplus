package cn.mxz.mission.old.demon;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cn.mxz.MapTemplet;
import cn.mxz.dogz.Dogz;
import cn.mxz.fighter.Fighter;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class DemonCampContainsBoss extends AbstractDemonGroup {

	private MapTemplet	mapTemplet;
	private Map<Integer, Demon> pos = Maps.newHashMap();

	public DemonCampContainsBoss(List<Demon> demons, MapTemplet temp) {
		super(demons);

		Iterator<Integer> itBoss = Lists.newArrayList(7, 6, 8, 5, 9).iterator();	//Boss位置分配序列
		Iterator<Integer> itDemon = Lists.newArrayList(1, 3, 0, 4, 2).iterator();	//Boss位置分配序列

		mapTemplet = temp;

		for (Demon d : demons) {
			int p;
			if(isBoss(d)) {
				p = itBoss.next();
			} else {
				p = itDemon.next();
			}
			this.pos.put(p, d);
		}
	}

	private boolean isBoss(Demon d) {
		String bossId = mapTemplet.getBossId();
		String typeId = d.getTypeId() + "";
		return bossId.contains(typeId);
	}

	@Override
	public int getPosition(Fighter f) {
		Set<Entry<Integer, Demon>> s = pos.entrySet();
		for (Entry<Integer, Demon> entry : s) {
			Demon d = entry.getValue();
			Integer k = entry.getKey();
			if(d.equals(f)) {
				return k;
			}
		}
		throw new IllegalArgumentException("未找到怪物!" + f);
	}

	@Override
	public int getShenJia() {
		int a = 0;
		for (Demon d : pos.values()) {
			a += d.getShenJia();
		}
		return a;
	}

	@Override
	public Dogz getDogz() {
		return null;
	}

}
