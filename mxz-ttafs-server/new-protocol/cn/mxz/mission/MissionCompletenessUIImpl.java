package cn.mxz.mission;

import java.util.List;

import cn.mxz.city.City;

import com.google.common.collect.Lists;

public class MissionCompletenessUIImpl implements MissionCompletenessUI {

	private City	user;
	private int[]	completions;
	private int	chapterId;

	public MissionCompletenessUIImpl(City user, int chapterId) {
		this.user = user;
		this.chapterId = chapterId;
		IMissionManager mission = user.getMission();
		completions = mission.getCompletion(chapterId);
	}

	@Override
	public int getN() {
		return completions[1];
	}

	@Override
	public int getD() {
		return completions[0];
	}

	@Override
	public List<BoxUI> getBoxes() {
		List<MissionBox> boxes = user.getMission().getBoxes(chapterId);

		List<BoxUI> ls = Lists.newArrayList();
		for (MissionBox b : boxes) {
			ls.add(new BoxUIImpl(b));
		}
		return ls;
	}
}
