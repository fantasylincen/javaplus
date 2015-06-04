package cn.mxz.mission.old.demon;

import cn.mxz.FighterTempletConfig;
import cn.mxz.MapTemplet;
import cn.mxz.MissionMapTemplet;
import cn.mxz.battle.Camp;
import cn.mxz.city.City;
import cn.mxz.mission.old.Location;
import cn.mxz.mission.old.MapDemon;
import cn.mxz.util.LocationImpl;


/**
 * 小怪信息
 * @author 林岑
 *
 */
class MapDemonImpl implements MapDemon {

	private int id;

	private Location location;

	private boolean isBoss;

	private boolean isFirstDemon;

	private boolean isLastDemon;

	private int firstStoryId;

	private int secondStoryId;

	private AbstractDemonGroup	camp;

	private int	groupId;


	public MapDemonImpl(City city, MapTemplet temp, int path, int index, int id, boolean isBoss, int firstStoryId, int secondStoryId, boolean isFirstDemon, boolean isLastDemon, int mapId) {

		setLocation(new LocationImpl(path, index));

		this.id = id;

		this.isBoss = isBoss;

		this.firstStoryId = firstStoryId;

		this.isFirstDemon = isFirstDemon;

	    this.isLastDemon = isLastDemon;

	    this.secondStoryId = secondStoryId;

	    this.camp = MapDemonFactory.createRandomCamp(city, temp, this);
	}

	public MapDemonImpl(MissionMapTemplet temp, int path, int index, int id, boolean isBoss, int firstStoryId, int secondStoryId, boolean isFirstDemon, boolean isLastDemon, int mapId, int demonGroupId, City city) {

		this.groupId = demonGroupId;

		setLocation(new LocationImpl(path, index));

		this.id = id;

		if(FighterTempletConfig.get(id) == null) {

			throw new NullPointerException("id = " + id);
		}

		this.isBoss = isBoss;

		this.firstStoryId = firstStoryId;

		this.isFirstDemon = isFirstDemon;

	    this.isLastDemon = isLastDemon;

	    this.secondStoryId = secondStoryId;

	    this.camp = MapDemonFactory.createGroupCamp(demonGroupId, temp, city);
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public boolean isBoss() {

		return isBoss;
	}

	@Override
	public boolean isFirstDemon() {

		return isFirstDemon;
	}

	@Override
	public boolean isLastDemon() {

		return isLastDemon;
	}

	@Override
	public int getFirstStoryId() {

		return firstStoryId;
	}

	@Override
	public int getSecondStoryId() {

		return secondStoryId;
	}

	public void setFirstDemon(boolean isFirstDemon) {
		this.isFirstDemon = isFirstDemon;
	}

	public void setLastDemon(boolean isLastDemon) {
		this.isLastDemon = isLastDemon;
	}


	public void setSecondStoryId(int secondStoryId) {
		this.secondStoryId = secondStoryId;
	}

	public void setFirstStoryId(int firstStoryId) {
		this.firstStoryId = firstStoryId;
	}

	public void setBoss(boolean isBoss) {
		this.isBoss = isBoss;
	}

	@Override
	public Camp<Demon> getCamp() {
		return camp;
	}

	@Override
	public int getGroupId() {

		return groupId;
	}



}
