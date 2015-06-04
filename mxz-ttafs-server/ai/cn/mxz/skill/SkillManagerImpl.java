package cn.mxz.skill;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import message.S;
import cn.javaplus.random.IntegerFetcher;
import cn.javaplus.util.Util;
import cn.mxz.Chipable;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.SkillTemplet;
import cn.mxz.SkillTempletConfig;
import cn.mxz.base.exception.IllegalOperationException;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.dogz.GenerateManager;
import cn.mxz.team.Skill;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounterSetter;
import cn.mxz.util.debuger.Debuger;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import db.dao.impl.DaoFactory;
import db.dao.impl.SkillsDao;
import db.domain.Skills;
import db.domain.SkillsImpl;

public class SkillManagerImpl extends GenerateManager<Skill> implements
		SkillManager {

	private final class IntegerFetcherImpl implements IntegerFetcher<Skill> {
		@Override
		public Integer get(Skill t) {
			return t.getIdentification();
		}
	}

	private final class PredicateImpl implements Predicate<Skill> {
		private final int skillId;

		private PredicateImpl(int skillId) {
			this.skillId = skillId;
		}

		@Override
		public boolean apply(Skill s) {
			return skillId == s.getId();
		}
	}

	private Integer maxId;

	private List<Skill> all;

	public SkillManagerImpl(City city) {
		super(city);
		init();
	}

	private void init() {

		List<Skills> skills = getAllFromDb();

		all = Lists.newArrayList();
		for (Skills s : skills) {
			int skillId = s.getSkillId();
			SkillTemplet st = SkillTempletConfig.get(skillId);
			if (st == null) {
				continue;
			}
			Skill impl = SkillFactory.create(s);
			all.add(impl);
		}
	}

	@Override
	public List<Skill> getSkillsCanEquip() {

		List<Skill> skillAll = getSkillAll();
		Iterator<Skill> it = skillAll.iterator();
		while (it.hasNext()) {
			Skill skill = (Skill) it.next();
			if (skill.isEquipment()) {
				it.remove();
			}
		}
		return skillAll;
	}

	@Override
	public Skill generate(int id) {
		Skill generate = super.generate(id);
		UserCounterSetter uc = city.getUserCounterHistory();
		uc.add(CounterKey.SKILL_GENERATE_COUNT, 1);
		return generate;
	}

	private List<Skill> getSkillAll() {

		return Lists.newArrayList(all);
	}

	private List<Skills> getAllFromDb() {
		SkillsDao DAO = DaoFactory.getSkillsDao();
		List<Skills> skills = DAO.findByUname(city.getId());
		return skills;
	}

	@Override
	public List<Skill> getSkills(final int skillId) {

		Collection<Skill> ls = Collections2.filter(all, new PredicateImpl(
				skillId));

		return Lists.newArrayList(ls);
	}

	@Override
	public void equipmentToFighter(int fighterTypeId, int id) {
		SkillsDao DAO = DaoFactory.getSkillsDao();
		Skill s = getByIdentification(id);
		checkRepeated(s.getId(), fighterTypeId);
		Skills dto = s.getDto();
		dto.setFighterTypeId(fighterTypeId);
		DAO.update(dto);
	}

	private void checkRepeated(int id, int fighterTypeId) {
		if(fighterTypeId == -1) {
			return;
		}
		List<Skill> ss = getSkillsByFighterId(fighterTypeId);
		for (Skill skill : ss) {
			int sid = skill.getId();
			if(id == sid) {
				throw new OperationFaildException(S.S10331);
			}
		}
	}

	@Override
	public Skill getByIdentification(int id) {
		for (Skill s : all) {
			if (s.getIdentification() == id) {
				return s;
			}
		}
		return null;
	}

	@Override
	public void tackOff(int id) {
		Skill s = getByIdentification(id);
//		Debuger.debug("SkillManagerImpl.tackOff()" + s.getId());
		checkTianFu(s);
		equipmentToFighter(-1, id);
	}

	private void checkTianFu(Skill s) {
		int f = s.getFighterId();
		Hero hero = city.getTeam().get(f);
		if (hero == null) {
			return;
		}
		FighterTemplet t = FighterTempletConfig.get(hero.getTypeId());
		int sId = t.getSkill();
		if (sId == -1 || sId == 0) {
			return;
		}
		if (sId == s.getId()) {
			throw new OperationFaildException(S.S10332);
		}
	}

	@Override
	public List<Skill> getSkills(Hero hero) {
		return getSkillsByFighterId(hero.getTypeId());
	}
	
	@Override
	public List<Skill> getSkillsByFighterId(int fighterId) {

		List<Skill> ls = Lists.newArrayList();

		// ensureTianFu(hero);

		for (Skill skills : all) {
			Skills dto = skills.getDto();
			int fighterTypeId = dto.getFighterTypeId();
			if (fighterTypeId == fighterId) {
				ls.add(skills);
			}
		}

		return ls;
	}

	
	@Override
	public void ensureTianFu(Hero hero) {
		if (!containsTianFuJiNeng(hero)) {// 如果不包含天赋技能
			createTianFuJiNeng(hero); // 创建天赋技能
		}
	}

	/**
	 * 为这个神将创建天赋技能
	 * 
	 * @param hero
	 */
	private void createTianFuJiNeng(Hero hero) {
		FighterTemplet temp = FighterTempletConfig.get(hero.getTypeId());
		int skill = temp.getSkill();
		Skill add = add(skill);
		equipmentToFighter(hero.getTypeId(), add.getIdentification());
	}

	/**
	 * 是否包含天赋技能
	 * 
	 * @param hero
	 * @return
	 */
	private boolean containsTianFuJiNeng(Hero hero) {
		FighterTemplet temp = FighterTempletConfig.get(hero.getTypeId());
		int skill = temp.getSkill();

		for (Skill s : all) {
			int id = s.getId();
			if (s.getFighterId() == hero.getTypeId() && id == skill) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void removeByIds(Integer id) {

		SkillsDao DAO = DaoFactory.getSkillsDao();

		DAO.delete(id, city.getId());

		Iterator<Skill> it = all.iterator();
		while (it.hasNext()) {
			Skill skill = (Skill) it.next();
			if (id.equals(skill.getIdentification())) {
				it.remove();
				break;
			}
		}
	}

	@Override
	public Skill add(int typeId) {
		List<Integer> ls = Lists.newArrayList(typeId);
		List<Skill> add = add(ls);
		return add.get(0);
	}

	@Override
	public List<Skill> add(List<Integer> all) {

		if (maxId == null) {
			initMaxId();
		}

		List<Skill> l = Lists.newArrayList();
		List<Skills> ls = Lists.newArrayList();
		for (Integer typeId : all) {
			if (SkillTempletConfig.get(typeId) != null) {
				Skills s = new SkillsImpl();
				s.setExp(0);
				s.setLevel(1);
				s.setFighterTypeId(-1);
				s.setSkillId(typeId);
				s.setUname(city.getId());
				s.setIds(++maxId);
				ls.add(s);
				l.add(SkillFactory.create(s));

				Debuger.debug("CreateSkill", typeId);
			} else {
				Debuger.debug("SkillManagerImpl.add() 技能不存在!" + typeId);
			}
		}

		SkillsDao DAO = DaoFactory.getSkillsDao();

		DAO.addAll(ls);

		addToAll(ls);

		return l;
	}

	private void addToAll(List<Skills> ls) {
		for (Skills skills : ls) {
			all.add(SkillFactory.create(skills));
		}
	}

	private void initMaxId() {
		int[] a = Util.Collection.getArrayByOneFields(new IntegerFetcherImpl(),
				all);

		if (a.length == 0) {
			maxId = 0;
			return;
		}

		maxId = Util.Array.getMax(a);
	}

	@Override
	protected Chipable getTemplet(int id) {
		return SkillTempletConfig.get(id);
	}

	@Override
	public List<Skill> getAll() {
		Comparator< Skill> c = new Comparator<Skill>() {

			@Override
			public int compare(Skill o1, Skill o2) {
				return o1.getId() - o2.getId();
			}
		};
		Collections.sort(all, c);
		return Lists.newArrayList(all);
	}

	@Override
	public List<Skill> getSkillsUnEquipment() {
		List<Skill> all = getAll();
		Iterator<Skill> it = all.iterator();
		while (it.hasNext()) {
			Skill s = it.next();
			int fighterId = s.getFighterId();
			if (fighterId != 0 && fighterId != -1) {
				it.remove();
			}
		}
		return all;
	}
}
