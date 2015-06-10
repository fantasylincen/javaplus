package game.events.all.gm;

import define.SystemCfg;

public class YoXiImpl implements YoXi {

	private String yoXi;

	public YoXiImpl(String yoXi) {
		byte id = SystemCfg.GAME_DISTRICT;
		String name = SystemCfg.SERVER_NAME;
		this.yoXi = id + ":" + name + ":" + yoXi;
	}

	@Override
	public String getYoXi() {
		return yoXi;
	}

}
