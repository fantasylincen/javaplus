package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;
public class UserItemMinusExRequest extends eRatingProtocol {
	
	private long userID=0;
	private long roleID=0;
	private long itemCount=0;
	private long[] activityID=null;
	private String[] itemCode=null;
	private int[] itemNum=null;
	
	public long getUserID() {
		return userID;
	}
	public long getRoleID() {
		return roleID;
	}
	
	public long getItemCount() {
		return itemCount;
	}
	
	public long[] getActivityID() {
		return activityID;
	}
	public String[] getItemCode() {
		return itemCode;
	}
	public int[] getItemNum() {
		return itemNum;
	}
	
	public int setUserID(long userID) {
		if( userID<minUint4Value || userID>maxUint4Value )
			return -1;
		this.userID = userID;
		return 0;
		
	}
	
	public int setRoleID(long roleID) {
		if( roleID<minUint4Value || roleID>maxUint4Value )
			return -1;
		this.roleID = roleID;
		return 0;
	}
	
	public int setItemCount(long itemCount) {
		if( itemCount<minUint4Value || itemCount>maxUint4Value )
			return -1;
		this.itemCount = itemCount;
		return 0;
	}
	public void setActivityID(long[] activityID) {
		this.activityID = activityID;
	}
	public void setItemCode(String[] itemCode) {
		this.itemCode = itemCode;
	}
	public void setItemNum(int[] itemNum) {
		this.itemNum = itemNum;
	}
	
	public byte[] getBody()
	{
		long bodyLength = 4+4+4+itemCount*(4+32+4);
		IoBuffer b = IoBuffer.allocate((int)bodyLength);
		
		  b.putUnsignedInt(userID)
		  .putUnsignedInt(roleID)
		  .putUnsignedInt(itemCount);
		  for(int i=0;i<itemCount;++i)
		  {
			  b.putUnsignedInt(activityID[i])
			  .put(fixLength(itemCode[i], 32))
			  .putInt(itemNum[i]);
		  }
		  b.flip();
		 
		return b.array();
	}
	public  int analyzeBody(byte[] UserItemMinusExReqBody)
	{	
		if( (UserItemMinusExReqBody.length-(4+4+4))%(4+32+4)!=0 )
			return -1;
		
		byte[] body = UserItemMinusExReqBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		userID			=	b.getUnsignedInt();
		roleID			=	b.getUnsignedInt();
		itemCount		=	b.getUnsignedInt();
		if(itemCount>0)
		{
			activityID = new long[(int)itemCount];
			itemCode   = new String[(int)itemCount];
			itemNum	   = new int[(int)itemCount];
		}
		for(int i=0;i<itemCount;++i)
		{
			activityID[i]	=	b.getUnsignedInt();
			itemCode[i]		=	getStringByLength(b,32);
			itemNum[i]		=	b.getInt();
		}
		
		
		return 0;
	}

}
