package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class UserRegistRequest extends eRatingProtocol {
	private final int bodyLength=32+32+64+32+32+32;
	
	private String userName="test1000";
	private String Pwd="0";
	private String email="test_dev@linkong.com";
	private String IdCode="421202198104232818";
	private String realName="ɺ";
	private String userIP="127.0.0.1";
	

	public int setUserName(String userName) {
		if(userName.length()>32)
			return -1;
		this.userName = userName;
		return 0;
	}
	public int setOldPwd(String Pwd) {
		if(Pwd.length()>32)
			return -1;
		this.Pwd = Pwd;
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
		IdCode = idCode;
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
	public String getOldPwd() {
		return Pwd;
	}
	public String getEmail() {
		return email;
	}
	public String getIdCode() {
		return IdCode;
	}
	public String realName() {
		return realName;
	}
	public String getUserIP() {
		return userIP;
	}
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.put(fixLength(userName,32))	.put(fixLength(Pwd,32))
		 .put(fixLength(email,64))		.put(fixLength(IdCode,32))
		 .put(fixLength(realName,32))	.put(fixLength(userIP,32))
		 
		 .flip();
		
		return b.array();
	}
	
	public  int analyzeBody(byte[] userRegistRequestBody)
	{	
		if(userRegistRequestBody.length!=bodyLength)
			return -1;
		
		byte[] body = userRegistRequestBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
				
		userName	=	getStringByLength(b,32);
		Pwd			=	getStringByLength(b,32);
		email		=	getStringByLength(b,64);
		IdCode		=	getStringByLength(b,32);
		realName	=	getStringByLength(b,32);
		userIP		=	getStringByLength(b,32);
		
		return 0;
	}
}
