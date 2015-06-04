package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class CreateGroupRespond extends eRatingProtocol {
	private final int bodyLength=4+4;
	
	private   int resultCode=0;
	private   long groupId=0;
	
	public  int getResultCode() {
		return resultCode;
	}
	public  void setResultCode(int resultCode) {
		this.groupId = resultCode;
	}
	public  long getGroupId() {
		return groupId;
	}
	public  int setGroupId(long groupId) {
		if(groupId<minUint4Value || groupId>maxUint4Value)
			return -1;
		this.groupId = groupId;
		return 0;
	}
	

	public  byte[] getBody() {
		
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.putInt(resultCode)
		 .putUnsignedInt(groupId)
		 .flip();

		return b.array();
	}
	
	public  int analyzeBody(byte[] CreateGroupResBody)
	{
		byte[] body = CreateGroupResBody;
	
		if(CreateGroupResBody.length != bodyLength)
		{
			return -1;
		}
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		resultCode	=	b.getInt();
		groupId		=	b.getUnsignedInt();
		return 0;
	}

}
