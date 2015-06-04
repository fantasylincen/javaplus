package com.linekong.platform.protocol.erating.eRatingAGIP;
import org.apache.mina.core.buffer.IoBuffer;

public class RoleListRespond extends eRatingProtocol{
	//private final int bodyLength=  4;
	
	private   int resultCode=0;
	private   int roleCount=0;
	
	private   long[] 	gatewayID=null;
	private   long[] 	roleID=null;
	private	  String[] 	roleName=null;
	private   int[] 	roleLevel=null;
	private   short[]   roleGender=null;
	private   short[]   roleOccupation=null;
	private   long[] 	roleCreateTime=null;
	private   int[] 	money1=null;
	private   int[] 	money2=null;
	
	
	public int getResultCode() {
		return resultCode;
	}
	public int getRoleCount() {
		return roleCount;
	}
	public long[] getGatewayID() {
		return gatewayID;
	}
	public long[] getRoleID() {
		return roleID;
	}
	public String[] getRoleName() {
		return roleName;
	}
	public int[] getRoleLevel() {
		return roleLevel;
	}
	public short[] getRoleGender() {
		return roleGender;
	}
	public short[] getRoleOccupation() {
		return roleOccupation;
	}
	public long[] getRoleCreateTime() {
		return roleCreateTime;
	}
	public int[] getMoney1() {
		return money1;
	}
	public int[] getMoney2() {
		return money2;
	}
	
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public void setRoleCount(int roleCount) {
		this.roleCount = roleCount;
	}
	public void setGatewayID(long[] gatewayID) {
		this.gatewayID = gatewayID;
	}
	public void setRoleID(long[] roleID) {
		this.roleID = roleID;
	}
	public void setRoleName(String[] roleName) {
		this.roleName = roleName;
	}
	public void setRoleLevel(int[] roleLevel) {
		this.roleLevel = roleLevel;
	}
	public void setRoleGender(short[] roleGender) {
		this.roleGender = roleGender;
	}
	public void setRoleOccupation(short[] roleOccupation) {
		this.roleOccupation = roleOccupation;
	}
	public void setRoleCreateTime(long[] roleCreateTime) {
		this.roleCreateTime = roleCreateTime;
	}
	public void setMoney1(int[] money1) {
		this.money1 = money1;
	}
	public void setMoney2(int[] money2) {
		this.money2 = money2;
	}
	
	public byte[] getBody()
	{
		if(
				(roleCount != gatewayID.length) || 
				(gatewayID.length!=roleID.length) ||
				(roleID.length != roleName.length) ||
				(roleName.length != roleLevel.length) ||
				(roleLevel.length != roleGender.length) ||
				(roleGender.length != roleOccupation.length) ||
				(roleOccupation.length != roleCreateTime.length) ||
				(roleCreateTime.length != money1.length) ||
				(money1.length != money2.length)
		   )
		{
			return null;
		}
		IoBuffer b = IoBuffer.allocate(4+4+roleCount*(4+4+32+2+1+1+4+4+4));
		
		b .putInt(resultCode)
		  .putInt(roleCount);
		  for(int i=0;i<roleCount;++i)
		  {
			  b.putUnsignedInt(gatewayID[i])
			   .putUnsignedInt(roleID[i])
			   .put( fixLength(roleName[i],32) )
			   .putUnsignedShort(roleLevel[i])
			   .putUnsigned(roleGender[i])
			   .putUnsigned(roleOccupation[i])
			   .putUnsignedInt(roleCreateTime[i])
			   .putInt(money1[i])
			   .putInt(money2[i]);
		  }
		  b.flip();
		  
		return b.array();	
	}
	public  int analyzeBody(byte[] resBody)
	{
		if( (resBody.length-(4+4))%(4+4+32+2+1+1+4+4+4) !=0 )
			return -1;
		byte[] body = resBody;
		IoBuffer b = IoBuffer.allocate(body.length);
		b.put(body);
		b.flip();
				
		resultCode = b.getInt();
		roleCount = b.getInt();
		if(roleCount>0 && resultCode==1)
		{
			gatewayID = new long[roleCount];
			roleID	  = new long[roleCount];
			roleName  = new String[roleCount];
			roleLevel = new int[roleCount];
			roleGender= new short[roleCount];
			roleOccupation = new short[roleCount];
			roleCreateTime = new long[roleCount];
			money1 = new int[roleCount];
			money2 = new int[roleCount];
		
			for(int i=0;i<roleCount;++i)
			{
				gatewayID[i]=b.getUnsignedInt();
				roleID[i]=b.getUnsignedInt();
				roleName[i]=getStringByLength(b,32);
				roleLevel[i]=b.getUnsignedShort();
				roleGender[i]=b.getUnsigned();
				roleOccupation[i]=b.getUnsigned();
				roleCreateTime[i]=b.getUnsignedInt();
				money1[i] = b.getInt();
				money2[i] = b.getInt();
			}
		}
		return 0;
	}
}
