package com.linekong.platform.protocol.erating.server.packets;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;

import org.apache.mina.core.buffer.IoBuffer;
import org.xsocket.connection.INonBlockingConnection;

import cn.mxz.base.config.ServerConfig;

import com.linekong.platform.protocol.erating.LineKongServerPassword;
import com.linekong.platform.protocol.erating.PDUMessage;
import com.linekong.platform.protocol.erating.define.D;
import com.linekong.platform.protocol.erating.eRatingAGIP.BindRequest;
import com.linekong.platform.protocol.erating.eRatingAGIP.BindRespond;
import com.linekong.platform.protocol.erating.eRatingAGIP.eRatingProtocol;

public class BindPacket {


	public static void run(ByteBuffer data, INonBlockingConnection con, long sequenceId, long gameId) throws BufferOverflowException, IOException{
		System.out.println("\n*****BindRequestAnalyze result******");
		BindRequest req = new BindRequest();
		System.out.println("BindBody is " + data.remaining() + " byte");

		int resAnalyze = req.analyzeBody(data.array());
		if (resAnalyze < 0) {
			System.out.println("BindAnalyzeError");

		} else {
			//req.
			
			System.out.println("getMac=" + req.getMac());
			System.out.println("getGatewayCode=" + req.getGatewayCode());
			System.out.println("getGatewayPassword=" + req.getGatewayPassword());
			System.out.println("getReconnectFlag=" + req.getReconnectFlag());
			
			System.out.println("getServerId=" + req.getServerId());
			//req.
			
		}
		
		req.getGatewayPassword().equals(LineKongServerPassword.getPassword());
		int len =  con.write( getResponse( 1, sequenceId,gameId  ) );
	}

	static byte[] getResponse( int ret, long sequenceId, long gameId ){

		int bind_totalLength;


		long bind_commandId = PDUMessage.CMD_BIND_RES;
//		long sequenceId = 1111;
//		long gameId = 26;
//		long gatewayId = 26002;
		BindRespond bindRes = new BindRespond();
		// *****************************************************rework Bind
		bindRes.setResultCode(ret);
		byte[] bindResBody = bindRes.getBody();
		// *******************************************************rework Bind
		short resv = 0;
		int bind_checksum;

		IoBuffer bindhead = IoBuffer.allocate(20);

		bind_totalLength = 20 + bindResBody.length + 4;

		bindhead.putUnsignedShort(bind_totalLength);
		bindhead.putUnsigned(D.VERSION);// version
		bindhead.putUnsigned(D.remain_packages);// remainPackage
		bindhead.putUnsignedInt(bind_commandId);// commandId
		bindhead.putUnsignedInt(sequenceId);// sequenceId
		bindhead.putUnsignedInt(gameId);// gameId  559
		bindhead.putUnsignedInt(ServerConfig.getServerId());// gatewayId,测试用，需要修改  559001
		bindhead.flip();

		IoBuffer bindRes_data = IoBuffer.allocate(bind_totalLength);
		bindRes_data.put(bindhead);
		bindRes_data.put(bindResBody);
		bindRes_data.putShort(resv);// resv
		bind_checksum = eRatingProtocol.crc16(bindRes_data.array(), bind_totalLength - 2);
		bindRes_data.putUnsignedShort(bind_checksum);// checkSum
		bindRes_data.flip();
		return bindRes_data.array();
	}


}
