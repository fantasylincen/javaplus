package cn.mxz.team.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.mxz.Attribute;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.GodQurlityTemplet;
import cn.mxz.GodQurlityTempletConfig;
import cn.mxz.battle.FighterSkillBuilder;
import cn.mxz.battle.YuanShen;
import cn.mxz.battle.YuanShenBuilder;
import cn.mxz.city.City;
import cn.mxz.equipment.Equipment;
import cn.mxz.equipment.EquipmentBuilder;
import cn.mxz.fighter.SkillTianMing;
import cn.mxz.protocols.user.god.FighterP.FighterPro;
import cn.mxz.protocols.user.god.FighterP.FighterPro.Builder;
import cn.mxz.team.HeroTianMing;
import cn.mxz.team.Skill;
import cn.mxz.tianming.WuXingTianMing;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.FractionBuilder;

import com.google.common.collect.Lists;

/**
 * 单个神将数据
 */
public class FighterBuilder {

	public FighterPro build(Hero f) {

		// Debuger.debug("FighterBuilder.build()" + f.getTypeId());

		FighterPro.Builder b = FighterPro.newBuilder();

		int typeId = f.getTypeId();
		b.setType(typeId);

		City city = f.getCity();

		b.setId(typeId);

		int position = city.getFormation().getPosition(f);

		b.setPosition(position);
		
//		Debuger.debug("FighterBuilder.build()" + f.getName() + " " + position);

		b.setHpNow(f.getHpNow() > 0 ? f.getHpNow() : 0);
		// b.setHpNow(f.getHpMax());
		b.setHpMax(f.getHpMax());

		Attribute a = f.getAttribute();

		b.setAttack(a.getAttack());
		b.setDefend(a.getDefend());
		b.setMAttack(a.getMAttack());
		b.setMDefend(a.getMDefend());
		b.setSpeed(a.getSpeed());
		
//		Debuger.debug("FighterBuilder.build() " + f.getName() + ", " + a.getSpeed());

		b.setCrit(a.getCrit());
		b.setRCrit(a.getRCrit());
		b.setCritAddition(a.getCritAddition());

		b.setHit(a.getHit());
		b.setDodge(a.getDodge());

		b.setBlock(a.getBlock());
		b.setRBlock(a.getRBlock());
		b.setMagic(a.getMagic());

		// Debuger.debug("FighterBuilder.build() 法力值:" + a.getMagic());

		List<Equipment> es = f.getEquipments();

		// int i = 0;
		for (Equipment equipment : es) {
			// i++;
			b.addEquipments(new EquipmentBuilder().build(equipment));
		}

		// Debuger.debug("FighterBuilder.build() 装备数量:" + i);

		b.setQuality(f.getQuality());

		b.setExp(new FractionBuilder().build(f.getExp()));

		b.setFaLi(new FractionBuilder().build(f.getFaLi()));

		b.setLevel(f.getLevel());
		
		b.setShenJia(f.getShenJia());

		int expAll = f.getExpAll();

		b.setExpAll(expAll);

		addTianMing(b, f);
		addTianMingEquipments(b, f);
		addZSEquipments(b, f, city);

		int quality;
		int step;
		FighterTemplet ft = FighterTempletConfig.get(typeId);
		quality = f.getQuality();
		GodQurlityTemplet temp = GodQurlityTempletConfig.get(quality);
		step = temp.getStep();
		b.setStep(step);

		b.setStar(f.getStar());

		List<Skill> skills = f.getSkills();

		sort(skills, ft);

		// Debuger.debug("FighterBuilder.build()" + ft.getName() + " 技能列表:");
		for (Skill skill : skills) {
			b.addSkills(new FighterSkillBuilder().build(city, skill));
			// Debuger.debug("FighterBuilder.build()" + skill.getId());
		}

		List<YuanShen> yuanshens = f.getYuanShenManager().getYuanShens();
		for (YuanShen yuanShen : yuanshens) {
			b.addYuanShen(new YuanShenBuilder().build(yuanShen));
		}

		return b.build();
	}

	private void sort(List<Skill> skills, final FighterTemplet ft) {

		Comparator<Skill> c = new Comparator<Skill>() {

			@Override
			public int compare(Skill o1, Skill o2) {

				int skill = ft.getSkill();

				int a1 = 0;
				int a2 = 0;

				if (skill == o1.getId()) {
					a1 = 1;
				}

				if (skill == o2.getId()) {
					a2 = 1;
				}

				return a2 - a1;
			}
		};

		Collections.sort(skills, c);
	}

	private void addZSEquipments(Builder b, Hero f, City city) {
		List<Equipment> ls = f.getZhuanShuEquipments();

		List<Integer> l = Lists.newArrayList();
		for (Equipment e : ls) {
			l.add(e.getTypeId());
		}

		b.addAllZsEquipments(l);
	}

	private void addTianMingEquipments(Builder b, Hero f) {

		EquipmentTianMing tianMing = f.getEquipmentTianMing();
		List<Integer> ls = tianMing.getTianMingIds();
		b.addAllEquipmentTianMing(ls);
	}

	private void addTianMing(FighterPro.Builder b, Hero f) {

		HeroTianMing tianMing = f.getTianMing();

		ArrayList<Integer> ls = Lists.newArrayList();
		
		for (Integer id : tianMing.getTianMingIds()) {
			ls.add(id);
		}

		SkillTianMing t = f.getSkillTianMing();
		List<Integer> ids = t.getIds();
		for (Integer id : ids) {
			ls.add(id);
		}
		
		WuXingTianMing wxtm = f.getWuXingTianMing();
		for (Integer id : wxtm.getIds()) {
			ls.add(id);
		}
		
		for (Integer id : ls) {
//			ExclusiveTemplet temp = ExclusiveTempletConfig.get(id);
//			if(Sets.newHashSet(1,2,3).contains(temp.getType())) {
				b.addTianMing(id);
//			}
		}
	}
}
