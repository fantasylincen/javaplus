package cn.mxz.base.handler;

import java.util.Arrays;
import java.util.concurrent.ConcurrentMap;

import cn.javaplus.util.Util;

import com.google.common.collect.Maps;

public class MinaPacketDispatcher {

	private static final byte			HEAD	= 116;
	private static final byte			TAIL	= 127;
	private ConcurrentMap<Long, byte[]>	packets;

	public MinaPacketDispatcher() {
		packets = Maps.newConcurrentMap();
	}

	/**
	 * 将数据加入到分发器
	 *
	 * @param id
	 *            客户端的ID
	 * @param data
	 *            客户端发过来的数据
	 */
	public void push(long id, byte[] data) {
		if (data == null || data.length == 0) {
			return;
		}
		byte[] bs = packets.get(id);
		if (bs != null) {
			data = Util.Byte.merge(bs, data);
		}
		packets.put(id, data);
	}

	/**
	 * 取出这次客户端完整的数据包, 如果取不出来, 返回null
	 *
	 * @param id
	 * @return 有可能为null
	 */
	public byte[] poll(long id) {
//		byte[] data = packets.get(id);
//		removeDirty(data, id); // 移除脏数据
//		byte[] packet = getFullPacket(data);
//		if (packet == null) {
//			return null;
//		}
//		delete(packet, id); // 删除前packet.length个字节
//		return packet;
		return packets.get(id);
	}
	
	

	private void removeDirty(byte[] data, long id) {
		int index = findHead(data);
		if (index == -1) {
			clear(id);
		} else {
			data = Arrays.copyOfRange(data, index, data.length);
			packets.put(id, data);
		}
	}

	private int findHead(byte[] data) {
		for (int i = 0; i < data.length - 2; i++) {
			byte head = data[i];
			short length = Util.Byte.toShort(data[i + 1], data[i + 2]);
			if(head == HEAD && length >= 0) {
				return i;
			}
		}
		return -1;
	}

	public void clear(long id) {
		packets.remove(id);
	}

	public static void main(String[] args) {

		byte[] d1 = new byte[] { 11, 2, 3, 4 };
		byte[] d2 = new byte[] { 99 };

		System.out.println(Arrays.toString(Util.Byte.merge(d1, d2)));
	}

}
