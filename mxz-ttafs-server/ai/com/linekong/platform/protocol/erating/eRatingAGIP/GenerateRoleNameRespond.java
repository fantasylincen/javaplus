package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class GenerateRoleNameRespond extends eRatingProtocol {
	//private final int bodyLength=4+4+RoleCount*32;
	
	private int 		ResultCode=0;
	private long 		RoleCount=0;
	private String[] 	roleName=null;
	
	public void setResultCode(int resultCode) {
		ResultCode = resultCode;
	}
	public int setRoleCount(long roleCount) {
		if(roleCount<minUint4Value || roleCount>maxUint4Value)
			return -1;
		RoleCount = roleCount;
		return 0;
	}
	public void setRoleName(String[] roleName) {
		this.roleName = roleName;
	}
	
	public int getResultCode() {
		return ResultCode;
	}
	public long getRoleCount() {
		return RoleCount;
	}
	public String[] getRoleName() {
		return roleName;
	}
	
	public byte[] getBody()
	{
		if(RoleCount != roleName.length)
		{
			return null;
		}
		
		IoBuffer b = IoBuffer.allocate( (int)(4+4+(4+4)*RoleCount));
		
		b .putInt(ResultCode)
		  .putUnsignedInt(RoleCount);
		  for(int i=0;i<RoleCount;++i)
		  {
			  b.put( fixLength((roleName[i]),32) );
		  }
		  b.flip();
		  
		return b.array();	
	}
	public  int analyzeBody(byte[] GenerateRoleNameResBody)
	{	
		if( (GenerateRoleNameResBody.length-(4+4))%(32) !=0 )
			return -1;
		
		byte[] body = GenerateRoleNameResBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		ResultCode		=	b.getInt();
		RoleCount		=	b.getUnsignedInt();
		if(RoleCount>0)
		{
			roleName	=	new String[(int)RoleCount];
		}
		for(int i=0;i<RoleCount;++i)
		{
			roleName[i]	=	getStringByLength(b,32);				
		}
		
		return 0;
	}
	
	
}
