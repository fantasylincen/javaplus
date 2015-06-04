package com.linekong.platform.protocol.erating.eRatingAGIP;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.mina.core.buffer.IoBuffer;
public class PDUMessage {
	public final static short CURRENT_VERSION = 0x20;
	public static final int ERATING_PDU_HEAD_LEN = 20;
	public static final int ERATING_PDU_VERIFY = 4;
	public static final int MAX_DATA_LEN = 1000;
	public static AtomicLong sequence = new AtomicLong(1);

	public static final long CMD_BIND = 0x10000001;
	public static final long CMD_UNBIND = 0x10000002;
	public static final long CMD_GW_DATA_REPORT = 0x10002003;
	public static final long CMD_RENAME_USER = 0x10003007;
	public static final long CMD_CREATE_ROLE = 0x10003102;
	public static final long CMD_CREATE_ROLE_WITH_ID = 0x10003108;
	public static final long CMD_DELETE_ROLE = 0x10003103;
	public static final long CMD_ROLE_ENTER_GAME_EX = 0x10003106;
	public static final long CMD_ROLE_ENTER_GAME_EX2 = 0x10003110;
	public static final long CMD_ROLE_ENTER_GAME_EX3 = 0x10003111;
	public static final long CMD_RENAME_ROLE = 0x10003107;
	public static final long CMD_USER_ITEM_MINUS = 0x10003505;
	public static final long CMD_USER_ITEM_MINUS_EX = 0x10003506;
	public static final long CMD_ACTIVITY_ITEM_QUERY = 0x10003509;
	public static final long CMD_ACTIVITY_ITEM_QUERY_EX = 0x10003512;
	public static final long CMD_USER_AUTH_EX = 0x10003306;
	public static final long CMD_USER_AUTH_EX3 = 0x10003309;
	public static final long CMD_JOINT_AUTHEN = 0x10003801;
	public static final long CMD_JOINT_AUTHEN_EX = 0x10003802;
	public static final long CMD_USER_LOGOUT = 0x10003303;
	public static final long CMD_RECORD_PLUGIN_INFO = 0x10003310;

	public static final long CMD_ROLE_STATE_QUERY = 0x10003104;
	public static final long CMD_USER_IB_PAY_REQ = 0x10003701;
	public static final long CMD_USER_IB_PAY_EX_REQ = 0x10003712;
	public static final long CMD_GET_NEW_USER_ID = 0x10003008;
	public static final long CMD_USER_REGIST = 0x10003005;
	public static final long CMD_USER_REGIST_WITH_ID = 0x10003009;
	public static final long CMD_USER_CHANGE_PWD = 0x10003010;
	public static final long CMD_CREATE_GROUP = 0x10003901;
	public static final long CMD_DELETE_GROUP = 0x10003902;
	public static final long CMD_TRANSFER_GROUP = 0x10003903;
	public static final long CMD_USER_BALANCE_INFO_EX = 0x10003408;
	public static final long CMD_ROLE_LIST = 0x10003101;

