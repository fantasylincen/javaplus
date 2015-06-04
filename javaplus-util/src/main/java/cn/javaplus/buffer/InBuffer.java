package cn.javaplus.buffer;


/**
 * 客户端发往服务端的缓存字节
 * @author 	lincen
 * @time	2012年6月18日 09:19:54
 */
public interface InBuffer {
	
	/**
	 * 读取一个自然数, 如果读到的不是自然数, 那么就会抛出NotNaturalNumberException
	 */
	byte get();
	
	/**
	 * 读取一个自然数, 如果读到的不是自然数, 那么就会抛出NotNaturalNumberException
	 */
	short getShort();
	
	/**
	 * 读取一个自然数, 如果读到的不是自然数, 那么就会抛出NotNaturalNumberException
	 */
	int getInt();

	/**
	 * @return	没有限制地读取
	 */
	int getIntUnlimited();
	
	/**
	 * @return	没有限制地读取
	 */
	short getShortUnlimited();

	/**
	 * @return	没有限制地读取
	 */
	byte getByteUnlimited();
	
	/**
	 * 指针往回移pos个单位
	 * @param pos
	 */
	void moveBack(int pos);

	/**
	 * 字符串读取, 最大长度为2048, 如果超限, 那么就会抛出RuntimeException
	 */
	String getString();

	/**
	 * 可读剩余字节数
	 * @return
	 */
	int available();

	/**
	 * 读取指定长度的字节数组
	 * @param len
	 * @return
	 */
	byte[] get(int len);

	boolean getBoolean();
}
