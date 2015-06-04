import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.WorldChatRecordDao;
import mongo.gen.MongoGen.WorldChatRecordDto;
import cn.javaplus.util.Util;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.chat.WorldChatMessage;
import cn.mxz.chat.WorldChatMessages;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.city.PlayerProperty;
import cn.mxz.temp.TempKey;
import cn.mxz.user.Player;
import db.dao.impl.DaoFactory;
import db.domain.NewFighter;

public class TestCodeBackUp {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public String test() {
//		City city = CityFactory.getCity("脫脫錐藍");
////		System.out.println(city.getPlayer().getNick());
//		World w = WorldFactory.getWorld();
//		Map<String, String> ns = w.getNickManager().getNickAll();
//		String s = ns.get("脫脫錐藍");
//		setOut();
		return " ok ";
		
//		String userId = getCity("uuiii").get(TempKey.USER_ID);
//		return userId;
		
		
//		
//		City city = CityFactory.getCity("100183");
//		if(city == null)
//			return WorldFactory.getWorld().getAll().size() + ""; 
//		int i = city.getUserCounterHistory().get(CounterKey.GAME_ID);
//		return "Over 啊 " + i;
//		ThirdPartyPlatform c = ThirdPartyPlatformFactory.getThirdPartyPlatform();
//		String roleId = c.getRoleId( "10000121", 107 );
//		City user = CityFactory.getCity(roleId);
		
		
//		reset("pvp_robot_sid_106001i_1",		"打我這好嗎");
//		reset("pvp_robot_sid_106001i_2",		"打我臉好嗎");
//		reset("pvp_robot_sid_106001i_3",		"打我手好嗎");
//		reset("pvp_robot_sid_106001i_4",		"打我腳好嗎");
//		reset("pvp_robot_sid_106001i_5",		"用力點好嗎");
//		reset("pvp_robot_sid_106001i_6",		"打我嘴好嗎");
//		reset("pvp_robot_sid_106001i_7"	,	"打腫我好嗎");
//		reset("pvp_robot_sid_106001i_8"	,	"一起打好嗎");
//		reset("pvp_robot_sid_106001i_9"	,	"一起上好嗎");
//
//		
//		Collection<City> all = WorldFactory.getWorld().getAll();
//		StringBuilder sb = new StringBuilder();
//		for (City city : all) {
//			sb.append("<br>");
//			Player player = city.getPlayer();
//			sb.append(player.getId() + "   " + player.getNick() + "    等级    " + city.getLevel() + "  金币:" + player.get(PlayerProperty.CASH));
//		}
//		
//		return sb + " size";
	}
	
	private void reset(String userId, String string2) {
		City user = CityFactory.getCity(userId);
		user.getPlayer().setNick(string2);
		NewFighter dto = user.getTeam().getPlayer().getDto();
		dto.setLevel(Util.Random.get(25, 30));
		DaoFactory.getNewFighterDao().update(dto);
	}

	private static void setOut() {

		File file = new File("systemlog");
		if (!file.exists()) {
			file.mkdir();
		}

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String format = sf.format(new Date(System.currentTimeMillis()));
		File out = new File("systemlog/out-" + format + ".log");
		File err = new File("systemlog/err-" + format + ".log");

		create(out);
		create(err);

		try {
			PrintStream outp = new PrintStream(out, "utf8");
			PrintStream oute = new PrintStream(err, "utf8");
			System.setOut(outp);
			System.setErr(oute);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void create(File out) {
		if (!out.exists()) {
			try {
				out.createNewFile();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private String getMessages() {
		List<WorldChatMessage> all = WorldChatMessages.getInstance().getAll();
		StringBuilder sb = new StringBuilder();
		for (WorldChatMessage w : all) {
			sb.append(w.getContent());
		}
		return sb.toString();
	}

	private int clearWorldChatRecord() {
		List<WorldChatMessage> all = WorldChatMessages.getInstance().getAll();
		for (WorldChatMessage mes : all) {
			WorldChatRecordDto dto = mes.getDto();
			WorldChatRecordDao dao = Daos.getWorldChatRecordDao();
			dao.delete(dto);
		}
		int size = all.size();
		all.clear();
		return size;
	}
}