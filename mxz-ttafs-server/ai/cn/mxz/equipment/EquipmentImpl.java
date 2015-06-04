package cn.mxz.equipment;

import cn.mxz.Attribute;
import cn.mxz.EquipmentTemplet;
import cn.mxz.EquipmentTempletConfig;
import cn.mxz.ExclusiveTemplet;
import cn.mxz.ExclusiveTempletConfig;
import cn.mxz.battle.buff.AttributeSingle;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.fighter.AttributeCalculator;
import cn.mxz.fighter.AttributeEmpty;
import cn.mxz.fighter.EquipmentTianMingConfig;
import cn.mxz.fighter.Part;
import cn.mxz.fighter.TianMingAddition;
import cn.mxz.formation.AdditionType;
import cn.mxz.user.team.god.Hero;
import db.dao.impl.DaoFactory;
import db.dao.impl.NewEquipmentDao;
import db.domain.NewEquipment;

class EquipmentImpl implements Equipment {

	protected NewEquipment data;

	public EquipmentImpl(NewEquipment data) {
		this.data = data;
	}

	/**
	 * 装备基础加成
	 * 
	 * @return
	 */
	private Attribute getBaseAddition() {

		EquipmentTemplet temp = getTemplet();

		int type = temp.getBaseAdditionType();

		AdditionType at = AdditionType.fromNum(type);

		if (at == null) {
			return new AttributeEmpty();
		}

		int value = temp.getAdditionValue1();

		value += temp.getAdditionGrow1() * getLevel();

		return new AttributeSingle(at, value);
	}

	@Override
	public int getLevel() {
		return data.getLevel();
	}

	@Override
	public int getPrice() {

		return getTemplet().getSellPrice();
	}

	@Override
	public EquipmentTemplet getTemplet() {
		int typeId = getTypeId();
		EquipmentTemplet t = EquipmentTempletConfig.get(typeId);
		if (t == null) {
			throw new NullPointerException("null typeid=" + typeId);
		}
		return t;
	}

	@Override
	public int getStep() {

		int quality = getTemplet().getQuality();

		return quality;
	}

	@Override
	public int getTypeId() {

		return data.getTempletId();
	}

	@Override
	public boolean isEquipped() {

		int gridIndex = data.getFighterTypeId();

		return gridIndex != -1;
	}

	@Override
	public void exchangePosition(Equipment e2) {

		NewEquipmentDao DAO = DaoFactory.getNewEquipmentDao();

		EquipmentImpl e2R = (EquipmentImpl) e2;

		int index = e2R.data.getFighterTypeId();

		e2R.data.setFighterTypeId(data.getFighterTypeId());

		data.setFighterTypeId(index);

		DAO.update(data);

		DAO.update(e2R.data);
	}

	@Override
	public void qualityLevelUp() {

		// int step = getStep();

		double qOld = EquipmentConfig.getQ(data.getTempletId());

		data.setTempletId(new EquipmentAfterLevelUp(this).getTypeId());

		double qNew = EquipmentConfig.getQ(data.getTempletId());

		int level = getLevel();

		level = (int) (level * (qOld / qNew));

		data.setLevel(level);

		DaoFactory.getNewEquipmentDao().update(data);

	}

	@Override
	public Integer getId() {

		return data.getEquipmentId();
	}

	@Override
	public int hashCode() {
		return (data.getEquipmentId() + data.getUname()).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquipmentImpl other = (EquipmentImpl) obj;

		return other.data.getEquipmentId() == data.getEquipmentId()
				&& other.data.getUname().equals(data.getUname());
	}

	@Override
	public int getAdditionType() {

		return getTemplet().getBaseAdditionType();
	}

	@Override
	public Hero getHero() {

		City city = CityFactory.getCity(data.getUname());

		Hero hero = city.getTeam().get(data.getFighterTypeId());

		return hero;
	}

	@Override
	public Attribute getAddition() {

		Attribute a = getBaseAddition();

		Attribute d = getAdditionZhuanShu();

		Attribute adding = AttributeCalculator.adding(a, d);

		return adding;
	}

	/**
	 * 专属装备加成
	 * 
	 * @return
	 */
	private Attribute getAdditionZhuanShu() {

		Hero hero = getHero();
		if (hero == null) {
			return new AttributeEmpty();
		}

		Integer id2 = EquipmentTianMingConfig.getId(hero.getTypeId(),
				getTypeId());

		if (id2 != null) {
			
			Attribute base = hero.getAdditions().getBase2();

			ExclusiveTemplet temp = ExclusiveTempletConfig.get(id2);

			if (temp.getFighterId() == hero.getTypeId()) {

				return new TianMingAddition(base, temp);
			}
		}

		return new AttributeEmpty();
	}

	@Override
	public int getPriceLevelUpHistory() {
		return data.getPrice() + getTemplet().getSellPrice();
	}

	@Override
	public int getShenJia() {
		EquipmentTemplet temp = getTemplet();
		return (int) (temp.getSocial() + (getLevel()) * temp.getSocialGrow());
	}

	public NewEquipment getDto() {
		return data;
	}

	@Override
	public String toString() {
		return getTemplet().getName();
	}

	@Override
	public int getLevelUpCashNeed() {

		return EquipmentConfig.getLevelUpNeed(data.getTempletId(),
				data.getLevel());
	}

	@Override
	public int getHeroId() {
		return data.getFighterTypeId();
	}

	@Override
	public int getSuitId() {
		EquipmentTemplet t = getTemplet();
		int suitId = t.getSuitId();
		return suitId;
	}

	@Override
	public Part getPart() {

		int type = getAdditionType();

		AdditionType additionType = AdditionType.fromNum(type);

		Part part = Part.getPart(additionType);

		return part;
	}

	@Override
	public Attribute getBase() {
		return getBaseAddition();
	}
}
