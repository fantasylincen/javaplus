package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;
public class UserBalanceInfoExRequest extends eRatingProtocol {
	private final int bodyLength=4+4+4+4;
	private  long userId=0;
	private  long roleId=0;
	private  long ratingId=0;
	private  int subjectId=0;
	
	
	public long getUserId() {
		return userId;
	}
	public long getRoleId() {
		return roleId;
	}
	public long getRatingId() {
		return ratingId;
	}	
	public int getSubjectId() {
		return subjectId;
	}
	
	public int setUserId(long userId) {
		if(userId<minUint4Value || userId>maxUint4Value)
			return -1;
		this.userId = userId;
		return 0;
	}
	public int setRoleId(long roleId) {
		if(roleId<minUint4Value || roleId>maxUint4Value)
			return -1;
		this.roleId = roleId;
		return 0;
	}
	public int setRatingId(long ratingId) {
		if(ratingId<minUint4Value || ratingId>maxUint4Value)
			return -1;
		this.ratingId = ratingId;
		return 0;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public  byte[] getBody()
	{
		IoBuffer b;
		
		b = IoBuffer.allocate(bodyLength);
		b.putUnsignedInt(userId)
		 .putUnsignedInt(roleId)
		 .putUnsignedInt(ratingId)
		 .putInt(subjectId)
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
		userId		=	b.getUnsignedInt();
		roleId		=	b.getUnsignedInt();
		ratingId	=	b.getUnsignedInt();
		subjectId	=	b.getInt();
	
		return 0;
	}
	
}
