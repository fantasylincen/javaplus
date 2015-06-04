package cn.mxz.listeners;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.UserGuideDao;
import mongo.gen.MongoGen.UserGuideDto;
import cn.mxz.city.City;
import cn.mxz.events.Listener;
import cn.mxz.events.OldUserAccessEvent;
import define.D;

//测试用户
public class TesterAccess implements Listener<OldUserAccessEvent> {

	@Override
	public void onEvent(OldUserAccessEvent event) {

		City city = event.getCity();

		if (city.isTester()) {

			// 引导
			guide(city);
		}
	}

	public void guide(City city) {
		createTestGuide(city, 0);
		createTestGuide(city, 1);
		
//		City city = CityFactory.getCity("hzmxz62975291hzmxz"); cn.javaplus.db.KeyValueCollection<Object, String> c = cn.javaplus.db.KeyValueCollectionFactory.getMongoCollection();for (int i = 0; i < 300; i++) {c.put(key("appid", city), i + "");}
	}

	private static void createTestGuide(City city, int id) {

		UserGuideDao DAO = Daos.getUserGuideDao();

		UserGuideDto g = new UserGuideDto();

		g.setGuideId(id);
		g.setGuideStatus(buildText());

		g.setUname(city.getId());

		DAO.save(g);
	}

	private static String buildText() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < D.GUIDE_SIZE; i++) {

			sb.append("1");
		}
		return sb + "";
	}
}
