package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;
public class GeneratePassportRequest extends eRatingProtocol {
	private final int bodyLength=4+32;
	
	private long userIP=127L*256L*256L*256L+1L;
	private String userName="test001";
	
	public long getUserIP() {
		return userIP;
	}
	public String getUserName() {
		return userName;
	}
	
	public int setUserIP(long userIP) {
		if(userIP<minUint4Value || userIP>maxUint4Value)
			return -1;
		this.userIP = userIP;
		return 0;
	}
	public int setUserName(String userName) {
		if(userName.length()>32)
			return -1;
		this.userName = userName;
		return 0;
	}
	
	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.putUnsignedInt(userIP)
		 .put(fixLength(userName,32))
		 .flip();		
		return b.array();
	}
	
	public  int analyzeBody(byte[] GeneratePassportReqBody)
	{	
		if(GeneratePassportReqBody.length!=bodyLength)
			return -1;
		
		byte[] body = GeneratePassportReqBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
	
		userIP	=	b.getUnsignedInt();
		userName=	getStringByLength(b,32);
		return 0;
	}

}
