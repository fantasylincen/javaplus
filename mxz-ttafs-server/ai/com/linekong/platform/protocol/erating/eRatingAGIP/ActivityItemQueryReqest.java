package com.linekong.platform.protocol.erating.eRatingAGIP;

import org.apache.mina.core.buffer.IoBuffer;

public class ActivityItemQueryReqest extends eRatingProtocol{
	//private final int bodyLengthVersion1=4+4+4+4+2+2+4;
	private final int bodyLengthVersion2=  4+4+4+2+2+4;
	
	//private  long gatewayId=0;//version 1 	
	private  long userId=0;
	private  long roleId=0;
	private  long activityId=0;
	private  int roleLevel=0;
	private final int pad1=0;
	private final long pad2=0;
	
	/*
	public  int setGatewayId(long gatewayId) {
		if(gatewayId<minUint4Value || gatewayId>maxUint4Value)
			return -1;
		this.gatewayId = gatewayId;
		return 0;
	}*/
	public  int setUserId(long userId) {
		if(userId<minUint4Value || userId>maxUint4Value)
			return -1;
		this.userId = userId;
		return 0;
	}
	public  int setRoleId(long roleId) {
		if(roleId<minUint4Value || roleId>maxUint4Value)
			return -1;
		this.roleId = roleId;
		return 0;
	}
	public  int setActivityId(long activityId) {
		if(activityId<minUint4Value || activityId>maxUint4Value)
			return -1;
		this.activityId = activityId;
		return 0;
	}
	public  int setRoleLevel(int roleLevel) {
		if(roleLevel<minUint2Value || roleLevel>maxUint2Value)
			return -1;
		this.roleLevel = roleLevel;
		return 0;
	}
	/*
	public  long getGatewayId() {
		return gatewayId;
	}*/
	public  long getUserId() {
		return userId;
	}
	public  long getRoleId() {
		return roleId;
	}
	public  long getActivityId() {
		return activityId;
	}
	public  int getRoleLevel() {
		return roleLevel;
	}
	public  byte[] getBody()
	{
		IoBuffer b;
		
		b = IoBuffer.allocate(bodyLengthVersion2);
		b.putUnsignedInt(userId)
		 .putUnsignedInt(roleId)
		 .putUnsignedInt(activityId)
		 .putUnsignedShort(roleLevel)
		 .putUnsignedShort(pad1)
		 .putUnsignedInt(pad2)
		 .flip();
	
		return b.array();
	}
	
	public  int analyzeBody(byte[] reqBody)
	{
		byte[] body = reqBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
	
		if(body.length !=bodyLengthVersion2)
		{
			return -1;
		}
		userId		=	b.getUnsignedInt();
		roleId		=	b.getUnsignedInt();
		activityId	=	b.getUnsignedInt();
		roleLevel	=	b.getUnsignedShort();
						b.getUnsignedShort();
				  		b.getUnsignedInt();
	
		return 0;
	}
}
