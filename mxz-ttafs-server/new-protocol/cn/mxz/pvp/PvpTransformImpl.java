package cn.mxz.pvp;

import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;

public class PvpTransformImpl implements PvpTransform {

	private City user;

	@Override
	public PvpDuiHuanUI getDuiHuanUI() {
		PvpDuiHuanManager m = user.getPvpDuiHuanManager();
		return new PvpDuiHuanUIImpl(m);
	}

	@Override
	public PvpDuiHuanResultPro duiHuan(int typeId) {

		PvpDuiHuanManager m = user.getPvpDuiHuanManager();
		PvpDHItem p = m.duiHuan(typeId);
		int rongYu = user.getPlayer().get(PlayerProperty.RONG_YU);
		return new PvpDuiHuanResultProImpl(p, rongYu);
	}

	@Override
	public void setUser(City user) {
		this.user = user;
	}

}
