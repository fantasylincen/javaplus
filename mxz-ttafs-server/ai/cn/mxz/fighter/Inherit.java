package cn.mxz.fighter;

import java.util.List;

import message.S;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.battle.YuanShen;
import cn.mxz.city.City;
import cn.mxz.user.team.Formation;
import cn.mxz.user.team.Team;
import cn.mxz.user.team.god.Hero;
import cn.mxz.user.team.god.YuanShenManager;
import define.D;

/**
 * 神将传承
 *
 * @author 林岑
 * @since 2013年12月26日 16:51:52
 *
 */
class Inherit {

	interface InheritBean {

		boolean isInheritYuanShen();

		float getExpPercent();

		int getPropNeed();

	}

	/**
	 * 传承
	 *
	 * @param from
	 * @param to
	 * @param propId
	 *            使用的道具ID
	 */
	public void run(Hero from, Hero to, int propId, City user) {

		InheritBean inheritType = getInheritType(propId);

		check(inheritType, user, from, to);

		inherit(user, from, to, inheritType);
	}

	private InheritBean getInheritType(int propId) {
		String[] split = D.INHERIT_EXP.split(",");
		for (String string : split) {
			if (string.contains("" + propId)) {
				return new InheritBeanImpl(string);
			}
		}
		throw new OperationFaildException(S.S10183);
	}

	private void check(InheritBean inheritType, City user, Hero from, Hero to) {
		checkPlayer(from);
		checkPlayer(to);
		checkLevelThan10(from);
		checkLevelThan10(from);
		checkPropEnouph(user, inheritType.getPropNeed()); // 检查道具是否足够
		checkOnFormation(user, from);
	}

	private void checkOnFormation(City user, Hero from) {
		Formation formation = user.getFormation();
		int position = formation.getPosition(from);
		if(position != -1) {
			throw new OperationFaildException(S.S10231);
		}
	}

	private void checkPropEnouph(City user, int propNeed) {
		int count = user.getBagAuto().getCount(propNeed);
		if (count < 0) {
			throw new OperationFaildException(S.S10008);
		}
	}

	private void checkLevelThan10(Hero from) {
		int level = from.getLevel();
		if (level < D.MIN_INHERIT_LEVEL) {
			throw new SureIllegalOperationException("-小于10级的战士不可参与传承!");
		}
	}

	private void checkPlayer(Hero from) {
		if (from instanceof PlayerHero) {
			throw new SureIllegalOperationException("-主角不可传承");
		}
	}

	private void inherit(City user, Hero from, Hero to, InheritBean inheritType) {
		float expPercent = inheritType.getExpPercent();// 传承经验比例
		boolean isInheritYuanShen = inheritType.isInheritYuanShen();

		if (isInheritYuanShen) {
			inheritYuanShen(from, to);
		}

		int all = from.getExpAll();
		from.getDto().setExp(0);
		from.getDto().setLevel(1);
		from.commit();
		to.addExp((int) (all * expPercent));

//		Debuger.debug("Inherit.inherit() 传 承 成功等级:" + to.getLevel());

		new SkillDeleter().removeSkill(from);

		user.getBagAuto().remove(inheritType.getPropNeed(), 1);
	}

	/**
	 * 传承元神
	 *
	 * @param from
	 * @param to
	 */
	private void inheritYuanShen(Hero from, Hero to) {
		YuanShenManager ym = to.getYuanShenManager();
		ym.clear();
		
		
		YuanShenManager yuanShenManager = from.getYuanShenManager();
		List<YuanShen> yuanShens = yuanShenManager.getYuanShens();
		ym.set(yuanShens);
		
		yuanShenManager.clear();
	}

	public int getToLevel(int from, int to, int propId, City user) {
		InheritBean inheritType = getInheritType(propId);

		float expPercent = inheritType.getExpPercent();// 传承经验比例
	
		Team team = user.getTeam();
		Hero hFrom = team.get(from);
		HeroImpl hTo = new HeroImpl(team.get(to));
		float expAdd = hFrom.getExpAll() * expPercent;
		hTo.addExpWithOutEvent((int) expAdd);
		return hTo.getLevel();
	}
}
