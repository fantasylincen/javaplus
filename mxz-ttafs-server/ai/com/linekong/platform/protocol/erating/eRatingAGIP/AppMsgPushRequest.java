package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;
public class AppMsgPushRequest extends eRatingProtocol {
	private final int bodyLength=4+4+256;
	private long 		userID=0;
	private long 		clientType=0;
	private String		AppRegistID="123";
	
	
	public int setUserID(long userID) {
		if(userID<minUint4Value || userID>maxUint4Value)
			return -1;
		this.userID = userID;
		return 0;
	}
	public int setClientType(long clientType) {
		if(clientType<minUint4Value || clientType>maxUint4Value)
			return -1;
		this.clientType = clientType;
		return 0;
	}
	public int setAppRegistID(String appRegistID) {
		if(AppRegistID.length()>256)
			return -1;
		AppRegistID = appRegistID;
		return 0;
	}
	
	public long getUserID() {
		return userID;
	}
	public long getClientType() {
		return clientType;
	}
	public String getAppRegistID() {
		return AppRegistID;
	}
	
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.putUnsignedInt(userID)
		 .putUnsignedInt(clientType)	
		 .put(fixLength(AppRegistID,256)) 
		 .flip();
		
		return b.array();
	}
	
	public  int analyzeBody(byte[] AppMsgPushReqBody)
	{	
		if(AppMsgPushReqBody.length!=bodyLength)
			return -1;
		
		byte[] body = AppMsgPushReqBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		
		userID			=	b.getUnsignedInt();
		clientType		=	b.getUnsignedInt();		
		AppRegistID		=	getStringByLength(b,256);		
		return 0;
	}
	
	
	
}
