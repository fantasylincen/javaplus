package cn.mxz.rechargeFeedback;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.service.AbstractService;
import cn.mxz.city.City;
import cn.mxz.handler.RechargeFeedbackService;
import cn.mxz.protocols.rechargeFeedback.RechargeFeedbackP.RechargeFeedbackPro;
import cn.mxz.protocols.rechargeFeedback.RechargeFeedbackP.RechargeFeedbackPro.Builder;
import cn.mxz.protocols.user.UsersBaseP.UserBaseSPro;
import cn.mxz.user.builder.UserBaseBuilder;

@Component("rechargeFeedbackService")
@Scope("prototype")

public class RechargeFeedBackServiceImpl extends AbstractService implements RechargeFeedbackService {

	@Override
	public RechargeFeedbackPro getData() {
		City user = getCity();
		RechargeFeedbackPlayerImpl r = RechargeFeedbackObjects.getPlayer( user.getId() );
		Builder b = RechargeFeedbackPro.newBuilder();
		for( boolean getPrize : r.getIsGetPrize() ){
			b.addIsGet( getPrize );
		}
		return b.build();

	}

	@Override
	public void getPrize(int type) {
		City user = getCity();
		RechargeFeedbackPlayerImpl r = RechargeFeedbackObjects.getPlayer( user.getId() );

		r.getPrize(type);

	}

	@Override
	public UserBaseSPro getTop10() {

//		City user = getCity();
//		RechargeFeedbackPlayerImpl r = RechargeFeedbackObjects.getPlayer( user.getId() );
		List<City> users = RechargeFeedbackPlayerImpl.getRankList();

		cn.mxz.protocols.user.UsersBaseP.UserBaseSPro.Builder b = UserBaseSPro.newBuilder();
		for( City c : users ){
			UserBaseBuilder ub = new UserBaseBuilder();
			b.addUsers( ub.build(c.getPlayer()) );
		}
		return b.build();

	}

}
