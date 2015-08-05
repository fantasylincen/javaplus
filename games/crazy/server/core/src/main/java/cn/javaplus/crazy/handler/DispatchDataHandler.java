package cn.javaplus.crazy.handler;

import org.jboss.netty.channel.Channel;

import cn.javaplus.crazy.events.Events;
import cn.javaplus.crazy.events.Listener;
import cn.javaplus.crazy.protocol.Protocols;
import cn.javaplus.crazy.user.Game;
import cn.javaplus.crazy.user.User;
import cn.javaplus.crazy.user.World;
import cn.javaplus.log.Log;

public class DispatchDataHandler implements Listener<MessageReceivedEvent> {

	private class MessageSenderImpl implements Protocols.Channel {

		private Channel channel;

		public MessageSenderImpl(Channel channel) {
			this.channel = channel;
		}

		@Override
		public void send(String data) {
			channel.write(data);
		}

		@Override
		public int getId() {
			return channel.getId();
		}
	}

	private static Protocols protocols;

	@Override
	public void onEvent(MessageReceivedEvent e) {

		ensureInit();

		Integer id = e.getEvent().getChannel().getId();
		Log.d("client action, channel id:" + id);
		World world = Game.getWorld();
		User user = world.getByClientId(id);

		MessageSenderImpl sender = new MessageSenderImpl(e.getEvent()
				.getChannel());

		String data = e.getData();
		Log.d(data);
		protocols.onData(user, data, sender);
	}

	private void ensureInit() {
		if (protocols == null) {
			protocols = new Protocols();
			Events.dispatch(new CreateProtocolsEvent(protocols));
		}
	}
}
