package com.linekong.platform.protocol.erating.eRatingAGIP;
import java.math.BigInteger;

import org.apache.mina.core.buffer.IoBuffer;

public class UserIbPayRequest extends eRatingProtocol {
	
private final short Ordinary_Product=1;
//private final short Group_Product=2;
private final int bodyLength=8+4+4+1+1+2+4+32+1+1+2+4+4+2+2+4+4;

private BigInteger DetailID=new BigInteger("1380175328",10);
private long userID=0;
private long roleId=0;
private short roleGender=1;
private short roleOccupation=1;
private int roleLevel=0;
private long ratingId=202901;
private String ibCode="55555";
private short packageFlag=Ordinary_Product;
private final byte pad1=0;
private int count=1;
private long payTime=1380175461;
private long userIP=100;

private short subjectId=3;
private short auditFlag=1;
private int price=100;
private int discountPrice=100;

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
public int setRoleId(long roleId) {
	if(roleId<minUint4Value || roleId>maxUint4Value)
		return -1;
	this.roleId = roleId;
	return 0;
}
public int setRoleGender(short roleGender) {
	if(roleGender<this.minUint1Value || roleGender>this.maxUint1Value)
		return -1;
	this.roleGender = roleGender;
	return 0;
}
public int setRoleOccupation(short roleOccupation) {
	if(roleOccupation<minUint1Value || roleOccupation>maxUint1Value)
		return -1;
	this.roleOccupation = roleOccupation;
	return 0;
}
public int setRoleLevel(int roleLevel) {
	if(roleLevel<this.minUint2Value || roleLevel>this.maxUint2Value)
		return -1;
	this.roleLevel = roleLevel;
	return 0;
}
public int setRatingId(long ratingId) {
	if(ratingId<this.minUint4Value || ratingId>this.maxUint4Value)
		return -1;
	this.ratingId = ratingId;
	return 0;
}
public int setIbCode(String ibCode) {
	if(ibCode.length()>32)
		return -1;
	this.ibCode = ibCode;
	return 0;
}
public int setPackageFlag(short packageFlag) {
	if(packageFlag<this.minUint1Value || packageFlag>maxUint1Value)
		return -1;
	this.packageFlag = packageFlag;
	return 0;
}

public int setCount(int count) {
	if(count<this.minUint2Value ||count>this.maxUint2Value)
		return -1;
	this.count = count;
	return 0;
}
public int setPayTime(long payTime) {
	if(payTime<this.minUint4Value || payTime>this.maxUint4Value)
		return -1;
	this.payTime = payTime;
	return 0;
}
public int setUserIP(long userIP) {
	if(userIP<minUint4Value || userIP>maxUint4Value)
		return -1;
	this.userIP = userIP;
	return 0;
}
public void setSubjectId(short subjectId) {
	
	this.subjectId = subjectId;
}
public void setAuditFlag(short auditFlag) {
	
	this.auditFlag = auditFlag;
			
}
public void setPrice(int price) {
	this.price = price;
}
public void setDiscountPrice(int discountPrice) {
	this.discountPrice = discountPrice;
}


public BigInteger getDetailID() {
	return DetailID;
}
public long getUserID() {
	return userID;
}
public long getRoleId() {
	return roleId;
}
public short getRoleGender() {
	return roleGender;
}
public short getRoleOccupation() {
	return roleOccupation;
}
public int getRoleLevel() {
	return roleLevel;
}
public long getRatingId() {
	return ratingId;
}
public String getIbCode() {
	return ibCode;
}
public short getPackageFlag() {
	return packageFlag;
}

public int getCount() {
	return count;
}
public long getPayTime() {
	return payTime;
}
public long getUserIP() {
	return userIP;
}
public short getSubjectId() {
	return subjectId;
}
public short getAuditFlag() {
	return auditFlag;
}
public int getPrice() {
	return price;
}
public int getDiscountPrice() {
	return discountPrice;
}

public byte[] getBody()
{
	IoBuffer b = IoBuffer.allocate(bodyLength);
	
	b.putLong(DetailID.longValue()).putUnsignedInt(userID).putUnsignedInt(roleId)
	.putUnsigned(roleGender).putUnsigned(roleOccupation).putUnsignedShort(roleLevel)
	.putUnsignedInt(ratingId).put(fixLength(ibCode,32)).putUnsigned(packageFlag)
	.put(pad1).putUnsignedShort(count).putUnsignedInt(payTime)
	.putUnsignedInt(userIP).putShort(subjectId).putShort(auditFlag).putInt(price).putInt(discountPrice).flip();
	
	return b.array();
}
public  int analyzeBody(byte[] UserIbPayRequestBody)
{	
	if(UserIbPayRequestBody.length!=bodyLength)
		return -1;
	
	byte[] body = UserIbPayRequestBody;
	IoBuffer b = IoBuffer.allocate(body.length);
	b.put(body);
	b.flip();
	
	long temDetailIdBuf=b.getLong();
	long temDetailIdL=temDetailIdBuf&0x00000000ffffffffl;
	long temDetailIdH=temDetailIdBuf>>>32;
	
	DetailID=BigInteger.valueOf(temDetailIdH);
	DetailID=DetailID.shiftLeft(32);
	DetailID=DetailID.add(BigInteger.valueOf(temDetailIdL));
	userID=b.getUnsignedInt();
	roleId=b.getUnsignedInt();
	roleGender=b.getUnsigned();
	roleOccupation=b.getUnsigned();
	roleLevel=b.getUnsignedShort();
	ratingId=b.getUnsignedInt();
	ibCode=getStringByLength(b,32);
	packageFlag=b.getUnsigned();
	b.get();
	count=b.getUnsignedShort();
	payTime=b.getUnsignedInt();
	userIP=b.getUnsignedInt();
	subjectId=b.getShort();
	auditFlag=b.getShort();
	price=b.getInt();
	discountPrice=b.getInt();
	return 0;
}

}
