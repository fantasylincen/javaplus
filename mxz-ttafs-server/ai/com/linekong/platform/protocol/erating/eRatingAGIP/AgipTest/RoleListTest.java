package com.linekong.platform.protocol.erating.eRatingAGIP.AgipTest;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.mina.core.buffer.IoBuffer;

import com.linekong.platform.protocol.erating.PDUMessage;
import com.linekong.platform.protocol.erating.eRatingAGIP.BindRequest;
import com.linekong.platform.protocol.erating.eRatingAGIP.BindRespond;
import com.linekong.platform.protocol.erating.eRatingAGIP.RoleListRequest;
import com.linekong.platform.protocol.erating.eRatingAGIP.RoleListRespond;
import com.linekong.platform.protocol.erating.eRatingAGIP.eRatingProtocol;
public class RoleListTest extends debugTest {
	public static void main(String args[]){
		InetSocketAddress endpoint=new InetSocketAddress("192.168.41.89",1416);
		Socket socket=null;
		OutputStream out=null;
		InputStream in=null;
		
		
		
		int bind_totalLength;
		short version=0x20;
		short remain_packages=0;
		long bind_commandId=PDUMessage.CMD_BIND;
		long sequenceId=1111;
		long gameId=26;
		long gatewayId=26001;
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
		RoleListRequest request=new RoleListRequest();
		debugSet( request.setUserId(432777588),"setUserId");
		long request_commandId = PDUMessage.CMD_ROLE_LIST;
		
		//**************************************************every times need to rewrite if you want to test a new protocol************
		byte[] requestBody=request.getBody();
		if(requestBody==null)
		{
			System.out.println("no param");
			throw new IllegalArgumentException();
//			System.exit(0);
		}
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
			   System.out.println("gatewayId="+ResBuf.getUnsignedInt());
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
				   System.out.println("\n*****RoleListRespond result******");
				   RoleListRespond respond= new RoleListRespond();
				   
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
						   int roleCount=respond.getRoleCount();
						   long[] 	gatewayID=respond.getGatewayID();
						   long[] 	roleID=respond.getRoleID();
						   String[] roleName=respond.getRoleName();
						   int[] 	roleLevel=respond.getRoleLevel();
						   short[]   roleGender=respond.getRoleGender();
						   short[]   roleOccupation=respond.getRoleOccupation();
						   long[] 	roleCreateTime=respond.getRoleCreateTime();
						   int[] 	money1=respond.getMoney1();
						   int[] 	money2=respond.getMoney2();
						   
						   System.out.println("getRoleCount="+roleCount);
						   for(int i=0;i<roleCount;i++)
						   {
							   System.out.println("gatewayID["+i+"]="+gatewayID[i]);
							   System.out.println("roleID["+i+"]="+roleID[i]);
							   System.out.println("roleName["+i+"]="+roleName[i]);
							   System.out.println("roleLevel["+i+"]="+roleLevel[i]);
							   System.out.println("roleGender["+i+"]="+roleGender[i]);
							   System.out.println("roleOccupation["+i+"]="+roleOccupation[i]);
							   System.out.println("roleCreateTime["+i+"]="+roleCreateTime[i]);
							   System.out.println("money1["+i+"]="+money1[i]);
							   System.out.println("money2["+i+"]="+money2[i]);													   
						   } 
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
