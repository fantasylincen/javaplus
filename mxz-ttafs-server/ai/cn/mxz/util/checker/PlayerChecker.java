package cn.mxz.util.checker;

import java.util.List;

import message.S;
import cn.mxz.bag.BagAuto;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.mission.old.GoldNeed;
import cn.mxz.prop.PropTempletFactory;
import cn.mxz.team.TeamConfig;
import cn.mxz.user.Player;
import cn.mxz.user.team.Team;
import cn.mxz.user.team.god.Hero;

/**
 *
 * 玩家检测器
 *
 * @author 林岑
 *
 */
public class PlayerChecker implements Checker {

	private World		world;

	private City		city;

	private BagAuto	bag;

	private Team		team;

	public PlayerChecker(Player player) {

		world = WorldFactory.getWorld();

		city = world.get(player.getId());

		bag = city.getBagAuto();

		team = city.getTeam();
	}

	@Override
	public void checkProp(int propId, int need) {

		final int count = bag.getCount(propId);

		if (count < need) {

			throw new OperationFaildException(S.S10008);
		}
	}

	@Override
	public void checkFighterMax() {

		if (team.size() >= TeamConfig.FIGHTER_MAX) {

			throw new OperationFaildException(S.S10009);
		}
	}

	@Override
	public void checkFighterExist(int... fighterId) {

		for (int id : fighterId) {

			Hero g = team.get(id);

			if (g == null) {

				throw new OperationFaildException(S.S10036, fighterId);
			}
		}
	}

	@Override
	public void checkPlayerExist(String userId) {

		City c = world.get(userId);

		if (c == null) {

			throw new OperationFaildException(S.S10087);
		}
	}

	@Override
	public void checkNull(Object o) {

		if (o == null) {

			throw new NullPointerException();
		}
	}

	@Override
	public void checkPlayerProperty(PlayerProperty playerProperty, int need) {

		int now = city.getPlayer().get(playerProperty);

		if (now < need) {

			int s = getCode(playerProperty);

			throw new OperationFaildException(s);
		}
	}

	private int getCode(PlayerProperty playerProperty) {

		switch (playerProperty) {

		case ACTIVITY1:
			return S.S10143;

		case ACTIVITY2:
			return S.S10142;

		case ACTIVITY3:
			return S.S10141;

		case ACTIVITY4:
			return S.S10140;

		case ACTIVITY5:
			return S.S10139;

		case ACTIVITY_SCORE:
			return S.S10138;

		case CASH:
			return S.S10137;

		case CHARM:
			return S.S10136;

		case CLOTHES:
			return S.S10135;

		case CULTIVATION:
			return S.S10134;

		case DI_XIAN_LING:
			return S.S10133;

		case JIN_XIAN_LING:
			return S.S10130;

		case LIVENESS:
			return S.S10129;

		case PHYSICAL:
			return S.S10128;

		case POINTS:
			return S.S10127;

		case POWER:
			return S.S10126;

		case REPUTATION:
			return S.S10125;

		case TIAN_XIAN_LING:
			return S.S10124;

		case RONG_YU:
			return S.S10155;
			
		default:
			return S.S10123;
		}
	}

	@Override
	public void checkPropBySpotId(int spotId, int need) {

		List<Integer> ids = PropTempletFactory.getTypeIdsBySpotId(spotId);

		int now = 0;

		for (Integer propId : ids) {

			now += bag.getCount(propId);
		}

		if (now < need) {

			throw new OperationFaildException(S.S10008);
		}
	}

	@Override
	public void checkGold(int count) {
		GoldNeed g = new GoldNeed(count);
		g.checkEnouph(city.getPlayer());
	}

	@Override
	public void checkGoldOrJinDing(int price) {
		GoldOrJinDingNeed g = new GoldOrJinDingNeed(price);
		g.checkEnouph(city.getPlayer());
	}
}
