package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class ActivityItemQueryRespond extends eRatingProtocol {
	//private final int bodyLength=4+4+(4+32+4+4+4)*itemCount;
	
	private  int resultCode=0;
	private  int itemCount=0;
	
	private  long[] activityId=null;
	private  String[] itemCode=null;
	private  int[] itemNum=null;
	private  long[] bgnTime=null;
	private  long[] endTime=null;
	
	public int getResultCode() {
		return resultCode;
	}
	public int getItemCount() {
		return itemCount;
	}
	public long[] getActivityId() {
		return activityId;
	}
	public String[] getItemCode() {
		return itemCode;
	}
	public int[] getItemNum() {
		return itemNum;
	}
	public long[] getBgnTime() {
		return bgnTime;
	}
	public long[] getEndTime() {
		return endTime;
	}
	
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	public void setActivityId(long[] activityId) {
		this.activityId = activityId;
	}
	public void setItemCode(String[] itemCode) {
		this.itemCode = itemCode;
	}
	public void setItemNum(int[] itemNum) {
		this.itemNum = itemNum;
	}
	public void setBgnTime(long[] bgnTime) {
		this.bgnTime = bgnTime;
	}
	public void setEndTime(long[] endTime) {
		this.endTime = endTime;
	}
	
	
	public  byte[] getBody()
	{
		if(
				(this.itemCount != this.activityId.length) 		|| 
				(this.activityId.length!=this.itemCode.length) 	|| 
				(this.itemCode.length!=this.itemNum.length) 	|| 
				(this.itemNum.length!=this.bgnTime.length) 		||
				(this.bgnTime.length!=this.endTime.length)
		   )
		{
			return null;
		}
		IoBuffer b = IoBuffer.allocate(4+4+itemCount*(4+32+4+4+4));
		b.putInt(this.resultCode)
		 .putInt(this.itemCount);
		 for(int j=0;j<this.itemCount;++j)
		 {
			b.putUnsignedInt(this.activityId[j])
			 .put(fixLength(this.itemCode[j],32))
			 .putInt(this.itemNum[j])
			 .putUnsignedInt(this.bgnTime[j])
			 .putUnsignedInt(this.endTime[j]);
		 }
		 b.flip();
		
		return b.array();	
	}
	
	public  int analyzeBody(byte[] resBody)
	{
		byte[] body = resBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		if((body.length -8)%(4+32+4+4+4) !=0)
		{
			System.out.println("len="+body.length);
			return -1;
		}
		
		this.resultCode=b.getInt();
		this.itemCount=b.getInt();
		if(itemCount>0)
		{
			activityId 	= new long[itemCount];
			itemCode	= new String[itemCount];
			itemNum		= new int[itemCount];
			bgnTime		= new long[itemCount];
			endTime		= new long[itemCount];
		}
		for(int i=0;i<itemCount;++i)
		{
			activityId[i]=b.getUnsignedInt();
			itemCode[i]=getStringByLength(b,32);
			itemNum[i]=b.getInt();
			bgnTime[i]=b.getUnsignedInt();
			endTime[i]=b.getUnsignedInt();
		}
		return 0;
	}

}
