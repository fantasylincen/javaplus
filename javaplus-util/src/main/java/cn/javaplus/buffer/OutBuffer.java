package cn.javaplus.buffer;

import java.nio.ByteBuffer;

/**
 * 输出缓存, 一般用于向客户端发包用
 * 
 * @author 林岑
 * @since 2012年6月18日 09:22:01
 */
public interface OutBuffer {

	void put(int p, byte b);

	void put(ByteBuffer b);

	void putInt(int h);

	void putShort(short das);

	void put(byte[] bytes);

	void put(byte dat);

//	void putString(String str, int size);
	
	void putString(String str);
	
	void flip();

	void position(int pos);

	int position();

	int limit();
}
