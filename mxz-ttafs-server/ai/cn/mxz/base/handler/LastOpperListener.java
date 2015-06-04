package cn.mxz.base.handler;

import cn.javaplus.comunication.OnDataEvent;
import cn.javaplus.comunication.OnDataListener;
import cn.javaplus.comunication.Request;
import cn.mxz.city.City;
import cn.mxz.temp.TempKey;

public class LastOpperListener implements OnDataListener<City> {

	@Override
	public void beforeOnData(OnDataEvent<City> e) {
		City user = e.getUser();
		if (user != null) {
			Request r = e.getRequest();
			String cn = r.getClassName();
			String mn = r.getMethodName();
			int last = cn.lastIndexOf(".");
			if (last != -1) {
				cn = cn.substring(last + 1, cn.length());
			}
			String v = cn + "." + mn;
			user.getTempCollection().put(TempKey.OPERATION_THIS_TIME, v);
		}
	}

	@Override
	public void afterOnData(OnDataEvent<City> e) {
	}
}
