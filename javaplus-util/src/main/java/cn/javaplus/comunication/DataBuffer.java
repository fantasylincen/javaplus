package cn.javaplus.comunication;

import java.nio.ByteBuffer;
import java.util.Arrays;


/**
 * 
 * 反馈给客户端的消息
 * @author 	林岑
 * @since	2013年6月4日 15:22:45
 *
 */
public class DataBuffer implements Bytes{

	private ByteBuffer data;
	
	/**
	 * 缓冲区大小
	 */
	private int size;
	
	public DataBuffer() {
		data = ByteBuffer.allocate(30);
	}
	
	@Override
	public byte[] array() {
		return Arrays.copyOf(data.array(), size);
	}

	public void putInt(int value){
		final int len = 4;
		tryExpand(len);
		data.putInt(value);
		size += len;
	}
	
	private void tryExpand(int len) {
		if(size + len >= data.limit()) {
			expand(len);
		}		
	}

	public void putString(String str) {

		if(str == null) {
			str = "";
		}
		byte[] bytes = str.getBytes();
		
		final int len = 2 + bytes.length;
		
		tryExpand(len);
		
		this.data.putShort((short) bytes.length);
		this.data.put(bytes);
		
		size += len;
	}
	
	/**
	 * 扩容
	 * @param len 
	 */
	private void expand(int len) {
		ByteBuffer newData = ByteBuffer.allocate(data.limit() + len + 30);
		newData.put(array());
		data = newData;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(array());
	}

	public void putBoolean(boolean value) {
		final int len = 1;
		tryExpand(len);
		data.put((byte) (value ? 1 : 0));
		size += len;
	}
}
