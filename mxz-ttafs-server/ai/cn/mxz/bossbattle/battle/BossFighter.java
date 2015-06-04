package cn.mxz.bossbattle.battle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.javaplus.util.Util;
import cn.mxz.Attribute;
import cn.mxz.BossBasicDataTemplet;
import cn.mxz.BossBasicDataTempletConfig;
import cn.mxz.FighterTempletConfig;
import cn.mxz.IFighterTemplet;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.equipment.Equipment;
import cn.mxz.fighter.AbstractFighter;
import cn.mxz.fighter.AttributeCalculator;
import cn.mxz.fighter.AttributeEmpty;
import cn.mxz.fighter.PlayerHero;
import cn.mxz.newpvp.PvpPlaceImpl;
import cn.mxz.newpvp.PvpPlayer;
import cn.mxz.team.Skill;
import cn.mxz.user.team.Team;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;

import define.D;

public class BossFighter extends AbstractFighter {

	private int			hpNow;
	private PlayerHero	player;
	private Attribute	attribute	= new AttributeEmpty();
	private int			activityType;
	private City		first;


	/**
	 * @param activityType
	 *            boss活动类型 1、上午的活动 2、下午的活动
	 */
	public BossFighter(int activityType) {

		this.activityType = activityType;

		PvpPlayer p = PvpPlaceImpl.getInstance().getFirst();

		if(p != null) {
			first = p.getCity();
			player = first.getTeam().getPlayer();
		}

		genHpMax();

	}

	@Override
	protected int getLowerestDamageLevel() {
		return first.getLevel();
	}
	
	public void genHpMax() {

		Collection<City> all = WorldFactory.getWorld().getOnlineAll();
		for (BossBasicDataTemplet t : BossBasicDataTempletConfig.getAll()) {
			int count = /*getCount(all, t) + t.getCount();*/  getCount(all,t);
			Attribute a = AttributeCalculator.multiply(t, count);
			attribute = AttributeCalculator.adding(a, attribute);
		}



		hpNow = getAttribute().getHp() + D.BOSS_INIT_HP;

//		if( Debuger.isDevelop() ){
//			hpNow = 2000;
//		}
		Debuger.debug("BossFighter.BossFighter()" + hpNow);
	}

	@Override
	public int getHpMax() {
		int i = getAttribute().getHp() + D.BOSS_INIT_HP;
		return i;
	}

	private int getCount(Collection<City> all, BossBasicDataTemplet t) {
		String section = t.getSection();
		List<Integer> integers = Util.Collection.getIntegers(section);
		int min = integers.get(0);
		int max = integers.get(1);

		int count = 0;
		for (City c : all) {
			int l = c.getLevel();
			if (l >= min && l <= max) {
				count++;
			}
		}
		return count;
	}

	public String getName() {
		if (isMorning()) {
			return first.getPlayer().getNick();
		}
		return getTemplet().getName();
	}

	/**
	 * 早上boss?
	 * @return
	 */
	private boolean isMorning() {
		return activityType == 1;
	}

	@Override
	public int getHpNow() {
		return hpNow;
	}

	@Override
	public List<Skill> getSkills() {
		ArrayList<Skill> ls = Lists.newArrayList();
		ls.add(new BossSkill(this, D.BOSS_SKILL));
		return ls;
	}


	@Override
	public int getTypeId() {


		if (isMorning()) {

//			NewPvpPlayer first = PvpPlaceImpl.getInstance().getTopsToday().get(0);
			PvpPlayer first = PvpPlaceImpl.getInstance().getFirst();
			City city = first.getCity();
			Team team = city.getTeam();
			PlayerHero p = team.getPlayer();
			int typeId = p.getTypeId();

			return typeId;
		}
		return D.BOSS_ID;

	}

	@Override
	public int getLevel() {
		return player.getLevel();
	}

	@Override
	public List<Equipment> getEquipments() {
		return Lists.newArrayList();
	}

	@Override
	public void reduceHp(int reduce) {
		hpNow -= reduce;
	}

	@Override
	public void addHp(int add) {
		hpNow += add;
	}

	@Override
	protected float getNanDuXiShu() {
		return 1;
	}

	@Override
	public Attribute getAttribute() {
		return attribute;
	}

	@Override
	protected IFighterTemplet getTemplet() {
		return FighterTempletConfig.get(getTypeId());
	}

	@Override
	public int getNormalSkillId() {
		return getTemplet().getCommonSkill();
	}
}
