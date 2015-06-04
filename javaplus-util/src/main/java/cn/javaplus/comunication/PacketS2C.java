package cn.javaplus.comunication;

import java.nio.ByteBuffer;

import cn.javaplus.compressor.Compressor;



/**
 * 服务器发往客户端的包
 *
 * @author 	林岑
 * @time	2013年5月28日 16:37:54
 *
 */
public class PacketS2C extends Packet implements Response {

	public PacketS2C() {
		super();
	}

	public PacketS2C(int userCreateData) {
		super((short) userCreateData);
	}

	@Override
	protected int getHead() {
		return 117;
	}

	@Override
	public void put(Bytes message) {
		put(message.array());
	}

	@Override
	public int compress(Compressor c) {

		info.flip();

		byte [] data = get(info);

//		System.out.println("被压缩的数据:" + Arrays.toString(data));

		byte[] compress = c.compress(data);

//		System.out.println("压缩后的数据:" + Arrays.toString(compress));
//
//		System.out.println("解压后的数据:" + Arrays.toString(test));

//		System.out.println("PacketS2C.compress() 压缩率:" + (compress.length + 0f) / data.length * 100+ "%  字节数:" + compress.length + "/" + data.length);

		packetSize = compress.length;

		info = ByteBuffer.allocate(packetSize);

		info.put(compress);

		return compress.length;
	}

//	@Override
//	public void compress(String compressType, int offset, int len) {
//
//		Compressor c = getCompressor(compressType);
//
//		info.flip();
//
//		byte [] data = sub(info, offset, len);
//
//		byte[] compress = c.compress(data);
//
//		packetSize = len + offset;
//
//		set(compress, offset, len);
//	}
//
//	private byte[] sub(ByteBuffer info, int offset, int len) {
//
//		byte [] data = new byte[len];
//
//		for (int i = 0; i < data.length; i++) {
//
//			data[i] = info.get(i + offset);
//		}
//
//		return data;
//	}
//
//	private void set(byte[] compress, int offset, int len) {
//
//		ByteBuffer old = info;
//
//		//压缩时会将Buff分为3段. remain为第3段字节长度
//
//		int p1 = 0;
//
//		int p2 = offset;
//
//		int p3 = offset + len;
//
//		int p4 = old.limit();
//
//		if(p3 > p4 || p2 > p3 || p1 > p2) {
//
//			throw new IllegalArgumentException("非法参数:" + p1 + ", " + p2 + ", " + p3 + ", " + p4);
//		}
//
//		info = ByteBuffer.allocate(p2 - p1 + p4 - p3 + compress.length);
//
//		for (int i = 0; i < p2; i++) {
//
//			info.put(old.get(i));
//		}
//
//		info.put(compress);
//
//
//		for (int i = 0; i < p4 - p3; i++) {
//
//			info.put(old.get(i + p3));
//		}
//	}

	private byte[] get(ByteBuffer info) {

		byte[] data = new byte[info.limit()];

		for (int i = 0; i < data.length; i++) {

			data[i] = info.get(i);
		}

		return data;
	}

	@Override
	public int position() {

		return info.position();
	}

//	public static void main(String[] args) {
//
//		ByteBuffer buff = ByteBuffer.allocate(10);
//
//		buff.put((byte) 0);
//		buff.put((byte) 1);
//		buff.put((byte) 2);
//		buff.put((byte) 3);
//		buff.put((byte) 4);
//		buff.put((byte) 5);
//		buff.put((byte) 6);
//		buff.put((byte) 7);
//		buff.put((byte) 8);
//
//		buff.flip();
//
//		byte [] data = new byte[10];
//
//		buff.get(data, 3, 2);
//
//		System.out.println(Arrays.toString(data));
//	}
}
