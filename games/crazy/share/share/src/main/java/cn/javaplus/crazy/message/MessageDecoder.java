package cn.javaplus.crazy.message;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;

import cn.javaplus.crazy.D;

/**
 * 消息解码器
 * 
 */
public class MessageDecoder extends LengthFieldBasedFrameDecoder {

	public MessageDecoder() {
		super(10 * 1024 * 1024, 0, D.LENTH_FIELD_LENGHT);
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) throws Exception {

		ChannelBuffer frame = (ChannelBuffer) super
				.decode(ctx, channel, buffer);
		if (frame == null) {
			return null;
		}

		int readInt = frame.readInt();
		byte[] bytes = new byte[readInt];
		frame.readBytes(bytes);

		return new String(bytes);
	}

	@Override
	protected Object decodeLast(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) throws Exception {

		return this.decode(ctx, channel, buffer);
	}

}