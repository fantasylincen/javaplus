package cn.mxz.chuangzhen;

import message.S;
import cn.mxz.Responses;
import cn.mxz.city.City;

public class ChuangZhenTransformImpl implements ChuangZhenTransform {

	private City	user;

	@Override
	public void setUser(City user) {
		this.user = user;
	}

	@Override
	public ChuangZhenPro getData() {
		ChuangZhenPlayer p = user.getChuangZhenPlayer();
		return p;
	}

	@Override
	public RankingListPro getRankingList(int count) {
//		return new RankingListProImpl(user.getChuangZhenPlayer(), count);
		return new RankingListProImpl(user.getChuangZhenPlayer(), count);
	}

	@Override
	public ChuangZhenReward getBattleReward() {
		ChuangZhenPlayer p = user.getChuangZhenPlayer();
		ChuangZhenReward r = p.getChuangZhenReward();
		return r;
	}

	@Override
	public void select(int index) {
		ChuangZhenPlayer player = user.getChuangZhenPlayer();
		ChuangZhenRewardImpl chuangZhenReward = (ChuangZhenRewardImpl) player.getChuangZhenReward();
		chuangZhenReward.select(index);
		backGetData();
	}

	private void backGetData() {
		Responses r = new Responses(user);
		r.getChuangZhenTransform().responseGetData(user.getChuangZhenPlayer());
	}

	@Override
	public void receive() {
		ChuangZhenRewardImpl chuangZhenReward = (ChuangZhenRewardImpl) user.getChuangZhenPlayer().getChuangZhenReward();
		chuangZhenReward.receive();
		user.getMessageSender().send(S.S10256);
	}

	@Override
	public ChuangZhenHeads getHeads() {
		return user.getChuangZhenPlayer().getHeads();
	}
//	TODO LC:发放排名奖励
//	user.getPrizeCenter().addPrize(3, "10002,11,1009090,1", "渡天劫描述", "渡天劫奖励");

	@Override
	public void skip() {
		ChuangZhenPlayer player = user.getChuangZhenPlayer();
		ChuangZhenRewardImpl chuangZhenReward = (ChuangZhenRewardImpl) player.getChuangZhenReward();
		chuangZhenReward.skip();
	}
}
