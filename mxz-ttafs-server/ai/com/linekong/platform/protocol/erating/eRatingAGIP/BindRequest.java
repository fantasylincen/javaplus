package com.linekong.platform.protocol.erating.eRatingAGIP;

import org.apache.mina.core.buffer.IoBuffer;
public class BindRequest extends eRatingProtocol {
	private final int bodyLength=32+32+12+1+1+2+4;
	
	private  String gatewayCode="sys_gateway";
	private  String gatewayPassword="1";
	private  String mac="abcs12456123";
	private  byte reconnectFlag=0;
	private  final byte  pad1=0;
	private  final int pad2=0;
	private  long serverId=0;
	
	public  String getGatewayCode() {
		return gatewayCode;
	}

	public  int setGatewayCode(String gatewayCode) {
		if(gatewayCode.length()>32)
			return -1;
		this.gatewayCode = gatewayCode;
		return 0;
	}

	public  String getGatewayPassword() {
		return gatewayPassword;
	}

	public  int setGatewayPassword(String gatewayPassword) {
		if(gatewayPassword.length()>32)
			return -1;
		this.gatewayPassword = gatewayPassword;
		return 0;
	}

	public  String getMac() {
		return mac;
	}

	public  int setMac(String mac) {
		if(mac.length()>12)
			return -1;
		this.mac = mac;
		return 0;
	}

	public  byte getReconnectFlag() {
		return reconnectFlag;
	}

	public  void setReconnectFlag(byte reconnectFlag) {
		
		this.reconnectFlag = reconnectFlag;
	}

	public  long getServerId() {
		return serverId;
	}

	public  int setServerId(long serverId) {
		if( serverId<minUint4Value || serverId>maxUint4Value )
			return -1;
		this.serverId = serverId;
		return 0;
	}

	public  byte[] getBindReqBody(String code, String pwd, String Mac, byte reFlag, long serverID) {
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.put(fixLength(code, 32))
		 .put(fixLength(pwd, 32))
		 .put(fixLength(Mac.toUpperCase(), 12))
		 .put((byte) reFlag)
		 .put((byte)0)
		 .putUnsignedShort(0)
		 .putUnsignedInt(serverID)
		 .flip();
		gatewayCode=code;
		gatewayPassword=pwd;
		mac=Mac;
		reconnectFlag=reFlag;
		serverId=serverID;		
		return b.array();
	}
	public  byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.put(fixLength(gatewayCode, 32))
		 .put(fixLength(gatewayPassword, 32))
		 .put(fixLength(mac.toUpperCase(), 12))
		 .put(reconnectFlag)
		 .put(pad1)
		 .putUnsignedShort(pad2)
		 .putUnsignedInt(serverId)
		 .flip();
		return b.array();
	}
	
	public  int analyzeBody(byte[] bindReqBody)
	{
		if(bindReqBody.length !=bodyLength)
		{
			return -1;
		}
		
		byte[] body = bindReqBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		gatewayCode=getStringByLength(b,32);
		gatewayPassword=getStringByLength(b,32);
		mac=getStringByLength(b,12);
		reconnectFlag=b.get();
		b.get();
		b.getUnsignedShort();
		serverId=b.getUnsignedInt();
		
		return 0;
	}

}
