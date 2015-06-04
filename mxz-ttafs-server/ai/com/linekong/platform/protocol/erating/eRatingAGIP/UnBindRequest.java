package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class UnBindRequest extends eRatingProtocol {
	private long gatewayId=0;

	public long getGatewayId() {
		return gatewayId;
	}

	public int setGatewayId(long gatewayId) {
		if( gatewayId<minUint4Value || gatewayId>maxUint4Value )
			return -1;
		this.gatewayId = gatewayId;
		return 0;
	}
	
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(4);
		
		b .putUnsignedInt(gatewayId)
		  .flip();
		return b.array();	
	}
	
	public  int analyzeBody(byte[] UnbindReqBody)
	{	
		if(UnbindReqBody.length!=4)
			return -1;
		
		byte[] body = UnbindReqBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		gatewayId=b.getUnsignedInt();
		return 0;
	}

}
