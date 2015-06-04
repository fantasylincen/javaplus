package com.linekong.platform.protocol.erating.eRatingAGIP.AgipTest;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.mina.core.buffer.IoBuffer;

import com.linekong.platform.protocol.erating.PDUMessage;
import com.linekong.platform.protocol.erating.eRatingAGIP.BindRequest;
import com.linekong.platform.protocol.erating.eRatingAGIP.BindRespond;
import com.linekong.platform.protocol.erating.eRatingAGIP.UserAuthEx3Request;
import com.linekong.platform.protocol.erating.eRatingAGIP.UserAuthEx3Respond;
import com.linekong.platform.protocol.erating.eRatingAGIP.eRatingProtocol;

public class UserAuthEx3Test extends debugTest {
	public static void main(String args[]){
		InetSocketAddress endpoint=new InetSocketAddress("192.168.41.204",6000);
		Socket socket=null;
		OutputStream out=null;
		InputStream in=null;
		
		
		
		int bind_totalLength;
		short version=0x20;
		short remain_packages=0;
		long bind_commandId=PDUMessage.CMD_BIND;
		long sequenceId=1111;
		long gameId=3100;
		long gatewayId=0;
		BindRequest bindReq=new BindRequest();
		//*****************************************************rework Bind
		bindReq.setGatewayCode("sys_gateway");
		bindReq.setGatewayPassword("0");
		bindReq.setMac("001D7DD14955");
		bindReq.setReconnectFlag((byte)1);
		bindReq.setServerId(1L);
		byte[] bindbody=bindReq.getBody();
		//*******************************************************rework Bind
		short resv=0;
		int bind_checksum;
		
		IoBuffer bindhead=IoBuffer.allocate(20);
		
		
		bind_totalLength=20+bindbody.length+4;
		IoBuffer bind_dates=IoBuffer.allocate(bind_totalLength);
		
		bindhead.putUnsignedShort(bind_totalLength);
		bindhead.putUnsigned(version);//version
		bindhead.putUnsigned(remain_packages);//remainPackage
		bindhead.putUnsignedInt(bind_commandId);//commandId
		bindhead.putUnsignedInt(sequenceId);//sequenceId
		bindhead.putUnsignedInt(gameId);//gameId
		bindhead.putUnsignedInt(gatewayId);//gatewayId
		bindhead.flip();
		
		bind_dates.put(bindhead);
		bind_dates.put(bindbody);
		bind_dates.putShort(resv);//resv
		bind_checksum=eRatingProtocol.crc16(bind_dates.array(), bind_totalLength-2);
		bind_dates.putUnsignedShort(bind_checksum);//checkSum
		bind_dates.flip();	
		
		//**************************************************every times need to rewrite if you want to test a new protocol**********
		UserAuthEx3Request request=new UserAuthEx3Request();
		
		debugSet(request.setUserName("test010"),"setUserName");
		debugSet(request.setPwd("123"),"setPwd");
		request.setPwdType((byte)1);
		request.setGameClientType((byte)1);
		debugSet(request.setUserPort(5000),"setUserPort");
		debugSet(request.setUserIP(127l*256l*256l*256l+1l),"setUserIP");
		debugSet(request.setMatrixPwd("1238line"),"setMatrixPwd");
		debugSet(request.setMatrixCoord("linekong"),"setMatrixCoord");
		debugSet(request.setMac("123456abcdef"),"setMac");
		debugSet(request.setAdID("123456adid"),"setAdID");
		debugSet(request.setHardwareSN1("123HardwareSN1"),"setHardwareSN1");
		debugSet(request.setHardwareSN2("123HardwareSN2"),"setHardwareSN2");
		debugSet(request.setUddi("123Uddi64"),"setUddi");
		long request_commandId = PDUMessage.CMD_USER_AUTH_EX3;
		
		//**************************************************every times need to rewrite if you want to test a new protocol************
		byte[] requestBody=request.getBody();
		System.out.println("requestBody.length="+requestBody.length);
		int request_totalLength=20+requestBody.length+4;
		IoBuffer requestData=IoBuffer.allocate(request_totalLength);
		IoBuffer requestHead=IoBuffer.allocate(20);
		
		requestHead.putUnsignedShort(request_totalLength);
		requestHead.putUnsigned(version);//version
		requestHead.putUnsigned(remain_packages);//remainPackage
		requestHead.putUnsignedInt(request_commandId);//commandId
		requestHead.putUnsignedInt(sequenceId);//sequenceId
		requestHead.putUnsignedInt(gameId);//gameId
		requestHead.putUnsignedInt(gatewayId);//gatewayId
		requestHead.flip();
		
		requestData.put(requestHead);
		requestData.put(requestBody);
		requestData.putShort(resv);//resv
		int request_checkSum=eRatingProtocol.crc16(requestData.array(), request_totalLength-2);
		requestData.putUnsignedShort(request_checkSum);//checkSum
		requestData.flip();
		
		
		try{
			socket=new Socket();
			socket.setSoLinger(true, 2);
		   //socket.setSoTimeout(2000);
		   socket.setSendBufferSize(32*1024);
		   socket.setReceiveBufferSize(32*1024);
		   socket.setTcpNoDelay(true);
		   socket.connect(endpoint);
		   out = socket.getOutputStream();   
		   in = socket.getInputStream();
		   
		   
		   IoBuffer[] sendMessage={bind_dates,requestData};
		   for(int j=0;j<2;++j)
		   {
			   //bind
			   out.write(sendMessage[j].array());
			   out.flush();
			   
			   
			   //bindӦ
			   in=socket.getInputStream();
		
			   byte[] charBuf = new byte[4096]; 
			   int size = 0; 
			   size = in.read(charBuf , 0 , 4096);
			   IoBuffer ResBuf=IoBuffer.allocate(size);
			   ResBuf.put(charBuf,0,size);
			   ResBuf.flip();
			   
			   System.out.println("***************package head***************");
			   System.out.println("totalLength="+ResBuf.getUnsignedShort());
			   System.out.println("version="+ResBuf.getUnsigned());
			   System.out.println("remainPackages="+ResBuf.getUnsigned());
			   System.out.println("commandId="+ResBuf.getUnsignedInt());
			   System.out.println("sequenceId="+ResBuf.getUnsignedInt());
			   System.out.println("gameId="+ResBuf.getUnsignedInt());
			   System.out.println("gatewayId="+ResBuf.getUnsignedInt()+"\n");
			   byte[] ResBody=new byte[size-20-4];
			   ResBuf.get(ResBody);
			   
			   if(0==j)
			   {		
				   System.out.println("\n*****Bind result******");
				   BindRespond bindRes=new BindRespond();
				   if( bindRes.analyzeBody(ResBody)<0)
				   {
					   System.out.println("BindRespondAnalyzeError");
					   
				   }
				   else
				   {
					   System.out.println("BindResult="+bindRes.getResultCode()+"\n");
					   
				   }
			   }
			   else
			   {
				   //**************************************************every times need to rewrite*******************************
				   
				   System.out.println("\n*****UserAuthEx3Respond result******");
				   UserAuthEx3Respond respond= new UserAuthEx3Respond();
				   
				   //**************************************************every times need to rewrite*******************************
				   int resAnalyze= respond.analyzeBody(ResBody);		   
				   if( resAnalyze<0)
				   {
					   System.out.println("RespondAnalyzeError");
					   
				   }
				   else
				   {
					   System.out.println("getResultCode="+respond.getResultCode());
					   if( respond.getResultCode()==1)
					   {
						   System.out.println("getUserID="+respond.getUserID());
					   	   System.out.println("getUserType="+respond.getUserType());
						   System.out.println("getAdultFlag="+respond.getAdultFlag());
						   System.out.println("getUserClass="+respond.getUserClass());
						   System.out.println("getUserPoint="+respond.getUserPoint());
						   System.out.println("getUserFlag="+respond.getUserFlag());
					   } 
				   }
				   
			   }
		   }
		   out.close();
		   in.close();
		   socket.close();

			
		} catch ( Exception e)
		{
			System.out.println("Error"+e);			
		}

	}
}
