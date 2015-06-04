package cn.mxz.chengzhang;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import cn.mxz.PmtpTemplet;
import cn.mxz.PmtpTempletConfig;

public class ChengZhangUIImpl implements ChengZhangUI {

	private ChengZhangPlayer chengZhangPlayer;

	public ChengZhangUIImpl(ChengZhangPlayer chengZhangPlayer) {
		this.chengZhangPlayer = chengZhangPlayer;
	}

	@Override
	public int getBackAll() {
		List<PmtpTemplet> all = PmtpTempletConfig.getAll();
		int sum = 0;
		for (PmtpTemplet p : all) {
			sum += p.getRebate();
		}
		return sum;
	}

	@Override
	public int getPrice() {
		return chengZhangPlayer.getNeed();
	}

	@Override
	public boolean getHasBought() {
		return chengZhangPlayer.hasBought();
	}

	@Override
	public List<ChengZhangItemUI> getItems() {
		List<ChengZhangBox> boxes = chengZhangPlayer.getBoxes();
		ArrayList<ChengZhangItemUI> list = Lists.newArrayList();
		for (ChengZhangBox c : boxes) {
			list.add(new ChengZhangItemUIImpl(c));
		}
		return list;
	}

}
