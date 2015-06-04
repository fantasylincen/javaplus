package cn.mxz.chat;

import java.util.ArrayList;
import java.util.List;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.WorldChatRecordAllDao;
import mongo.gen.MongoGen.WorldChatRecordAllDao.WorldChatRecordAllDtoCursor;
import mongo.gen.MongoGen.WorldChatRecordAllDto;

import com.google.common.collect.Lists;

public class WorldChatRecords {

	/**
	 * 查找指定时间段内的世界聊天记录
	 * 
	 * @param start
	 *            开始时间 毫秒
	 * @param end
	 *            结束时间 毫秒
	 * @return
	 */
	public static List<WorldChatRecordAllDto> getChats(long start, long end) {
		WorldChatRecordAllDao all = Daos.getWorldChatRecordAllDao();
		WorldChatRecordAllDtoCursor find = all.find();
		ArrayList<WorldChatRecordAllDto> ls = Lists.newArrayList();

		while (find.hasNext()) {
			WorldChatRecordAllDto w = find.next();
			if (w.getTime() <= end && w.getTime() >= start) {
				ls.add(w);
			}
		}
		return ls;
	}
}
