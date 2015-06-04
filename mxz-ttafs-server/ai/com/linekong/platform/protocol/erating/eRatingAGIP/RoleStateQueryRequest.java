package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class RoleStateQueryRequest extends eRatingProtocol {
	private final int bodyLength=4+32;
	
	private long 	userID=0;
	private String	roleName="test001";
	
	public int setUserID(long userID) {
		if(userID<minUint4Value || userID>maxUint4Value)
			return -1;
		this.userID = userID;
		return 0;
	}
	public int setRoleName(String roleName) {
		if(roleName.length()>32)
			return -1;
		this.roleName = roleName;
		return 0;
	}
	
	public long getUserID() {
		return userID;
	}
	public String getRoleName() {
		return roleName;
	}
	
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.putUnsignedInt(userID).put(fixLength(roleName,32))
		.flip();		
		return b.array();
	}
	
	public  int analyzeBody(byte[] RoleSateQueryReqBody)
	{	
		if(RoleSateQueryReqBody.length!=bodyLength)
			return -1;
		
		byte[] body = RoleSateQueryReqBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		userID			=	b.getUnsignedInt();
		roleName		=	getStringByLength(b,32);
		return 0;
	}
}
