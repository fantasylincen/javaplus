package cn.mxz.thirdpaty;

import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.fighter.PlayerHero;
import cn.mxz.user.team.Team;

import com.lemon.commons.socket.ISocket;

class LineKongRoleImpl implements ThirdPartyRole {

	private City	city;

	LineKongRoleImpl(City city) {
		this.city = city;
	}

	@Override
	public String getRoleId() {
		return city.getId();
	}

	@Override
	public int getLevel() {
		return city.getLevel();
	}

	@Override
	public boolean isMan() {
		Team team = city.getTeam();
		PlayerHero player = team.getPlayer();
		int typeId = player.getTypeId();
		FighterTemplet temp = FighterTempletConfig.get(typeId);
		return temp.getSex() == 1;
	}

	@Override
	public String getIp() {
		ISocket s = city.getSocket();
		return s.getIP();
	}

	@Override
	public int getClientType() {
		
		return city.getPlayer().getClientType();

	}

	@Override
	public String getUserId() {
		String object = city.getPlayer().getThirdPartyId();
		return object;
	}

	@Override
	public int getCash() {
		return city.getPlayer().get(PlayerProperty.CASH);
	}

	@Override
	public int getGold() {
		return city.getPlayer().getGold();
	}

	@Override
	public int getRoleType() {
		return city.getTeam().getPlayer().getTypeId();
	}

}
