package cn.mxz.shenmo;

import java.util.List;

import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.protocols.shenmo.ShenMoP.AttackPro;
import cn.mxz.protocols.shenmo.ShenMoP.ShenmoPro;
import cn.mxz.user.builder.UserBaseBuilder;
import db.domain.BossDamageData;
import db.domain.BossData;

public class ShenMoBuilder {

	public ShenmoPro build( ShenmoItem shenmo, City user ) {
		ShenmoPro.Builder b = ShenmoPro.newBuilder();
		BossData bd = shenmo.getBossData();
		City founder = CityFactory.getCity( shenmo.getFounder() );
		b.setShenmoId( shenmo.getBossId() );
		b.setFounder( founder.getPlayer().getNick() );
		b.setRunSecond( shenmo.getRunSecond() );
		boolean isGotPrize = false;
		BossDamageData userData = shenmo.getUserData( user.getId() );
		if( userData != null ){
			isGotPrize = userData.getAward();
		}
		b.setGotPrize( isGotPrize );
		b.setHp( shenmo.getHp() );
		b.setLevel( bd.getLevel() );
		b.setTempletId( bd.getBossTempletId() );
		List<BossDamageData> damageList = shenmo.getDamageList();
		b.setKillNumber( damageList.size() );
		b.setHpMax( shenmo.getFighter().getHpMax() );

		for( BossDamageData bdd : damageList ){
			b.addAttacks(buildAttackPro(bdd));
		}
		String killer = shenmo.getBossData().getKiller();
		b.setKiller( killer == null ? "" : killer );

		return b.build();
	}

	AttackPro buildAttackPro(BossDamageData bdd ){
		AttackPro.Builder b =  AttackPro.newBuilder();
		City user = CityFactory.getCity( bdd.getChallengerId() );
		b.setUser( new UserBaseBuilder().build(user.getPlayer()));
		b.setDamage( bdd.getDamage() );
		return b.build();
	}

}
