package cn.javaplus.crazy.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;

public class ExceptionCaughtEvent {
	private ChannelHandlerContext ctx;
	private ExceptionEvent e;

	public ExceptionCaughtEvent(ChannelHandlerContext ctx, ExceptionEvent e) {
		this.ctx = ctx;
		this.e = e;
	}

	public ChannelHandlerContext getContext() {
		return ctx;
	}

	public ExceptionEvent getEvent() {
		return e;
	}
}
