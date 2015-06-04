package cn.mxz.dogz;

import java.util.Map;

import cn.mxz.DogzTemplet;
import cn.mxz.GodQurlityTemplet;
import cn.mxz.GodQurlityTempletConfig;
import cn.mxz.city.City;
import define.D;

public class DogzUIImpl implements DogzUI {

	private City				user;
	private DogzTemplet			temp;
	private Dogz				dogz;
	private Map<Integer, Dogz>	dogzAll;

	/**
	 * 神兽最大等级
	 */
	private int					maxDogzLevel	= 0;

	public DogzUIImpl(City user, DogzTemplet temp) {
		this.user = user;
		this.temp = temp;
		dogz = user.getDogzManager().getDogz(temp.getId());
		dogzAll = user.getDogzManager().getDogzAll();
		for (Dogz d : dogzAll.values()) {
			int level = d.getLevel();
			if (level > maxDogzLevel) {
				maxDogzLevel = level;
			}
		}
	}

	@Override
	public int getStatus() {

		if (dogz != null) {
			return 1;
		}

		if (temp.getOpen() == 0) {
			return 3;
		}

		int max = DogzConfig.getCountMax(maxDogzLevel, user.getLevel());

		if (dogzAll.size() >= max) {
			return 3; // 不可以开启
		}

		if (max == 1 && dogzAll.isEmpty() && temp.getId() == D.ID_BAI_HU) {
			return 2;
		}

		if (max > dogzAll.size()) {
			return 2;
		}

		return 3;
	}

	@Override
	public int getTempletId() {
		return temp.getId();
	}

	@Override
	public boolean isFighting() {
		if (dogz != null) {
			return dogz.isFighting();
		}
		return false;
	}

	@Override
	public int getStep() {
		if (dogz != null) {
			GodQurlityTemplet t = GodQurlityTempletConfig.get(dogz.getQuality());
			return t.getStep();
		}
		return 0;
	}

	@Override
	public int getSkillId() {
		if (dogz != null) {
			return dogz.getNormalSkillId();
		}
		return 0;
	}

}