	public static final long CMD_BIND_RES = 0x20000001;
	public static final long CMD_UNBIND_RES = 0x20000002; 
	public static final long CMD_GW_DATA_REPORT_RES = 0x20002003;
	public static final long CMD_RENAME_USER_RES = 0x20003007;
	public static final long CMD_CREATE_ROLE_RES = 0x20003102;
	public static final long CMD_CREATE_ROLE_WITH_ID_RES = 0x20003108;
	public static final long CMD_DELETE_ROLE_RES = 0x20003103;
	public static final long CMD_ROLE_ENTER_GAME_EX_RES = 0x20003106;
	public static final long CMD_ROLE_ENTER_GAME_EX2_RES = 0x20003110;
	public static final long CMD_RENAME_ROLE_RES = 0x20003107;
	public static final long CMD_USER_ITEM_MINUS_RES = 0x20003505;
	public static final long CMD_USER_ITEM_MINUS_EX_RES = 0x20003506;
	public static final long CMD_ACTIVITY_ITEM_QUERY_RES = 0x20003509;
	public static final long CMD_ACTIVITY_ITEM_QUERY_EX_RES = 0x20003512;
	public static final long CMD_USER_AUTH_EX_RES = 0x20003306;
	public static final long CMD_USER_AUTH_EX3_RES = 0x20003309;
	public static final long CMD_RECORD_PLUGIN_INFO_RES = 0x20003310;
	public static final long CMD_JOINT_AUTHEN_RES = 0x20003801;
	public static final long CMD_JOINT_AUTHEN_EX_RES = 0x20003802;
	public static final long CMD_USER_LOGOUT_RES = 0x20003303;
	public static final long CMD_ROLE_STATE_QUERY_RES = 0x20003104;
	public static final long CMD_USER_IB_PAY_RES = 0x20003701;
	public static final long CMD_GET_NEW_USER_ID_RES = 0x20003008;
	public static final long CMD_USER_REGIST_WITH_ID_RES = 0x20003009;
	public static final long CMD_USER_CHANGE_PWD_RES = 0x20003010;
	public static final long CMD_USER_REGIST_RES = 0x20003005;
	public static final long CMD_CREATE_GROUP_RES = 0x20003901;
	public static final long CMD_DELETE_GROUP_RES = 0x20003902;
	public static final long CMD_TRANSFER_GROUP_RES = 0x20003903;
	public static final long CMD_USER_BALANCE_INFO_EX_RES = 0x20003408;
	public static final long CMD_ROLE_LIST_RES = 0x20003101;
	
	public int totalLength;
	public short version;
	public short remainPackages;
	public long commandId;
	public long sequenceId;
	public long gameId;
	public long gatewayId;
	public short resv;
	public int checkSum;
	public byte[] body;
	
	public int count;
	public long userId;
	public long roleId;
	public long sessionId;
	public Object data;
	@Override
	public String toString() {
		return String.format("totalLength=%s|version=%s|remainPackage=%s|commandId=%s|sequenceId=%s|gameId=%s|gatewayId=%s|resv=%s|checkSum=%s", 
				totalLength,version,remainPackages,commandId,sequenceId,gameId,gatewayId,resv,checkSum);
	}
	
	public static PDUMessage createMessage(long commandId, long gameId, long gatewayId) {
		PDUMessage message = new PDUMessage();
		message.commandId = commandId;
		message.gameId = gameId;
		message.gatewayId = gatewayId;
		message.version = CURRENT_VERSION;
		message.sequenceId = sequence.getAndIncrement();
		return message;
	}
	
	private IoBuffer toData0(byte[] byteData, int remainPackage) {
		
		totalLength = ERATING_PDU_HEAD_LEN + ERATING_PDU_VERIFY + byteData.length;
		
		IoBuffer data = IoBuffer.allocate(totalLength);
		
		//Total_Length
		data.putUnsignedShort(totalLength);
		
		//Version
		data.putUnsigned(CURRENT_VERSION);
		
		//Remain_Packages
		data.putUnsigned(remainPackage);
		
		//Command_ID
		data.putUnsignedInt(commandId);
		
		//Sequence_ID
		data.putUnsignedInt(sequenceId);
		
		//Game_ID
		data.putUnsignedInt(gameId);
		
		//Gateway_ID
		data.putUnsignedInt(gatewayId);
		
		//data
		data.put(byteData);
		
		//Resv
		data.putShort(resv);

		//Checksum
		data.putUnsignedShort(checkSum);
		
		data.flip();
		return data;
	}
	
	public IoBuffer[] toData() {
		if (body == null) {
			body = new byte[0];
		}
		
		
		int packageCount = body.length / MAX_DATA_LEN;
		if (body.length % MAX_DATA_LEN > 0) {
			packageCount++;
		}
		
		if (packageCount == 0) {
			packageCount++;
		}
		
		IoBuffer[] result = new IoBuffer[packageCount];
		
		for (int i = 0;i < packageCount;i++) {
			byte[] tmp = null;
			if (i == packageCount - 1) {
				tmp = Arrays.copyOfRange(body, i * MAX_DATA_LEN, body.length);
			} else {
				tmp = Arrays.copyOfRange(body, i * MAX_DATA_LEN, (i + 1) * MAX_DATA_LEN);
			}
			
			result[i] = toData0(tmp, packageCount - i - 1);
		}
		
		return result;
	}

}
