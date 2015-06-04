package cn.javaplus.commons.socket;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

import cn.javaplus.collections.keyvalue.Value;



/**
 *
 * 套接字
 * @author 	林岑
 * @time	2013年5月28日 10:03:42
 *
 */
public interface ISocket extends Value, Closeable{

	/**
	 * 套接字唯一ID
	 */
	long getId();

	/**
	 * 远端IP地址
	 * @return
	 */
	String getIP();

	/**
	 * 写入数据
	 */
	void write(ByteBuffer data) throws IOException;

	/**
	 * 关闭该套接字
	 */
	@Override
	void close();

}
