package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;
public class BindRespond extends eRatingProtocol{
	//private final int bodyLengthVersion1=4+4;
	private final int bodyLength=4;
	
	private   int resultCode=0;
	//private   long gatewayId=0;
	
	public  int getResultCode() {
		return resultCode;
	}
	public  void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	/*
	public  long getGatewayId() {
		return gatewayId;
	}
	public  void setGatewayId(long gatewayId) {
		this.gatewayId = gatewayId;
	}*/
	

	public  byte[] getBody() {
		IoBuffer b;
	
		b = IoBuffer.allocate(bodyLength);
		b.putInt(resultCode)
		 .flip();
		
		return b.array();
	}
	
	public  int analyzeBody(byte[] bindResBody)
	{
		byte[] body = bindResBody;
		
		if(bindResBody.length != bodyLength)
		{
			return -1;
		}
		IoBuffer b = IoBuffer.allocate(bodyLength);
		b.put(body);
		b.flip();
		
		resultCode=b.getInt();
		
		return 0;
	}
}
