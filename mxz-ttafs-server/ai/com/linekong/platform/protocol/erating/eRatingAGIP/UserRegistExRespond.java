package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class UserRegistExRespond extends eRatingProtocol {
	private final int bodyLength=4+4;
	
	private int 	resultCode=0;
	private long 	userID=1009;
	
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public int setUserID(long userID) {
		if(userID<minUint4Value || userID>maxUint4Value)
			return -1;
		this.userID = userID;
		return 0;
	}
	public int getResultCode() {
		return resultCode;
	}
	public long getUserID() {
		return userID;
	}
	
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.putInt(resultCode).putUnsignedInt(userID).flip();
		
		return b.array();
	}
	public  int analyzeBody(byte[] UserRegistExResBody)
	{	
		if(UserRegistExResBody.length!=bodyLength)
			return -1;
		
		byte[] body = UserRegistExResBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
				
		resultCode=b.getInt();
		userID=b.getUnsignedInt();
		
		return 0;
	}
}
