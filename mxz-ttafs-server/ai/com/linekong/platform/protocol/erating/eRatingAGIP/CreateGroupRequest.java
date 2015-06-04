package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class CreateGroupRequest extends eRatingProtocol {
	private final int bodyLength=4+32+1+1+2;
	
	private long 		role_ID=0;
	private String 		groupName="LK";
	private short 		groupType=1;
	private	final short pad1=0;
	private final int	pad2=0;
	
	public int setRole_ID(long role_ID) {
		if(role_ID<minUint4Value || role_ID>maxUint4Value)
			return -1;
		this.role_ID = role_ID;
		return 0;
	}
	public int setGroupName(String groupName) {
		if(groupName.length()>32)
			return -1;
		this.groupName = groupName;
		return 0;
	}
	public int setGroupType(short groupType) {
		if(groupType<minUint1Value || groupType>maxUint1Value)
			return -1;
		this.groupType = groupType;
		return 0;
	}
	
	public long getRole_ID() {
		return role_ID;
	}
	public String getGroupName() {
		return groupName;
	}
	public short getGroupType() {
		return groupType;
	}
	
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.putUnsignedInt(role_ID)
		 .put(fixLength(groupName,32))
		 .putUnsigned(groupType)
		 .putUnsigned(pad1)
		 .putUnsignedShort(pad2)
		 .flip();
		
		return b.array();
	}
	
	public  int analyzeBody(byte[] CreateGroupReqBody)
	{	
		if(CreateGroupReqBody.length!=bodyLength)
			return -1;
		
		byte[] body = CreateGroupReqBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();

		role_ID=b.getUnsignedInt();
		groupName=getStringByLength(b,32);
		groupType=b.getUnsigned();
		
		return 0;
	}

}
