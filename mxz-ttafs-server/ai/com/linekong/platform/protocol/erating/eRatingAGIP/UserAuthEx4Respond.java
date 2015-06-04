package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;
public class UserAuthEx4Respond extends eRatingProtocol {
	private final int bodyLength=4+4+1+1+1+1+4+4+4;
	
	private int ResultCode=0;
	private long userID=0;
	private byte userType=0;
	private byte adultFlag=0;
	private byte userClass=0;
	private final byte pad=0;
	
	private int userPoint=0;
	private final int promoterID=0;
	private long userFlag=0;
	
	
	
	public void setResultCode(int resultCode) {
		ResultCode = resultCode;
	}
	public int setUserID(long userID) {
		if( userID<minUint4Value || userID>maxUint4Value )
			return -1;
		this.userID = userID;
		return 0;
	}
	public void setUserType(byte userType) {
		this.userType = userType;
	}
	public void setAdultFlag(byte adultFlag) {
		this.adultFlag = adultFlag;
	}
	public void setUserClass(byte userClass) {
		this.userClass = userClass;
	}
	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}
	public int setUserFlag(long userFlag) {
		if( userFlag<minUint4Value || userFlag>maxUint4Value )
			return -1;
		this.userFlag = userFlag;
		return 0;
	}
	
	public int getResultCode() {
		return ResultCode;
	}
	public long getUserID() {
		return userID;
	}
	public byte getUserType() {
		return userType;
	}
	public byte getAdultFlag() {
		return adultFlag;
	}
	public byte getUserClass() {
		return userClass;
	}
	public byte getPad() {
		return pad;
	}
	public int getUserPoint() {
		return userPoint;
	}
	public int getPromoterID() {
		return promoterID;
	}
	public long getUserFlag() {
		return userFlag;
	}
	
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		 b.putInt(ResultCode)
		  .putUnsignedInt(userID) 
		  .put(userType)
		  .put(adultFlag)
		  .put(userClass)
		  .put(pad)
		  .putInt(userPoint)
		  .putInt(promoterID)
		  .putUnsignedInt(userFlag)
		  .flip();
		 
		return b.array();
	}
	
	public  int analyzeBody(byte[] UserAuthEx4ResBody)
	{	
		if(UserAuthEx4ResBody.length!=bodyLength)
			return -1;
		
		byte[] body = UserAuthEx4ResBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		ResultCode		=	b.getInt();
		userID			=	b.getUnsignedInt();
		userType		=	b.get();
		adultFlag		=	b.get();
		userClass		=	b.get();
						 	b.get();
		userPoint		=	b.getInt();
							b.getInt();
		userFlag		=	b.getUnsignedInt();
		
		
		return 0;
	}
	
}
