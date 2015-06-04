package com.linekong.platform.protocol.erating.eRatingAGIP;

import org.apache.mina.core.buffer.IoBuffer;

public class ActivityItemQueryExRespond extends eRatingProtocol {
	
	private  int resultCode=0;
	private  int activityCount=0;
	
	private  long[] activityId=null;
	private  String[] activityDesc=null;
	private  int[] itemCount=null;
	
	private  String[][] itemCode=null;
	private  int[][]	ItemNum=null;
	private  long[][]	BeginTime=null;
	private  long[][]	EndTime=null;
	
	public int getResultCode() {
		return resultCode;
	}
	public int getActivityCount() {
		return activityCount;
	}
	public long[] getActivityId() {
		return activityId;
	}
	public String[] getActivityDesc() {
		return activityDesc;
	}
	public int[] getItemCount() {
		return itemCount;
	}
	public String[][] getItemCode() {
		return itemCode;
	}
	public int[][] getItemNum() {
		return ItemNum;
	}
	public long[][] getBeginTime() {
		return BeginTime;
	}
	public long[][] getEndTime() {
		return EndTime;
	}
	
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public void setActivityCount(int activityCount) {
		this.activityCount = activityCount;
	}
	public void setActivityId(long[] activityId) {
		this.activityId = activityId;
	}
	public void setActivityDesc(String[] activityDesc) {
		this.activityDesc = activityDesc;
	}
	public void setItemCount(int[] itemCount) {
		this.itemCount = itemCount;
	}
	public void setItemCode(String[][] itemCode) {
		this.itemCode = itemCode;
	}
	public void setItemNum(int[][] itemNum) {
		ItemNum = itemNum;
	}
	public void setBeginTime(long[][] beginTime) {
		BeginTime = beginTime;
	}
	public void setEndTime(long[][] endTime) {
		EndTime = endTime;
	}
	
	public  byte[] getBody()
	{
		if(
				(this.activityCount != this.activityId.length) 		|| 
				(this.activityId.length!=this.activityDesc.length) 	|| 
				(this.activityDesc.length!=this.itemCount.length) 	|| 
				(this.itemCount.length!=this.itemCode.length) 		||
				(this.itemCode.length!=this.BeginTime.length)		||
				(this.BeginTime.length!=this.EndTime.length)
		   )
		{
			return null;
		}
		
		for(int i=0;i<this.itemCount.length;++i)
		{
			if( 
					(this.itemCount[i] != itemCode[i].length )		  ||
					(itemCode[i].length != this.BeginTime[i].length ) ||
					(this.BeginTime[i].length != this.EndTime.length)
			   )
			{
				return null;
			}
		}
		
		int bodyLength=4+4;
		for(int i=0;i<activityCount;++i)
		{
			bodyLength += 4+64+4+itemCount[i]*(32+4+4+4);
		}
		
		IoBuffer b = IoBuffer.allocate(bodyLength);
		b.putInt(this.resultCode)
		 .putInt(this.activityCount);
		 for(int j=0;j<activityCount;++j)
		 {
			b.putUnsignedInt(this.activityId[j])
			 .put(fixLength(this.activityDesc[j],64))
			 .putInt(this.itemCount[j]);
			
			for(int k=0;k<itemCount[j];++j)
			{
				b.put( fixLength(this.itemCode[j][k],32) )
				.putInt(ItemNum[j][k])
				.putUnsignedInt(this.BeginTime[j][k])
				.putUnsignedInt(this.EndTime[j][k]);
			}
			
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
		
		
		resultCode = b.getInt();
		activityCount = b.getInt();
		if(activityCount>0)
		{
			activityId 		= new long[activityCount];
			activityDesc	= new String[activityCount];
			itemCount		= new int[activityCount];
			
			itemCode		= new String[activityCount][];
			ItemNum			= new int[activityCount][];
			BeginTime		= new long[activityCount][];
			EndTime			= new long[activityCount][];
		}
		for(int i=0;i<activityCount;++i)
		{
			activityId[i]=b.getUnsignedInt();
			activityDesc[i]=getStringByLength(b,64);
			itemCount[i]=b.getInt();
			if(itemCount[i]>0)
			{
				itemCode[i]=new String[ itemCount[i] ];
				ItemNum[i] = new int[ itemCount[i] ];
				BeginTime[i] = new long[ itemCount[i] ];
				EndTime[i] = new long[ itemCount[i] ];
				for(int j=0;j<itemCount[i];j++)
				{
					itemCode[i][j]=getStringByLength(b,32);
					ItemNum[i][j]=b.getInt();
					BeginTime[i][j]=b.getUnsignedInt();
					EndTime[i][j]=b.getUnsignedInt();
				}			
			}
		}
		return 0;
	}
}
