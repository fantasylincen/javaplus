package cn.javaplus.comunication;


import cn.javaplus.commons.socket.ISocket;
import cn.javaplus.compressor.Compressor;


/**
 * 该响应的包结构( byte: 包头,   short:包长,	byte[] 主体数据,	byte:包尾 127)
 * @author 林岑
 *
 */
public interface Response {

	void send(ISocket u);

	void putInt(int value);

	void putDouble(double value);

	void putString(String value);

	void put(Bytes message);

	void putBoolean(boolean value);

	void put(byte[] compress);

	/**
	 * 压缩数据包
	 * @param compressType	压缩方式: 仅支持支持 "gzip"
	 * @return 压缩后的字节数
	 *
	 */
	int compress(Compressor c);

	int position();
}
