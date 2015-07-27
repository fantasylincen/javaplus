package cn.javaplus.crazy.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import cn.javaplus.crazy.events.Events;
import cn.javaplus.log.Log;

public class NettyHandler extends SimpleChannelUpstreamHandler {

	@Override
	public final void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		String data = (String) e.getMessage();
		Events.dispatch(new MessageReceivedEvent(ctx, e, data));
	}

	@Override
	public final void exceptionCaught(ChannelHandlerContext ctx,
			ExceptionEvent e) {
		Log.d("NettyHandler.exceptionCaught()" + ctx.getChannel().getId());
		Events.dispatch(new ExceptionCaughtEvent(ctx, e));
	}

	@Override
	public final void channelConnected(ChannelHandlerContext ctx,
			ChannelStateEvent e) throws Exception {
		Log.d("NettyHandler.channelConnected()");
		Events.dispatch(new ChannelConnectedEvent(ctx, e));
	}

	/**
	 * 断线
	 */
	@Override
	public final void channelDisconnected(ChannelHandlerContext ctx,
			ChannelStateEvent e) throws Exception {
		Log.d("NettyHandler.channelDisconnected ");
		Events.dispatch(new ChannelDisconnectedEvent(ctx, e));
	}

	@Override
	public final void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
	}

	/**
	 * 关闭
	 */
	@Override
	public final void channelClosed(ChannelHandlerContext ctx,
			ChannelStateEvent e) throws Exception {
	}
}