package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class GetNewUserRequest extends eRatingProtocol {
	private final int bodyLength=4;
	
	private long userIP=127L*256L*256L*256L+1L;

	public long getUserIP() {
		return userIP;
	}

	public int setUserIP(long userIP) {
		if(userIP<this.minUint4Value || userIP>this.maxUint4Value)
			return -1;
		this.userIP = userIP;
		return 0;
	}
	
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.putUnsignedInt(userIP).flip();		
		return b.array();
	}
	
	public  int analyzeBody(byte[] getGetNewUserRequestBody)
	{	
		if(getGetNewUserRequestBody.length!=bodyLength)
			return -1;
		
		byte[] body = getGetNewUserRequestBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
	
		userIP	=	b.getUnsignedInt();
		
		return 0;
	}
	

}
