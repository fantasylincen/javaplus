package cn.mxz.daji;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.javaplus.util.Util;
import cn.mxz.CustodianMapTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.battle.AbstractCamp;
import cn.mxz.city.City;
import cn.mxz.dogz.Dogz;
import cn.mxz.fighter.Fighter;
import cn.mxz.mission.old.demon.Demon;
import cn.mxz.mission.old.demon.DemonImpl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

class CustodianMapCamp extends AbstractCamp<Demon> {

	private CustodianMapTemplet	temp;

	private Map<Integer, Demon>	demons;

	private final int			monsterCount;


	CustodianMapCamp(CustodianMapTemplet temp, int monsterCount, City city ) {
		this.temp = temp;

		this.monsterCount = monsterCount;
		buildDemons(temp.getMonsterId().split(","), city);

	}

	private void buildDemons(String[] split, City city) {

		demons = Maps.newHashMap();

		List<Integer> ls = Lists.newArrayList(1, 3, 4, 5, 7);

		cn.javaplus.util.Util.Collection.upset(ls);

		Iterator<Integer> it = ls.iterator();

		for( int i = 0; i < monsterCount; i++ ){

			int index = Util.Random.get( 0, split.length - 1 );
			int monsterId = Integer.parseInt( split[index] );
			demons.put(it.next(), new DemonImpl(FighterTempletConfig.get(monsterId), new MapAdaptor(temp), false, false, city));
		}
//		for (String s : split) {
//			if (s.isEmpty()) {
//				continue;
//			}
//			Integer id = new Integer(s.trim());
//
//			demons.put(it.next(), new DemonImpl(FighterTempletConfig.get(id), temp));
//		}


	}

	@Override
	public List<Demon> getFighters() {
		return Lists.newArrayList(demons.values());
	}

	@Override
	public int getPosition(Fighter f) {
		for (Entry<Integer, Demon> e : demons.entrySet()) {
			Demon d = e.getValue();
			if(d.equals(f)) {
				Integer p = e.getKey();
				return p;
			}
		}
		throw new IllegalArgumentException(f + " 不在阵形中");
	}

	@Override
	public int getShenJia() {
		int ret = 0;
		for (Demon h : demons.values()) {
			ret += h.getShenJia();
		}
		return ret;
	}
	
	@Override
	public Fighter getMainFighter() {
		for (Demon d : demons.values()) {
			return d;
		}
		return null;
	}

	@Override
	public Dogz getDogz() {
		return null;
	}

	@Override
	public String getUserId() {
		return null;
	}

}
