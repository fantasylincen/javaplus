import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.javaplus.util.Util;
import cn.mxz.FeedBackTwoTemplet;
import cn.mxz.FeedBackTwoTempletConfig;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;

public class TestCode {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public String test() {

		String a = "";
		FeedBackTwoTempletConfig.load();
		List<FeedBackTwoTemplet> all = FeedBackTwoTempletConfig.getAll();
		for (FeedBackTwoTemplet t : all) {
			a += t.getBagName() + "<br>";
		}
		
		return a;
	}

	private void print(PrintWriter s, String string) {
		City city = CityFactory.getCity(string);
		String nick = city.getPlayer().getNick();
		s.println("<br>" + nick + " --- " + string);
	}

	/**
	 * 根据 昵称 或者 id 获取用户
	 */
	private City getCity(String user) {
		City city = CityFactory.getCity(user);
		if (city != null) {
			return city;
		}

		Map<String, String> all = WorldFactory.getWorld().getNickManager()
				.getNickAll();
		String id = all.get(user);
		if (id == null) {
			return null;
		}

		return getCity(id);
	}
}