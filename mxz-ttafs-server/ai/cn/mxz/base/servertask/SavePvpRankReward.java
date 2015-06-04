package cn.mxz.base.servertask;

import java.util.List;

import cn.javaplus.util.Util;
import cn.mxz.RankRewardTempletConfig;
import cn.mxz.city.FunctionOpenManager;
import cn.mxz.city.FunctionOpenManager.ModuleType;
import cn.mxz.newpvp.PvpPlace;
import cn.mxz.newpvp.PvpPlaceImpl;
import cn.mxz.newpvp.PvpPlayer;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;

import db.dao.impl.DaoFactory;
import db.dao.impl.PvpRankRewardDao;
import db.domain.PvpRankReward;
import db.domain.PvpRankRewardImpl;

public class SavePvpRankReward extends TaskSafetyLogToFile {

	@Override
	public void runSafty() {
		PvpRankRewardDao DAO = DaoFactory.getPvpRankRewardDao();
		DAO.clear();
		List<PvpRankReward> all = buildRankRewardAll();
		DAO.addAll(all);
	}

	private List<PvpRankReward> buildRankRewardAll() {

		Debuger.debug("SavePvpRankReward.buildRankRewardAll() ------ start");

		PvpPlace instance = PvpPlaceImpl.getInstance();
		List<PvpPlayer> all = instance.getAll();
		List<Integer> s = RankRewardTempletConfig.getListByStep();
		int max = Util.Collection.getMax(s);

		List<PvpRankReward> rankRewards = Lists.newArrayList();

		List<String> ranks = Lists.newArrayList();
		for (int i = 0; i < all.size() && i + 1 < max; i++) {
			
			PvpPlayer player = all.get(i);
			
			FunctionOpenManager m = player.getCity().getFunctionOpenManager();
			if(!m.isOpen(ModuleType.DouFaMoKuai)) {
				continue;
			}

			ranks.add(player.getId());

			PvpRankRewardImpl r = new PvpRankRewardImpl();
			r.setUname(player.getCity().getId());
			r.setRank(i + 1);
			rankRewards.add(r);
		}


		Debuger.debug("SavePvpRankReward.buildRankRewardAll() ------ end");
		return rankRewards;
	}

}
