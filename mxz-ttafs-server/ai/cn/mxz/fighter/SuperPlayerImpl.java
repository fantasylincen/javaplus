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
public class SuperPlayerImpl extends PlayerHeroImpl {

	private int	shenJia;

	private Attribute	attribute;

	private NewFighter	dto;

	public SuperPlayerImpl(PlayerHeroImpl hero, AdditionMultiplier attributeX) {

		this.city = hero.city;

		this.dto = new NewFighterImpl(hero.getDto());

		shenJia = hero.getShenJia();

		Attribute attribute2 = hero.getAttribute();
		
		attribute = AttributeCalculator.multiply(attribute2, attributeX);

		toFullHp();
	}

	private void toFullHp() {
		this.dto.setV(HeroProperty.HP.getValue(), attribute.getHp());
	}
	@Override
	public Attribute getAttribute() {

		return attribute;
	}

	@Override
	public int getShenJia() {

		return shenJia;
	}

	@Override
	public NewFighter getDto() {

		return dto;
	}
}
