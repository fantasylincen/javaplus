package cn.mxz.chuangzhen;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import define.D;

public class RankingListProImpl extends AbstactRankingListPro implements RankingListPro {

	public RankingListProImpl(ChuangZhenPlayer player, int count) {
		super(count, player);
	}

	@Override
	public List<ChuangZhenRankPlayer> getPlayers() {
		ChuangZhenRankingList list = ChuangZhenRankingList.getInstance();

		List<ChuangZhenPlayer> ls = list.getAll(count);
		ArrayList<ChuangZhenRankPlayer> l = Lists.newArrayList();

		int rank = 1;
		for (ChuangZhenPlayer p : ls) {
			ChuangZhenRankPlayerImpl e = new ChuangZhenRankPlayerImpl(p, rank);
			if(rank > D.CHUANG_ZHEN_RANK_LIST_SIZE) {
				break;
			}
			rank++;
			l.add(new JiaChuangZhenRankPlayer(e));
		}
		return l;
	}

}
