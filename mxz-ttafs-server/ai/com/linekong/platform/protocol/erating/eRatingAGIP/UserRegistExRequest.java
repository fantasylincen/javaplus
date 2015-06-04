package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class UserRegistExRequest extends eRatingProtocol {
	private final int bodyLength=32+32+64+32+32+32;
	
	private String	userName="test001";
	private String	pwd="12344";
	private String	email="test_dev@linkong.com";
	private String	idCode="421202198104232818";
	private String	realName="linkong";
	private String	userIP="127.0.0.1";
	
	public int setUserName(String userName) {
		if(userName.length()>32)
			return -1;
		this.userName = userName;
		return 0;
	}
	public int setOldPwd(String pwd) {
		if(pwd.length()>32)
			return -1;
		this.pwd = pwd;
		return 0;
	}
	
	public int setEmail(String email) {
		if(email.length()>64)
			return -1;
		this.email = email;
		return 0;
	}
	public int setIdCode(String idCode) {
		if(idCode.length()>32)
			return -1;
		this.idCode = idCode;
		return 0;
	}
	public int setRealNmae(String realName) {
		if(realName.length()>32)
			return -1;
		this.realName = realName;
		return 0;
	}
	public int setUserIP(String userIP) {
		if(userIP.length()>32)
			return -1;
		this.userIP = userIP;
		return 0;
	}
	
	public String getUserName() {
		return userName;
	}
	public String getPwd() {
		return pwd;
	}
	public String getEmail() {
		return email;
	}
	public String getIdCode() {
		return idCode;
	}
	public String getRealName() {
		return realName;
	}
	public String getUserIP() {
		return userIP;
	}
	
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.put(fixLength(userName,32)).put(fixLength(pwd,32))	
		.put(fixLength(email,64)).put(fixLength(idCode,32))
		.put(fixLength(realName,32)).put(fixLength(userIP,32))
		.flip();
		
		return b.array();
	}
	
	public  int analyzeBody(byte[] UserRegistExReqBody)
	{	
		if(UserRegistExReqBody.length!=bodyLength)
			return -1;
		
		byte[] body = UserRegistExReqBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		userName	=	getStringByLength(b,32);
		pwd			=	getStringByLength(b,32);
		email		=	getStringByLength(b,64);
		idCode		=	getStringByLength(b,32);
		realName	=	getStringByLength(b,32);
		userIP		=	getStringByLength(b,32);
		
		return 0;
	}
	
}
