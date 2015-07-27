package cn.javaplus.crazy.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

public class MessageReceivedEvent {

	private ChannelHandlerContext context;
	private MessageEvent event;
	private String data;

	public MessageReceivedEvent(ChannelHandlerContext context,
			MessageEvent event, String data) {
		this.context = context;
		this.event = event;
		this.data = data;
	}

	public MessageEvent getEvent() {
		return event;
	}

	public String getData() {
		return data;
	}

	public ChannelHandlerContext getContext() {
		return context;
	}
}
