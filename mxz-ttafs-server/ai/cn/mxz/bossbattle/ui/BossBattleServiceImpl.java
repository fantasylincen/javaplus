package cn.mxz.bossbattle.ui;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.service.AbstractService;
import cn.mxz.battle.WarSituationBuilder;
import cn.mxz.bossbattle.BossBattleActivity;
import cn.mxz.bossbattle.BossBattleResult;
import cn.mxz.handler.BossBattleService;
import cn.mxz.protocols.bossbattle.BossbattleP.BossBattleResultPro;

@Component("bossBattleService")
@Scope("prototype")
public class BossBattleServiceImpl extends AbstractService implements BossBattleService {

	@Override
	public BossBattleResultPro doBattle() {
		BossBattleResult result = BossBattleActivity.INSTANCE.doBattle(getCity());
		BossBattleResultPro.Builder builder = BossBattleResultPro.newBuilder(); 
		builder.setAllDamage( result.getAllDamage() );
		builder.setDamage( result.getDamage() );
		builder.setRank( result.getRank() );
		builder.setSituation( new WarSituationBuilder().build( getCity(), result.getSituation() ) );
		return builder.build();

	}


}
