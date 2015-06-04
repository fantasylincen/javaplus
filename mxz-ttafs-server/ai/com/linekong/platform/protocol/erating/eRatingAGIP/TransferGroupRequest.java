package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class TransferGroupRequest extends eRatingProtocol {
	private final int bodyLength=4+4+4;
	
	private long groupID=1;
	private long fromRoleID=0;
	private long toRoleID=11;
	
	public int setGroupID(long groupID) {
		if(groupID<minUint4Value || groupID>maxUint4Value)
			return -1;
		this.groupID = groupID;
		return 0;
	}
	public int setFromRoleID(long fromRoleID) {
		if(fromRoleID<minUint4Value || fromRoleID>maxUint4Value)
			return -1;
		this.fromRoleID = fromRoleID;
		return 0;
	}
	public int setToRoleID(long toRoleID) {
		if(toRoleID<minUint4Value || toRoleID>maxUint4Value)
			return -1;
		this.toRoleID = toRoleID;
		return 0;
	}
	
	public long getGroupID() {
		return groupID;
	}
	public long getFromRoleID() {
		return fromRoleID;
	}
	public long getToRoleID() {
		return toRoleID;
	}
	
	public  byte[] getBody() {
		
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.putUnsignedInt(groupID)
		 .putUnsignedInt(fromRoleID)
		 .putUnsignedInt(toRoleID)
		 .flip();

		return b.array();
	}
	
	public  int analyzeBody(byte[] TransferGroupReqBody)
	{
		byte[] body = TransferGroupReqBody;
	
		if(TransferGroupReqBody.length != bodyLength)
		{
			return -1;
		}
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		groupID		=	b.getUnsignedInt();
		fromRoleID	=	b.getUnsignedInt();
		toRoleID	=	b.getUnsignedInt();
		return 0;
	}

}
