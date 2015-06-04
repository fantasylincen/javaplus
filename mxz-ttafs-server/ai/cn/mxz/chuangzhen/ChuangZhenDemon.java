package cn.mxz.chuangzhen;

import java.util.List;

import cn.mxz.Attribute;
import cn.mxz.AuthorityTemplet;
import cn.mxz.AuthorityTempletConfig;
import cn.mxz.CopterMonsterTemplet;
import cn.mxz.IFighterTemplet;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.equipment.Equipment;
import cn.mxz.fighter.AbstractFighter;
import cn.mxz.fighter.AttributeCalculator;
import cn.mxz.mission.old.demon.Demon;
import cn.mxz.team.Skill;

import com.google.common.collect.Lists;

import define.D;

public class ChuangZhenDemon extends AbstractFighter implements Demon {

	private CopterMonsterTemplet temp;
	private int hpNow;
	private int hpMax;
	private int floor;
	private float fector;
	private ChuangZhenPlayer player;

	public ChuangZhenDemon(CopterMonsterTemplet temp, ChuangZhenPlayer player,
			float fector) {
		this.temp = temp;
		this.player = player;
		this.floor = player.getCurrentFloor();
		this.fector = fector;
		hpNow = temp.getHp();
		hpMax = temp.getHp();
	}

	@Override
	public int getHpNow() {
		return hpNow;
	}

	@Override
	public int getHpMax() {
		return hpMax;
	}

	@Override
	public int getTypeId() {
		return temp.getId();
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public List<Equipment> getEquipments() {
		return Lists.newArrayList();
	}

	@Override
	public int getShenJia() {
		return 0;
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
		String id = player.getId();
		City user = CityFactory.getCity(id);

		AuthorityTemplet t = AuthorityTempletConfig.get(user.getLevel());

		double a = D.CHUANG_ZHEN_CHU_SHI_NAN_DU * t.getInitialAddition();
		double b = D.CHUANG_ZHEN_ZENG_LIANG;
		double c = D.CHUANG_ZHEN_ZENG_ZENG_LIANG;
		double nandu = 0;
		int n = floor;

		int d = ((int) ((n) / 5));
		nandu = a + (n - 1) * b + 5 * (c * d * (d - 1) / 2) + ((n) % 5) * d * c;
		nandu *= fector;
		return (float) nandu;
	}

	@Override
	public Attribute getAttribute() {
		float nd = getNanDuXiShu();
		Attribute v = AttributeCalculator.multiply(temp, nd);
		return v;
	}

	@Override
	public Attribute getBaseAttribute() {
		return getAttribute();
	}

	@Override
	public List<Skill> getSkills() {

		List<Skill> ls = Lists.newArrayList();
		ls.add(new ChuangZhenDemonSkill(this, temp.getSkill()));
		return ls;
	}

	@Override
	protected IFighterTemplet getTemplet() {
		return temp;
	}

	@Override
	public int getNormalSkillId() {
		return getTemplet().getCommonSkill();
	}

	@Override
	protected int getLowerestDamageLevel() {
		return player.getLevel();
	}
}
