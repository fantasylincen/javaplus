package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class UnionCpDataPushRequest extends eRatingProtocol {
	private final int bodyLength=32+32+4+32+32;
	
	private String 		UnionUserID="test001";
	private String 		UnionUserName="test001";
	private long 		clientIP=0;
	private String 		CpID="test001";
	private String 		Pad="";
	
	
	
	public String getUnionUserID() {
		return UnionUserID;
	}

	public String getUnionUserName() {
		return UnionUserName;
	}

	public long getClientIP() {
		return clientIP;
	}

	public String getCpID() {
		return CpID;
	}
	
	public int setUnionUserID(String unionUserID) {
		if(unionUserID.length()>32)
			return -1;
		UnionUserID = unionUserID;
		return 0;
	}

	public int setUnionUserName(String unionUserName) {
		if(unionUserName.length()>32)
			return -1;
		UnionUserName = unionUserName;
		return 0;
	}

	public int setClientIP(long clientIP) {
		if(clientIP<minUint4Value || clientIP>maxUint4Value)
			return -1;
		this.clientIP = clientIP;
		return 0;
	}

	public int setCpID(String cpID) {
		if(cpID.length()>32)
			return -1;
		CpID = cpID;
		return 0;
	}

	public byte[] getBody()
	{
		IoBuffer b = IoBuffer.allocate(bodyLength);
		
		b.put(fixLength(UnionUserID,32))
		.put(fixLength(UnionUserName,32))
		.putUnsignedInt(clientIP)
		.put(fixLength(CpID,32))
		.put(fixLength(Pad,32))
		.flip();
		
		return b.array();
	}
	
	public  int analyzeBody(byte[] UnionCpDataPushReqBody)
	{	
		if(UnionCpDataPushReqBody.length!=bodyLength)
			return -1;
		
		byte[] body = UnionCpDataPushReqBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		UnionUserID		=	getStringByLength(b,32);
		UnionUserName	=	getStringByLength(b,32);
		clientIP		=	b.getUnsignedInt();
		CpID			=	getStringByLength(b,32);
		Pad				=	getStringByLength(b,32);
		
		return 0;
	}
}
