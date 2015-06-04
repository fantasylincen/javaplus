package cn.mxz.user.builder;

import cn.mxz.newpvp.PvpWarsituationUser;
import cn.mxz.protocols.user.UserBaseP.UserBasePro;
import cn.mxz.protocols.user.UserBaseP.UserBasePro.Builder;

/**
 * 用户协议
 */
public class UserBaseBuilder {

	public UserBasePro build(PlayerBase p) {

		UserBasePro.Builder b = buildWithOutFightingCapCity(p);

		b.setFightingCapacity(p.getShenJia());

		return b.build();
	}

	public UserBasePro.Builder buildWithOutFightingCapCity(PlayerBase p) {


		UserBasePro.Builder b = UserBasePro.newBuilder();
		int typeId = p.getMainFighterTypeId();

		b.setClothes(typeId);

		b.setLevel(p.getLevel());

		b.setNick(p.getNick());

		b.setType(typeId);

		b.setIsMan(p.isMan());


		b.setUserId(p.getId());

		b.setCharm(0); // 魅力

		b.setFightingCapacity(0);

		b.setCharmStartCount(0);

		b.setTotalGold(p.getTotalRechargeGold());

		b.setVipLevel(p.getVipLevel());

		b.setDanId(p.getDanId());

		b.setRank(0/*RankingListImpl.getInstance().getRank(city)*/);

		return b;
	}

	UserBasePro build(PlayerBase p, int fightingCapacity) {

		Builder b = buildWithOutFightingCapCity(p);

		b.setFightingCapacity(fightingCapacity);

		return b.build();
	}

	public UserBasePro build(PvpWarsituationUser p) {
		UserBasePro.Builder b = UserBasePro.newBuilder();
		b.setCharm(0);
		b.setCharmStartCount(0);
		b.setClothes(0);
		b.setDanId(0);
		b.setFightingCapacity(0);
		b.setIsMan(p.isMan());
		b.setLevel(p.getLevel());
		b.setNick(p.getNick());
		b.setRank(0);
		b.setTotalGold(0);
		b.setType(p.getFighterType());
		String userId = p.getUserId();
//		Debuger.debug("UserBaseBuilder.build()" + userId);
		b.setUserId(userId);
		b.setVipLevel(0);
		return b.build();
		
	}

}
