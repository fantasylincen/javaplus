package cn.mxz.guide;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.MissionMapTemplet;
import cn.mxz.MissionMapTempletConfig;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.base.service.AbstractService;
import cn.mxz.city.City;
import cn.mxz.handler.GuideService;
import cn.mxz.mission.IMissionManager;
import cn.mxz.mission.type.GuidePlayer;
import cn.mxz.protocols.user.god.FighterP.FighterPro;
import cn.mxz.team.builder.FighterBuilder;
import cn.mxz.user.team.god.Hero;
import define.D;

@Component("guideService")
@Scope("prototype")

public class GuideServiceImpl extends AbstractService implements GuideService {

	@Override
	public String getStatusAll(int id) {

		return getCity().getGuide().getStatus(id);
	}

	@Override
	public void mark(int id, int index, int status) {
		getCity().getGuide().mark(id, index, status);
	}

	@Override
	public FighterPro takeFighter(int id) {
		City c = getCity();
		IMissionManager mission = c.getMission();
		int cId = mission.getCurMissionId();

		MissionMapTemplet temp = MissionMapTempletConfig.get(cId);

		GuidePlayer g = new GuidePlayer(temp, c);


		if(g.isNew() && !c.getTeam().contains(D.NEW_PLAYER_FIGHTER_ID)) {
			Hero hero = c.getTeam().createNewHero(D.NEW_PLAYER_FIGHTER_ID);
			return new FighterBuilder().build(hero);
		}
		throw new SureIllegalOperationException("-不可领取黄天化!");
	}

//	public static void main(String[] args) {
//		StringBuilder sb = new StringBuilder("xxxx");
//		sb.replace(1, 2, "y");
//		System.out.println(sb);
//	}
}
