package com.linekong.platform.protocol.erating.eRatingAGIP;
import java.math.BigInteger;

import org.apache.mina.core.buffer.IoBuffer;

public class UserIbPayRespond extends eRatingProtocol {
	private final int bodyLength=4+8+4+4+2+2+4;
	
	private int 		resultCode=0;
	private BigInteger 	DetailID=new BigInteger("0",10);
	private long 		userID=0;
	private long 		ratingId=0;
	private short 		subjectId=3;
	private short 		auditFlag=2;
	private int 		Total_Pay_Amount=0;
	
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

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
	
	public int setRatingId(long ratingId) {
		if(ratingId<this.minUint4Value || ratingId>this.maxUint4Value)
			return -1;
		this.ratingId = ratingId;
		return 0;
	}
	
	public int setSubjectId(short subjectId) {
		if(subjectId<0 || subjectId>5)
			return -1;
		this.subjectId = subjectId;
		return 0;
	}
	
	public int setAuditFlag(short auditFlag) {
		if(auditFlag<1 || auditFlag>3)
			return -1;
		this.auditFlag = auditFlag;
		return 0;		
	}
	public void setTotal_Pay_Amount(int total_Pay_Amount) {
		Total_Pay_Amount = total_Pay_Amount;
	}

	
	public int getResultCode() {
		return resultCode;
	}

	public BigInteger getDetailID() {
		return DetailID;
	}

	public long getUserID() {
		return userID;
	}

	public long getRatingId() {
		return ratingId;
	}

	public short getSubjectId() {
		return subjectId;
	}

	public short getAuditFlag() {
		return auditFlag;
	}

	public int getTotal_Pay_Amount() {
		return Total_Pay_Amount;
	}
	
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.putInt(resultCode).putLong(DetailID.longValue()).putUnsignedInt(userID)
		 .putUnsignedInt(ratingId).putShort(subjectId).putShort(auditFlag).putInt(Total_Pay_Amount)
		 .flip();
		
		return b.array();
	}
	
	public  int analyzeBody(byte[] UserIbPayRespondBody)
	{	
		if(UserIbPayRespondBody.length!=bodyLength)
			return -1;
		
		byte[] body = UserIbPayRespondBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		resultCode=b.getInt();
		
		long temDetailIdBuf=b.getLong();
		long temDetailIdL=temDetailIdBuf&0x00000000ffffffffl;
		long temDetailIdH=temDetailIdBuf>>>32;
		DetailID=BigInteger.valueOf(temDetailIdH);
		DetailID=DetailID.shiftLeft(32);
		DetailID=DetailID.add(BigInteger.valueOf(temDetailIdL));
		
		userID=b.getUnsignedInt();
		ratingId=b.getUnsignedInt();
		subjectId=b.getShort();
		auditFlag=b.getShort();
		Total_Pay_Amount=b.getInt();
		return 0;
	}
}
