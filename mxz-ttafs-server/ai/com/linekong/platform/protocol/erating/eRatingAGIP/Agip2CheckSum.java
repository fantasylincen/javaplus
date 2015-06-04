package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;
public class Agip2CheckSum extends eRatingProtocol {
	private final int bodyLength=2+2;
	
	private final short resv=0;
	private int checksum=0;
	
	public int getChecksum() {
		return checksum;
	}
	
	public int setChecksum(int checksum) {
		if(checksum<minUint2Value || checksum>maxUint2Value)
			return -1;
		this.checksum = checksum;
		return 0;
	}
	
	public  byte[] getBody()
	{
		IoBuffer b;
		
		b = IoBuffer.allocate(bodyLength);
		b.putShort(resv)
		 .putUnsignedShort(checksum)		 
		 .flip();
	
		return b.array();
	}
	
	public  int analyzeBody(byte[] Agip2CheckSumBody)
	{
		byte[] body = Agip2CheckSumBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
	
		if(body.length !=bodyLength)
		{
			return -1;
		}
		
					b.getShort();
		checksum = 	b.getUnsignedShort();
	
		return 0;
	}
}
