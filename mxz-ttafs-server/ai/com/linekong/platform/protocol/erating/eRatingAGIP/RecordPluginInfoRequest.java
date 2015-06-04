package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class RecordPluginInfoRequest extends eRatingProtocol {
	private final int bodyLength=4+4+4+4+256+128+4;
	
	private long userID=0;
	private long roleID=0;
	private long timeStamp=0;
	private long userIP=0;
	private String mac="5364789175";
	private String message="test";
	private long type=0;
	
	public long getUserID() {
		return userID;
	}
	public long getRoleID() {
		return roleID;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public long getUserIP() {
		return userIP;
	}
	public String getMac() {
		return mac;
	}
	public String getMessage() {
		return message;
	}
	public long getType() {
		return type;
	}
	
	public int setUserID(long userID) {
		if(userID<minUint4Value || userID>maxUint4Value)
			return -1;
		this.userID = userID;
		return 0;
	}
	public int setRoleID(long roleID) {
		if(roleID<minUint4Value || roleID>maxUint4Value)
			return -1;
		this.roleID = roleID;
		return 0;
	}
	public int setTimeStamp(long timeStamp) {
		if(timeStamp<minUint4Value || timeStamp>maxUint4Value)
			return -1;
		this.timeStamp = timeStamp;
		return 0;
	}
	public int setUserIP(long userIP) {
		if(userIP<minUint4Value || userIP>maxUint4Value)
			return -1;
		this.userIP = userIP;
		return 0;
	}
	public int setMac(String mac) {
		if(mac.length()>256)
			return -1;
		this.mac = mac;
		return 0;
	}
	public int setMessage(String message) {
		if(message.length()>128)
			return -1;
		this.message = message;
		return 0;
	}
	public int setType(long type) {
		if(type<minUint4Value || type>maxUint4Value)
			return -1;
		this.type = type;
		return 0;
	}
	
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.putUnsignedInt(userID)
		 .putUnsignedInt(roleID)
		 .putUnsignedInt(timeStamp)
		 .putUnsignedInt(userIP)
		 .put(fixLength(mac,256))	
		 .put(fixLength(message,128))
		 .putUnsignedInt(type)
		 
		 .flip();
		
		return b.array();
	}
	
	public  int analyzeBody(byte[] RecordPluginInfoReqBody)
	{	
		if(RecordPluginInfoReqBody.length!=bodyLength)
			return -1;
		
		byte[] body = RecordPluginInfoReqBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		
		userID			=	b.getUnsignedInt();
		roleID			=	b.getUnsignedInt();
		timeStamp		=	b.getUnsignedInt();
		userIP			=	b.getUnsignedInt();		
		mac				=	getStringByLength(b,256);
		message			=	getStringByLength(b,128);
		type			=	b.getUnsignedInt();
		
		return 0;
	}

	
	
}
