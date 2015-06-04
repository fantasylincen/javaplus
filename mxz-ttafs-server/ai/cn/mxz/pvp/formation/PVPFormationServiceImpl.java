package cn.mxz.pvp.formation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.service.AbstractService;
import cn.mxz.handler.PVPFormationService;

@Component("pVPFormationService")
@Scope("prototype")

public class PVPFormationServiceImpl extends AbstractService implements
		PVPFormationService {

//	@Override
//	public FormationAllPro getData() {
//
//		return new PVPFormationAllBuilder().build(getCity());
//	}

	@Override
	public void selectPVP(int formationId) {

//		getCity().getFormation().selectPvp(formationId);

//        new PVPFormationImpl(getCity()).select(formationId);
	}

	@Override
	public void movePVP(int positionSrc, int positionDst) {

//		getCity().getFormation().move(positionSrc, positionDst);

//		new PVPFormationImpl(getCity()).move(positionSrc, positionDst);
	}

	@Override
	public void addFighterPVP(int fighterTypeId, int position) {

//		getCity().getFormation().addFighter(fighterTypeId, position);

//		new PVPFormationImpl(getCity()).addFighter(fighterTypeId, position);
	}

	@Override
	public void removeFighterPVP(int position) {

//		getCity().getFormation().remove(position);
//		new PVPFormationImpl(getCity()).remove(position);
	}

}
