package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;
public class RoleListRequest extends eRatingProtocol{
	private final int bodyLength=  4;
	
	private  long userId=0;
	
	public  int setUserId(long userId) {
		if(userId<minUint4Value || userId>maxUint4Value)
			return -1;
		this.userId = userId;
		return 0;
	}
	public  long getUserId() {
		return userId;
	}
	
	public  byte[] getBody()
	{
		IoBuffer b;
		
		b = IoBuffer.allocate(bodyLength);
		b.putUnsignedInt(userId)
		 .flip();
	
		return b.array();
	}
	
	public  int analyzeBody(byte[] reqBody)
	{
		byte[] body = reqBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
	
		if(body.length !=bodyLength)
		{
			return -1;
		}
		userId		=	b.getUnsignedInt();

		return 0;
	}
}
