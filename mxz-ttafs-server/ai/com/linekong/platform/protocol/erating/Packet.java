package com.linekong.platform.protocol.erating;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.mina.core.buffer.IoBuffer;

import cn.javaplus.util.Util;
import cn.mxz.base.config.ServerConfig;

import com.linekong.platform.protocol.erating.define.D;
import com.linekong.platform.protocol.erating.eRatingAGIP.IERatingProtocol;
import com.linekong.platform.protocol.erating.eRatingAGIP.eRatingProtocol;

public class Packet {

	private Socket	socket;
	private long	cmdId;
	private IERatingProtocol	body;

	public Packet(Socket socket) {
		this.socket = socket;
	}

	public Response send(long gameId) {
		try {
			sendRequest(gameId);
//			Debuger.debug("Packet.send() 发送了数据包");
			return getResponse(socket);

		} catch (java.net.SocketTimeoutException e){
			throw new RequestTimeoutException("发包服务器没有响应!");
		} catch (IOException e) {
			throw Util.Exception.toRuntimeException(e);
		}
	}
	
	public Response send() {
		return send(D.GAME_ID);
	}

	private void sendRequest(long gameId) throws IOException {
		int bind_totalLength;
		short version = 0x20;
		short remain_packages = 0;
		long sequenceId = 1111;
		
//		Debuger.debug("Packet.sendRequest() gameId = " + D.GAME_ID);

		byte[] bindbody = body.getBody();
		short resv = 0;
		int bind_checksum;

		IoBuffer bindhead = IoBuffer.allocate(20);

		bind_totalLength = 24 + bindbody.length;
		IoBuffer d = IoBuffer.allocate(bind_totalLength);

		bindhead.putUnsignedShort(bind_totalLength);
		bindhead.putUnsigned(version);// version
		bindhead.putUnsigned(remain_packages);// remainPackage
		bindhead.putUnsignedInt(cmdId);// commandId
		bindhead.putUnsignedInt(sequenceId);// sequenceId
		bindhead.putUnsignedInt(gameId);// gameId
		bindhead.putUnsignedInt(ServerConfig.getServerId());// gatewayId
		bindhead.flip();

		d.put(bindhead);
		d.put(bindbody);
		d.putShort(resv);// resv
		bind_checksum = eRatingProtocol.crc16(d.array(), bind_totalLength - 2);
		d.putUnsignedShort(bind_checksum);// checkSum
		d.flip();

		OutputStream os = socket.getOutputStream();
//		Debuger.debug("Packet.sendRequest() os.write(d.array());");
		os.write(d.array());
		os.flush();
//		Debuger.debug("Packet.sendRequest() os.write(d.array()) success");
	}

	private Response getResponse(Socket s) throws IOException {
		s.setSoTimeout(5000);
		InputStream in = s.getInputStream();
		byte[] charBuf = new byte[4096];
		int size = 0;
		size = in.read(charBuf, 0, 4096);
		if (size == -1) {
			throw new RuntimeException("服务器没有响应!");
		}

		IoBuffer buf = IoBuffer.allocate(size);
		buf.put(charBuf, 0, size);
		buf.flip();
		ResponseImpl rs = new ResponseImpl();
		rs.setTotalLength(buf.getUnsignedShort());
		rs.setVersion(buf.getUnsigned());
		rs.setRemainPackages(buf.getUnsigned());
		rs.setCommandId(buf.getUnsignedInt());
		rs.setSequenceId(buf.getUnsignedInt());
		rs.setGameId(buf.getUnsignedInt());
		rs.setGatewayId(buf.getUnsignedInt());

		byte[] body = new byte[size - 24];

		buf.get(body);
		rs.setBody(body);

//		Debuger.debug("Packet.getResponse() 收到了服务器完整的返回!");

		return rs;
	}

	public void setCommandId(long cmdId) {
		this.cmdId = cmdId;
	}

	public void setBody(IERatingProtocol body) {
		this.body = body;
	}

}
