package cn.mxz.mission.type;

import java.util.List;

import cn.javaplus.random.Fetcher;
import cn.javaplus.util.Util.Collection;
import cn.mxz.battle.Camp;
import cn.mxz.dogz.Dogz;
import cn.mxz.fighter.Fighter;
import cn.mxz.mission.old.demon.Demon;

public class DemonCamp2 implements Camp<Demon> {

	private List<DemonInCamp>	demons;
	private int					capacity;

	public DemonCamp2(List<DemonInCamp> demons) {
		this.demons = demons;
		initDemonCapacity();
	}

	private void initDemonCapacity() {
		for (DemonInCamp d : demons) {
			int capacity = d.getDemon().getShenJia();
			this.capacity += capacity;
		}
	}

	@Override
	public List<Demon> getFighters() {

		List<Demon> demons = Collection.getListByOneFields(new Fetcher<DemonInCamp, Demon>() {

			@Override
			public Demon get(DemonInCamp cup) {
				return cup.getDemon();
			}

		}, this.demons);

		return demons;
	}

	@Override
	public int getPosition(Fighter f) {
		for (DemonInCamp d : demons) {
			if (d.getDemon().equals(f)) {
//				Debuger.debug("DemonCamp2.getPosition()" + d.getDemon() + d.getDemon().getClass());
				return d.getPosition();
			}
		}
		throw new IllegalArgumentException("不在阵容中" + getFighters() + ",,," + f + f.getClass());
	}

	@Override
	public Fighter get(int position) {
		for (DemonInCamp d : demons) {
			if (d.getPosition() == position) {
				return d.getDemon();
			}
		}
		return null;
	}

	@Override
	public int getShenJia() {
		return capacity;
	}

	@Override
	public Fighter getMainFighter() {
		return demons.get(0).getDemon();
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
