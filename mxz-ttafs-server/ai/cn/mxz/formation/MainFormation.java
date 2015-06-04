package cn.mxz.formation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.mxz.ArmyOpenNewTempletConfig;
import cn.mxz.city.City;
import cn.mxz.dogz.Dogz;
import cn.mxz.fighter.Fighter;
import cn.mxz.fighter.PlayerHero;
import cn.mxz.user.team.god.Hero;
import cn.mxz.user.team.god.ShenJiaAble;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableSet;

import db.dao.impl.DaoFactory;
import db.dao.impl.NewCampDao;
import db.domain.NewCamp;
import db.domain.NewCampImpl;

/**
 * 主力阵容
 *
 * @author Administrator
 *
 */
class MainFormation implements PlayerCamp, ShenJiaAble {

	private static final ImmutableSet<Integer>	VALID_POSITION	= ImmutableSet.of(1, 3, 4, 5, 7);
	private City								user;
	private BiMap<Integer, Integer>				heros			= HashBiMap.create();				// <英雄id，位置>
	private NewCamp								camp;
	private final NewCampDao			db				= DaoFactory.getNewCampDao();
	private IFormationMove						fm				= new PositionMove();

	/**
	 * 构造函数，从数据库获取数据，如果主力阵容数据不存在，则创建一个
	 *
	 * @param user
	 */
	public MainFormation(City user) {
		this.user = user;
		camp = db.get(user.getId());
		if (camp == null) {
			create();
		}
		Map<Integer, Integer> map = FormationUtil.buildMapFromStr(camp.getPositionstr());
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			heros.put(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * 根据配置表获取系统允许的上阵人数
	 *
	 * @return
	 */
	public int getHeroMaxNum() {
		int levle = user.getLevel();

		int[] arr = ArmyOpenNewTempletConfig.getArrayByLeadLv();

		int i = 0;
		for (; i < arr.length; i++) {
			if (arr[i] > levle) {
				break;
			}
		}
		return ArmyOpenNewTempletConfig.get(i).getId();
	}

	@Override
	public List<Hero> getFighters() {
		List<Hero> list = new ArrayList<Hero>();
		for (int fid : getValidHeros().keySet()) {
			// Fighter f = ;
			list.add(user.getTeam().get(fid));
		}
		return list;
	}

	@Override
	public int getPosition(Fighter f) {
		int fid = f.getTypeId();
		Integer pos = getValidHeros().get(fid);
		return pos == null ? -1 : pos;
	}

	@Override
	public Fighter get(int position) {
		Integer fid = getValidHeros().inverse().get(position);
		if (fid == null) {
			return null;
		}

		return user.getTeam().get(fid);
	}

	@Override
	public int getShenJia() {
		int ret = 0;
		for (int fid : getValidHeros().keySet()) {
			Hero h = user.getTeam().get(fid);
			ret += h.getShenJia();
		}
		return ret;
	}

	@Override
	public City getCity() {
		return user;
	}

	/**
	 * 数据库没有阵容，则创建一个并保存到数据库
	 *
	 * @return
	 */
	private void create() {
		camp = new NewCampImpl();
		PlayerHero player = user.getTeam().getPlayer();

		heros.put(player.getTypeId(), 4);
		camp.setPositionstr(FormationUtil.buildStrFromMap(getValidHeros()) );
		camp.setUname(user.getId());
		db.add(camp);
	}

	String getAll() {
		// return camp.getPositionstr()
		return FormationUtil.buildStrFromMap(getValidHeros() );

	}

	/**
	 * 判断此位置是否位于阵型中
	 *
	 * @param position
	 * @return
	 */
	boolean isContain(int position) {
		return VALID_POSITION.contains(position);
	}

	IFormationMove get() {
		return fm;
	}

	private class PositionMove implements IFormationMove {

		@Override
		public void remove(int srcPos) {
			heros.inverse().remove(srcPos);
			camp.setPositionstr(FormationUtil.buildStrFromMap(getValidHeros()));
			db.update(camp);
		}

		@Override
		public void put(int srcHeroId, int desPos) {
			heros.forcePut(srcHeroId, desPos);
			camp.setPositionstr(FormationUtil.buildStrFromMap(getValidHeros()));
			db.update(camp);
		}

		@Override
		public boolean add(int srcHeroId, int desPos) {
			if (getValidHeros().size() < getHeroMaxNum()) {
				put(srcHeroId, desPos);
				return true;
			}
			return false;
		}
	}

	BiMap<Integer, Integer> getValidHeros() {
		// return heros;
		BiMap<Integer, Integer> validHeros = HashBiMap.create();
		for (Entry<Integer, Integer> entry : heros.entrySet()) {
			if (user.getTeam().get(entry.getKey()) != null) {
				validHeros.put(entry.getKey(), entry.getValue());
			}
		}
		if (validHeros.size() != heros.size()) {
			camp.setPositionstr(FormationUtil.buildStrFromMap(heros));
			db.update(camp);
		}
		return validHeros;
	}

	public String getLevels() {
		String ret = "";
		for (int fid : getValidHeros().keySet()) {
			Hero h = user.getTeam().get(fid);
			ret += h.getTypeId() + ",";
			ret += h.getLevel();
			ret += ",";
		}
		return ret;
	}

	public int getFormationPart(Hero hero) {
		int position = getPosition( hero );
		if( position == -1 ){
			throw new IllegalArgumentException( hero + "不在阵上" );
		}
		return position / 3;
	}

	@Override
	public Fighter getMainFighter() {
		List<Hero> all = getFighters();
		for (Hero hero : all) {
			if(hero.isPlayer()) {
				return hero;
			}
		}
		return null;
	}

	@Override
	public Dogz getDogz() {
		return user.getDogzManager().getFighting();
	}

	@Override
	public String getUserId() {
		return user.getId();
	}

}
