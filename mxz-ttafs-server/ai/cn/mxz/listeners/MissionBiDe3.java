package cn.mxz.listeners;

import java.util.List;

import cn.javaplus.util.StringResolver;
import cn.javaplus.util.Util;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.city.UserPrizeSender;
import cn.mxz.events.Listener;
import cn.mxz.events.MissionFightingWinEvent;
import cn.mxz.fighter.PlayerHero;
import cn.mxz.mission.type.MissionBattleImpl;
import cn.mxz.mission.type.MissionBattleImpl.MyPrize;
import cn.mxz.user.team.Team;
import define.D;

//第3关 支线BOSS（妖狼200107）在新手阶段，胜利后必得137425,1（物理）、137430,1（法术）其一，根据主角类型判断。战士和刺客得物理，法士得法术。
//第3关 主线BOSS（金吒200108）在新手阶段，胜利后必得151520,1（物理）、151521,1（法术）其一，根据主角类型判断。战士和刺客得物理，法士得法术。

//关卡必得  格式: 
//    1新手 0非新手:
//       1支线  0主线:
//          1Boss 0非Boss:
//            关卡ID:
//               1攻击系  2法术系  3通用:
//                  必得物品
//                         
//                         # 更多...
//                              #更多...

//  1:1:1:3:1:137425,1#1:1:1:3:2:137430,1#1:0:1:3:1:151520,1#1:0:1:3:2:151521,1

public class MissionBiDe3 implements Listener<MissionFightingWinEvent> {

	@Override
	public void onEvent(MissionFightingWinEvent e) {
		StringResolver resolve = Util.Str.resolve(D.MISSION_BI_DE);
		List<StringResolver> split = resolve.split("#");
		for (StringResolver s : split) {
			List<StringResolver> split2 = s.split(":");
			boolean isNewPlayer = split2.get(0).getInt() == 1;
			boolean isMain = split2.get(1).getInt() == 0;
			boolean isBoss = split2.get(2).getInt() == 1;
			int mapId = split2.get(3).getInt();
			int xi = split2.get(4).getInt();	//1攻击系  2法术系  3通用:
			String reward = split2.get(5).getString();
			
			if(isNewPlayer != e.isNewPlayer()) {
				continue;
			}
			
			if(isMain != e.getBattle().isMain()) {
				continue;
			}
			
			if(isBoss != e.getBattle().isBoss()) {
				continue;
			}
			
			int mapId2 = e.getMapId();
			
			if(mapId != mapId2) {
				continue;
			}
			
			
			City user = e.getUser();
			boolean isAttack = isAttack(user);
			if(isAttack && xi == 2) {
				continue;
			}
			
			if(!isAttack && xi == 1) {
				continue;
			}
			
			UserPrizeSender sender = user.getPrizeSender1();
			List<Prize> prize = sender.buildPrizes(reward);
			for (Prize p : prize) {
				MyPrize pm = new MissionBattleImpl.MyPrize(p);
				e.getBattle().getPropPrize().add(pm);
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
}
