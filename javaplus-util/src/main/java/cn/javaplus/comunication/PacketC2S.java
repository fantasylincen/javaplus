package cn.javaplus.comunication;

/**
 * 客户端发往服务器的包(测试用)
 *
 * @author 	林岑
 * @time	2013年5月28日 16:37:54
 *
 */
public class PacketC2S extends Packet {

	public PacketC2S() {
		super();
	}

	@Override
	protected int getHead() {
		return 116;
	}

	@Override
	public void put(Bytes message) {
		put(message.array());
	}
}
