package cn.mxz.listeners.init;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.UserGuideDao;
import mongo.gen.MongoGen.UserGuideDto;
import cn.mxz.city.City;
import cn.mxz.events.Listener;
import cn.mxz.events.OldMxzUserAccessEvent;
import cn.mxz.listeners.TesterCreate;
import cn.mxz.util.debuger.Debuger;
import define.D;

/**
 * 漫想族内部老帐号接入, 自动跳过引导
 * 
 * @author 林岑
 */
public class PassGuide implements Listener<OldMxzUserAccessEvent> {

	@Override
	public void onEvent(OldMxzUserAccessEvent event) {

		// 引导
		City user = event.getUser();
		if(user.getId().startsWith("hbs")) {
			return;
		}
		if(user.isTester() && !user.getId().startsWith(TesterCreate.MXZ1)) {
			pass(user);
			Debuger.debug("PassGuide.onEvent() 老账号接入服务器, 跳过引导");
		}
	}

	public void pass(City city) {
		createTestGuide(city, 0);
		createTestGuide(city, 1);
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
