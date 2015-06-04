package cn.mxz.spirite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import message.S;
import cn.javaplus.util.Util;
import cn.mxz.FighterTempletConfig;
import cn.mxz.GodQurlityTemplet;
import cn.mxz.GodQurlityTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.user.team.god.Hero;

import com.google.common.collect.Lists;

import db.dao.impl.DaoFactory;
import db.dao.impl.SpiriteDao;
import define.D;

public class SpiriteManagerImpl implements SpiriteManager {

	private City city;
	private List<Spirite> spirites;

	public SpiriteManagerImpl(City city) {
		this.city = city;

		spirites = new ArrayList<Spirite>();
		SpiriteDao DAO = DaoFactory.getSpiriteDao();
		List<db.domain.Spirite> all = DAO.findByUname(city.getId());
		for (db.domain.Spirite spirite : all) {
			spirites.add(new SpiriteImpl(spirite, this));
		}
	}

	@Override
	public List<Spirite> getSpirites() {

		Collections.sort(spirites, new Comparator<Spirite>() {

			@Override
			public int compare(Spirite o1, Spirite o2) {

				return o2.getStep() - o1.getStep();
			}
		});
		return spirites;
	}

	@Override
	public Spirite get(int typeId) {
		for (Spirite spirite : spirites) {
			if (spirite.getTypeId() == typeId) {
				return spirite;
			}
		}
		return null;
	}

	@Override
	public void remove(Integer id) {
		Iterator<Spirite> it = spirites.iterator();
		while (it.hasNext()) {
			Spirite spirite = (Spirite) it.next();
			if (spirite.getTypeId() == id) {
				it.remove();
				break;
			}
		}
		DaoFactory.getSpiriteDao().delete(id, city.getId());
	}

	@Override
	public void add(int id) {
		add(id, 1);
	}

	@Override
	public void add(int id, int count) {
		SpiriteDao DAO = DaoFactory.getSpiriteDao();
		db.domain.Spirite s = getDto(id);
		if (s != null) {
			int count2 = s.getCount();
			s.setCount(count2 + count);
			DAO.update(s);
		} else {
			s = DAO.createDTO();
			s.setCount(count);
			s.setTypeId(id);
			s.setUname(city.getId());
			DAO.add(s);
			spirites.add(new SpiriteImpl(s, this));
		}
	}

	@Override
	public void remove(int typeId, int count) {

		if (count <= 0) {
			return;
		}

		db.domain.Spirite spirite = getDto(typeId);

		if (spirite.getCount() == count) {
			remove(typeId);
		} else if (spirite.getCount() < count) {
			throw new OperationFaildException(S.S10189);
		} else {
			spirite.setCount(spirite.getCount() - count);
			SpiriteDao DAO = DaoFactory.getSpiriteDao();
			DAO.update(spirite);
		}
	}

	private db.domain.Spirite getDto(int typeId) {
		for (Spirite s : spirites) {
			if (s.getTypeId() == typeId) {
				return s.getDto();
			}
		}
		return null;
	}

