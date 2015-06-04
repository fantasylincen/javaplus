package cn.vgame.a.log;

import java.util.concurrent.atomic.AtomicInteger;

import cn.javaplus.log.IPrintStream;
import cn.javaplus.log.Log;
import cn.javaplus.log.Out;
import cn.javaplus.log.SystemOut;
import cn.javaplus.util.Util;
import cn.vgame.a.gen.dto.MongoGen.ConsoleLogDao;
import cn.vgame.a.gen.dto.MongoGen.ConsoleLogDto;
import cn.vgame.a.gen.dto.MongoGen.Daos;

public class MongoDbLogOutput implements Out {

	public static class MongoOutPutStream implements IPrintStream {
		
		static int initId = 10000;

		private static AtomicInteger id = new AtomicInteger(initId);

		@Override
		public void println(Object obj) {
			if (obj == null)
				return;
			ConsoleLogDao dao = Daos.getConsoleLogDao();
			ConsoleLogDto dto = dao.createDTO();
			dto.setDate(Util.Time.getCurrentFormatTime());
			dto.setId(System.currentTimeMillis() + "" + getId());
			dto.setLog(obj.toString());
			dao.save(dto);
		}

		private long getId() {
			if (id.get() > 99999) {
				id.set(initId);
			}
			int addAndGet = id.addAndGet(1);
			return addAndGet;
		}

	}

	public class MongoOut {

		private IPrintStream mongoOutPutStream = new MongoOutPutStream();

		public void println(String head, Object... message) {
			Log.print(head, mongoOutPutStream, message);
		}

	}

	SystemOut out = new SystemOut();
	private MongoOut mongo = new MongoOut();

	@Override
	public void println(String head, Object... message) {
		out.println(head, message);
		mongo.println(head, message);
	}

}
