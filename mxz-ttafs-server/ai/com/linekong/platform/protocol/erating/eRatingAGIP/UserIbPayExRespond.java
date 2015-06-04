package com.linekong.platform.protocol.erating.eRatingAGIP;

import java.math.BigInteger;

import org.apache.mina.core.buffer.IoBuffer;

public class UserIbPayExRespond extends eRatingProtocol {
private final int bodyLength=4+8+4+4+4+4;
	
	private int 		resultCode=0;
	private BigInteger 	detailID=new BigInteger("123456");
	private long		userID=1000;
	private long		ratingID=10000;
	private long		costAmount=100;
	private long		balance=100;
	
	
	
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
	public int setCostAmount(long costAmount) {
		if(costAmount<minUint4Value || costAmount>maxUint4Value)
			return -1;
		this.costAmount = costAmount;
		return 0;
	}
	public int setBalance(long balance) {
		if(balance<minUint4Value || balance>maxUint4Value)
			return -1;
		this.balance = balance;
		return 0;
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
	public long getCostAmount() {
		return costAmount;
	}
	public long getBalance() {
		return balance;
	}
	
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.putInt(resultCode).putLong(detailID.longValue()).putUnsignedInt(userID).putUnsignedInt(ratingID)
		.putUnsignedInt(costAmount).putUnsignedInt(costAmount)
		.flip();
		
		return b.array();
	}
	public  int analyzeBody(byte[] UserIbPayExResBody)
	{	
		if(UserIbPayExResBody.length!=bodyLength)
			return -1;
		
		byte[] body = UserIbPayExResBody;
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
		costAmount			=	b.getUnsignedInt();
		balance				=	b.getUnsignedInt();
		
		
		return 0;
	}

}
