import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.InviteUsersDao;
import mongo.gen.MongoGen.InviteUsersDto;
import mongo.gen.MongoGen.WorldChatRecordDao;
import mongo.gen.MongoGen.WorldChatRecordDto;
import cn.mxz.base.world.GameWorld;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.chat.WorldChatMessage;
import cn.mxz.chat.WorldChatMessages;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;

public class SetOutStream {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public String test() {
//		City city = CityFactory.getCity("脫脫錐藍");
////		System.out.println(city.getPlayer().getNick());
//		World w = WorldFactory.getWorld();
//		Map<String, String> ns = w.getNickManager().getNickAll();
//		String s = ns.get("脫脫錐藍");
		setOut();
		return "Over 啊";
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