	@Override
	public void add(Set<Integer> all) {

		check(all);

		SpiriteDao DAO = DaoFactory.getSpiriteDao();
		List<db.domain.Spirite> ls = Lists.newArrayList();
		for (Integer id : all) {
			db.domain.Spirite s = getDto(id);
			if (s != null) {
				s.setCount(s.getCount() + 1);
				DAO.update(s);
			} else {
				s = DAO.createDTO();
				s.setCount(1);
				s.setTypeId(id);
				s.setUname(city.getId());
				ls.add(s);
			}
		}

		try {
			DAO.addAll(ls);

			for (db.domain.Spirite spirite : ls) {
				spirites.add(new SpiriteImpl(spirite, this));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void check(Set<Integer> all) {
		for (Integer id : all) {
			if (FighterTempletConfig.get(id) == null) {
				throw new IllegalArgumentException(id + " 神将不存在");
			}
		}
	}

	@Override
	public int getCount(int typeId) {
		Spirite sp = get(typeId);
		if (sp == null) {
			return 0;
		}
		return sp.getCount();
	}

	/**
	 * 最大星星数量
	 * 
	 * @return
	 */
	private int getMaxStarStep() {

		List<Integer> ss = Lists.newArrayList();

		for (GodQurlityTemplet g : GodQurlityTempletConfig.getAll()) {

			ss.add(g.getStep());
		}

		return Util.Collection.getMax(ss);
	}

	/**
	 * 进阶所需进阶丹
	 * 
	 * @param hero
	 * @return
	 */
	@Override
	public int getJinJieDanNeed(Hero hero) {
		GodQurlityTemplet temp = GodQurlityTempletConfig.get(hero.getQuality());
		if (hero.isPlayer()) {
			return temp.getPlayerAdvanceMedicine();
		} else {
			return temp.getAdvanceMedicine();
		}
	}

	@Override
	public int getJinJieSpiriteNeed(int typeId) {

		Hero hero = city.getTeam().get(typeId);

		if (hero == null || hero.isPlayer()) {
			return 0;
		}
		GodQurlityTemplet temp = GodQurlityTempletConfig.get(hero.getQuality());
		int advanceSoul = temp.getAdvanceSoul();
		return advanceSoul;
	}

	public void checkMaxQuality(Hero hero) {
		check(checkMaxQualityInt(hero));
	}

	private void check(int code) {
		if (code != 0) {
			throw new OperationFaildException(code);
		}
	}

	public void checkSpirite(Hero hero) {

		check(checkSpiriteInt(hero));
	}

	public void checkJinJieDan(Hero hero) {
		check(checkJinJieDanInt(hero));
	}

	public int checkMaxQualityInt(Hero hero) {
		int max = getMaxStarStep();

		int quality = hero.getQuality();

		GodQurlityTemplet temp = GodQurlityTempletConfig.get(quality);

		if (temp.getQualityIdAfterLevelUp() == -1) {
			return S.S10265;
		}

		int step = temp.getStep();
		if (step >= max) {

			return S.S10089;
		}
		return 0;
	}

	public int checkSpiriteInt(Hero hero) {

		int count = getCount(hero.getTypeId());

		int need = getJinJieSpiriteNeed(hero.getTypeId());

		if (count < need) {
			return S.S10224;
		}
		return 0;
	}

	public int checkJinJieDanInt(Hero hero) {
		int need = getJinJieDanNeed(hero);
		int now = city.getBagAuto().getCount(D.ID_JIN_JIE_DAN);
		if (now < need) {
			return S.S10285;
		}
		return 0;
	}

	public boolean canLevelUp(int typeId) {
		Hero hero = city.getTeam().get(typeId);
		if (hero == null) {
			return false;
		}
		int code;
		code = checkJinJieDanInt(hero);
		if (code != 0) {
			return false;
		}
		code = checkMaxQualityInt(hero);
		if (code != 0) {
			return false;
		}
		code = checkSpiriteInt(hero);
		if (code != 0) {
			return false;
		}
		return true;
	}

	@Override
	public void checkJingYuan(Hero hero) {
		if (!hero.isPlayer()) {
			return;
		}
		check(checkJingYuanInt(hero));
	}

	private int checkJingYuanInt(Hero hero) {
		int need = getJingYuanNeed(hero);
		int now = city.getBagAuto().getCount(D.ID_JING_YUAN);
		if (now < need) {
			return S.S10311;
		}
		return 0;
	}

	public int getJingYuanNeed(Hero hero) {
		GodQurlityTemplet temp = GodQurlityTempletConfig.get(hero.getQuality());
		if (hero.isPlayer()) {
			return temp.getPlayerAdvanceSunrex();
		} else {
			return 0;
		}
	}

}
