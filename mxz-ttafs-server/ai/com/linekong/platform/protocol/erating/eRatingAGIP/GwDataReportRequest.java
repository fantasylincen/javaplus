package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class GwDataReportRequest extends eRatingProtocol{
	
	//private final int bodyLengthVersion2=  4+4+DataCount*(4+4);
	private long 	serverID=100;
	private long 	dataCount=100;
	private int[] 	dataType=null;
	private int[] 	dataValue=null;
	
	public long getServerID() {
		return serverID;
	}
	public long getDataCount() {
		return dataCount;
	}
	public int[] getDataType() {
		return dataType;
	}
	public int[] getDataValue() {
		return dataValue;
	}
	
	public int setServerID(long serverID) {
		if(serverID<minUint4Value || serverID>maxUint4Value)
			return -1;
		this.serverID = serverID;
		return 0;
	}
	public int setDataCount(long dataCount) {
		if( dataCount<minUint4Value || dataCount>maxUint4Value)
			return -1;
		this.dataCount = dataCount;
		return 0;
	}
	public void setDataType(int[] dataType) {
		this.dataType = dataType;
	}
	public void setDataValue(int[] dataValue) {
		this.dataValue = dataValue;
	}
	
	public byte[] getBody()
	{
		if(
				(dataCount != dataType.length) || 
				(dataType.length!=dataValue.length)
		   )
		{
			return null;
		}
		IoBuffer b = IoBuffer.allocate( (int)(4+4+(4+4)*dataCount));
		
		b .putUnsignedInt(serverID)
		  .putUnsignedInt(dataCount);
		  for(int i=0;i<dataCount;++i)
		  {
			  b.putInt(dataType[i])
			   .putInt(dataValue[i]);
		  }
		  b.flip();
		  
		return b.array();	
	}
	public  int analyzeBody(byte[] GwDataReportReqBody)
	{	
		if( (GwDataReportReqBody.length-(4+4))%(4+4) !=0 )
			return -1;
		
		byte[] body = GwDataReportReqBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
		
		serverID		=	b.getInt();
		dataCount		=	b.getInt();
		if(dataCount>0)
		{
			dataType	=	new int[(int)dataCount];
			dataValue	=	new	int[(int)dataCount];
		}
		for(int i=0;i<dataCount;++i)
		{
			dataType[i]	=	b.getInt();
			dataValue[i]	=	b.getInt();			
		}
		
		return 0;
	}
}
