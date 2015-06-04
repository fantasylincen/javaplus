package com.linekong.platform.protocol.erating.eRatingAGIP;
import java.math.BigInteger;

import org.apache.mina.core.buffer.IoBuffer;

public class DeleteUserIbPayRequest extends eRatingProtocol {
	private final int bodyLength=8+4;
	
	private BigInteger  DetailID=new BigInteger("123456",16);
	private long 		userID=0;
	
	public int setDetailID(BigInteger detailID) {
		if(detailID.compareTo(this.minUint8Value)<0 || detailID.compareTo(this.maxUint8Value)>0)
			return -1;
		DetailID = detailID;
		return 0;
	}
	public int setUserID(long userID) {
		if(userID<minUint4Value || userID>maxUint4Value)
			return -1;
		this.userID = userID;
		return 0;
	}
	
	public BigInteger getDetailID() {
		return DetailID;
	}
	public long getUserID() {
		return userID;
	}
	
	public  byte[] getBody() {
		
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.putLong(DetailID.longValue())
		 .putUnsignedInt(userID)
		 .flip();

		return b.array();
	}
	
	public  int analyzeBody(byte[] DeleteUserIbPayReqBody)
	{
		byte[] body = DeleteUserIbPayReqBody;
	
		if(DeleteUserIbPayReqBody.length != bodyLength)
		{
			return -1;
		}
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		long temExperienceBuf=b.getLong();
		long temExperienceL=temExperienceBuf&0x00000000ffffffffl;
		long temExperienceH=temExperienceBuf>>>32;
		
		DetailID=BigInteger.valueOf(temExperienceH);
		DetailID=DetailID.shiftLeft(32);
		DetailID=DetailID.add(BigInteger.valueOf(temExperienceL));
		DetailID=DetailID.add(BigInteger.valueOf(temExperienceL));
		
		userID			=	b.getUnsignedInt();
		
		return 0;
	}

}
