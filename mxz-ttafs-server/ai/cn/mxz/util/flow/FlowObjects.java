package cn.mxz.util.flow;

import cn.mxz.city.City;
import cn.mxz.city.CityFactory;

public class FlowObjects {

	public static FlowManager getManager(String id) {
		
		City city = CityFactory.getCity(id);
		
		FlowManagerImpl manager = new FlowManagerImpl(city);
		
		return manager;
	}

}
