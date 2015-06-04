package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class GetNewUserRespond extends eRatingProtocol {
	private final int bodyLength=32+32+4;
	
	private String 	userName="test1000";
	private String 	pwd="1";
	private int		resultCode=0;
	
	public String getUserName() {
		return userName;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public int getResultCode() {
		return resultCode;
	}
		
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
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b .put(fixLength(userName,32))
		  .put(fixLength(pwd,32))
		  .putInt(resultCode)
		  .flip();
		return b.array();	
	}
	public  int analyzeBody(byte[] getGetNewUserResBody)
	{	
		if(getGetNewUserResBody.length!=bodyLength)
			return -1;
		
		byte[] body = getGetNewUserResBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		userName	=	getStringByLength(b,32);
		pwd			=	getStringByLength(b,32);
		resultCode	=	b.getInt();
		return 0;
	}
}
