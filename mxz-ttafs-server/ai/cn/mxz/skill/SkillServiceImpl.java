package cn.mxz.skill;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.service.AbstractService;
import cn.mxz.battle.FighterSkillBuilder;
import cn.mxz.events.AfterSkillGenerateEvent;
import cn.mxz.events.BeforeSkillGenerateEvent;
import cn.mxz.events.Events;
import cn.mxz.handler.SkillService;
import cn.mxz.protocols.user.god.FighterP.FighterSkillPro;
import cn.mxz.team.Skill;

@Component("skillService")
@Scope("prototype")
public class SkillServiceImpl extends AbstractService implements SkillService {

	@Override
	public FighterSkillPro generate(int id) {
		SkillManager manager = getCity().getSkillManager();

		Events.getInstance().dispatch(new BeforeSkillGenerateEvent(getCity(), id));


		SkillSnapshoot sn = new SkillSnapshoot(getCity());
		sn.snapshoot();

		Skill s = manager.generate(id);

		sn.snapshoot();

		Events.getInstance().dispatch(new AfterSkillGenerateEvent(getCity(), s));


		return new FighterSkillBuilder().build(getCity(), s);
	}

	@Override
	public FighterSkillPro getAddition(int id) {
		return cn.mxz.skill.SkillFactory.getBaseAddition(id);
	}

}
