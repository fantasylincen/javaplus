package cn.mxz.mission.old;

import cn.mxz.user.mission.FighterCapture;

class FighterCaptureImpl implements FighterCapture {

	private int	id;

	private int	position;

	private int	step;

	FighterCaptureImpl(Integer id, Integer position, int step) {

		this.id = id;

		this.position = position;

		this.step = step;
	}

	@Override
	public int getStep() {
		return step;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public int getPosition() {
		return position;
	}
}
