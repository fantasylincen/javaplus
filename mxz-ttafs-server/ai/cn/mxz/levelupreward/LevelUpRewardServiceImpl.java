package cn.mxz.levelupreward;

import java.util.List;

import message.S;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.LevelUpRewardTempletConfig;
import cn.mxz.PrizeInExcel;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.base.service.AbstractService;
import cn.mxz.fighter.FighterSnapshoot;
import cn.mxz.handler.LevelUpRewardService;
import cn.mxz.protocols.user.LevelUpRewardP.LevelUpRewardPro;
import cn.mxz.protocols.user.UserP.UserLevelUpPro;
import cn.mxz.task.achieve.LevelUpRewardPlayer;
import db.dao.impl.DaoFactory;
import db.dao.impl.LevelUpRewardDao;
import db.domain.LevelUpReward;
import db.domain.LevelUpRewardImpl;

@Component("levelUpRewardService")
@Scope("prototype")
public class LevelUpRewardServiceImpl extends AbstractService implements LevelUpRewardService {

	private void markAccept(int level) {

		LevelUpRewardDao DAO = DaoFactory.getLevelUpRewardDao();

		LevelUpReward o = new LevelUpRewardImpl();

		o.setLevel(level);

		o.setUname(getPlayer().getId());

		DAO.add(o);
	}

	private void check(int level) {

		if (getPlayer().getLevel() < level) {

			throw new OperationFaildException(S.S10085);
		}

		if (isAccepted(level)) {

			throw new OperationFaildException(S.S10086);
		}

		if (!exist(level)) {

			throw new SureIllegalOperationException("-该等级没有奖励! level = " + level);
		}
	}

	private boolean exist(int level) {
		return LevelUpRewardTempletConfig.get(level) != null;
	}

	/*
	 * 是否已经领取过了
	 */
	private boolean isAccepted(int level) {

		LevelUpRewardDao DAO = DaoFactory.getLevelUpRewardDao();

		LevelUpReward l = DAO.get(level, getPlayer().getId());

		return l != null;
	}

	@Override
	public LevelUpRewardPro getData() {

		LevelUpRewardPro.Builder b = LevelUpRewardPro.newBuilder();

		LevelUpRewardPlayer player = getCity().getLevelUpRewardPlayer();

		List<Reward> rewards = player.getRewards();
		
		for (Reward reward : rewards) {
			b.addRewards(new LevelUpRewardBuilder().build(reward));
		}

		return b.build();
	}

	@Override
	public UserLevelUpPro receive(int level) {
		FighterSnapshoot s = new FighterSnapshoot(getCity());
		s.snapshoot();

		check(level);
		PrizeInExcel reward = LevelUpRewardTempletConfig.get(level);
		sendPrize(reward);
		markAccept(level);

		s.snapshoot();

		return new JiaUserLevelUpBuilder().build(getCity());
	}

}
