package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class UserAuthEx4Request extends eRatingProtocol {
	private final int bodyLength=32+32+1+1+2+4+32+16+256+32+32+32+64;
	private String UserName="test001";
	private String pwd="0";
	private byte pwdType=0;
	private byte GameClientType=0;
	private int UserPort=5000;
	private long userIP=0;
	private String MatrixPwd="0";
	private String MatrixCoord="0";
	private String Mac="1";
	private String adID="0";
	private String hardwareSN1="0";
	private String hardwareSN2="0";
	private String uddi="0";
	
	
	
	public int setUserName(String userName) {
		if(userName.length()>32)
			return -1;
		UserName = userName;
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
		GameClientType = gameClientType;
	}
	public int setUserPort(int userPort) {
		if( userPort<minUint2Value || userPort>maxUint2Value )
			return -1;
		UserPort = userPort;
		return 0;
	}
	public int setUserIP(long userIP) {
		if( userIP<minUint4Value || userIP>maxUint4Value )
			return -1;
		this.userIP = userIP;
		return 0;
	}
	public int setMatrixPwd(String matrixPwd) {
		if(matrixPwd.length()>32)
			return -1;
		MatrixPwd = matrixPwd;
		return 0;
	}
	public int setMatrixCoord(String matrixCoord) {
		if(matrixCoord.length()>16)
			return -1;
		MatrixCoord = matrixCoord;
		return 0;
	}
	public int setMac(String mac) {
		if(mac.length()>256)
			return -1;
		Mac = mac;
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
		return UserName;
	}
	public String getPwd() {
		return pwd;
	}
	public byte getPwdType() {
		return pwdType;
	}
	public byte getGameClientType() {
		return GameClientType;
	}
	public int getUserPort() {
		return UserPort;
	}
	public long getUserIP() {
		return userIP;
	}
	public String getMatrixPwd() {
		return MatrixPwd;
	}
	public String getMatrixCoord() {
		return MatrixCoord;
	}
	public String getMac() {
		return Mac;
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
		
		 b.put(fixLength(UserName, 32))
		  .put(fixLength(pwd, 32))
		  .put(pwdType)
		  .put(GameClientType)
		  .putUnsignedShort(UserPort) 
		  .putUnsignedInt(userIP)
		  .put(fixLength(MatrixPwd, 32))
		  .put(fixLength(MatrixCoord, 32))
		  .put(fixLength(Mac, 16))
		  .put(fixLength(adID, 256))
		  .put(fixLength(hardwareSN1, 32))
		  .put(fixLength(hardwareSN2, 32))
		  .put(fixLength(uddi, 32))
		 
		  .flip();
		 
		return b.array();
	}
	
	public  int analyzeBody(byte[] UserAuthEx4ReqBody)
	{	
		if(UserAuthEx4ReqBody.length!=bodyLength)
			return -1;
		
		byte[] body = UserAuthEx4ReqBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		UserName		=	getStringByLength(b,32);
		pwd				=	getStringByLength(b,32);
		pwdType			=	b.get();
		GameClientType	=	b.get();
		UserPort		=	b.getUnsignedShort();
		userIP			= 	b.getUnsignedInt();
		MatrixPwd		=	getStringByLength(b,32);
		MatrixCoord		=	getStringByLength(b,32);
		Mac				=	getStringByLength(b,16);
		adID			=	getStringByLength(b,256);
		hardwareSN1		=	getStringByLength(b,32);
		hardwareSN2		=	getStringByLength(b,32);
		uddi			=	getStringByLength(b,32);
		
		return 0;
	}
	
}
