package com.linekong.platform.protocol.erating.server.packets;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;

import org.apache.mina.core.buffer.IoBuffer;
import org.xsocket.connection.INonBlockingConnection;

import cn.mxz.base.config.ServerConfig;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.protocols.user.UserP.UserPro;
import cn.mxz.thirdpaty.ThirdPartyPlatform;
import cn.mxz.thirdpaty.ThirdPartyPlatformFactory;
import cn.mxz.user.builder.UserBuilder;
import cn.mxz.util.debuger.Debuger;

import com.linekong.platform.protocol.erating.ErrorCode;
import com.linekong.platform.protocol.erating.define.D;
import com.linekong.platform.protocol.erating.eRatingAGIP.Agip2Header;
import com.linekong.platform.protocol.erating.eRatingAGIP.UserChaegeRequest;
import com.linekong.platform.protocol.erating.eRatingAGIP.UserChargeRespond;
import com.linekong.platform.protocol.erating.eRatingAGIP.eRatingProtocol;
import com.linekong.platform.protocol.erating.server.RechargeLogic;

import db.GameDB;
import db.dao.impl.RechargeRecordDao;
import db.dao.impl.RechargeRecordDao1;
import db.domain.RechargeRecord;

public class RechargePacket {

	public static void run(ByteBuffer data, INonBlockingConnection con,long sequenceId, long gameId) throws BufferOverflowException, IOException {
		UserChaegeRequest userChargeReq = new UserChaegeRequest();
		
		
		
//		System.out.println("UserChaegeRequestBody is " + data.array().length + " byte");
		int resAnalyze = userChargeReq.analyzeBody(data.array());
		if (resAnalyze < 0) {
			Debuger.debug("UserChaegeRequestAnalyzeError  充值包解析失败");
			return;

		}
		int ret = 0;
		ThirdPartyPlatform c = ThirdPartyPlatformFactory.getThirdPartyPlatform();
		String roleId = c.getRoleId( userChargeReq.getUserID() + "", gameId );
		Debuger.debug("充值包中的充值userID: " + userChargeReq.getUserID() + " roleId :" + roleId + " gameId:" + gameId );
		City user = CityFactory.getCity(roleId);
		if (user != null) {
			ret = process(userChargeReq, user); 
		}
		else{
			Debuger.debug("充值包中的充值userID: " + userChargeReq.getUserID()  + " 未找到相应的用户");
			ret = ErrorCode.E_ACCOUNT_NOT_FOUND;
		}
		
		 
		con.write(getResponse(ret, userChargeReq,sequenceId, user,gameId ));
	}

	private static int process(UserChaegeRequest userChargeReq, City user) {
//		userChargeReq.getUserID();
		int amount = userChargeReq.getAmount();
		//System.out.println( user.getId() + " 充值元宝 " + amount);
//		System.out.println("getDetailID=" + userChargeReq.getDetailID());
//		System.out.println("getRatingID=" + userChargeReq.getRatingID());
//		System.out.println("getSubjectID=" + userChargeReq.getSubjectID());
//		System.out.println("getAuditFlag=" + userChargeReq.getAuditFlag());
//		System.out.println("getAmount=" + amount);
//		System.out.println("getChargeTime=" + userChargeReq.getChargeTime());

//		if( userChargeReq.getAuditFlag() != 2 ){//目前仅处理为此选项为2的情况
//			return ErrorCode.E_ERROR;
//		}

		

		
		

		BigInteger detailID = userChargeReq.getDetailID();// 充值流水号

		if (checkDuplicate(detailID)) {
			Debuger.debug("充值包订单号 " + detailID  + "重复");
			return ErrorCode.E_CHARGE_DUPLICATE;
		}

		RechargeLogic rechargeLogic = new RechargeLogic(user );
		rechargeLogic.recharge(amount);//充值元宝数

		update(user);//林岑 更新用户变量 2014年8月6日 16:00:16
		
		saveDB( userChargeReq, user.getId() );
		return ErrorCode.S_SUCCESS;

	}
	
	//林岑 更新用户变量 2014年8月6日 16:00:16
	private static void update(City city) {
		if(city == null) {
			return;
		}
		UserBuilder bd = new UserBuilder();
		UserPro d = bd.build(city);
		MessageFactory.getUser().onUpdateUserList(city.getSocket(), d);
	}


	/**
	 * 写入我们的数据库
	 * @param userChargeReq
	 * @param roleId
	 */
	private static void saveDB(UserChaegeRequest userChargeReq, String roleId) {

		RechargeRecord o = getDao().createDTO();
		o.setAmount(userChargeReq.getAmount());
		o.setCreatetime( (int) userChargeReq.getChargeTime() );
		o.setIds( userChargeReq.getDetailID().longValue() );
		o.setUname( roleId );

		getDao().add(o);
	}

	/**
	 * 订单号是否重叠
	 *
	 * @return
	 */
	private static boolean checkDuplicate(BigInteger detailID) {
		if( getDao().get( detailID.longValue() ) != null ){
			return true;
		}
		return false;
	}

	private static RechargeRecordDao getDao() {
		return new RechargeRecordDao1(GameDB.getInstance());
	}

	private static byte[] getResponse(int ret, UserChaegeRequest request, long sequenceId, City user, long gameId) {
		short resv = 0;
		UserChargeRespond respond = new UserChargeRespond();

		respond.setResultCode(ret);
		short auditFlag = request.getAuditFlag();
		respond.setAuditFlag(auditFlag);
		respond.setDetailID(request.getDetailID());
		respond.setRatingID(request.getRatingID());
		respond.setSubjectID(request.getSubjectID());
//		if (auditFlag == 1) {
//			City user = CityFactory.getCity(request.getUserID() + "");
//			respond.setTotalAmount(user.getUserCounterHistory().get(CounterKey.TOTAL_RECHARGE_GOLD_COUNT));
//		}
		respond.setUserID(request.getUserID());

		long request_commandId = 0x20003401L;

		// **************************************************every times need to
		// rewrite if you want to test a new protocol************
		byte[] userChargeResBody = respond.getBody();

		//System.out.println("userChargeResBody.length=" + userChargeResBody.length);
		int request_totalLength = 20 + userChargeResBody.length + 4;
		IoBuffer userChargeRes_data = IoBuffer.allocate(request_totalLength);
		IoBuffer requestHead = buildHeader(request_commandId, request_totalLength, sequenceId, gameId);

		userChargeRes_data.put(requestHead);
		userChargeRes_data.put(userChargeResBody);
		userChargeRes_data.putShort(resv);// resv
		int request_checkSum = eRatingProtocol.crc16(userChargeRes_data.array(), request_totalLength - 2);
		userChargeRes_data.putUnsignedShort(request_checkSum);// checkSum
		userChargeRes_data.flip();
		return userChargeRes_data.array();
	}

	private static IoBuffer buildHeader(long request_commandId, int request_totalLength, long sequenceId, long gameId) {
		IoBuffer head = IoBuffer.allocate(20);

		head.putUnsignedShort(request_totalLength);
		head.putUnsigned(D.VERSION);// version
		head.putUnsigned(D.remain_packages);// remainPackage
		head.putUnsignedInt(request_commandId);// commandId
		head.putUnsignedInt(sequenceId);// sequenceId
		head.putUnsignedInt(gameId);// gameId
		head.putUnsignedInt(ServerConfig.getServerId());// gatewayId
		head.flip();
		return head;
	}

}
