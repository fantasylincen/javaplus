package cn.javaplus.crazy.message;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;

import cn.javaplus.crazy.D;

/**
 * 消息编码器
 * 
 */
public class MessageEncoder extends LengthFieldPrepender {

	public MessageEncoder() {
		super(D.LENTH_FIELD_LENGHT);
	}

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object obj) throws Exception {

		ChannelBuffer ob = ChannelBuffers.dynamicBuffer(channel.getConfig()
				.getBufferFactory());

		String message = (String) obj;

		byte[] strBytes = message.getBytes();
		// ob.writeInt(strBytes.length);
		ob.writeBytes(strBytes);

		return super.encode(ctx, channel, ob);
	}

}