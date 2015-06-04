package cn.javaplus.commons.socket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

import cn.javaplus.commons.define.Define;
import cn.javaplus.commons.event.IAllHandler;
import cn.javaplus.util.UtilShangJie;


public class MSocket implements ISocket, MySocket {

	private SocketChannel sc;
	private SelectionKey sk = null;
	private IAllHandler handler;
	private ByteBuffer buffer;

	@SuppressWarnings("unused")
	private int bufferLength;

	private final ConcurrentLinkedQueue<ByteBuffer> readBuffers = new ConcurrentLinkedQueue<ByteBuffer>();
	private final ConcurrentLinkedQueue<ByteBuffer> writeBuffers = new ConcurrentLinkedQueue<ByteBuffer>();
	private long lastTimeReceived;
	private String IP = "";
	private int port = 0;

	private final ConcurrentLinkedQueue<byte[]> readBytes = new ConcurrentLinkedQueue<byte[]>();
	private byte sing = 0;
	private int qunum = 0;
	private int qunumhead = 0;
	private byte headLength = 3;
	private byte temphead[] = new byte[headLength];
	private byte tempbody[] = null;
	private byte head = 0;

	@SuppressWarnings("unused")
	private byte end = 0;

	private short packLength = 0;

	private String xml = "<cross-domain-policy> <allow-access-from domain=\"*\" to-ports=\"*\"/></cross-domain-policy> ";

	private static AtomicLong ids = new AtomicLong();

	private Long id;

	MSocket(SocketChannel sc) {
		this.sc = sc;
		buffer = ByteBuffer.allocate(1024 * 10);
		lastTimeReceived = System.currentTimeMillis();
		id = ids.addAndGet(1);
	}

	@Override
	public long getId() {
		return id;
	}

	void init(final IAllHandler handler) throws IOException {
		this.handler = handler;
		init();
	}

	private void init() throws IOException {
		handler.onConnectionOpening(this);
	}

	public SocketChannel getChannel() {
		return sc;
	}

	@Override
	public long getLastReceivingTime() {
		return lastTimeReceived;
	}

	public void setLastReceivingTime(long time) {
		lastTimeReceived = time;
	}

