package cn.mxz.tianming;

import java.util.ArrayList;
import java.util.List;

import cn.mxz.ExclusiveTemplet;
import cn.mxz.ExclusiveTempletConfig;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;

import com.google.common.collect.Lists;

public class WuXingTianMing {

	private int typeId;
	private int star;

	public WuXingTianMing(int typeId, int star) {
		this.typeId = typeId;
		this.star = star;
	}

	public List<Integer> getIds() {
		ArrayList<Integer> ls = Lists.newArrayList();
		if (star < 2) {
			return ls;
		}
		FighterTemplet temp = FighterTempletConfig.get(typeId);
		int tmid = temp.getExclusive10();
		ExclusiveTemplet tt = ExclusiveTempletConfig.get(tmid);
		if (tt == null || tt.getJudge() == 0) {
			return ls;
		}

		ls.add(tt.getId());
		return ls;
	}

}
