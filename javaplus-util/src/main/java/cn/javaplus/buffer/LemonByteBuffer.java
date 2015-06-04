package cn.javaplus.buffer;

import java.nio.ByteBuffer;
import java.util.Arrays;

import cn.javaplus.checker.NaturalNumberChecker;



/**
 * 扩展ByteBuffer, 用于处理客户端发过来的数据.
 * @author 	林岑
 * @since	2012年3月31日 14:11:37
 */
public class LemonByteBuffer implements InBuffer, OutBuffer{

	private ByteBuffer buff;

	/**
	 * 初始化一个扩展缓冲区.长度为data的长度. 初始位置为positionStop
	 * @param data
	 * @param positionStop	初始位置
	 */
	public LemonByteBuffer( byte [] data, int positionStop ) {
		buff = ByteBuffer.allocate( data.length );
		buff.put(data);
		buff.position(positionStop);
	}

	/**
	 * 新建一个定长的缓冲区, 作用和ByteBuffer相同
	 * @param limit
	 */
	public LemonByteBuffer(int limit){
		this.buff = ByteBuffer.allocate(limit);
	}

	@Override
	public final void position(int pos) {
		this.buff.position(pos);
	}
	
	@Override
	public final int position(){
		return buff.position();
	}

	@Override
	public final byte get(){
		byte b = buff.get();
		new NaturalNumberChecker().check(b);
		return b;
	}

	@Override
	public final short getShort(){
		short b = buff.getShort();
		new NaturalNumberChecker().check(b);
		return b;
	}

	@Override
	public final int getInt(){
		int b = buff.getInt();
		new NaturalNumberChecker().check(b);
		return b;
	}

	@Override
	public final String getString() {

		int size = this.buff.getShort();
		
		if(size > 10240) {
			throw new RuntimeException("玩家发送的字符串过长! 大小:" + size);
		}
		
		byte[] dst = new byte[size];

		this.buff.get(dst, 0, size);

		return new String(dst).trim();
	}

	/*
	 * 读取字符串, 从data的第i个字节开始读取length个长度
	 */
	public static final String getString(byte[] data, int i, int length) {
		return new String(Arrays.copyOfRange(data, i, length + i));
	}

	@Override
	public final int limit(){
		return buff.limit();
	}

	@Override
	public void flip() {
		this.buff.flip();
	}

	@Override
	public void put(byte dat) {
		this.buff.put(dat);
	}

	@Override
	public void put(byte[] bytes) {
		this.buff.put(bytes);
	}

	@Override
	public void putShort(short das) {
		this.buff.putShort(das);
	}

	@Override
	public void putInt(int h) {
		this.buff.putInt(h);
	}

	@Override
	public void put(ByteBuffer b) {
		this.buff.put(b);
	}

	@Override
	public void put(int index, byte v) {
		this.buff.put(index, v);
	}

	@Override
	public void moveBack(int pos) {
		position(position() - pos);
	}

	@Override
	public boolean getBoolean() {
		return get() == 1;
	}
	
	@Override
	public int available() {
		return limit() - position();
	}

	@Override
	public byte[] get(int len) {
		byte[] bs = new byte[len];
		buff.get(bs);
		return bs;
	}

	@Override
	public void putString(String str) {
		buff.putShort((short) str.getBytes().length);
		buff.put(str.getBytes());
	}

	@Override
	public int getIntUnlimited() {
		return buff.getInt();
	}

	@Override
	public short getShortUnlimited() {
		return buff.getShort();
	}

	@Override
	public byte getByteUnlimited() {
		return buff.get();
	}
}
