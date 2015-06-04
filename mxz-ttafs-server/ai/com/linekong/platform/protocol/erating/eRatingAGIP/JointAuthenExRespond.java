package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class JointAuthenExRespond extends eRatingProtocol {
	
	private final int bodyLength=4+4+1+1+1+1+4+4+4+4+220+32+2+2;
	
	private int 		resultCode=0;
	private long 		userID=0;
	private byte 		userType=1;
	private byte 		adultFlag=1;
	private byte		userClase=1;
	private final byte	userRoleCount=1;
	private int			userPoint=0;
	private final long	promoterID=0;
	private long		userFlag=0;
	private int			cpReturnValue=0;
	private String		cpErrMsg="";
	private String		userName="test1000";
	private int			appendix1=0;
	private int			appendix2=0;
	
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public int setUserID(long userID) {
		if(userID<minUint4Value || userID>maxUint4Value)
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
	public void setUserClase(byte userClase) {
		this.userClase = userClase;
	}
	
	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}
	public int setUserFlag(long userFlag) {
		if(userFlag<minUint4Value || userFlag>maxUint4Value)
			return -1;
		this.userFlag = userFlag;
		return 0;
	}
	public void setCpReturnValue(int cpReturnValue) {	
		this.cpReturnValue = cpReturnValue;
	}
	public int setCpErrMsg(String cpErrMsg) {
		if(cpErrMsg.length()>220)
			return -1;
		this.cpErrMsg = cpErrMsg;
		return 0;
	}
	public int setUserName(String userName) {
		if(userName.length()>32)
			return -1;
		this.userName = userName;
		return 0;
	}
	public int setAppendix1(int appendix1) {
		if(appendix1<minUint2Value || appendix1>maxUint2Value)
			return -1;
		this.appendix1 = appendix1;
		return 0;
	}
	public int setAppendix2(int appendix2) {
		if(appendix2<minUint2Value || appendix2>maxUint2Value)
			return -1;
		this.appendix2 = appendix2;
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
	public byte getUserClase() {
		return userClase;
	}
	
	public int getUserPoint() {
		return userPoint;
	}
	public long getUserFlag() {
		return userFlag;
	}
	public int getCpReturnValue() {
		return cpReturnValue;
	}
	public String getCpErrMsg() {
		return cpErrMsg;
	}
	public String getUserName() {
		return userName;
	}
	public int getAppendix1() {
		return appendix1;
	}
	public int getAppendix2() {
		return appendix2;
	}
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.putInt(resultCode)			.putUnsignedInt(userID)
		 .put(userType)					.put(adultFlag)
		 .put(userClase)				.put(userRoleCount)
		 .putInt(userPoint)				.putUnsignedInt(promoterID)
		 .putUnsignedInt(userFlag)		.putInt(cpReturnValue)
		 .put(fixLength(cpErrMsg,220))	.put(fixLength(userName,32))
		 .putUnsignedShort(appendix1)	.putUnsignedShort(appendix2)
		 .flip();
		
		return b.array();
	}
	
	public  int analyzeBody(byte[] JointAuthenExResBody)
	{	
		if(JointAuthenExResBody.length!=bodyLength)
			return -1;
		
		byte[] body = JointAuthenExResBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		resultCode		=	b.getInt();
		userID			=	b.getUnsignedInt();
		userType		=	b.get();
		adultFlag		=	b.get();
		userClase		=	b.get();
							b.get();
		userPoint		=	b.getInt();
							b.getUnsignedInt();
		userFlag		=	b.getUnsignedInt();
		cpReturnValue	=	b.getInt();
		cpErrMsg		=	getStringByLength(b,220);
		userName		=	getStringByLength(b,32);
		appendix1		=	b.getUnsignedShort();
		appendix2		=	b.getUnsignedShort();
		
		return 0;
	}

	

}
