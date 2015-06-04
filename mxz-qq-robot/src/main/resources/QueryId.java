import java.util.ArrayList;
import java.util.Map;

import cn.javaplus.util.Util;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;

public class QueryId {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public String query(String user) {
		City city = getCity(user);
		if (city == null) {
			throw new RuntimeException("玩家不存在" + user);
		}

		return "<br>	<br>" + user + "	<br>" + city.getId();
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
			id = findNick(user, all);
			if (id == null) {
				return null;
			}
		}

		return getCity(id);
	}

	private String findNick(String user, Map<String, String> all) {
		ArrayList<String> ls = new ArrayList<String>(all.keySet());
		
		for (String s : ls) {
			String pinYinHump = Util.Chinese.getPinYinHump(s);
			String pinYinHump2 = Util.Chinese.getPinYinHump(user);
			pinYinHump = pinYinHump.replaceAll("ing", "in");
			pinYinHump2 = pinYinHump2.replaceAll("ing", "in");
//			System.out.println("xxxxxxxxxxxx:" + pinYinHump + "  " + pinYinHump2 +"  "+ s);
			if(pinYinHump.equals(pinYinHump2)) {
				String id = all.get(s);
				return id;
			}
		}
		return null;
	}
}