package cn.mxz.battle;

class BuffDisappearImpl implements BuffDisappear {

	private boolean	isUnder;

	private int	position;

	private Integer	id;

	BuffDisappearImpl(boolean isUnder, int position, Integer id) {
		this.isUnder = isUnder;
		this.position = position;
		this.id = id;
	}

	@Override
	public int getBuffId() {
		return id;
	}

	@Override
	public int getPosition() {
		return position;
	}

	@Override
	public boolean isUnder() {
		return isUnder;
	}
}
