package cn.mxz.fighter;

import cn.mxz.AdditionMultiplier;
import cn.mxz.Attribute;
import db.domain.NewFighter;
import db.domain.NewFighterImpl;

/**
 * 超级战士
 * @author 林岑
 *
 */
public class SuperHeroImpl extends HeroImpl {

	private int shenJia = -1;

	private Attribute	attribute;

	private NewFighter	dto;

	public SuperHeroImpl(HeroImpl hero, AdditionMultiplier attributeX) {
		super(hero);
		this.dto = new NewFighterImpl(hero.getDto());
		shenJia = hero.getShenJia();
		attribute = AttributeCalculator.multiply(hero.getAttribute(), attributeX);

		toFullHp();
	}

	private void toFullHp() {
		this.dto.setV(HeroProperty.HP.getValue(), attribute.getHp());
	}

	@Override
	public int getShenJia() {
		return shenJia;
	}

	@Override
	protected void ensureHpLowerMax() {
		if(attribute != null) {
			super.ensureHpLowerMax();
		}
	}

	@Override
	public Attribute getAttribute() {
		return attribute;
	}

	@Override
	public NewFighter getDto() {
		return dto;
	}
}
