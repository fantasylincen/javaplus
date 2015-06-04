package cn.mxz.mission;

public class BoxUIImpl implements BoxUI {

	private MissionBox	box;

	public BoxUIImpl(MissionBox box) {
		this.box = box;
	}

	@Override
	public boolean getHasReceived() {
		return box.getHasReceived();
	}

	@Override
	public boolean getCanReceived() {
		return box.getCanReceived();
	}

}
