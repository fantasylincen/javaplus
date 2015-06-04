package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class UserLogoutRespond extends eRatingProtocol {
	//private final int bodyLength=4+4+balanceCount*(4+4);
	
	private   int 	resultCode=0;	
	private   int 	balanceCount=1;
	private   int[] subjectID=null;
	private   int[] Amount=null;
	
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
		Amount = amount;
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
		return Amount;
	}
	
	public  byte[] getBody()
	{
		if(
				(this.balanceCount != this.subjectID.length) 		&& 
				(this.subjectID.length!=this.Amount.length)
		   )
		{
			return null;
		}
		IoBuffer b = IoBuffer.allocate(4+4+balanceCount*(4+4));
		b.putInt(this.resultCode)
		 .putInt(this.balanceCount);
		 for(int j=0;j<this.balanceCount;++j)
		 {
			b.putInt(this.subjectID[j])
			 .putInt(this.Amount[j]);
		 }
		 b.flip();
		
		return b.array();	
	}
	
	public  int analyzeBody(byte[] UserLogoutResBody)
	{
		byte[] body = UserLogoutResBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		if((body.length -(4+4))%(4+4) !=0)
		{
			System.out.println("len="+body.length);
			return -1;
		}
		
		this.resultCode=b.getInt();
		this.balanceCount=b.getInt();
		if(balanceCount>0)
		{
			
			subjectID	= new int[balanceCount];
			Amount		= new int[balanceCount];
			
		}
		for(int i=0;i<balanceCount;++i)
		{
			subjectID[i]=b.getInt();
			Amount[i]	=b.getInt();
			
		}
		return 0;
	}
}
