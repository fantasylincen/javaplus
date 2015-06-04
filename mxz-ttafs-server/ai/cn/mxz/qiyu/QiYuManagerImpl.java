package cn.mxz.qiyu;

import cn.mxz.city.City;

public class QiYuManagerImpl implements QiYuManager {

	private QiYuButtons	buttons;

	public QiYuManagerImpl(City city) {
		buttons = new QiYuButtonsImpl(city);
	}

	@Override
	public QiYuButtons getButtons() {
		return buttons;
	}

}
