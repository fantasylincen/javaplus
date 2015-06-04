package cn.mxz.equipment;

import message.S;
import cn.mxz.city.City;
import cn.mxz.protocols.user.equipment.EquipmentP.LogSnatchPro.LogsNubPro;

public class LogsNubBuilder {

	public LogsNubPro build(City city, SnatchLog s) {
		LogsNubPro.Builder b = LogsNubPro.newBuilder();
		b.setDataType(s.getDataType());
		b.setExcelnub(s.getExcelnub());
		b.setId(s.getId());

		boolean win = isWin(city, s);
		b.setIsWin(win);
		b.setLevel(s.getOtherLevel());
		b.setMyNice(s.getMyNice());
		b.setNub(s.getNub());
		b.setRooberNice(s.getOtherNick());
		String roberType = s.getRoberType();

		b.setRooberType(roberType);
		b.setSnatchTime(s.getSnatchTime());
		b.setUserType(s.getUserType());
		b.setWarSituationId(s.getWarsituationId());
		
		b.setIsSnatchSuccess(s.isSuccess());
		
		
		//是否是我抢了别人
		boolean isISnatchOther = s.isQuilt();
		b.setIsQuilt(isISnatchOther);

		boolean isA = isISnatchOther;
		
		if(s.isSuccess()) {
			if(isA) {
				b.setMessageId(S.S50166);
			} else {
				b.setMessageId(S.S50071);
			}
		} else {
			if(isA) {
				b.setMessageId(S.S50167);
			} else {
				b.setMessageId(S.S50072);
			}
		}
//		Debuger.debug("LogsNubBuilder.build()" + b.getMessageId() + ", success:" + b.getIsSnatchSuccess() + ", win:" + win);
		
//		A掠夺B
		
//		if(A 胜利了) {
//			if(掠夺成功) {
//				B 看到50071
//				A 看到50166
//			} else {
//				B 看到50072
//				A 看到50167
//			}
//		}
		
		return b.build();
	}

	private boolean isWin(City city, SnatchLog s) {
		if(s.isQuilt()) {
			return s.isWin();
		}
		return !s.isWin();
	}

	public LogsNubPro buildDev() {
		LogsNubPro.Builder b = LogsNubPro.newBuilder();
		b.setDataType(100001);
		b.setExcelnub(21111);
		b.setMessageId(50071);
		b.setIsSnatchSuccess(true);
		b.setId(1);
		b.setIsQuilt(false);
		b.setMyNice("上王洛");
		b.setNub(2);
		b.setRooberNice("上王洛");
		b.setSnatchTime("2013-1-1");
		b.setRooberType("300006");
		b.setUserType(300006);
		b.setLevel(100);
		b.setWarSituationId(-1);
		b.setIsWin(true);
		return b.build();
//		required int32 id = 9;	//ID
//		required string myNice = 10;    //我的昵称
//		required int32 dataType = 11;  //材料类型
//		required int32 nub = 12;       //材料数量
//		required string rooberNice = 20;//强我材料的人的昵称
//		required int32 excelnub = 30;       //excel静态内容
//		required bool isQuilt= 40; //判断是否是强
//		required string snatchTime = 50;//抢夺时间
//		required string rooberType = 60;//强我材料的人的类型ID
//		required int32 userType = 1;			//角色类型
//		required int32 level = 4;			//玩家等级
//		required int32 warSituationId = 70;			//战况信息ID
//		required bool isWin = 80;					//他是否胜利了
	}

}
