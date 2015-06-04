package game;

import java.nio.ByteBuffer;

/**
 * 允许在网络上传输的实体类
 * @author deng
 * 2014-2-21 下午2:29:01
 */
public interface ITransformStream {
	
	/**
	 * 构建用于网络传输的byteBuffer
	 * @param	buffer			把数据放入此buffer
	 */
	void buildTransformStream( ByteBuffer buffer );

}
