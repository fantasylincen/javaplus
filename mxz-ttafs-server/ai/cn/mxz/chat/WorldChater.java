package cn.mxz.chat;

import java.util.Collection;
import java.util.List;

import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.events.Events;
import cn.mxz.events.chat.ReceiveWorldChatEvent;
import cn.mxz.messagesender.ChatMessageSender;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.prompt.PromptManager;
import cn.mxz.task.TaskQueue;
import cn.mxz.task.TaskQueueManager;
import cn.mxz.user.builder.UserBuilder;

import com.lemon.commons.socket.ISocket;

public class WorldChater implements Chater {

	private City city;

	public WorldChater(City city) {
		this.city = city;
	}

	@Override
	public void send(String message) {
		World world = WorldFactory.getWorld();
		Collection<City> all = world.getOnlineAll();
		final Message m = new MessageImpl(this.city, null, message);

		
		TaskQueue tasks = TaskQueueManager.getQueue();

		if (!tasks.isRunning()) {
			tasks.start();
		}

		saveToDB(m);
		
		for (final City city : all) {
			
			tasks.push(new Runnable() {

				@Override
				public void run() {
					send(m, city);

					if (!WorldChater.this.city.equals(city)) {

						city.getPromptManager().markMessage(PromptManager.SJYXXX);
						
						MessageFactory.getUser().onUpdateUserList(city.getSocket(), new UserBuilder().build(city));
					}
				}

			});
			
		}

		Events.getInstance().dispatch(new ReceiveWorldChatEvent(city, m));
	}

	public void send(Message m, City receiver) {
		ChatMessageSender chat = MessageFactory.getChat();
		ISocket socket = receiver.getSocket();
		String sender = m.getSender();

		String sb = new MessageBuilder().build(m, receiver, sender);

		chat.onMessageWorld(socket, sb);
	}

	private void saveToDB(Message m) {
		WorldChatMessages mes = WorldChatMessages.getInstance();

		mes.add(m);
		mes.ensureLimit();
	}

	public List<WorldChatMessage> getMessages() {
		return WorldChatMessages.getInstance().getAll();
	}

}
