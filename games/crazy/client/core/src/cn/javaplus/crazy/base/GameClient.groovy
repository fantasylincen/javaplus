package cn.javaplus.crazy.base;

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

import org.jboss.netty.bootstrap.ClientBootstrap
import org.jboss.netty.channel.Channel
import org.jboss.netty.channel.ChannelFuture
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.channel.ChannelPipeline
import org.jboss.netty.channel.ChannelPipelineFactory
import org.jboss.netty.channel.ChannelStateEvent
import org.jboss.netty.channel.Channels
import org.jboss.netty.channel.ExceptionEvent
import org.jboss.netty.channel.MessageEvent
import org.jboss.netty.channel.SimpleChannelHandler
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory

import cn.javaplus.crazy.Protocols
import cn.javaplus.crazy.App.AppContext
import cn.javaplus.crazy.Protocols.MessageSender
import cn.javaplus.crazy.events.Events
import cn.javaplus.crazy.handler.ChannelConnectedEvent
import cn.javaplus.crazy.handler.ChannelDisconnectedEvent
import cn.javaplus.crazy.handler.ExceptionCaughtEvent
import cn.javaplus.crazy.log.Log
import cn.javaplus.crazy.message.MessageDecoder
import cn.javaplus.crazy.message.MessageEncoder

public class GameClient {

	private class MessageSenderImpl implements MessageSender {

		@Override
		public void send(String text) {
			channel.write(text);
		}
	}

	private int port;
	private ClientBootstrap client;
	private ExecutorService boss;
	private ExecutorService worker;
	private String host;
	private Channel channel;
	private Protocols messageHandler;

	public GameClient(String host, int port) {
		this.host = host;
		this.port = port;
		boss = Executors.newCachedThreadPool();
		worker = Executors.newCachedThreadPool();
		client = new ClientBootstrap(newChannelFactory());
		messageHandler = new Protocols(new MessageSenderImpl());
	}

	public Protocols getMessageSender() {
		return messageHandler;
	}

	private NioClientSocketChannelFactory newChannelFactory() {
		return new NioClientSocketChannelFactory(boss, worker);
	}

	public void connect() {
		client.setPipelineFactory(new ClientPipelineFactory());
		InetSocketAddress addr = new InetSocketAddress(host, port);
		ChannelFuture future = client.connect(addr);
		channel = future.awaitUninterruptibly().getChannel();
	}

	private class ClientPipelineFactory implements ChannelPipelineFactory {

		public ChannelPipeline getPipeline() throws Exception {
			ChannelPipeline pipeline = Channels.pipeline();
			pipeline.addLast("encoder", new MessageEncoder());
			pipeline.addLast("decoder", new MessageDecoder());
			pipeline.addLast("handler", new NettyClientHandler());
			return pipeline;
		}
	}

	private class NettyClientHandler extends SimpleChannelHandler {

		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
		throws Exception {

			final String message = (String) e.getMessage();

			AppContext.getApp().append(new Runnable() {

						@Override
						public void run() {
							messageHandler.onData(message);
						}
					});
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
}
