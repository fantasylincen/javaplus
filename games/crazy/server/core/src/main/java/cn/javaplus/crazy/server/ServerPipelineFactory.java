package cn.javaplus.crazy.server;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

import cn.javaplus.crazy.handler.NettyHandler;
import cn.javaplus.crazy.message.MessageDecoder;
import cn.javaplus.crazy.message.MessageEncoder;

public class ServerPipelineFactory implements ChannelPipelineFactory {

	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();
		pipeline.addLast("decoder", new MessageDecoder());
		pipeline.addLast("encoder", new MessageEncoder());
		pipeline.addLast("handler", new NettyHandler());
		return pipeline;
	}
}