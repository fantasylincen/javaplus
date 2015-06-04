package cn.mxz.qiyu;

import cn.javaplus.util.Util;
import cn.mxz.ActivityTemplet;
import cn.mxz.ActivityTempletConfig;
import cn.mxz.MissionMapTemplet;
import cn.mxz.MissionMapTempletConfig;
import cn.mxz.city.City;
import cn.mxz.mission.IMissionManager;
import cn.mxz.mission.type.GuidePlayer;

public abstract class AbstractQiYuButton implements QiYuButton {

	protected City city;

	public AbstractQiYuButton(City city) {
		this.city = city;
	}

	protected boolean isInActivityTime() {
		ActivityTemplet t = ActivityTempletConfig.get(getId());
		if (t == null) {
			return false;
		}
		return Util.Time.isIn(t.getTime());
	}

	@Override
	public boolean isOpen() {
		ActivityTemplet t = ActivityTempletConfig.get(getId());
		if (t == null) {
			return false;
		}

		if (t.getShow() == 0) {
			return false;
		}

		int lv = t.getLevel();
		int level = city.getLevel();
		return level >= lv;
	}

	@Override
	public int getFighterId() {
		return 0;
	}

	@Override
	public int getYunYouId() {
		return 0;
	}

	@Override
	public boolean getHasTips() {
		if (!isOpen()) {
			return false;
		}
		return !isNewPlayer();
	}

	public boolean getIsShow() {
		ActivityTemplet t = ActivityTempletConfig.get(getId());
		if (t == null) {
			return false;
		}
		return t.getShow() == 1;
	}

	protected boolean isNewPlayer() {
		IMissionManager mission = city.getMission();

		int cId = mission.getMaxMissionId();

		MissionMapTemplet temp = MissionMapTempletConfig.get(cId);

		boolean new1;

		if (cId == 0) {
			new1 = true;
		} else if (temp == null) {
			new1 = false;
		} else {
			GuidePlayer g = new GuidePlayer(temp, city);
			new1 = g.isNew();
		}

		return new1;
	}

}
