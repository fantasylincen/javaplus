package cn.mxz.chat;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.WorldChatRecordAllDao;
import mongo.gen.MongoGen.WorldChatRecordAllDto;
import mongo.gen.MongoGen.WorldChatRecordDao;
import mongo.gen.MongoGen.WorldChatRecordDao.WorldChatRecordDtoCursor;
import mongo.gen.MongoGen.WorldChatRecordDto;

import com.google.common.collect.Lists;

/**
 * 世界聊天消息
 * 
 * @author 林岑
 */
public class WorldChatMessages {

	private static final int MESSAGE_COUNT_LIMINT = 5;
	
	private static WorldChatMessages instance;
	
	private List<WorldChatMessage> messages;
	
	AtomicLong ids;

	private WorldChatMessages() {
		messages = Lists.newArrayList();
		WorldChatRecordDao dao = Daos.getWorldChatRecordDao();
		WorldChatRecordDtoCursor find = dao.find();
		int count = find.getCount();

		long maxId = Long.MIN_VALUE;
		for (WorldChatRecordDto dto : find) { // 只取后MESSAGE_COUNT_LIMINT调数据
			if (count > MESSAGE_COUNT_LIMINT) {
				dao.delete(dto);
			} else {
				WorldChatMessage message = new WorldChatMessage(dto);
				messages.add(message);
				long id = dto.getId();
				maxId = Math.max(id, maxId);
			}
			count--;
		}

		ids = new AtomicLong(maxId);
	}

	public static WorldChatMessages getInstance() {
		if (instance == null) {
			instance = new WorldChatMessages();
		}
		return instance;
	}

	public List<WorldChatMessage> getAll() {
		return messages;
	}

	public long nextId() {
		return ids.addAndGet(1);
	}

	/**
	 * 确保消息数量限制
	 */
	public void ensureLimit() {
		WorldChatMessage mes = null;
		synchronized (messages) {
			if (messages.size() > MESSAGE_COUNT_LIMINT) {
				mes = messages.remove(0);
			}
		}
		if (mes != null) {
			WorldChatRecordDto dto = mes.getDto();
			WorldChatRecordDao dao = Daos.getWorldChatRecordDao();
			dao.delete(dto);
		}
	}

	public void add(Message m) {
		WorldChatRecordDto u = save1(m);
		save2(m);
		WorldChatMessage message = new WorldChatMessage(u);
		synchronized (messages) {
			messages.add(message);
		}
	}

	private void save2(Message m) {
		WorldChatRecordAllDao dao = Daos.getWorldChatRecordAllDao();
		WorldChatRecordAllDto u = new WorldChatRecordAllDto();
		u.setId(nextId());
		u.setMessage(m.getMessage());
		u.setSender(m.getSender());
		u.setTime(System.currentTimeMillis());
		dao.save(u);
	}

	private WorldChatRecordDto save1(Message m) {
		WorldChatRecordDao dao = Daos.getWorldChatRecordDao();
		WorldChatRecordDto u = new WorldChatRecordDto();
		u.setId(nextId());
		u.setMessage(m.getMessage());
		u.setSender(m.getSender());
		u.setTime(System.currentTimeMillis());
		dao.save(u);
		return u;
	}
}
