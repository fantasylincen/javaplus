package cn.mxz.levelupreward;

import java.util.List;

import cn.mxz.Attribute;
import cn.mxz.AuthorityTemplet;
import cn.mxz.AuthorityTempletConfig;
import cn.mxz.QualityUpTemplet;
import cn.mxz.QualityUpTempletConfig;
import cn.mxz.base.prize.PrizeSenderFactory;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.fighter.PlayerHero;
import cn.mxz.protocols.user.UserP.UserLevelUpPro;
import cn.mxz.user.Player;
import define.D;

public class JiaUserLevelUpBuilder {

	// 伪造的
	public UserLevelUpPro build(City city) {
		UserLevelUpPro.Builder b = UserLevelUpPro.newBuilder();
		QualityUpTemplet t = QualityUpTempletConfig.get(city.getLevel());
		PlayerHero player = city.getTeam().getPlayer();
		Attribute a = player.getAttribute();

		b.setAttack(a.getAttack() - t.getAttack());

		AuthorityTemplet temp = AuthorityTempletConfig.get(city.getLevel());

		int count = getAwardCount(temp, city.getPlayer());

		b.setCash(count);
		b.setDefend(a.getDefend() - t.getDefend());
		b.setFighterCount(city.getFormation().getMaxCount());
		b.setHp(a.getHp() - t.getHp());
		int value = a.getMAttack() - t.getMAttack();
		value = Math.max(value, 0);
		b.setMAttack(value);
		b.setMDefend(a.getMDefend() - t.getMDefend());
		b.setSpeed(a.getSpeed() - t.getSpeed());

		return b.build();
	}

	private int getAwardCount(AuthorityTemplet temp, Player player) {
		List<Prize> ps = PrizeSenderFactory.getPrizeSender().buildPrizes(player, temp.getLevelUpAward());

		int count = 0;
		for (Prize prize : ps) {
			int id = prize.getId();
			if (id == D.ID_GIFT_GOLD || id == D.ID_NEW_GOLD) {
				count += prize.getCount();
			}
		}
		return count;
	}

	// 真实的
	public UserLevelUpPro build(City city, Attribute a) {
		UserLevelUpPro.Builder b = UserLevelUpPro.newBuilder();

		AuthorityTemplet temp = AuthorityTempletConfig.get(city.getLevel());

		b.setAttack(a.getAttack());
		b.setCash(getAwardCount(temp, city.getPlayer()));
		b.setDefend(a.getDefend());
		b.setFighterCount(city.getFormation().getMaxCount());
		b.setHp(a.getHp());
		b.setMAttack(a.getMAttack());
		b.setMDefend(a.getMDefend());
		b.setSpeed(a.getSpeed());

		return b.build();
	}

}
