package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class UserItemMinusExRespond extends eRatingProtocol {
	private final int bodyLength=4;
	
	private   int resultCode=0;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b .putInt(resultCode)
		  .flip();
		return b.array();	
	}
	public  int analyzeBody(byte[] UserItemMinusResBody)
	{	
		if(UserItemMinusResBody.length!=bodyLength)
			return -1;
		
		byte[] body = UserItemMinusResBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		resultCode=b.getInt();
		return 0;
	}
}
