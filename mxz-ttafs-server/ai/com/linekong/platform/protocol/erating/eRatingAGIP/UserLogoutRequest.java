package com.linekong.platform.protocol.erating.eRatingAGIP;
import java.math.BigInteger;

import org.apache.mina.core.buffer.IoBuffer;
public class UserLogoutRequest extends eRatingProtocol {
	private final int bodyLength=4+4+1+1+2+4+4+4+8;
	
	private long 	userID=0;
	private long 	roleID=0;
	private short	logoutFlag=1;
	private short	roleOccupation=1;
	private int 	roleLevel=1;
	private long 	ratingID=1;
	private long	money1=1;
	private long	money2=1;
	private BigInteger experience=new BigInteger("10000");
	
	public int setUserID(long userID) {
		if(userID<minUint4Value || userID>maxUint4Value)
			return -1;
		this.userID = userID;
		return 0;
	}
	public int setRoleID(long roleID) {
		if(roleID<minUint4Value || roleID>maxUint4Value)
			return -1;
		this.roleID = roleID;
		return 0;
	}
	public int setLogoutFlag(short logoutFlag) {
		if(logoutFlag<minUint1Value || logoutFlag>maxUint1Value)
			return -1;
		this.logoutFlag = logoutFlag;
		return 0;
	}
	public int setRoleOccupation(short roleOccupation) {
		if(roleOccupation<minUint1Value || roleOccupation>maxUint1Value)
			return -1;
		this.roleOccupation = roleOccupation;
		return 0;
	}
	public int setRoleLevel(int roleLevel) {
		if(roleLevel<minUint2Value || roleLevel>maxUint2Value)
			return -1;
		this.roleLevel = roleLevel;
		return 0;
	}
	public int setRatingID(long ratingID) {
		if(ratingID<minUint4Value || ratingID>maxUint4Value)
			return -1;
		this.ratingID = ratingID;
		return 0;
	}
	public int setMoney1(long money1) {
		if(money1<minUint4Value || money1>maxUint4Value)
			return -1;
		this.money1 = money1;
		return 0;
	}
	public int setMoney2(long money2) {
		if(money2<minUint4Value || money2>maxUint4Value)
			return -1;
		this.money2 = money2;
		return 0;
	}
	public int setExperience(BigInteger experience) {
		if(experience.compareTo(this.minUint8Value)<0 || experience.compareTo(this.maxUint8Value)>0)
			return -1;
		this.experience = experience;
		return 0;
	}
	
	public long getUserID() {
		return userID;
	}
	public long getRoleID() {
		return roleID;
	}
	public short getLogoutFlag() {
		return logoutFlag;
	}
	public short getRoleOccupation() {
		return roleOccupation;
	}
	public int getRoleLevel() {
		return roleLevel;
	}
	public long getRatingID() {
		return ratingID;
	}
	public long getMoney1() {
		return money1;
	}
	public long getMoney2() {
		return money2;
	}
	public BigInteger getExperience() {
		return experience;
	}
	public  byte[] getBody() {
		
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.putUnsignedInt(userID)
		 .putUnsignedInt(roleID)
		 .putUnsigned(logoutFlag)
		 .putUnsigned(roleOccupation)
		 .putUnsignedShort(roleLevel)
		 .putUnsignedInt(ratingID)
		 .putUnsignedInt(money1)
		 .putUnsignedInt(money2)
		 .putLong(experience.longValue())
		 .flip();

		return b.array();
	}
	
	public  int analyzeBody(byte[] UserLogoutRequestReqBody)
	{
		byte[] body = UserLogoutRequestReqBody;
	
		if(UserLogoutRequestReqBody.length != bodyLength)
		{
			return -1;
		}
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		userID			=	b.getUnsignedInt();
		roleID			=	b.getUnsignedInt();
		logoutFlag		=	b.getUnsigned();
		roleOccupation	=	b.getUnsigned();
		roleLevel		=	b.getUnsignedShort();
		ratingID		=	b.getUnsignedInt();
		money1			=	b.getUnsignedInt();
		money2			=	b.getUnsignedInt();
		
		long temExperienceBuf=b.getLong();
		long temExperienceL=temExperienceBuf&0x00000000ffffffffl;
		long temExperienceH=temExperienceBuf>>>32;
		
		experience=BigInteger.valueOf(temExperienceH);
		experience=experience.shiftLeft(32);
		experience=experience.add(BigInteger.valueOf(temExperienceL));
		return 0;
	}
}
