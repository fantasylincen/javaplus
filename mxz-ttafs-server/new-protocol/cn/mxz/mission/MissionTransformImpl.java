package cn.mxz.mission;

import java.util.List;

import cn.mxz.city.City;

public class MissionTransformImpl implements MissionTransform {

	private City	user;

	@Override
	public MissionCompletenessUI getCompleteness(int chapterId) {
		return new MissionCompletenessUIImpl(user, chapterId);
	}

	@Override
	public void setUser(City user) {
		this.user = user;
	}

	@Override
	public void receiveBox(int chapterId, int index) {
		IMissionManager mission = user.getMission();
		List<MissionBox> boxes = mission.getBoxes(chapterId);
		MissionBox b = boxes.get(index);
		b.receive();
	}

}
