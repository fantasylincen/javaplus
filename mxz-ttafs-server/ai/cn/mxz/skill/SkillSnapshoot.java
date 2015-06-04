package cn.mxz.skill;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.javaplus.util.Util;
import cn.mxz.city.City;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.protocols.user.god.FighterP.FighterSkillsPro;
import cn.mxz.team.Skill;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.lemon.commons.socket.ISocket;

public class SkillSnapshoot {

	public class Changes {
		Set<Integer>	removes	= Sets.newHashSet();
		List<Skill>		changes	= Lists.newArrayList();
	}

	private City					city;

	private Map<Integer, String>	md5s	= Maps.newHashMap();

	public SkillSnapshoot(City city) {
		this.city = city;
	}

	public void snapshoot() {
		if (md5s.isEmpty()) {
			saveFirst();
		} else {
			snapshootToOld();
		}
	}

	private void snapshootToOld() {

		Changes changes = getChangedFighters();

		ISocket socket = city.getSocket();

		if (!changes.changes.isEmpty()) {
			FighterSkillsPro ss = new FighterSkillsBuilder().build(city, changes.changes);
			MessageFactory.getSkill().skillsUpdate(socket, ss);
		}

		if (!changes.removes.isEmpty()) {
			MessageFactory.getSkill().skillsRemove(socket, buildIds(changes.removes));
//			Debuger.debug("SkillSnapshoot.snapshootToOld()");
		}
	}

	private String buildIds(Set<Integer> removes) {
		return Util.Collection.linkWith(",", removes);
	}

	/**
	 * 获得所有发生变化了的技能
	 *
	 * @return
	 */
	private Changes getChangedFighters() {
		Changes changes = new Changes();

		List<Skill> all = city.getSkillManager().getAll();

		setRemoves(changes, all);

		for (Skill s : all) {

			String old = md5(s);

			int id = s.getIdentification();

			String newMd5 = md5s.get(id);

			if (newMd5 == null || !newMd5.equals(old)) {
				changes.changes.add(s);
			}

			md5s.put(id, newMd5); // update
		}

		return changes;
	}

	private void setRemoves(Changes changes, List<Skill> all) {
		Set<Integer> idsOld = Sets.newHashSet(md5s.keySet());

		HashSet<Object> ls = Sets.newHashSet();
		for (Skill skill : all) {
			ls.add(skill.getIdentification());
		}

		idsOld.removeAll(all);
		changes.removes.addAll(idsOld);
	}

	private void saveFirst() {
		List<Skill> all = city.getSkillManager().getAll();
		for (Skill e : all) {
			md5s.put(e.getIdentification(), md5(e));
		}
	}

	private String md5(Skill skill) {
		StringBuilder sb = new StringBuilder();

		sb.append(skill.getIdentification());
		sb.append(skill.getId());
		sb.append(skill.getFighterId());
		sb.append(skill.getLevel());

		String md5 = Util.Secure.md5(sb.toString());
		return md5;
	}
}
