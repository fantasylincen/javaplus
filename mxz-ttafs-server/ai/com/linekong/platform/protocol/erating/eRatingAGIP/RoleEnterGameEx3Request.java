package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class RoleEnterGameEx3Request extends eRatingProtocol {
	private final int bodyLength=4+4+4+2+1+1+4+2+2+4+4+12+32+32+64;
	
	private long serverId=0;
	private long userId=0;
	private long roleId=0;
	private int level=0;
	private short gender=1;
	private short OccupationID=1;
	private long corpsId=1;
	private int communityId=0;
	private int clientPort=6000;
	private long clientIP=127l*256l*256l*256l+1l;
	private long clientType=6000;
	private String clientMac="001D7DD14955";
	private String hardwareSN1="9874123653";
	private String hardwareSN2="1236987456";
	private String uddi="1234567890asdfghjkl";
	public int setServerId(long serverId) {
		if( serverId<minUint4Value || serverId>maxUint4Value )
			return -1;
		this.serverId = serverId;
		return 0;
	}
	public int setUserId(long userId) {
		if( userId<minUint4Value || userId>maxUint4Value )
			return -1;
		this.userId = userId;
		return 0;
	}
	public int setRoleId(long roleId) {
		if( roleId<minUint4Value || roleId>maxUint4Value )
			return -1;
		this.roleId = roleId;
		return 0;
	}
	public int setLevel(int level) {
		if( level<this.minUint2Value || level>this.maxUint2Value)
			return -1;
		this.level = level;
		return 0;
	}
	public int setGender(short gender) {
		if( gender<minUint1Value || gender>maxUint1Value)
			return -1;
		this.gender = gender;
		return 0;
	}
	public int setOccupationID(short occupationID) {
		if( occupationID<minUint1Value || occupationID>maxUint1Value)
			return -1;
		OccupationID = occupationID;
		return 0;
	}
	public int setCorpsId(long corpsId) {
		this.corpsId = corpsId;
		return 0;
	}
	public int setCommunityId(int communityId) {
		if( communityId<this.minUint2Value || communityId>this.maxUint2Value)
			return -1;
		this.communityId = communityId;
		return 0;
	}
	public int setClientPort(int clientPort) {
		if( clientPort<this.minUint2Value || clientPort>this.maxUint2Value)
			return -1;
		this.clientPort = clientPort;
		return 0;
	}
	public int setClientIP(long clientIP) {
		if( clientIP<minUint4Value || clientIP>maxUint4Value )
			return -1;
		this.clientIP = clientIP;
		return 0;
	}
	public int setClientType(long clientType) {
		if( clientType<minUint4Value || clientType>maxUint4Value )
			return -1;
		this.clientType = clientType;
		return 0;
	}
	public int setClientMac(String clientMac) {
		if(clientMac.length() >12)
			return -1;
		this.clientMac = clientMac;
		return 0;
	}
	public int setHardwareSN1(String hardwareSN1) {
		if(hardwareSN1.length() >32)
			return -1;
		this.hardwareSN1 = hardwareSN1;
		return 0;
	}
	public int setHardwareSN2(String hardwareSN2) {
		if(hardwareSN2.length() >32)
			return -1;
		this.hardwareSN2 = hardwareSN2;
		return 0;
	}
	public int setUddi(String uddi) {
		if(uddi.length() >64)
			return -1;
		this.uddi = uddi;
		return 0;
	}
	public long getServerId() {
		return serverId;
	}
	public long getUserId() {
		return userId;
	}
	public long getRoleId() {
		return roleId;
	}
	public int getLevel() {
		return level;
	}
	public short getGender() {
		return gender;
	}
	public short getOccupationID() {
		return OccupationID;
	}
	public long getCorpsId() {
		return corpsId;
	}
	public int getCommunityId() {
		return communityId;
	}
	public int getClientPort() {
		return clientPort;
	}
	public long getClientIP() {
		return clientIP;
	}
	public long getClientType() {
		return clientType;
	}
	public String getClientMac() {
		return clientMac;
	}
	public String getHardwareSN1() {
		return hardwareSN1;
	}
	public String getHardwareSN2() {
		return hardwareSN2;
	}
	public String getUddi() {
		return uddi;
	}
	
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		 b.putUnsignedInt(serverId)
		  .putUnsignedInt(userId)
		  .putUnsignedInt(roleId)
		  .putUnsignedShort(level)
		  .putUnsigned(gender)
		  .putUnsigned(OccupationID)
		  .putUnsignedInt(corpsId)
		  .putUnsignedShort(communityId)
		  .putUnsignedShort(clientPort)
		  .putUnsignedInt(clientIP)
		  .putUnsignedInt(clientType)
		  .put(fixLength(clientMac, 12))
		  .put(fixLength(hardwareSN1, 32))
		  .put(fixLength(hardwareSN2, 32))
		  .put(fixLength(uddi,64))
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
		
		serverId		=	b.getUnsignedInt();
		userId			=	b.getUnsignedInt();
		roleId			=	b.getUnsignedInt();
		userId			=	b.getUnsignedInt();
		level			=	b.getUnsignedShort();
		gender			=	b.getUnsigned();
		OccupationID	=	b.getUnsigned();
		corpsId			=	b.getUnsignedInt();
		communityId		=	b.getUnsignedShort();
		clientPort		=	b.getUnsignedShort();
		clientIP		=	b.getUnsignedInt();
		clientType		=	b.getUnsignedInt();		
		clientMac		=	getStringByLength(b,12);
		hardwareSN1		=	getStringByLength(b,32);
		hardwareSN2		=	getStringByLength(b,32);
		uddi			=	getStringByLength(b,64);
		
		return 0;
	}
}
