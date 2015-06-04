package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class RoleStateQueryRespond extends eRatingProtocol {
	private final int bodyLength=4+4;
	
	private   int resultCode=0;
	private   long roleState=1;
	
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public int setRoleState(long roleState) {
		if(roleState<minUint4Value || roleState>maxUint4Value)
			return -1;
		this.roleState = roleState;
		return 0;		
	}
	
	public int getResultCode() {
		return resultCode;
	}
	public long getRoleState() {
		return roleState;
	}
	
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b .putInt(resultCode)
		  .putUnsignedInt(roleState)
		  .flip();
		return b.array();	
	}
	public  int analyzeBody(byte[] RoleSateQueryResBody)
	{	
		if(RoleSateQueryResBody.length!=bodyLength)
			return -1;
		
		byte[] body = RoleSateQueryResBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		resultCode	=	b.getInt();
		roleState		=	b.getUnsignedInt();
		
		return 0;
	}
}
