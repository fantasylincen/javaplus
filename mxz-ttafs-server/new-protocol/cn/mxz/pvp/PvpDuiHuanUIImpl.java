package cn.mxz.pvp;

import java.util.List;

import cn.mxz.city.PlayerProperty;
import cn.mxz.user.Player;

import com.google.common.collect.Lists;

public class PvpDuiHuanUIImpl implements PvpDuiHuanUI {

	private PvpDuiHuanManager manager;

	public PvpDuiHuanUIImpl(PvpDuiHuanManager manager) {
		this.manager = manager;
	}

	@Override
	public int getRongYu() {
		Player player = manager.getCity().getPlayer();
		return player.get(PlayerProperty.RONG_YU);
	}

	@Override
	public List<PvpDuiHuanItem> getItems() {
		List<PvpDHItem> items = manager.getItems();
		List<PvpDuiHuanItem> ls = Lists.newArrayList();
		for (PvpDHItem i : items) {
			ls.add(new PvpDuiHuanItemImpl(i));
		}
		return ls;
	}

}
