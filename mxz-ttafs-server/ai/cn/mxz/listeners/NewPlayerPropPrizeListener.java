package cn.mxz.listeners;

import java.util.List;

import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.MissionMapTemplet;
import cn.mxz.MissionMapTempletConfig;
import cn.mxz.base.prize.PrizeSender;
import cn.mxz.base.prize.PrizeSenderFactory;
import cn.mxz.battle.Battle;
import cn.mxz.battle.MissionBattle;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.events.FightingWinEvent;
import cn.mxz.events.Listener;
import cn.mxz.fighter.PlayerHero;
import cn.mxz.log.Logs;
import cn.mxz.mission.type.GuidePlayer;
import cn.mxz.mission.type.MissionBattleImpl;
import cn.mxz.user.Player;
import cn.mxz.user.team.Team;
import cn.mxz.util.debuger.Debuger;
import define.D;

public class NewPlayerPropPrizeListener implements Listener<FightingWinEvent> {

	@Override
	public void onEvent(FightingWinEvent event) {

		Battle b = event.getBattle();
		if (b instanceof MissionBattle) {
			MissionBattle battle = (MissionBattle) b;

			City user = event.getCity();

			int mapId = battle.getMapId();
			MissionMapTemplet temp = MissionMapTempletConfig.get(mapId);
			GuidePlayer guidPlayer = new GuidePlayer(temp, user); // 引导玩家

			if (!guidPlayer.isNew()) {
				return;
			}

			PrizeSender sender = PrizeSenderFactory.getPrizeSender();

			Player player = user.getPlayer();

			// 第一关 BOSS奖励
			if (mapId == 1 && battle.isBoss() && battle.isMain()) {

				if (isAttack(user)) { // 如果是攻击系
					String newPlayerBossRewardAttack = D.NEW_PLAYER_BOSS_REWARD_ATTACK;
					List<Prize> send = sender
							.buildPrizes(player, newPlayerBossRewardAttack);
					add(send, user, battle);
				} else {// 如果是法术系
					List<Prize> send = sender
							.buildPrizes(player, D.NEW_PLAYER_BOSS_REWARD_MAGIC);
					add(send, user, battle);
				}
			}

			// （经验40 金币300 琥珀珠 151014）

			if (mapId == 2) {

				// 第2关 主线BOSS奖励
				if (battle.isBoss() && battle.isMain()) {
					PrizeSender s = PrizeSenderFactory.getPrizeSender();
					List<Prize> send = s.send(player,
							D.NEW_PLAYER_BOSS_PROP_REWARD_MISSION_2);
					add(send, user, battle);
				}

				// 第2关 支线BOSS奖励
				if (battle.isBoss() && !battle.isMain()) {

					if (isAttack(user)) { // 如果是攻击系
						String newPlayerBossRewardAttack = D.MISSION2_BRANCH_BOSS_REWARD_ATTACK;
						List<Prize> send = sender.send(player,
								newPlayerBossRewardAttack);
						add(send, user, battle);
					} else {// 如果是法术系
						List<Prize> send = sender.send(player,
								D.MISSION2_BRANCH_BOSS_REWARD_MAGIC);
						add(send, user, battle);
					}
				}
			}
		}
	}

	private boolean isAttack(City user) {
		Team team = user.getTeam();
		PlayerHero player = team.getPlayer();
		int typeId = player.getTypeId();
		FighterTemplet temp = FighterTempletConfig.get(typeId);
		return temp.getAttackType() == 1;
	}

	private void add(List<Prize> s, City user, MissionBattle battle) {
		Logs.bossLog.addLog(s, user);
		for (Prize prize : s) {
			battle.getPropPrize().add(new MissionBattleImpl.MyPrize(prize));
			Debuger.debug("MissionBattleImpl.PropPrizeListener.add() 获得副本奖励:"
					+ prize.getId() + "," + prize.getCount());
		}
	}
}
