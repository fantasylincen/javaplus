package cn.javaplus.comunication;

import java.nio.ByteBuffer;
import java.util.Arrays;

import cn.javaplus.commons.logger.Logs;
import cn.javaplus.commons.socket.ISocket;
import cn.javaplus.commons.user.IUser;

/**
 *
 * 信息包, 该信息包的最大长度为10KB (包括包头包尾以及包号在内)
 *
 * @author 	林	岑
 * @time	2012年6月29日 12:21:19
 */
public abstract class Packet implements IPacket{

	/**
	 * 待发送的数据信息
	 */
	protected ByteBuffer info = ByteBuffer.allocate(Short.MAX_VALUE);

	/**
	 * 包数据大小,包含了包头包尾的大小
	 */
	protected int packetSize;

	/**
	 * 新建一个数据包,不含包号
	 */
	protected Packet() {
	}

	public Packet(short userCreateData) {
		info.putShort(userCreateData);
	}

	/**
	 * 包头
	 */
	protected abstract int getHead();

	/**
	 * 将i强制转换后放入包中
	 */
	public void putByte(int i) {

		this.info.put((byte) i);
	}

	/**
	 * @deprecated 建议使用 putInt 因为大家常常会因为int short问题来重新定协议
	 */
	public void putShort(int i) {

		this.info.putShort((short) i);
	}

	@Override
	public void putInt(int i) {

		this.info.putInt(i);
	}

	@Override
	public void putDouble(double i) {

		this.info.putDouble(i);
	}

	@Override
	public void putString(String str) {

		if(str == null) {

			str = "";
		}

		byte[] bytes = str.getBytes();

		this.info.putShort((short) bytes.length);

		this.info.put(bytes);
	}

	/**
	 * 将包发送给前端, 如果已经发送过了, 那么就不做任何处理
	 */
	@Override
	public void send(ISocket writer) {

		if(writer == null) {
			return;
		}

		packetSize = info.position();

		info.flip();

		ByteBuffer buff = ByteBuffer.allocate(info.capacity() + 4);

		buff.put((byte) getHead());

		buff.putShort((short) packetSize);

		buff.put(info);

		buff.put((byte) 127);

		buff.flip();

//		System.out.println("Packet.send()" + Arrays.toString(buff.array()));
		
		try {

//			System.out.println("asdfasdlkujoiutqw mnvzxjhyfgas: " + Arrays.toString(buff.array()));

			writer.write(buff);

		} catch (Exception e) {

			e.printStackTrace();

			Logs.debug.error("包发送失败了!"  + e.getMessage());
		}

	}


	/**
	 * 将包发送给前端, 如果已经发送过了, 那么就不做任何处理
	 */
	public void send(IUser user) {
		if(user == null) {
			return;
		}
		send(user.getSocket());
	}

	public int position() {
		return this.info.position();
	}

	/**
	 * 在指定位置放入一个short值
	 * @param index
	 * @param value
	 * @deprecated 建议使用 putInt 因为大家常常会因为int short问题来重新定协议
	 */
	public void putShort(int index, int value) {
		this.info.putShort(index, (short) value);
	}

	/**
	 * 在指定位置放入一个int值
	 * @param index
	 * @param value
	 */
	public void putInt(int index, int value) {
		this.info.putInt(index, value);
	}

	/**
	 * 绝对放入一个byte
	 * @param index
	 * @param value
	 * @deprecated 建议使用 putInt 因为大家常常会因为int short问题来重新定协议
	 */
	public void putByte(int index, int value) {
		this.info.put(index, (byte) value);
	}

	public byte[] array() {
		return Arrays.copyOf(info.array(), limit());
	}

	/**
	 * 真实数据, 不包含废弃数据
	 * @return
	 */
	public byte[] dataArray() {

		return Arrays.copyOf(info.array(), packetSize);
	}

	public int limit() {
		return info.limit();
	}

	/**
	 * 放入一个包结构
	 * @param fps
	 */
	@Override
	public void put(PacketStruct fps) {
		fps.imbeddingTo(this);
	}

	public void put(byte[] data) {
		this.info.put(data);
	}

	@Override
	public void putBoolean(boolean value) {
		putByte(value ? 1 : 0);
	}

	public void putBoolean(int index, boolean value) {
		this.info.put(index, (byte) (value ? 1 : 0));
	}

}
