package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class RenameUserRequest extends eRatingProtocol {
	
	private final int bodyLength=4+32;
	private long userId=0;
	private String userName="test001";
	
	
	public long getUserId() {
		return userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public int setUserId(long userId) {		
		if(userId<minUint4Value || userId>maxUint4Value)
			return -1;
		this.userId = userId;
		return 0;
	}
	
	public int setUserName(String userName) {
		if(userName.length()>32)
			return -1;
		this.userName = userName;
		return 0;
	}
	
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.putUnsignedInt(userId).put(fixLength(userName,32)).flip();
		
		return b.array();
	}
	
	public  int analyzeBody(byte[] RenameUserRequestBody)
	{	
		if(RenameUserRequestBody.length!=bodyLength)
			return -1;
		
		byte[] body = RenameUserRequestBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		userId=b.getUnsignedInt();
		userName=getStringByLength(b,32);
		return 0;
	}
}
