package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;
public class Agip2Header extends eRatingProtocol {
	private final int bodyLength=2+1+1+4+4+4+4;
	private int 	total_length=0;
	private short  	version=0x20;
	private short	remain_packages=0;
	private long 	command_ID=0x10001001;
	private long 	sequence_ID=0;
	private long	game_id=0;
	private long	gateway_ID=0;
	
	
	
	public int setTotal_length(int totalLength) {
		if(totalLength<minUint2Value || totalLength>maxUint2Value)
			return -1;
		this.total_length = totalLength;
		return 0;
	}
	public int setVersion(short version) {
		if(version<minUint1Value || version>maxUint1Value)
			return -1;
		this.version = version;
		return 0;
	}
	public int setRemain_packages(short remainPackages) {
		if(remainPackages<minUint1Value || remainPackages>maxUint1Value)
			return -1;
		this.remain_packages = remainPackages;
		return 0;
	}
	public int setCommand_ID(long commandID) {
		if(commandID<minUint4Value || commandID>maxUint4Value)
			return -1;
		this.command_ID = commandID;
		return 0;
	}
	public int setSequence_ID(long sequenceID) {
		if(sequenceID<minUint4Value || sequenceID>maxUint4Value)
			return -1;
		this.sequence_ID = sequenceID;
		return 0;
	}
	public int setGame_id(long gameId) {
		if(gameId<minUint4Value || gameId>maxUint4Value)
			return -1;
		this.game_id = gameId;
		return 0;
	}
	public int setGateway_ID(long gatewayID) {
		if(gatewayID<minUint4Value || gatewayID>maxUint4Value)
			return -1;
		this.gateway_ID = gatewayID;
		return 0;
	}
	
	
	public int getTotal_length() {
		return total_length;
	}
	public short getVersion() {
		return version;
	}
	public short getRemain_packages() {
		return remain_packages;
	}
	public long getCommand_ID() {
		return command_ID;
	}
	public long getSequence_ID() {
		return sequence_ID;
	}
	public long getGame_id() {
		return game_id;
	}
	public long getGateway_ID() {
		return gateway_ID;
	}
	public  byte[] getBody()
	{
		IoBuffer b;
		
		b = IoBuffer.allocate(bodyLength);
		b.putUnsignedShort(total_length)
		 .putUnsigned(version)
		 .putUnsigned(remain_packages)
		 .putUnsignedInt(command_ID)
		 .putUnsignedInt(sequence_ID)
		 .putUnsignedInt(game_id)
		 .putUnsignedInt(gateway_ID)
		 .flip();
	
		return b.array();
	}
	
	public  int analyzeBody(byte[] Agip2HeaderBody)
	{
		byte[] body = Agip2HeaderBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
	
		if(body.length !=bodyLength)
		{
			return -1;
		}
		total_length	=	b.getUnsignedShort();
		version			=	b.getUnsigned();
		remain_packages = 	b.getUnsigned();
		
		command_ID		=	b.getUnsignedInt();
		sequence_ID		=	b.getUnsignedInt();
		game_id			=	b.getUnsignedInt();
		gateway_ID		=	b.getUnsignedInt();
	
		return 0;
	}
	
}
