package cn.mxz;import java.util.Map;import cn.javaplus.comunication.ProtocolUser;import cn.javaplus.comunication.Request;import cn.mxz.communication.Action;import com.google.common.collect.Maps;public class ProtocolHandler<U extends ProtocolUser> {	private Map<String, Action> actions = Maps.newConcurrentMap();		public ProtocolHandler() {		actions.put(key("cn.mxz.bossbattle.IBossTransform", "getBossUI1"), new cn.mxz.bossbattle.IBossTransformGetBossUI1Action());
		actions.put(key("cn.mxz.bossbattle.IBossTransform", "getBossUI2"), new cn.mxz.bossbattle.IBossTransformGetBossUI2Action());
		actions.put(key("cn.mxz.bossbattle.IBossTransform", "inspire"), new cn.mxz.bossbattle.IBossTransformInspireAction());
		actions.put(key("cn.mxz.bossbattle.IBossTransform", "rebirth"), new cn.mxz.bossbattle.IBossTransformRebirthAction());
		actions.put(key("cn.mxz.bossbattle.IBossTransform", "joinActivity"), new cn.mxz.bossbattle.IBossTransformJoinActivityAction());
		actions.put(key("cn.mxz.bossbattle.IBossTransform", "getRankList"), new cn.mxz.bossbattle.IBossTransformGetRankListAction());
		actions.put(key("cn.mxz.bossbattle.IBossTransform", "getAward"), new cn.mxz.bossbattle.IBossTransformGetAwardAction());

		actions.put(key("cn.mxz.chat.ChatTransform", "openMessage"), new cn.mxz.chat.ChatTransformOpenMessageAction());

		actions.put(key("cn.mxz.chuangzhen.ChuangZhenTransform", "getData"), new cn.mxz.chuangzhen.ChuangZhenTransformGetDataAction());
		actions.put(key("cn.mxz.chuangzhen.ChuangZhenTransform", "getRankingList"), new cn.mxz.chuangzhen.ChuangZhenTransformGetRankingListAction());
		actions.put(key("cn.mxz.chuangzhen.ChuangZhenTransform", "getBattleReward"), new cn.mxz.chuangzhen.ChuangZhenTransformGetBattleRewardAction());
		actions.put(key("cn.mxz.chuangzhen.ChuangZhenTransform", "select"), new cn.mxz.chuangzhen.ChuangZhenTransformSelectAction());
		actions.put(key("cn.mxz.chuangzhen.ChuangZhenTransform", "receive"), new cn.mxz.chuangzhen.ChuangZhenTransformReceiveAction());
		actions.put(key("cn.mxz.chuangzhen.ChuangZhenTransform", "getHeads"), new cn.mxz.chuangzhen.ChuangZhenTransformGetHeadsAction());

		actions.put(key("cn.mxz.dogz.DogzTransform", "getDogzOpenUI"), new cn.mxz.dogz.DogzTransformGetDogzOpenUIAction());
		actions.put(key("cn.mxz.dogz.DogzTransform", "open"), new cn.mxz.dogz.DogzTransformOpenAction());
		actions.put(key("cn.mxz.dogz.DogzTransform", "setFighting"), new cn.mxz.dogz.DogzTransformSetFightingAction());
		actions.put(key("cn.mxz.dogz.DogzTransform", "zhuHun"), new cn.mxz.dogz.DogzTransformZhuHunAction());

		actions.put(key("cn.mxz.equipment.EquipmentTransform", "hasEquipments"), new cn.mxz.equipment.EquipmentTransformHasEquipmentsAction());

		actions.put(key("cn.mxz.fengshentai.IFengshentaiService", "getdata"), new cn.mxz.fengshentai.IFengshentaiServiceGetdataAction());
		actions.put(key("cn.mxz.fengshentai.IFengshentaiService", "exchange"), new cn.mxz.fengshentai.IFengshentaiServiceExchangeAction());
		actions.put(key("cn.mxz.fengshentai.IFengshentaiService", "fengshen"), new cn.mxz.fengshentai.IFengshentaiServiceFengshenAction());
		actions.put(key("cn.mxz.fengshentai.IFengshentaiService", "getExchangePropUI"), new cn.mxz.fengshentai.IFengshentaiServiceGetExchangePropUIAction());
		actions.put(key("cn.mxz.fengshentai.IFengshentaiService", "buy"), new cn.mxz.fengshentai.IFengshentaiServiceBuyAction());

		actions.put(key("cn.mxz.heishi.HeiShiTransform", "getUI"), new cn.mxz.heishi.HeiShiTransformGetUIAction());
		actions.put(key("cn.mxz.heishi.HeiShiTransform", "refresh"), new cn.mxz.heishi.HeiShiTransformRefreshAction());
		actions.put(key("cn.mxz.heishi.HeiShiTransform", "exchange"), new cn.mxz.heishi.HeiShiTransformExchangeAction());

		actions.put(key("cn.mxz.keyvalue.KeyValueTransform", "put"), new cn.mxz.keyvalue.KeyValueTransformPutAction());
		actions.put(key("cn.mxz.keyvalue.KeyValueTransform", "getValue"), new cn.mxz.keyvalue.KeyValueTransformGetValueAction());

		actions.put(key("cn.mxz.mission.MissionTransform", "getCompleteness"), new cn.mxz.mission.MissionTransformGetCompletenessAction());
		actions.put(key("cn.mxz.mission.MissionTransform", "receiveBox"), new cn.mxz.mission.MissionTransformReceiveBoxAction());

		actions.put(key("cn.mxz.openserver.OpenServerTransform", "getOpenServerUI"), new cn.mxz.openserver.OpenServerTransformGetOpenServerUIAction());
		actions.put(key("cn.mxz.openserver.OpenServerTransform", "receive"), new cn.mxz.openserver.OpenServerTransformReceiveAction());

		actions.put(key("cn.mxz.packetlog.PacketLogTransform", "add"), new cn.mxz.packetlog.PacketLogTransformAddAction());

		actions.put(key("cn.mxz.prizecenter.IPrizeCenterTransform", "getData"), new cn.mxz.prizecenter.IPrizeCenterTransformGetDataAction());
		actions.put(key("cn.mxz.prizecenter.IPrizeCenterTransform", "getPrizeFromLinekong"), new cn.mxz.prizecenter.IPrizeCenterTransformGetPrizeFromLinekongAction());
		actions.put(key("cn.mxz.prizecenter.IPrizeCenterTransform", "getPrize"), new cn.mxz.prizecenter.IPrizeCenterTransformGetPrizeAction());

		actions.put(key("cn.mxz.xianshi.XianShiTransform", "getWanLiGold"), new cn.mxz.xianshi.XianShiTransformGetWanLiGoldAction());

		actions.put(key("cn.mxz.yoxi.YoXiTransform", "yoXi"), new cn.mxz.yoxi.YoXiTransformYoXiAction());

	}	public void onData(U user, Request rq) throws Throwable {		String className = rq.getClassName();		String methodName = rq.getMethodName();		Action a = getAction(className, methodName);		a.excuteBefore(user, rq.getArgsArray());		try {			a.excute(user, rq.getArgsArray());			a.excuteAfter(user, rq.getArgsArray());		} catch (Throwable e) {			a.excuteException(user, rq.getArgsArray());			throw e;		} finally {			a.excuteFinally(user, rq.getArgsArray());		}	}		private Action getAction(String className, String methodName) {		return actions.get(key(className, methodName));	}	private String key(String className, String methodName) {		return className + ":" + methodName;	}}