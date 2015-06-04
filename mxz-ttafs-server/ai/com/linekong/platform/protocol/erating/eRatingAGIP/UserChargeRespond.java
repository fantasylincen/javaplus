package com.linekong.platform.protocol.erating.eRatingAGIP;
import java.math.BigInteger;

import org.apache.mina.core.buffer.IoBuffer;
public class UserChargeRespond extends eRatingProtocol {
	private final int bodyLength=4+8+4+4+2+2+4;
	
	private int 		resultCode=0;
	private BigInteger 	detailID=new BigInteger("123456");
	private long		userID=1000;
	private long		ratingID=10000;
	private short		subjectID=3;
	private short		auditFlag=1;
	private int			totalAmount=100;
	
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public int setDetailID(BigInteger detailID) {
		if(detailID.compareTo(this.minUint8Value)<0 || detailID.compareTo(this.maxUint8Value)>0)
			return -1;
		this.detailID = detailID;
		return 0;
	}
	public int setUserID(long userID) {
		if(userID<minUint4Value || userID>maxUint4Value)
			return -1;
		this.userID = userID;
		return 0;
	}
	public int setRatingID(long ratingID) {
		if(ratingID<minUint4Value || ratingID>maxUint4Value)
			return -1;
		this.ratingID = ratingID;
		return 0;
	}
	public void setSubjectID(short subjectID) {
		this.subjectID = subjectID;
	}
	public void setAuditFlag(short auditFlag) {
		this.auditFlag = auditFlag;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	public int getResultCode() {
		return resultCode;
	}
	public BigInteger getDetailID() {
		return detailID;
	}
	public long getUserID() {
		return userID;
	}
	public long getRatingID() {
		return ratingID;
	}
	public short getSubjectID() {
		return subjectID;
	}
	public short getAuditFlag() {
		return auditFlag;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.putInt(resultCode).putLong(detailID.longValue()).putUnsignedInt(userID).putUnsignedInt(ratingID)
		.putShort(subjectID).putShort(auditFlag).putInt(totalAmount)
		.flip();
		
		return b.array();
	}
	public  int analyzeBody(byte[] userChargeResBody)
	{	
		if(userChargeResBody.length!=bodyLength)
			return -1;
		
		byte[] body = userChargeResBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		resultCode			=	b.getInt();
		long temDetailIdBuf	=	b.getLong();
		long temDetailIdL=temDetailIdBuf&0x00000000ffffffffl;
		long temDetailIdH=temDetailIdBuf>>>32;
		
		detailID=BigInteger.valueOf(temDetailIdH);
		detailID=detailID.shiftLeft(32);
		detailID=detailID.add(BigInteger.valueOf(temDetailIdL));
		
		userID				=	b.getUnsignedInt();
		ratingID			=	b.getUnsignedInt();
		subjectID			=	b.getShort();
		auditFlag			=	b.getShort();
		totalAmount			=	b.getInt();
		
		return 0;
	}
}
