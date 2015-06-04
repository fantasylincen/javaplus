package com.linekong.platform.protocol.erating.eRatingAGIP;
import java.math.BigInteger;

import org.apache.mina.core.buffer.IoBuffer;

public class UserIbPayExRequest extends eRatingProtocol {
	//private final int bodyLength=8+4+4+1+1+2+4+32+1+1+2+4+4+4+4+4+subjectCount*(4+4);
	
	private BigInteger DetailID=new BigInteger("1380175328",10);
	private long 	userID			=	0;
	private long 	roleId			=	0;
	private short 	roleGender		=	1;
	private short 	roleOccupation	=	1;
	private int 	roleLevel		=	0;
	private long 	ratingId		=	202901;
	private String 	ibCode			=	"55555";
	private short 	packageFlag		=	1;
	private final byte pad1=0;
	private int 	count			=	1;
	private int 	payTime			=	1380175461;
	private long 	userIP			=	100;
	private int 	price			=	100;
	private int 	discountPrice	=	100;
	private long 	subjectCount	=	0;
	private long[] subjectId		=	null;
	private long[] 	subAmount		=	null;

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
	public void setPayTime(int payTime) {
		this.payTime = payTime;
	}
	public int setUserIP(long userIP) {
		if(userIP<minUint4Value || userIP>maxUint4Value)
			return -1;
		this.userIP = userIP;
		return 0;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}
	public int setSubjectCount(long subjectCount) {
		if(subjectCount<minUint4Value || subjectCount>maxUint4Value)
			return -1;
		this.subjectCount = subjectCount;
		return 0;
	}
	public void setSubjectId(long[] subjectId) {	
		this.subjectId = subjectId;
	}
	public void setSubAmount(long[] subAmount) {
		this.subAmount = subAmount;
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
	public int getPayTime() {
		return payTime;
	}
	public long getUserIP() {
		return userIP;
	}
	
	public int getPrice() {
		return price;
	}
	public int getDiscountPrice() {
		return discountPrice;
	}
	public long getSubjectCount() {
		return subjectCount;
	}
	public long[] getSubjectId() {
		return subjectId;
	}
	
	public long[] getSubAmount() {
		return subAmount;
	}
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(8+4+4+1+1+2+4+32+1+1+2+4+4+4+4+4+(int)subjectCount*(4+4));
		
		b.putLong(DetailID.longValue())
		.putUnsignedInt(userID)
		.putUnsignedInt(roleId)
		.putUnsigned(roleGender)
		.putUnsigned(roleOccupation)
		.putUnsignedShort(roleLevel)
		.putUnsignedInt(ratingId)
		.put(fixLength(ibCode,32))
		.putUnsigned(packageFlag)
		.put(pad1)
		.putUnsignedShort(count)
		.putInt(payTime)
		.putUnsignedInt(userIP)
		.putInt(price)
		.putInt(discountPrice)
		.putUnsignedInt(subjectCount);
		if(subjectCount != subjectId.length || subjectId.length!=subAmount.length)
			return null;
		for(int i=0;i<subjectCount;++i)
		{
			b.putUnsignedInt(subjectId[i])
			 .putUnsignedInt(subAmount[i]);
		}
		b.flip();
		
		return b.array();
	}
	public  int analyzeBody(byte[] UserIbPayExReqBody)
	{	
		if(UserIbPayExReqBody.length-(8+4+4+1+1+2+4+32+1+1+2+4+4+4+4+4)%(2+4)!=0)
			return -1;
		
		byte[] body = UserIbPayExReqBody;
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
		payTime=b.getInt();
		userIP=b.getUnsignedInt();
		price=b.getInt();
		discountPrice=b.getInt();
		subjectCount=b.getInt();
		if(subjectCount>0)
		{
			subjectId=new long[(int)subjectCount];
			subAmount=new long[(int)subjectCount];
		}
		for(int i=0;i<subjectCount;++i)
		{
			subjectId[i]=b.getUnsignedInt();
			subAmount[i]=b.getUnsignedInt();
		}
		return 0;
	}
}
