package cn.mxz.equipment;

import cn.javaplus.util.Util;
import cn.mxz.AuthorityTemplet;
import cn.mxz.AuthorityTempletConfig;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.PropTemplet;
import cn.mxz.SnatchTemplet;
import cn.mxz.SnatchTempletConfig;
import cn.mxz.bag.BagAuto;
import cn.mxz.base.world.NickManager;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.newpvp.PvpManager;
import cn.mxz.newpvp.PvpPlayer;
import cn.mxz.prop.PropTempletFactory;
import cn.mxz.user.Player;
import cn.mxz.user.builder.PlayerBase;
import cn.mxz.user.team.Formation;
import cn.mxz.user.team.god.Hero;

public class SnatchRobotUser implements FightingUser {

	private City city;
	private double minCapacityX;
	private double maxCapacityX;
	private PlayerCamp camp;
	private JiaPlayer player;
	private int mainFighterTypeId;
	private String id;

	public SnatchRobotUser(City city, double minCapacityX, double maxCapacityX) {
		this.city = city;
		this.minCapacityX = minCapacityX;
		this.maxCapacityX = maxCapacityX;

		buildCamp();
		id = Util.Random.getRandomString(20);
	}

	private void buildCamp() {
		Formation formation = city.getFormation();
		PlayerCamp selected = formation.getSelected();
		this.camp = new SnatchRobotCamp(selected, minCapacityX, maxCapacityX);

		this.mainFighterTypeId = getMainFighterTypeId();
	}

	private int getMainFighterTypeId() {
		for (Hero h : this.camp.getFighters()) {
			FighterTemplet t = FighterTempletConfig.get(h.getTypeId());
			int c = t.getCategory();
			if (c == 3) {
				return t.getId();
			}
		}
		return 300001;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public PlayerCamp getCamp() {
		return camp;
	}

	@Override
	public boolean isRobot() {
		return true;
	}

	@Override
	public int getDanId() {
		PvpManager pm = city.getNewPvpManager();
		PvpPlayer p = pm.getPlayer();
		return p.getDan();
	}

	@Override
	public PlayerBase getPlayer() {
		if (player == null) {
			World w = WorldFactory.getWorld();
			NickManager nm = w.getNickManager();

			String nick = nm.getRandomNick();
			Player pla = city.getPlayer();
			player = new JiaPlayer(pla, mainFighterTypeId, nick, id);
		}
		return player;
	}

	@Override
	public BagAuto getBagAuto() {
		return city.getBagAuto();
	}

	@Override
	public float getProbability(City city, int stuffTempletId) {
		PropTemplet t = PropTempletFactory.get(stuffTempletId);
		int q = t.getQuality();
		SnatchTemplet temp = SnatchTempletConfig.get(q + "," + 1);
		float probability = temp.getFinder();

		AuthorityTemplet tt = AuthorityTempletConfig.get(city.getLevel());
		
		float f = probability * tt.getSnatchPar();
		if(f  > 1) {
			f = 1;
		}
		if(f < 0) {
			f = 0;
		}
		return f;
	}

}
