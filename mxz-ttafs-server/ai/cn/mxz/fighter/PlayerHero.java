package cn.mxz.fighter;

import cn.mxz.AdditionMultiplier;
import cn.mxz.Attribute;
import cn.mxz.city.City;
import cn.mxz.user.team.god.Hero;

/**
 * 玩家主神将
 * @author 林岑
 *
 */
public interface PlayerHero extends Hero {

	/**
	 * 魅力加成 (只对主角起效)
	 * @param old
	 * @return
	 */
	Attribute getCharmAddition();

	/**
	 * 魅力对其造成的能力值提升
	 * @return
	 */
	float getAbilityAddition();

	/**
	 * 玩家ID
	 * @return
	 */
	String getUname();

	AdditionMultiplier getCharmAdditions();

	@Override
	City getCity();

	/**
	 * 品阶
	 * @return
	 */
	@Override
	int getStep();

}