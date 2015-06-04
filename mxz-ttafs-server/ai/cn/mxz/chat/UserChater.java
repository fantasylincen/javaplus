package cn.mxz.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.UserChatRecordDao;
import mongo.gen.MongoGen.UserChatRecordDao.UserChatRecordDtoCursor;
import mongo.gen.MongoGen.UserChatRecordDto;
import cn.javaplus.collections.counter.Counter;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.events.Events;
import cn.mxz.events.chat.ReceiveUserChatEvent;
import cn.mxz.messagesender.ChatMessageSender;
import cn.mxz.messagesender.MessageFactory;

import com.google.common.collect.Lists;
import com.lemon.commons.socket.ISocket;

public class UserChater {

	private City city;
	private List<UserChatMessage> messages;
	private static AtomicLong maxId;

	public UserChater(City city) {
		this.city = city;
		loadFromDB();
		initMaxId();
	}

	private void initMaxId() {
		if(maxId == null) {
			UserChatRecordDao dao = Daos.getUserChatRecordDao();
			UserChatRecordDtoCursor find = dao.find();
			long max = Long.MIN_VALUE;
			for (UserChatRecordDto u : find) {
				long id = u.getId();
				if(id > max) {
					max = id;
				}
			}
			
			maxId = new AtomicLong(max);
		}
	}

	private void loadFromDB() {
		UserChatRecordDao dao = getDao();
		UserChatRecordDtoCursor sends = dao.findBySender(city.getId());
		UserChatRecordDtoCursor receives = dao.findByReceiver(city.getId());
		
		messages = Lists.newArrayList();
		for (UserChatRecordDto dto : receives) {
			messages.add(new PrivateChatMessage(dto));
		}
		
		for (UserChatRecordDto dto : sends) {
			messages.add(new PrivateChatMessage(dto));
		}
		
	}

	private UserChatRecordDao getDao() {
		return Daos.getUserChatRecordDao();
	}

	/**
	 * @param message
	 *            消息内容
	 * @param receiver
	 *            消息接受者
	 */
	public void send(String message, String receiver) {
		City rc = CityFactory.getCity(receiver);
		Message m = new MessageImpl(this.city, rc, message);
		
		showMessageToClient(m, rc);
		showMessageToClient(m, city);
		
		ReceiveUserChatEvent e = new ReceiveUserChatEvent(rc, this.city, m);
		Events.getInstance().dispatch(e);//Erating
		
		
		
		UserChatRecordDao dao = getDao();
		UserChatRecordDto dto = buildDto(receiver, m, dao);
		
		PrivateChatMessage mm = new PrivateChatMessage(dto);

		addToMemery(rc, mm);
		addToMemery(city, mm);
		
		dao.save(dto);
		
	}

	private void addToMemery(City city, PrivateChatMessage mm) {
		city.getUserChater().messages.add(mm);		
	}

	private UserChatRecordDto buildDto(String receiver, Message m,
			UserChatRecordDao dao) {
		UserChatRecordDto dto = dao.createDTO();

		dto.setMessage(m.getMessage());
		dto.setReceiver(receiver);
		dto.setSender(city.getId());
		dto.setTime(System.currentTimeMillis());
		dto.setId(maxId.addAndGet(1));
		return dto;
	}


	private void showMessageToClient(Message m, City receiver) {
		ChatMessageSender chat = MessageFactory.getChat();
		ISocket socket = receiver.getSocket();
		String sender = m.getSender();
		String sb = new MessageBuilder().build(m, receiver, sender);
		chat.onMessageUser(socket, sb);
	}

	public List<UserChatMessage> getMessages() {
		ensureLimit();
		return messages;
	}

	private void ensureLimit() {
		
		Counter<String> c = new Counter<String>();
		
		ArrayList<UserChatMessage> messages = Lists.newArrayList(this.messages);
		
		Collections.reverse(messages);
		
		Iterator<UserChatMessage> it = messages.iterator();

		while (it.hasNext()) {
			UserChatMessage cm = it.next();
			String key = key(cm);
			if(c.get(key) > 20) {
				it.remove();
				getDao().delete(cm.getDto());
			} else {
				c.add(key);
			}
		}
		
		this.messages = messages;
	}

	private String key(UserChatMessage cm) {
		List<String> s = Lists.newArrayList();
		s.add(cm.getId());
		s.add(cm.getReceiverId());
		Collections.sort(s);
		
		return s.get(0) + ":" + s.get(1);
	}

	public void send(Message m, City receiver) {
		ChatMessageSender chat = MessageFactory.getChat();
		ISocket socket = receiver.getSocket();
		String sender = m.getSender();

		String sb = new MessageBuilder().build(m, receiver, sender);

		chat.onMessageWorld(socket, sb);
	}

	public boolean hasNewMessage() {
		for (UserChatMessage c : messages) {
			boolean isSendByMySelf = c.getId().equals(city.getId());
			if(!c.hasRead() && !isSendByMySelf) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasNewMessage(String userId) {
		for (UserChatMessage c : messages) {
			boolean isSendByMySelf = c.getId().equals(userId);
			if(!c.hasRead() && isSendByMySelf) {
				return true;
			}
		}
		return false;
	}
}
