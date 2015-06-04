package cn.mxz.dogz;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import cn.mxz.DogzLevelTemplet;
import cn.mxz.DogzLevelTempletConfig;
import cn.mxz.DogzOpenTemplet;
import cn.mxz.DogzOpenTempletConfig;
import cn.mxz.DogzTemplet;
import cn.mxz.DogzTempletConfig;
import cn.mxz.city.City;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;

public class DogzOpenUIImpl implements DogzOpenUI {

	private final class DogzUIComparator implements Comparator<DogzUI> {
		@Override
		public int compare(DogzUI o1, DogzUI o2) {
			return o1.getTempletId() - o2.getTempletId();
		}
	}

	private City	user;

	public DogzOpenUIImpl(City user) {
		this.user = user;
	}

	@Override
	public List<DogzUI> getHeads() {

		List<DogzUI> ls = Lists.newArrayList();

		List<DogzTemplet> all = DogzTempletConfig.getAll();
		for (DogzTemplet dt : all) {
			ls.add(new DogzUIImpl(user, dt));
		}

		sort(ls);
		return ls;
	}

	private void sort(List<DogzUI> ls) {
		Comparator<DogzUI> c = new DogzUIComparator();
		Collections.sort(ls, c);
	}

	@Override
	public int getNextOpenDogzLevel() {
		Map<Integer, Dogz> all = user.getDogzManager().getDogzAll();
		DogzOpenTemplet temp = DogzOpenTempletConfig.get(all.size() + 1);
		if (temp == null) {
			return 0;
		}
		return temp.getDogzLevel();
	}

	@Override
	public int getNextOpenUserLevel() {
		Map<Integer, Dogz> all = user.getDogzManager().getDogzAll();
		DogzOpenTemplet temp = DogzOpenTempletConfig.get(all.size() + 1);
		if (temp == null) {
			return 0;
		}
		return temp.getUserLevel();
	}

	@Override
	public int getHunLiNeed() {
		int level = user.getDogzManager().getLevel();
		DogzLevelTemplet temp = DogzLevelTempletConfig.get(level);
		int n = temp.getSoulNumber();
		return n;
	}

	@Override
	public int getLevel() {

		return user.getDogzManager().getLevel();
	}

	@Override
	public int getHunLiNow() {
		return user.getDogzManager().getHunLiNow();
	}

	@Override
	public int getHunLiMax() {
		return user.getDogzManager().getHunLiMax();
	}
}
