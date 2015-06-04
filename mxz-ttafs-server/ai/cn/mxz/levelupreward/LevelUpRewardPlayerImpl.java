package cn.mxz.levelupreward;

import java.util.ArrayList;
import java.util.List;

import cn.mxz.LevelUpRewardTemplet;
import cn.mxz.LevelUpRewardTempletConfig;
import cn.mxz.city.City;
import cn.mxz.task.achieve.LevelUpRewardPlayer;
import cn.mxz.user.Player;

import com.google.common.collect.Lists;

import db.dao.impl.DaoFactory;
import db.dao.impl.LevelUpRewardDao;
import db.domain.LevelUpReward;

public class LevelUpRewardPlayerImpl implements LevelUpRewardPlayer {

	private City city;

	public LevelUpRewardPlayerImpl(City city) {
		this.city = city;
	}

	@Override
	public boolean isFinishAllSendGold() {
		return LevelUpRewardTempletConfig.getKeys().size() == getAcceptLevels()
				.size();
	}

	@Override
	public List<Integer> getAcceptLevels() {

		List<Integer> ls = new ArrayList<Integer>();

		LevelUpRewardDao DAO = DaoFactory.getLevelUpRewardDao();

		List<LevelUpReward> all = DAO.findByUname(getPlayer().getId());

		for (LevelUpReward l : all) {

			ls.add(l.getLevel());
		}

		return ls;
	}

	private Player getPlayer() {
		return city.getPlayer();
	}

	@Override
	public List<Reward> getRewards() {

		ArrayList<Reward> ls = Lists.newArrayList();

		List<Integer> received = getAcceptLevels();

		for (Integer key : LevelUpRewardTempletConfig.getKeys()) {

			LevelUpRewardTemplet l = LevelUpRewardTempletConfig.get(key);

			ls.add(new Reward(city, received, l));
		}

		return ls;

	}
}
