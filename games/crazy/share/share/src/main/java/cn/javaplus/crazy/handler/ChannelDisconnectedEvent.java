package cn.javaplus.crazy.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;

public class ChannelDisconnectedEvent {
	private ChannelHandlerContext ctx;
	private ChannelStateEvent e;

	public ChannelDisconnectedEvent(ChannelHandlerContext ctx,
			ChannelStateEvent e) {
		this.ctx = ctx;
		this.e = e;
	}

	public ChannelHandlerContext getContext() {
		return ctx;
	}

	public ChannelStateEvent getEvent() {
		return e;
	}
}
