package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class DeleteUserIbPayRespond extends eRatingProtocol {
	private final int bodyLength=4+4;
	
	private int 		resultCode=0;
	private long 		Balance=0;

	public int getResultCode() {
		return resultCode;
	}

	public long getBalance() {
		return Balance;
	}

	public int setBalance(long balance) {
		if(balance<minUint4Value || balance>maxUint4Value)
			return -1;
		Balance = balance;
		return 0;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.putInt(resultCode)
		 .putUnsignedInt(Balance)
		 .flip();
		
		return b.array();
	}
	
	public  int analyzeBody(byte[] DeleteUserIbPayResBody)
	{	
		if(DeleteUserIbPayResBody.length!=bodyLength)
			return -1;
		
		byte[] body = DeleteUserIbPayResBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		resultCode		=	b.getInt();
		Balance			=	b.getUnsignedInt();
		
		return 0;
	}
}
