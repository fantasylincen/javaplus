package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class GenerateRoleNameRequest extends eRatingProtocol {
	private final int bodyLength=4+4;
	
	private int 		RoleGender=0;
	private long 		RoleCount=0;

	
	
	public int getRoleGender() {
		return RoleGender;
	}	

	public long getRoleCount() {
		return RoleCount;
	}
	
	public void setRoleGender(int roleGender) {
		RoleGender = roleGender;
	}
	
	public int setRoleCount(long roleCount) {
		if(roleCount<minUint4Value || roleCount>maxUint4Value)
			return -1;
		RoleCount = roleCount;
		return 0;
	}

	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.putInt(RoleGender)
		 .putUnsignedInt(RoleCount)
		 .flip();
		
		return b.array();
	}
	
	public  int analyzeBody(byte[] GenerateRoleNameReqBody)
	{	
		if(GenerateRoleNameReqBody.length!=bodyLength)
			return -1;
		
		byte[] body = GenerateRoleNameReqBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		RoleGender		=	b.getInt();
		RoleCount		=	b.getUnsignedInt();
		
		return 0;
	}
}
