package cn.mxz.qiyu;

import cn.mxz.activity.ActivityIds;
import cn.mxz.city.City;
import cn.mxz.daji.DajiUserData;
import cn.mxz.daji.ProtectDajiManager;
import define.D;

public class DaJiButton extends AbstractQiYuButton {

	public DaJiButton(City city) {
		super(city);
	}

	@Override
	public int getId() {
		return ActivityIds.BaoHuDaJi_2;
	}

	@Override
	public boolean getHasTips() {
		if (D.LANGUAGE == 2 && !isOpen()) {
			return false;
		}
		if (isNewPlayer()) {
			return false;
		}
		// 保护妲己的感叹号, 神马时候显示
		// 驱赶可点，领取可点，保护可点
		// 任意满足其一
		// DajiUserData data = ProtectDajiManager.INSTANCE.getUserData(city);
		// int remainSec = data.getCd().getRemainingSec();
		// return remainSec > 0;
		// TODO LK
		DajiUserData data = ProtectDajiManager.INSTANCE.getUserData(city);
		return data.getHasTips();
	}
}
