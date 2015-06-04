package cn.mxz.fighter;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.javaplus.util.Util;
import cn.mxz.Attribute;
import cn.mxz.battle.YuanShen;
import cn.mxz.city.City;
import cn.mxz.equipment.Equipment;
import cn.mxz.formation.AdditionType;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.protocols.user.god.FighterP.FightersPro;
import cn.mxz.team.HeroTianMing;
import cn.mxz.team.Skill;
import cn.mxz.team.builder.FightersBuilder;
import cn.mxz.user.team.Team;
import cn.mxz.user.team.god.Hero;
import cn.mxz.user.team.god.YuanShenManager;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class FighterSnapshoot {

	public class Changes {
		Set<Integer>	removes	= Sets.newHashSet();
		List<Hero>		changes	= Lists.newArrayList();
	}

	private City					city;

	private Map<Integer, String>	md5s	= Maps.newHashMap();

	public FighterSnapshoot(City city) {
		this.city = city;
	}

	/**
	 * 快照, 如果跟之前的快照的结果对比发生了变化, 就会主动通知客户端这些神将发生变化了
	 *
	 * 如果有新增加的战士, 也会发送过去
	 *
	 */
	public void snapshoot() {
		if (md5s.isEmpty()) {
			saveFirst();
		} else {
			snapshootToOld();
		}
	}

	private void snapshootToOld() {

		Changes changes = getChangedFighters();

		if (!changes.changes.isEmpty()) {
//			Debuger.debug("FighterSnapshoot.snapshootToOld() 战士变化:" + changes);
			FightersPro fighters = new FightersBuilder().build(changes.changes);
			MessageFactory.getFighter().fightersUpdate(city.getSocket(), fighters);
		}

		if (!changes.removes.isEmpty()) {
//			Debuger.debug("FighterSnapshoot.snapshootToOld() 战士移除:" + changes.removes);
			MessageFactory.getFighter().fightersRemove(city.getSocket(), buildIds(changes.removes));
		}
	}

	private String buildIds(Set<Integer> removes) {
		return Util.Collection.linkWith(",", removes);
	}

	/**
	 * 获得所有发生变化了的战士
	 *
	 * @return
	 */
	private Changes getChangedFighters() {
		Changes changes = new Changes();
		Team team = city.getTeam();
		Collection<Hero> all = team.getAll();

		setRemoves(changes, all);

		for (Hero e : all) {
			Integer id = e.getTypeId();
			String old = md5(e);

			String newMd5 = md5s.get(id);

			if (newMd5 == null || !newMd5.equals(old)) {

				changes.changes.add(e);

				// Debuger.debug("FighterSnapshoot.getChangedFighters() 战士:" +
				// id + ":" + h.getName() + " 的属性发生了变化! 伙伴天命:" +
				// h.getTianMing().getTianMingIds() + " 技能天命:" +
				// h.getSkillTianMing().getIds() );
			}

			md5s.put(id, newMd5); // update
		}

		return changes;
	}

	private void setRemoves(Changes changes, Collection<Hero> all) {
		Set<Integer> idsOld = Sets.newHashSet(md5s.keySet());
		idsOld.removeAll(keySet(all));
		changes.removes.addAll(idsOld);
	}

	private Collection<Integer> keySet(Collection<Hero> all) {
		List<Integer> ls = Lists.newArrayList();
		for (Hero hero : all) {
			ls.add(hero.getTypeId());
		}
		return ls;
	}

	private void saveFirst() {
		Team team = city.getTeam();
		Collection<Hero> all = team.getAll();
		for (Hero e : all) {
			md5s.put(e.getTypeId(), md5(e));
		}
	}

	private String md5(Hero h) {
		Attribute attribute = h.getAttribute();
		StringBuilder sb = new StringBuilder();

		for (AdditionType t : AdditionType.values()) {
			sb.append(t.get(attribute));
		}

		putSkills(h, sb);
		putTianMings(h, sb);
		putEquipments(h, sb);
		putYuanShens(h, sb);
		putPosition(h, sb);
		
		String md5 = Util.Secure.md5(sb.toString());
		return md5;
	}

	private void putPosition(Hero h, StringBuilder sb) {
		int position = city.getFormation().getPosition(h);
		sb.append(position);
	}

	private void putYuanShens(Hero h, StringBuilder sb) {
		YuanShenManager y = h.getYuanShenManager();
		List<YuanShen> ys = y.getYuanShens();
		for (YuanShen yy : ys) {
			sb.append(yy.getType());
			sb.append(yy.getExpAll());
		}
	}

	private void putEquipments(Hero h, StringBuilder sb) {
		List<Equipment> es = h.getEquipments();
		for (Equipment e : es) {
			sb.append(e.getId());
			sb.append(e.getLevel());
		}
	}

	private void putTianMings(Hero h, StringBuilder sb) {
		HeroTianMing tm = h.getTianMing();
		List<Integer> is = tm.getTianMingIds();
		for (Integer i : is) {
			sb.append(i);
		}
	}

	private void putSkills(Hero h, StringBuilder sb) {
		List<Skill> all = h.getSkills();

		// Debuger.debug("FighterSnapshoot.md5()----------------------------" +
		// h.getTypeId());

		for (Skill skill : all) {
			sb.append(skill.getIdentification());
			sb.append(skill.getLevel());
			// Debuger.debug("FighterSnapshoot.md5()" +
			// skill.getIdentification());
		}

		sb.append(h.getExp().getNumerator());
		sb.append(h.getExp().getDenominator());
		sb.append(h.getLevel());

	}
}
