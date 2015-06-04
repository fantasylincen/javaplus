package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class RoleEnterGameEx4Respond extends eRatingProtocol {
	//private final int bodyLength=4+4+(4+4)*balanceCount;
	
	private   int resultCode=0;
	private   int balanceCount=0;
	private   int[] subjectID=null;
	private   int[] amount=null;
	
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public void setBalanceCount(int balanceCount) {
		this.balanceCount = balanceCount;
	}
	public void setSubjectID(int[] subjectID) {
		this.subjectID = subjectID;
	}
	public void setAmount(int[] amount) {
		this.amount = amount;
	}
	
	public int getResultCode() {
		return resultCode;
	}
	public int getBalanceCount() {
		return balanceCount;
	}
	public int[] getSubjectID() {
		return subjectID;
	}
	public int[] getAmount() {
		return amount;
	}
	public byte[] getBody()
	{
		if(
				(balanceCount != subjectID.length) || 
				(subjectID.length!=amount.length)
		   )
		{
			return null;
		}
		IoBuffer b = IoBuffer.allocate(4+4+(4+4)*balanceCount);
		
		b .putInt(resultCode)
		  .putInt(balanceCount);
		  for(int i=0;i<balanceCount;++i)
		  {
			  b.putInt(subjectID[i])
			   .putInt(amount[i]);
		  }
		  b.flip();
		  
		return b.array();	
	}
	public  int analyzeBody(byte[] RoleEnterGameEx4ResBody)
	{	
		if( (RoleEnterGameEx4ResBody.length-(4+4))%(4+4) !=0 )
			return -1;
		
		byte[] body = RoleEnterGameEx4ResBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		resultCode		=	b.getInt();
		balanceCount	=	b.getInt();
		if(balanceCount>0)
		{
			subjectID	=	new int[balanceCount];
			amount		=	new	int[balanceCount];
		}
		for(int i=0;i<balanceCount;++i)
		{
			subjectID[i]=	b.getInt();
			amount[i]	=	b.getInt();			
		}
		
		return 0;
	}
	
	

}
