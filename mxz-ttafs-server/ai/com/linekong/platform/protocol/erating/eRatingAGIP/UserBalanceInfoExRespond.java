package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;
public class UserBalanceInfoExRespond extends eRatingProtocol {
	private final int bodyLength=4+4+4;
	
	private int 		resultCode=0;
	private int 		totalCharge=0;
	private int 		leftAmount=0;
	
	public int getResultCode() {
		return resultCode;
	}
	public int getTotalCharge() {
		return totalCharge;
	}
	public int getLeftAmount() {
		return leftAmount;
	}
	
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public void setTotalCharge(int totalCharge) {
		this.totalCharge = totalCharge;
	}
	public void setLeftAmount(int leftAmount) {
		this.leftAmount = leftAmount;
	}
	
	public  byte[] getBody()
	{
		IoBuffer b;
		
		b = IoBuffer.allocate(bodyLength);
		b.putInt(resultCode)
		 .putInt(totalCharge)
		 .putInt(leftAmount)
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
		
		resultCode	=	b.getInt();
		totalCharge	=	b.getInt();
		leftAmount	=	b.getInt();
		return 0;
	}

}
