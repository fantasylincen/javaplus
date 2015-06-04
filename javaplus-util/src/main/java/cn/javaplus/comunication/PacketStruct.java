package cn.javaplus.comunication;

/**
 * 一个通信包结构
 * @author 	林岑
 * @since	2012年12月5日 15:18:05
 *
 */
public interface PacketStruct {

	/**
	 * 将该通信包结构置入(追加到)通信包中
	 * @param p
	 */
	void imbeddingTo(Packet p);
}
