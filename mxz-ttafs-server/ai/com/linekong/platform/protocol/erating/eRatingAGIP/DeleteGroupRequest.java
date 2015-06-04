package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class DeleteGroupRequest extends eRatingProtocol {
	private final int bodyLength=4+4;
	private long groupID=1;
	private long roleID=1;
	
	public int setGroupID(long groupID) {
		if(groupID<minUint4Value || groupID>maxUint4Value)
			return -1;
		this.groupID = groupID;
		return 0;
	}
	public int setRoleID(long roleID) {
		if(roleID<minUint4Value || roleID>maxUint4Value)
			return -1;
		this.roleID = roleID;
		return 0;
	}
	
	public long getGroupID() {
		return groupID;
	}
	public long getRoleID() {
		return roleID;
	}
	public  byte[] getBody() {
		
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.putUnsignedInt(groupID)
		 .putUnsignedInt(roleID)
		 .flip();

		return b.array();
	}
	
	public  int analyzeBody(byte[] DeleteGroupResBody)
	{
		byte[] body = DeleteGroupResBody;
	
		if(DeleteGroupResBody.length != bodyLength)
		{
			return -1;
		}
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		groupID		=	b.getUnsignedInt();
		roleID		=	b.getUnsignedInt();
		return 0;
	}

}
