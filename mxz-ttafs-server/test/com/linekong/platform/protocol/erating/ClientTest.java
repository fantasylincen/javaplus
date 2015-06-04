package com.linekong.platform.protocol.erating;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.mina.core.buffer.IoBuffer;

import com.linekong.platform.protocol.erating.debug.Debuger;
import com.linekong.platform.protocol.erating.eRatingAGIP.BindRequest;
import com.linekong.platform.protocol.erating.eRatingAGIP.BindRespond;
import com.linekong.platform.protocol.erating.eRatingAGIP.RoleStateQueryRequest;
import com.linekong.platform.protocol.erating.eRatingAGIP.RoleStateQueryRespond;
import com.linekong.platform.protocol.erating.eRatingAGIP.eRatingProtocol;

public class ClientTest {
	public static void main(String args[]) {
		InetSocketAddress endpoint = new InetSocketAddress("113.208.129.53", 14583);
		Socket socket = null;
		OutputStream out = null;
		InputStream in = null;

		int bind_totalLength;
		short version = 0x20;
		short remain_packages = 0;
		long bind_commandId = PDUMessage.CMD_BIND;
		long sequenceId = 1111;
		long gameId = 559;
		long gatewayId = 559001;
		BindRequest bindReq = new BindRequest();
		bindReq.setGatewayCode("gw559001");
		bindReq.setGatewayPassword("0xdw37261447212515ibxo7282211247");
		bindReq.setMac("001D7DD14955");
		bindReq.setReconnectFlag((byte) 1);
		bindReq.setServerId(1L);
		byte[] bindbody = bindReq.getBody();
		short resv = 0;
		int bind_checksum;

		IoBuffer bindhead = IoBuffer.allocate(20);

		bind_totalLength = 20 + bindbody.length + 4;
		IoBuffer bind_dates = IoBuffer.allocate(bind_totalLength);

		bindhead.putUnsignedShort(bind_totalLength);
		bindhead.putUnsigned(version);// version
		bindhead.putUnsigned(remain_packages);// remainPackage
		bindhead.putUnsignedInt(bind_commandId);// commandId
		bindhead.putUnsignedInt(sequenceId);// sequenceId
		bindhead.putUnsignedInt(gameId);// gameId
		bindhead.putUnsignedInt(gatewayId);// gatewayId
		bindhead.flip();

		bind_dates.put(bindhead);
		bind_dates.put(bindbody);
		bind_dates.putShort(resv);// resv




		bind_checksum = eRatingProtocol.crc16(bind_dates.array(), bind_totalLength - 2);
		bind_dates.putUnsignedShort(bind_checksum);// checkSum
		bind_dates.flip();

		RoleStateQueryRequest request = new RoleStateQueryRequest();

		long request_commandId = PDUMessage.CMD_ROLE_STATE_QUERY;

		byte[] requestBody = request.getBody();
		System.out.println("requestBody.length=" + requestBody.length);
		int request_totalLength = 20 + requestBody.length + 4;
		IoBuffer requestData = IoBuffer.allocate(request_totalLength);
		IoBuffer requestHead = IoBuffer.allocate(20);

		requestHead.putUnsignedShort(request_totalLength);
		requestHead.putUnsigned(version);// version
		requestHead.putUnsigned(remain_packages);// remainPackage
		requestHead.putUnsignedInt(request_commandId);// commandId
		requestHead.putUnsignedInt(sequenceId);// sequenceId
		requestHead.putUnsignedInt(gameId);// gameId
		requestHead.putUnsignedInt(gatewayId);// gatewayId
		requestHead.flip();

		requestData.put(requestHead);
		requestData.put(requestBody);
		requestData.putShort(resv);// resv
		int request_checkSum = eRatingProtocol.crc16(requestData.array(), request_totalLength - 2);
		requestData.putUnsignedShort(request_checkSum);// checkSum
		requestData.flip();

		try {
			socket = new Socket();
			socket.setSoLinger(true, 2);
			socket.setSendBufferSize(32 * 1024);
			socket.setReceiveBufferSize(32 * 1024);
			socket.setTcpNoDelay(true);
			socket.connect(endpoint);
			out = socket.getOutputStream();
			in = socket.getInputStream();

			IoBuffer[] sendMessage = { bind_dates, requestData };
			for (int j = 0; j < 2; ++j) {
				out.write(sendMessage[j].array());
				out.flush();

				in = socket.getInputStream();

				byte[] charBuf = new byte[4096];
				int size = 0;

				Debuger.debug("ClientTest.main()1");
				size = in.read(charBuf, 0, 4096);
				Debuger.debug("ClientTest.main()2");

				IoBuffer ResBuf = IoBuffer.allocate(size);
				ResBuf.put(charBuf, 0, size);
				ResBuf.flip();

				System.out.println("totalLength=" + ResBuf.getUnsignedShort());
				System.out.println("version=" + ResBuf.getUnsigned());
				System.out.println("remainPackages=" + ResBuf.getUnsigned());
				System.out.println("commandId=" + ResBuf.getUnsignedInt());
				System.out.println("sequenceId=" + ResBuf.getUnsignedInt());
				System.out.println("gameId=" + ResBuf.getUnsignedInt());
				System.out.println("gatewayId=" + ResBuf.getUnsignedInt());
				byte[] ResBody = new byte[size - 20 - 4];
				ResBuf.get(ResBody);
				if (0 == j) {
					BindRespond bindRes = new BindRespond();
					if (bindRes.analyzeBody(ResBody) < 0) {
						System.out.println("BindRespondAnalyzeError");

					} else {
						System.out.println("BindResult=" + bindRes.getResultCode() + "\n");

					}
				} else {

					RoleStateQueryRespond respond = new RoleStateQueryRespond();
					int resAnalyze = respond.analyzeBody(ResBody);
					if (resAnalyze < 0) {
						System.out.println("RespondAnalyzeError");

					} else {
						System.out.println("getResultCode=" + respond.getResultCode());
					}
				}
			}
			out.close();
			in.close();
			socket.close();

		} catch (Exception e) {
			System.out.println("Error" + e);
		}

	}

}
