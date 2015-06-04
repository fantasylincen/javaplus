package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class UserAuthEx3Respond extends eRatingProtocol {
	private final int bodyLength=4+4+1+1+1+1+4+4+4;
	
	private int 		resultCode=0;
	private long 		userID=0;
	private byte		userType=1;
	private byte		adultFlag=1;
	private byte 		userClass=1;
	private final byte 	pad1=0;
	private int			userPoint=0;
	private final long 	Promoter_ID=0L;
	private long 		userFlag=0;
	
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public int setUserID(long userID) {
		if(userID<minUint4Value || userID>maxUint4Value)
			return-1;
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
		if(userFlag<minUint4Value || userFlag>maxUint4Value)
			return-1;
		this.userFlag = userFlag;
		return 0;
	}
	
	public int getResultCode() {
		return resultCode;
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
	public int getUserPoint() {
		return userPoint;
	}
	public long getUserFlag() {
		return userFlag;
	}
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		 b.putInt(resultCode)	.putUnsignedInt(userID)		.put(userType)
		  .put(adultFlag)		.put(userClass)				.put(pad1)
		  .putInt(userPoint)	.putUnsignedInt(Promoter_ID).putUnsignedInt(userFlag)
		  
		  .flip();
		 
		return b.array();
	}
	
	public  int analyzeBody(byte[] RoleEnterGameEx3ReqBody)
	{	
		if(RoleEnterGameEx3ReqBody.length!=bodyLength)
			return -1;
		
		byte[] body = RoleEnterGameEx3ReqBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		resultCode		=	b.getInt();
		userID			=	b.getUnsignedInt();	
		userType		=	b.get();
		adultFlag		=	b.get();
		userClass		=	b.get();
							b.get();
		userPoint		=	b.getInt();
							b.getUnsignedInt();
		userFlag		=	b.getUnsignedInt();
		
		return 0;
	}
	
}