	void handlerData() throws IOException {

		buffer.clear();

		int result = sc.read(buffer);

		bufferLength = result;

		if (result > 0) {
			buffer.flip();

			byte packet[] = new byte[result];
			buffer.get(packet);

			try {
				byte[] a2 = packet;
				String b1 = new String(a2);

				if (b1.indexOf("<policy-file-request/>") >= 0) {
					ByteBuffer tempData = ByteBuffer.allocate(89);
					tempData.put(UtilShangJie.setStringLength(xml + "", 89).getBytes());
					tempData.flip();
					sc.write(tempData);
					sc.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			Datahandler(packet, result);

			if (readBytes.size() > 0) {
				handler.onData(this);
			}
			setLastReceivingTime(System.currentTimeMillis());
		} else if (result == 0) {

		} else if (result < 0) {
			throw new IOException();
		}
	}

	/**
	 * 接收数据分包处理
	 *
	 * */
	private void Datahandler(byte[] packet, int length) {

		// byte packet[]= new byte[length];
		// bfer.get(packet);

		int offset = 0;

		while (length > offset) {

			if (sing == 0) {

				if (headLength - qunumhead <= length - offset) {
					System.arraycopy(packet, offset, temphead, qunumhead,
							headLength - qunumhead);
					head = temphead[0];
					packLength = UtilShangJie.byteToShort(temphead[1], temphead[2]);
					if (head != Define.CLIENT_PACKETS_HEAD) {
						temphead[0] = 0;
						temphead[1] = 0;
						temphead[2] = 0;
						offset += 1;
						continue;
					}
					tempbody = new byte[packLength];
					offset += (headLength - qunumhead);
					sing = 1;
					qunum = 0;
				} else {
					System.arraycopy(packet, offset, temphead, qunumhead,
							length - offset);
					qunumhead += (length - offset);
					offset += (length - offset);
				}
			} else if (sing == 1) {

				if (packLength - qunum <= length - offset) {
					System.arraycopy(packet, offset, tempbody, qunum,
							packLength - qunum);
					readBytes.add(tempbody);
					offset += (packLength - qunum);
					sing = 2;
					packLength = 0;
				} else {
					System.arraycopy(packet, offset, tempbody, qunum, length
							- offset);
					qunum += (length - offset);
					offset += (length - offset);
				}

			} else {
				end = packet[offset];
				sing = 0;
				qunumhead = 0;
				offset += 1;
			}
		}
	}

	@Override
	public final void write(ByteBuffer buffer) throws IOException {
		short packetN = buffer.getShort(3);
		if (packetN != 20000) {
			// System.out.println("--数据包发送情况："+packetN);
		}
		writeBuffers.add(buffer);

		if (sk.isValid()) {
			sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
			sk.selector().wakeup();
		} else {
		}
		// throw new IOException();

	}

	protected void handleWrite() throws IOException {
		if (!writeBuffers.isEmpty()) {
			while (!writeBuffers.isEmpty()) {
				ByteBuffer temp = writeBuffers.poll();
				if (temp.remaining() > 0) {
					sc.write(temp);
				}
			}
		}

		sk.interestOps(SelectionKey.OP_READ);
	}

	final boolean hasDataToSend() {
		return !writeBuffers.isEmpty();
	}

	/**
	 * @see IConnection
	 */
	public synchronized final boolean isOpen() {
		if (getSocketChannel() == null) {
			return false;
		} else {
			return getSocketChannel().isOpen();
		}
	}

	public synchronized final SocketChannel getSocketChannel() {
		if (sc != null)
			return sc;
		return null;
	}

	@Override
	public ConcurrentLinkedQueue<byte[]> getBufferList() {
		return readBytes;
	}

	void registerSelect(Selector select, int ops) {
		if (sc != null && sc.isOpen()) {
			try {

				sc.configureBlocking(false);
				sk = sc.register(select, ops, this);
				port = sc.socket().getPort();
				IP = sc.socket().getInetAddress().getHostAddress();
				// ConCheckJob.addCheckObj(this); //安全检查

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("MSocket register Selector error.");
			}
		}
	}

	@Override
	public void close() {
		try {
			readBuffers.clear();
			writeBuffers.clear();
			sc.close();
			sk.cancel();
			handler.onDestroy(this);
//			ConCheckJob.UseCons.remove(IP+"_"+port); //移除连接的标识
		}catch(Exception e){
			e.printStackTrace();
			// ;if(GameDefine.PrintSession==1)System.out.println("MSocket close error.");
		}
	}

	@Override
	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	@Override
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public int hashCode() {
		return new Long(getId()).hashCode();
	}

	/**
	 * 判断两个对象的类型是否一致
	 *
	 * @param obj1
	 * @param obj2
	 * @param c
	 * @return
	 */
	private boolean isTypeEquals(Object obj1, Object obj2, Class<?> c) {

		if (obj1 == null || obj2 == null) {

			return false;
		}

		if (!obj1.getClass().equals(c) || !obj2.getClass().equals(c)) {

			return false;
		}

		return true;
	}

	@Override
	public boolean equals(Object obj) {

		if(!isTypeEquals(obj, this, getClass())) {

			return false;
		}

		MSocket m = (MSocket) obj;
		return m.getId() == getId() + 0;
	}

	@Override
	public String toString() {
		return id + "|" + getIP() + ":" + getPort();
	}

	@Override
	public void write(String message) throws IOException {
		byte[] data = message.getBytes();
		ByteBuffer buffer = ByteBuffer.allocate(data.length);
		buffer.put(data);
		buffer.flip();
		write(buffer);
	}
}
