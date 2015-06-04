package cn.mxz.nvwa;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.log.Debuger;
import cn.mxz.city.PlayerProperty;

import com.google.common.collect.Lists;

public class NvWaUIImpl implements NvwaUI {

	private Nvwa nvwa;

	public NvWaUIImpl(Nvwa nvwa) {
		this.nvwa = nvwa;
	}

	public int getRemainSec() {
		int remainSec = nvwa.getRemainSec();
		Debuger.debug("NvWaUIImpl.getRemainSec()" + remainSec);
		return remainSec;
	}

	public int getCountNow() {
		return nvwa.getCountNow();
	}

	public int getGoldWillBack() {
		return nvwa.getGoldWillBack();
	}

	public int getBoughtCount() {
		return nvwa.getBoughtCount();
	}

	public int getPriceOld() {
		return nvwa.getPriceOld();
	}

	public int getPriceNew() {
		return nvwa.getPriceNew();
	}

	public int getGoldAll() {
		return nvwa.getGoldAll();
	}

	public int getGold() {
		return nvwa.getGold();
	}

	@Override
	public List<LineItem> getLines() {
		List<SplitLine> lines = NvwaRule.getLines();
		ArrayList<LineItem> ls = Lists.newArrayList();
		for (SplitLine splitLine : lines) {
			ls.add(new LineItemImpl(splitLine));
		}
		return ls;
	}

	@Override
	public int getJinBeiKe() {
		return nvwa.getCity().getPlayer().get(PlayerProperty.NEW_GOLD);
	}

	@Override
	public int getBoxId() {
		return NvwaRule.getBoxId();
	}

}
