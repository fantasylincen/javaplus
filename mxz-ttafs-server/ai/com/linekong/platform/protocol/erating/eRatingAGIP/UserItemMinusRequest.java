package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class UserItemMinusRequest extends eRatingProtocol {
	private final int bodyLength=4+4+4+4+32+4;
	
	private long itemGatewayID=0;
	private long userID=0;
	private long roleID=0;
	private long activityID=0;
	private String itemCode="55555";
	private int itemNum=1;
	
	public int setItemGatewayID(long itemGatewayID) {
		if( itemGatewayID<minUint4Value || itemGatewayID>maxUint4Value )
			return -1;
		this.itemGatewayID = itemGatewayID;
		return 0;
		
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
	public int setActivityID(long activityID) {
		if( activityID<minUint4Value || activityID>maxUint4Value )
			return -1;
		this.activityID = activityID;
		return 0;
	}
	public int setItemCode(String itemCode) {
		if(itemCode.length()>32)
			return -1;
		this.itemCode = itemCode;
		return 0;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	
	public long getItemGatewayID() {
		return itemGatewayID;
	}
	public long getUserID() {
		return userID;
	}
	public long getRoleID() {
		return roleID;
	}
	public long getActivityID() {
		return activityID;
	}
	public String getItemCode() {
		return itemCode;
	}
	public int getItemNum() {
		return itemNum;
	}
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		 b.putUnsignedInt(itemGatewayID)
		  .putUnsignedInt(userID)
		  .putUnsignedInt(roleID)
		  .putUnsignedInt(activityID)
		  .put(fixLength(itemCode, 32))
		  .putInt(itemNum)
		  .flip();
		 
		return b.array();
	}
	
	public  int analyzeBody(byte[] UserItemMinusReqBody)
	{	
		if(UserItemMinusReqBody.length!=bodyLength)
			return -1;
		
		byte[] body = UserItemMinusReqBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		itemGatewayID	=	b.getUnsignedInt();
		userID			=	b.getUnsignedInt();
		roleID			=	b.getUnsignedInt();
		activityID		=	b.getUnsignedInt();
		itemCode		=	getStringByLength(b,32);
		itemNum			=	b.getInt();
		
		return 0;
	}
	
}
