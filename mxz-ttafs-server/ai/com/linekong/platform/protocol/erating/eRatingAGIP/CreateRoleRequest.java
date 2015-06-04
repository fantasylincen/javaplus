package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class CreateRoleRequest extends eRatingProtocol {
	private final int bodyLength=4+32+1+1+2+4+2+1+1;
	
	private long userID=0;
	private String roleName="test001";
	private short roleGender=1;
	private short roleOccupation=1;
	private int initialLevel=1;
	private long userIP=100;
	private int userPort=6000;
	private short communityID=0;
	private final short pad1=0;
	
	public int setUserID(long userID) {
		if(userID<minUint4Value || userID>maxUint4Value)
			return -1;
		this.userID = userID;
		return 0;
	}
	public int setRoleName(String roleName) {
		if(roleName.length()>32)
			return -1;
		this.roleName = roleName;
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
	public int setInitialLevel(int initialLevel) {
		if(initialLevel<this.minUint2Value || initialLevel>this.maxUint2Value)
			return -1;
		this.initialLevel = initialLevel;
		return 0;
	}
	public int setUserIP(long userIP) {
		if(userIP<minUint4Value || userIP>maxUint4Value)
			return -1;
		this.userIP = userIP;
		return 0;
	}
	public int setUserPort(int userPort) {
		if(userPort<minUint2Value || userPort>maxUint2Value)
			return -1;
		this.userPort = userPort;
		return 0;
	}
	public int setCommunityID(short communityID) {
		if(communityID<minUint1Value ||communityID>maxUint1Value)
			return -1;
		this.communityID = communityID;
		return 0;
	}
	
	public long getUserID() {
		return userID;
	}
	public String getRoleName() {
		return roleName;
	}
	public short getRoleGender() {
		return roleGender;
	}
	public short getRoleOccupation() {
		return roleOccupation;
	}
	public int getInitialLevel() {
		return initialLevel;
	}
	public long getUserIP() {
		return userIP;
	}
	public int getUserPort() {
		return userPort;
	}
	public short getCommunityID() {
		return communityID;
	}
	
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.putUnsignedInt(userID).put(fixLength(roleName,32))
		.putUnsigned(roleGender).putUnsigned(roleOccupation)
		.putUnsignedShort(initialLevel).putUnsignedInt(userIP)
		.putUnsignedShort(userPort).putUnsigned(communityID)
		.putUnsigned(pad1).flip();		
		return b.array();
	}
	
	public  int analyzeBody(byte[] CreateRoleRequestBody)
	{	
		if(CreateRoleRequestBody.length!=bodyLength)
			return -1;
		
		byte[] body = CreateRoleRequestBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		userID			=	b.getUnsignedInt();
		roleName		=	getStringByLength(b,32);
		roleGender		=	b.getUnsigned();
		roleOccupation	=	b.getUnsigned();
		initialLevel	=	b.getUnsignedShort();
		userIP			=	b.getUnsignedInt();
		userPort		=	b.getUnsignedShort();
		communityID		=	b.getUnsigned();
							b.getUnsigned();
		
		return 0;
	}
	
}
