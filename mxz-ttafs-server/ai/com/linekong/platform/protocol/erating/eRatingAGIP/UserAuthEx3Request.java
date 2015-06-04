package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class UserAuthEx3Request extends eRatingProtocol {
	private final int bodyLength=32+32+1+1+2+4+32+16+12+32+32+32+64;
	
	private String userName="test001";
	private String pwd="0";
	private byte pwdType=1;
	private byte gameClientType=1;
	private int userPort=6000;
	private long userIP=127l*256l*256l*256l+1l;
	private String matrixPwd="1";
	private String matrixCoord="A3D9G4";
	private String mac="0016E6DE3C74";
	private String adID="567th";
	private String hardwareSN1="9874123653";
	private String hardwareSN2="1236987456";
	private String uddi="1234567890asdfghjkltyhf";
	
	public int setUserName(String userName) {
		if(userName.length()>32)
			return -1;
		this.userName = userName;
		return 0;
	}
	public int setPwd(String pwd) {
		if(pwd.length()>32)
			return -1;
		this.pwd = pwd;
		return 0;
	}
	public void setPwdType(byte pwdType) {
		this.pwdType = pwdType;
		
	}
	public void setGameClientType(byte gameClientType) {
		this.gameClientType = gameClientType;
	}
	public int setUserPort(int userPort) {
		if(userPort<minUint2Value || userPort>maxUint2Value)
			return -1;
		this.userPort = userPort;
		return 0;
	}
	public int setUserIP(long userIP) {
		if(userIP<minUint4Value || userIP>maxUint4Value)
			return -1;
		this.userIP = userIP;
		return 0;
	}
	public int setMatrixPwd(String matrixPwd) {
		if(matrixPwd.length()>32)
			return -1;
		this.matrixPwd = matrixPwd;
		return 0;
	}
	public int setMatrixCoord(String matrixCoord) {
		if(matrixCoord.length()>16)
			return -1;
		this.matrixCoord = matrixCoord;
		return 0;
	}
	public int setMac(String mac) {
		if(mac.length()>12)
			return -1;
		this.mac = mac;
		return 0;
	}
	public int setAdID(String adID) {
		if(adID.length()>32)
			return -1;
		this.adID = adID;
		return 0;
	}
	public int setHardwareSN1(String hardwareSN1) {
		if(hardwareSN1.length()>32)
			return -1;
		this.hardwareSN1 = hardwareSN1;
		return 0;
	}
	public int setHardwareSN2(String hardwareSN2) {
		if(hardwareSN2.length()>32)
			return -1;
		this.hardwareSN2 = hardwareSN2;
		return 0;
	}
	public int setUddi(String uddi) {
		if(uddi.length()>64)
			return -1;
		this.uddi = uddi;
		return 0;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public String getPwd() {
		return pwd;
	}
	public byte getPwdType() {
		return pwdType;
	}
	public byte getGameClientType() {
		return gameClientType;
	}
	public int getUserPort() {
		return userPort;
	}
	public long getUserIP() {
		return userIP;
	}
	public String getMatrixPwd() {
		return matrixPwd;
	}
	public String getMatrixCoord() {
		return matrixCoord;
	}
	public String getMac() {
		return mac;
	}
	public String getAdID() {
		return adID;
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
		
		 b.put(fixLength(userName,32))		.put(fixLength(pwd,32))			.put(pwdType)
		  .put(gameClientType)		  		.putUnsignedShort(userPort)		.putUnsignedInt(userIP)
		  .put(fixLength(matrixPwd, 32))	.put(fixLength(matrixCoord, 16)).put(fixLength(mac, 12))
		  .put(fixLength(adID, 32))			.put(fixLength(hardwareSN1, 32)).put(fixLength(hardwareSN2, 32))
		  .put(fixLength(uddi, 64))
		  
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
		
		userName		=	getStringByLength(b,32);
		pwd				=	getStringByLength(b,32);
		pwdType			=	b.get();
		gameClientType	=	b.get();
		userPort		=	b.getUnsignedShort();
		userIP			=	b.getUnsignedInt();
		matrixPwd		=	getStringByLength(b,32);
		matrixCoord		=	getStringByLength(b,16);
		mac				=	getStringByLength(b,12);
		adID			=	getStringByLength(b,32);
		hardwareSN1		=	getStringByLength(b,32);
		hardwareSN2		=	getStringByLength(b,32);
		uddi			=	getStringByLength(b,64);
		
		return 0;
	}

}
