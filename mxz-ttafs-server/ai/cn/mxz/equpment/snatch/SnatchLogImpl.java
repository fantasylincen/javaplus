package cn.mxz.equpment.snatch;

import java.util.Date;

import message.S;
import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.LogSnatchDto;
import cn.javaplus.time.Time;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.equipment.SnatchLog;
import cn.mxz.fighter.PlayerHero;
import cn.mxz.user.team.Team;
import cn.mxz.util.debuger.Debuger;

public class SnatchLogImpl implements SnatchLog {

	private LogSnatchDto	l;
	private City		city;
	private City		robber;

	public SnatchLogImpl(City city, LogSnatchDto log) {
		this.city = city;
		this.l = log;

		robber = CityFactory.getCity(l.getRobber());
	}

	@Override
	public int getDataType() {
		return l.getDatatype();
	}

	@Override
	public int getExcelnub() {
		return S.S10193;
	}

	@Override
	public int getId() {
		return l.getId();
	}

	@Override
	public boolean isQuilt() {
		return l.getRobber().equals(city.getId());
	}

	@Override
	public boolean isWin() {
		return l.getIswin();
	}

	@Override
	public int getLevel() {
		return robber.getLevel();
	}

	@Override
	public String getMyNice() {
		City t = getTarget();
		return t.getPlayer().getNick();
	}

	@Override
	public int getNub() {
		return l.getNub();
	}

	@Override
	public String getRooberNice() {
		City robber = getRobber();
		return robber.getPlayer().getNick();
	}

	private City getRobber() {
		boolean equals = !l.getRobber().equals(city.getId());
		if (equals) {
			return robber;
		} else {
			return city;
		}
	}

	private City getTarget() {
		boolean equals = l.getRobber().equals(city.getId());
		if (equals) {
			return robber;
		} else {
			return city;
		}
	}

	@Override
	public String getRoberType() {

		boolean equals = l.getRobber().equals(city.getId());

		return equals ? l.getUname() : l.getRobber();
	}

	@Override
	public String getSnatchTime() {
		long time = l.getTime();
		long t2 = System.currentTimeMillis() - time * 1000;

		t2 = t2 / Time.MILES_ONE_MIN;

		if (t2 <= 60) {
			return t2 + S.STR10308;
		}

		t2 = t2 / 60;

		return t2 + S.STR10306;
	}

	@Override
	public int getUserType() {
		Team team = robber.getTeam();
		PlayerHero player = team.getPlayer();
		int typeId = player.getTypeId();
		return typeId;
	}

	@Override
	public int getWarsituationId() {
		return l.getWarsituationid();
	}

	@Override
	public Date getTime() {
		return new Date(l.getTime() * 1000L);
	}

	@Override
	public LogSnatchDto getDto() {
		return l;
	}

	@Override
	public boolean isSaw() {
		return l.getIsSaw();
	}

	@Override
	public void markSaw() {
		l.setIsSaw(true);
		Daos.getLogSnatchDao().save(l);
	}

	@Override
	public boolean isSuccess() {
		return l.getIsSnatchSuccess();
	}

	@Override
	public String getOtherNick() {
		City p = getOtherCity();
		return p.getPlayer().getNick();
		
	}


	private City getOtherCity() {
		String id = city.getId();
		if(id.equals(l.getRobber())) {
			City city2 = getCity(l.getUname());
			return city2;
		} else {
			City city2 = getCity(l.getRobber());
			return city2;
		}
	}

	private City getCity(String robber2) {
		City city2 = CityFactory.getCity(robber2);
		if(city2 == null) {
			Debuger.debug("SnatchLogImpl.getOtherLevel() error id:" + robber2);
		}
		return city2;
	}

	@Override
	public int getOtherLevel() {
		City p = getOtherCity();
		return p.getLevel();
	}

	@Override
	public City getOther() {
		return getOtherCity();
	}

}
