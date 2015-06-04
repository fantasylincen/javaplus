package cn.mxz.friend;

import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.log.PlaysQueryBuilder;
import cn.mxz.protocols.user.friend.QueryFriendAppayP.QueryListPro;

public class QueryListBuilder {

	public QueryListPro build(String id) {
		QueryListPro.Builder b = QueryListPro.newBuilder();
		b.setPageAll(1);
		b.setPageNow(1);
		City city = CityFactory.getCity(id);
		if (city != null) {
			b.addQueryMessage(new PlaysQueryBuilder().build(city));
		}
		return b.build();
	}

}
