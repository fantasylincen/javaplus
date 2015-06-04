package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class DeleteRoleRequest extends eRatingProtocol {
	private final int bodyLength=4+4;
	
	private long userID=0;
	private long roleID=0;
	
	public int setUserID(long userID) {
		if(userID<minUint4Value || userID>maxUint4Value)
			return -1;
		this.userID = userID;
		return 0;
	}	
	public int setroleID(long roleID) {
		if(roleID<minUint4Value || roleID>maxUint4Value)
			return -1;
		this.roleID = roleID;
		return 0;
	}
	
	public long getUserID() {
		return userID;
	}
	public long getRoleID() {
		return roleID;
	}
	
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.putUnsignedInt(userID).putUnsignedInt(roleID)
		.flip();		
		return b.array();
	}
	
	public  int analyzeBody(byte[] DeleteRoleRequestBody)
	{	
		if(DeleteRoleRequestBody.length!=bodyLength)
			return -1;
		
		byte[] body = DeleteRoleRequestBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		userID			=	b.getUnsignedInt();
		roleID			=   b.getUnsignedInt();
		
		return 0;
	}

}
