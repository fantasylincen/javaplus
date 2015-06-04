package cn.javaplus.byteutil;

import java.nio.ByteBuffer;


/**
 * 数据包分析类
 * @author 林岑
 */
public class PacketProfiler {


	private ByteBuffer bf = ByteBuffer.allocate(2048);
	private short [] sizes;
	
	public PacketProfiler(byte[] b) {
		this.bf.put(b);
		this.bf.flip();
		this.bf.position(0);
	}

	/**
	 * 按字节数分组
	 * @param i
	 */
	public void set(int... i) {	
		this.sizes = new short[i.length];
		int sizeAll = 0;
		for (int j = 0; j < i.length; j++) {
			this.sizes[j] = (short)i[j];
			sizeAll += this.sizes[j];
		}
		if(sizeAll != this.bf.limit() ) {
			throw new IllegalArgumentException("总大小错误!" + sizeAll);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (short s : this.sizes) {
			switch (s) {
			case 1:
				sb.append(bf.get() + ", ");
				break;
			case 2:
				sb.append(bf.getShort() + ", ");
				break;
			case 4:
				sb.append(bf.getInt() + ", ");
				break;
			default:
				byte [] b = new byte[s];
				bf.get(b);
				sb.append(new String(b) + ", ");
				break;
			}
		}
		
		sb.substring(0, sb.length() - 2);
		this.bf.position(0);
		return sb.toString();
	}
}
