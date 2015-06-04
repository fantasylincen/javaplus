package cn.mxz.yunyou;

import java.util.Collection;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.FighterTempletConfig;
import cn.mxz.RoamAwardTemplet;
import cn.mxz.RoamAwardTempletConfig;
import cn.mxz.base.service.AbstractService;
import cn.mxz.city.City;
import cn.mxz.handler.YunyouService;
import cn.mxz.protocols.yunyou.YunyouP.YunyouPro;
import cn.mxz.protocols.yunyou.YunyouP.YunyouSPro;
import cn.mxz.user.team.god.Hero;


@Component("yunyouService")
@Scope("prototype")

public class YunyouServiceImpl  extends AbstractService implements YunyouService {



	@Override
	public YunyouSPro getData() {
		City user = getCity();
		Collection<YunyouData> list = user.getYunYouPlayer().getList();
		YunyouSPro.Builder allBuilder =YunyouSPro.newBuilder();

		
		for( YunyouData d : list ){
			
			allBuilder.addMessages( buildYunyouPro(user,d).build() );
		}



		return allBuilder.build();
	}


	private YunyouPro.Builder buildYunyouPro(City user, YunyouData d) {
		YunyouPro.Builder b =YunyouPro.newBuilder();
		b.setCount( d.getPropCount() );
		b.setLevel( d.getLevel() );
		b.setPropId( d.getPropId() );
		b.setRemain( d.getRemainSec() );
		b.setId( d.getId() );
		b.setHeroId( d.getHeroId() );
		b.setMaxCount( d.getMaxPropCount() );
		b.setHeroName( FighterTempletConfig.get(d.getHeroId()).getName() );
		Hero h = user.getTeam().get( d.getHeroId() );
		b.setExp( d.calcExp( h.getLevel() ) );
		RoamAwardTemplet t = RoamAwardTempletConfig.get(d.getLevel());
		b.setNeedWine( t.getWine() );
		return b;
	}
	

//
//	@Override
//	public YunyouSPro getData() {
//		City user = getCity();
//		List<YunyouData> list = new YunYouPlayerImpl( user ).getList();
//		YunyouSPro.Builder b =YunyouSPro.newBuilder();
//
//		for( YunyouData d : list ){
//			b.addMessages(new YunYouBuilder().build(d));
//		}
//
//
//
//		return allBuilder.build();
//	}

	@Override
	public void buy(int index) {
		City user = getCity();
		user.getYunYouPlayer().buy(index);

	}

	@Override
	public void getExp(int index, Boolean isDouble) {
		City user = getCity();
		user.getYunYouPlayer().getExp(index, isDouble);

	}
	


	@Override
	public YunyouPro getById(int id) {
		City user = getCity();
		YunyouData data = user.getYunYouPlayer().getDataById(id);
		return buildYunyouPro(user,data).build();
	}


	@Override
	public void getExpForceDouble(int id) {
		City user = getCity();
		user.getYunYouPlayer().getExpForceDouble(id);
		
	}
}
