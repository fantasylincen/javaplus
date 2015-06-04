package com.linekong.platform.protocol.erating.eRatingAGIP.AgipTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.mina.core.buffer.IoBuffer;

import com.linekong.platform.protocol.erating.PDUMessage;
import com.linekong.platform.protocol.erating.eRatingAGIP.BindRequest;
import com.linekong.platform.protocol.erating.eRatingAGIP.BindRespond;
import com.linekong.platform.protocol.erating.eRatingAGIP.UserChaegeRequest;
import com.linekong.platform.protocol.erating.eRatingAGIP.UserChargeRespond;
import com.linekong.platform.protocol.erating.eRatingAGIP.eRatingProtocol;

public class UserChargeTest extends debugTest {
	public static void main(String args[]){
		
	int bind_totalLength;
	short version=0x20;
	short remain_packages=0;
	long bind_commandId=PDUMessage.CMD_BIND_RES;
	long sequenceId=1111;
	long gameId=26;
	long gatewayId=26002;
	BindRespond bindRes=new BindRespond();
	//*****************************************************rework Bind
	bindRes.setResultCode(1);
	byte[] bindResBody=bindRes.getBody();
	//*******************************************************rework Bind
	short resv=0;
	int bind_checksum;
	
	IoBuffer bindhead=IoBuffer.allocate(20);
	
	
	bind_totalLength=20+bindResBody.length+4;
	IoBuffer bindRes_data=IoBuffer.allocate(bind_totalLength);
	
	bindhead.putUnsignedShort(bind_totalLength);
	bindhead.putUnsigned(version);//version
	bindhead.putUnsigned(remain_packages);//remainPackage
	bindhead.putUnsignedInt(bind_commandId);//commandId
	bindhead.putUnsignedInt(sequenceId);//sequenceId
	bindhead.putUnsignedInt(gameId);//gameId
	bindhead.putUnsignedInt(gatewayId);//gatewayId
	bindhead.flip();
	
	bindRes_data.put(bindhead);
	bindRes_data.put(bindResBody);
	bindRes_data.putShort(resv);//resv
	bind_checksum=eRatingProtocol.crc16(bindRes_data.array(), bind_totalLength-2);
	bindRes_data.putUnsignedShort(bind_checksum);//checkSum
	bindRes_data.flip();	
	
	//**************************************************every times need to rewrite if you want to test a new protocol**********
	UserChargeRespond respond=new UserChargeRespond();
	
	respond.setAuditFlag((short) 1);
	respond.setDetailID(BigInteger.valueOf(300007));
	respond.setRatingID(26002);
	respond.setResultCode(1);
	respond.setSubjectID((short) 3);
	respond.setTotalAmount(1000);
	respond.setUserID(432776956);
	respond.setResultCode(1);
	
	long request_commandId = 0x20003401L;
	
	//**************************************************every times need to rewrite if you want to test a new protocol************
	byte[] userChargeResBody=respond.getBody();
	
//	System.out.println("userChargeResBody.length="+userChargeResBody.length);
	int request_totalLength=20+userChargeResBody.length+4;
	IoBuffer userChargeRes_data=IoBuffer.allocate(request_totalLength);
	IoBuffer requestHead=IoBuffer.allocate(20);
	
	requestHead.putUnsignedShort(request_totalLength);
	requestHead.putUnsigned(version);//version
	requestHead.putUnsigned(remain_packages);//remainPackage
	requestHead.putUnsignedInt(request_commandId);//commandId
	requestHead.putUnsignedInt(sequenceId);//sequenceId
	requestHead.putUnsignedInt(gameId);//gameId
	requestHead.putUnsignedInt(gatewayId);//gatewayId
	requestHead.flip();
	
	userChargeRes_data.put(requestHead);
	userChargeRes_data.put(userChargeResBody);
	userChargeRes_data.putShort(resv);//resv
	int request_checkSum=eRatingProtocol.crc16(userChargeRes_data.array(), request_totalLength-2);
	userChargeRes_data.putUnsignedShort(request_checkSum);//checkSum
	userChargeRes_data.flip();
	
	try
	{
		ServerSocket  server=new ServerSocket(10000);
		
		Socket socket=null;
		OutputStream out=null;
		InputStream in=null;
		System.out.println("gameServer is running ......");
		while(true)
		{
		
		   System.out.println("waitting client connect ......");	
		   socket=server.accept();
		   System.out.println("have client connect ......");
		   
		   socket.setSendBufferSize(32*1024);
		   socket.setReceiveBufferSize(32*1024);
		  
		   
		   out = socket.getOutputStream();   
		   in = socket.getInputStream();
		   
		   byte[] charBuf = new byte[4096]; 
		    
		   boolean acheve=true;
		   while(acheve)
		   {
			   int size = 0;
			   size = in.read(charBuf , 0 , 4096);
			   System.out.println("server receive "+size+" bytes data");
			   IoBuffer ReceiveBuf=IoBuffer.allocate(size);
			   ReceiveBuf.put(charBuf,0,size);
			   ReceiveBuf.flip();
			   
			   
			   int totalLength=ReceiveBuf.getUnsignedShort();
			   			   
			   short packageVersion=ReceiveBuf.getUnsigned();
			  			   
			   short remainPackages=ReceiveBuf.getUnsigned();
			   		   
			   long ReceiveComand=ReceiveBuf.getUnsignedInt();
			   
			   if(0x10==packageVersion)	
			   {
				   acheve=true;
				   continue;
			   }
			   if(PDUMessage.CMD_BIND==ReceiveComand)
				   System.out.println("***************Bind package head***************");
			   else if(0x10003401L==ReceiveComand)
				   System.out.println("***************userCharge package head***************");
			   System.out.println("totalLength="+totalLength);
			   System.out.println("packageVersion="+packageVersion);
			   System.out.println("remainPackages="+remainPackages);
			   System.out.println("commandId="+ReceiveComand);
			   System.out.println("sequenceId="+ReceiveBuf.getUnsignedInt());
			   System.out.println("gameId="+ReceiveBuf.getUnsignedInt());
			   System.out.println("gatewayId="+ReceiveBuf.getUnsignedInt());
			   byte[] ReceiveBody=new byte[totalLength-20-4];
			   ReceiveBuf.get(ReceiveBody);
			   
			   IoBuffer[] sendMessage={bindRes_data,userChargeRes_data};
			   
				  
			   if(PDUMessage.CMD_BIND==ReceiveComand)
			   {	
				   
				   System.out.println("\n*****BindRequestAnalyze result******");
				   BindRequest BindReq=new BindRequest();
				   System.out.println("BindBody is "+ReceiveBody.length+" byte");
				   int resAnalyze= BindReq.analyzeBody(ReceiveBody);		   
				   if( resAnalyze<0)
				   {
					   System.out.println("BindAnalyzeError");
					   
				   }
				   else
				   {
					   System.out.println("getMac="+BindReq.getMac());  
					   System.out.println("getGatewayCode="+BindReq.getGatewayCode());
					   System.out.println("getGatewayPassword="+BindReq.getGatewayPassword());		   
				   	   System.out.println("getReconnectFlag="+BindReq.getReconnectFlag());
					   System.out.println("getServerId="+BindReq.getServerId());
				   }
				   out.write(sendMessage[0].array());
				   out.flush();
				   acheve=true;
			   }
			   else if(0x10003401L==ReceiveComand)
			   {		   
				   
				   System.out.println("\n*****UserChaegeRequestAnalyze result******");
				   UserChaegeRequest UserChargeReq= new UserChaegeRequest();
				   System.out.println("UserChaegeRequestBody is "+ReceiveBody.length+" byte");
				   int resAnalyze= UserChargeReq.analyzeBody(ReceiveBody);		   
				   if( resAnalyze<0)
				   {
					   System.out.println("UserChaegeRequestAnalyzeError");
					   
				   }
				   else
				   {
					   System.out.println("getDetailID="+UserChargeReq.getDetailID());  
					   System.out.println("getUserID="+UserChargeReq.getUserID());
					   System.out.println("getRatingID="+UserChargeReq.getRatingID());		   
				   	   System.out.println("getSubjectID="+UserChargeReq.getSubjectID());
					   System.out.println("getAuditFlag="+UserChargeReq.getAuditFlag());
					   System.out.println("getAmount="+UserChargeReq.getAmount());
					   System.out.println("getChargeTime="+UserChargeReq.getChargeTime());
					   
				   }
				   out.write(sendMessage[1].array());
				   out.flush();
				   acheve=false;
			   }
			   else
			   {
				   System.out.println("commandId="+ReceiveComand+" is no handle now");
				   
			   }	   
		   }
		   out.close();
		   in.close();
		   socket.close();
		  
		}
	}
	catch (IOException e)
	{
		System.out.println("Error"+e);			
	}

}
}
