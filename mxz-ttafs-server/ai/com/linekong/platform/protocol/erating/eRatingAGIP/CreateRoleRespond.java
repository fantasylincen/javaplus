package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class CreateRoleRespond extends eRatingProtocol {
	private final int bodyLength=4+4;
		
	private   int resultCode=0;
	private   long roleId=0;

	public int getResultCode() {
		return resultCode;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	
	public int setRoleId(long roleId) {
		if(roleId<minUint4Value || roleId>maxUint4Value)
			return -1;
		this.roleId = roleId;
		return 0;
	}

	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b .putInt(resultCode)
		  .putUnsignedInt(roleId)
		  .flip();
		return b.array();	
	}
	public  int analyzeBody(byte[] CreateRoleResBody)
	{	
		if(CreateRoleResBody.length!=bodyLength)
			return -1;
		
		byte[] body = CreateRoleResBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		resultCode	=	b.getInt();
		roleId		=	b.getUnsignedInt();
		
		return 0;
	}

}
