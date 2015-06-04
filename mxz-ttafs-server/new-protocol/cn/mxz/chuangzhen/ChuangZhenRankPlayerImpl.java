package cn.mxz.chuangzhen;

import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.fighter.PlayerHero;
import cn.mxz.user.Player;

public class ChuangZhenRankPlayerImpl implements ChuangZhenRankPlayer {

	private ChuangZhenPlayer player;
	private City city;
	private int rank;
	private Player p;

	public ChuangZhenRankPlayerImpl(ChuangZhenPlayer player, int rank) {
		this.player = player;
		this.rank = rank;

		String id = player.getId();

		city = CityFactory.getCity(id);
		p = city.getPlayer();
	}

	@Override
	public int getFighterId() {
		PlayerHero player2 = city.getTeam().getPlayer();
		return player2.getTypeId();
	}

	@Override
	public boolean isMan() {
		return p.isMan();
	}

	@Override
	public String getNick() {
		return p.getNick();
	}

	@Override
	public String getUserId() {
		return city.getId();
	}

	@Override
	public int getLevel() {
		return city.getLevel();
	}

	@Override
	public int getVipLevel() {
		return city.getVipPlayer().getLevel();
	}

	@Override
	public int getFloor() {
		return player.getMaxFloor();
	}

	@Override
	public int getRank() {
		return rank;
	}

	@Override
	public int getStar() {
		return player.getStarMaxHistory();
	}

}
