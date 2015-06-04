package cn.mxz;

import cn.javaplus.comunication.ProtocolUser;
import cn.javaplus.comunication.Responser;
import cn.mxz.bossbattle.IBossUI1;
import cn.mxz.bossbattle.IBossUI2;
import cn.mxz.bossbattle.ISubBossUI2;
import cn.mxz.bossbattle.IRankInfoWithMyself;
import cn.mxz.bossbattle.IAwardInfo;
import cn.mxz.chat.ChatUI;
import cn.mxz.chengzhang.ChengZhangUI;
import cn.mxz.chuangzhen.ChuangZhenPro;
import cn.mxz.chuangzhen.RankingListPro;
import cn.mxz.chuangzhen.ChuangZhenReward;
import cn.mxz.chuangzhen.ChuangZhenHeads;
import cn.mxz.czfk.FeedBackUI;
import cn.mxz.czfk2.FeedBack2UI;
import cn.mxz.dogz.DogzOpenUI;
import cn.mxz.equipment.EquipmentHasUI;
import cn.mxz.fengshentai.IFengshenMainUI;
import cn.mxz.fengshentai.IExchangePropUI;
import cn.mxz.fengshentai.IPropTransform;
import cn.mxz.heishi.HeiShiUI;
import cn.mxz.heishi.HeiShiSingleUI;
import cn.mxz.hhdlb.HhdlbUI;
import cn.mxz.invite.InviteUI;
import cn.mxz.invite.CodeUI;
import cn.mxz.invite.CommitResult;
import cn.mxz.keyvalue.Value;
import cn.mxz.mission.MissionCompletenessUI;
import cn.mxz.nvwa.NvwaUI;
import cn.mxz.onlinereward.OnlineRewardUI;
import cn.mxz.openserver.OpenServerUI;
import cn.mxz.prizecenter.IPrizeCenterUI;
import cn.mxz.pvp.PvpDuiHuanUI;
import cn.mxz.pvp.PvpDuiHuanResultPro;
import cn.mxz.xianshi.WanLiGold;
import cn.mxz.yoxi.YoXi;
import cn.mxz.zbsr.ZbsrUI;

public class Responses {

	private Responser	responser;

	public Responses(ProtocolUser user) {
		responser = new Responser(user);
	}

	public IBossTransform getIBossTransform() {
	 	return new IBossTransform();
	}

	public ChatTransform getChatTransform() {
	 	return new ChatTransform();
	}

	public ChengZhangTransform getChengZhangTransform() {
	 	return new ChengZhangTransform();
	}

	public ChuangZhenTransform getChuangZhenTransform() {
	 	return new ChuangZhenTransform();
	}

	public FeedBackTransform getFeedBackTransform() {
	 	return new FeedBackTransform();
	}

	public FeedBack2Transform getFeedBack2Transform() {
	 	return new FeedBack2Transform();
	}

	public DogzTransform getDogzTransform() {
	 	return new DogzTransform();
	}

	public EquipmentTransform getEquipmentTransform() {
	 	return new EquipmentTransform();
	}

	public IFengshentaiService getIFengshentaiService() {
	 	return new IFengshentaiService();
	}

	public HeiShiTransform getHeiShiTransform() {
	 	return new HeiShiTransform();
	}

	public HhdlbTransform getHhdlbTransform() {
	 	return new HhdlbTransform();
	}

	public InviteTransform getInviteTransform() {
	 	return new InviteTransform();
	}

	public KeyValueTransform getKeyValueTransform() {
	 	return new KeyValueTransform();
	}

	public MissionTransform getMissionTransform() {
	 	return new MissionTransform();
	}

	public NvwaTransform getNvwaTransform() {
	 	return new NvwaTransform();
	}

	public OnlineRewardTransform getOnlineRewardTransform() {
	 	return new OnlineRewardTransform();
	}

	public OpenServerTransform getOpenServerTransform() {
	 	return new OpenServerTransform();
	}

	public PacketLogTransform getPacketLogTransform() {
	 	return new PacketLogTransform();
	}

	public IPrizeCenterTransform getIPrizeCenterTransform() {
	 	return new IPrizeCenterTransform();
	}

	public PvpTransform getPvpTransform() {
	 	return new PvpTransform();
	}

	public XianShiTransform getXianShiTransform() {
	 	return new XianShiTransform();
	}

	public YoXiTransform getYoXiTransform() {
	 	return new YoXiTransform();
	}

	public ZbsrTransform getZbsrTransform() {
	 	return new ZbsrTransform();
	}


	public class IBossTransform {

		public void responseGetBossUI1 (IBossUI1 o) {
			responser.response("cn.mxz.bossbattle.IBossTransform", "getBossUI1", o);
		}

		public void responseGetBossUI2 (IBossUI2 o) {
			responser.response("cn.mxz.bossbattle.IBossTransform", "getBossUI2", o);
		}
/**
	 * 战斗
	 * @return
	 */
//WarSituation doBattle( boolean isRebirth );  
/**
	 * 鼓舞士气
	 * @param isMax		是否极限鼓舞
	 * @return 
	 */

		public void responseInspire (ISubBossUI2 o) {
			responser.response("cn.mxz.bossbattle.IBossTransform", "inspire", o);
		}
/**
	 * 重生
	 * @return 
	 */

		public void responseRebirth (ISubBossUI2 o) {
			responser.response("cn.mxz.bossbattle.IBossTransform", "rebirth", o);
		}
/**
	 * 报名参加到活动
	 */

		public void responseJoinActivity () {
			responser.response("cn.mxz.bossbattle.IBossTransform", "joinActivity");
		}
/**
	 * 神符不足，强制购买并加入到活动
	 */

		public void responseForceJoinActivity () {
			responser.response("cn.mxz.bossbattle.IBossTransform", "forceJoinActivity");
		}
/**
	 * 当前排名列表
	 * @return
	 */

		public void responseGetRankList (IRankInfoWithMyself o) {
			responser.response("cn.mxz.bossbattle.IBossTransform", "getRankList", o);
		}
/**
	 * 打开奖励面板
	 * @return
	 */

		public void responseGetAward (IAwardInfo o) {
			responser.response("cn.mxz.bossbattle.IBossTransform", "getAward", o);
		}

		public void responseSetUser () {
			responser.response("cn.mxz.bossbattle.IBossTransform", "setUser");
		}
	}


	public class ChatTransform {
/**
	 * 打开聊天消息
	 * 
	 * @param type
	 *            0:世界 1:联盟 2:私聊 3: 客服
	 */

		public void responseOpenMessage (ChatUI o) {
			responser.response("cn.mxz.chat.ChatTransform", "openMessage", o);
		}
/**
	 * 关闭消息提示
	 * 
	 * @param type
	 *            0:世界 1:联盟 2:私聊 3: 客服
	 */

		public void responseCloseTips () {
			responser.response("cn.mxz.chat.ChatTransform", "closeTips");
		}
/**
	 * 打开你跟某人的私聊消息
	 * @param userId
	 * @return
	 */

		public void responseOpenPrivateMessage (ChatUI o) {
			responser.response("cn.mxz.chat.ChatTransform", "openPrivateMessage", o);
		}

		public void responseSetUser () {
			responser.response("cn.mxz.chat.ChatTransform", "setUser");
		}
	}

/**
 * 成长计划
 * @author 林岑
 */

	public class ChengZhangTransform {
/**
	 * 获取成长计划界面数据
	 */

		public void responseGetData (ChengZhangUI o) {
			responser.response("cn.mxz.chengzhang.ChengZhangTransform", "getData", o);
		}
/**
	 * 购买成长计划特权
	 */

		public void responseBuy (ChengZhangUI o) {
			responser.response("cn.mxz.chengzhang.ChengZhangTransform", "buy", o);
		}
/**
	 * 领取成长计划奖励
	 * @param id
	 */

		public void responseReceive (ChengZhangUI o) {
			responser.response("cn.mxz.chengzhang.ChengZhangTransform", "receive", o);
		}

		public void responseSetUser () {
			responser.response("cn.mxz.chengzhang.ChengZhangTransform", "setUser");
		}
	}


	public class ChuangZhenTransform {
/**
	 * 闯阵 第一个界面
	 * @return
	 */

		public void responseGetData (ChuangZhenPro o) {
			responser.response("cn.mxz.chuangzhen.ChuangZhenTransform", "getData", o);
		}
/**
	 * @return	排行榜信息
	 * @param count 上阵人数: 三人阵 四人阵 五人阵
	 */

		public void responseGetRankingList (RankingListPro o) {
			responser.response("cn.mxz.chuangzhen.ChuangZhenTransform", "getRankingList", o);
		}
/**
	 * 渡劫奖励奖励(包含了 "渡劫奖励" 和 "加成属性" 的数据)
	 * @return
	 */

		public void responseGetBattleReward (ChuangZhenReward o) {
			responser.response("cn.mxz.chuangzhen.ChuangZhenTransform", "getBattleReward", o);
		}
/**
	 * @param index	选择一种加成 0 选第一个  1 选第二个 2 选第三个
	 */

		public void responseSelect () {
			responser.response("cn.mxz.chuangzhen.ChuangZhenTransform", "select");
		}
/**
	 * 领取渡劫奖励
	 */

		public void responseReceive () {
			responser.response("cn.mxz.chuangzhen.ChuangZhenTransform", "receive");
		}
/**
	 * 闯阵头像列表
	 * @return
	 */

		public void responseGetHeads (ChuangZhenHeads o) {
			responser.response("cn.mxz.chuangzhen.ChuangZhenTransform", "getHeads", o);
		}
/**
	 * 下次再说
	 */

		public void responseSkip () {
			responser.response("cn.mxz.chuangzhen.ChuangZhenTransform", "skip");
		}

		public void responseSetUser () {
			responser.response("cn.mxz.chuangzhen.ChuangZhenTransform", "setUser");
		}
	}

/**
 * 充值反馈
 * @author 林岑
 *
 */

	public class FeedBackTransform {
/**
	 * 充值反馈界面
	 * @return
	 */

		public void responseGetUI (FeedBackUI o) {
			responser.response("cn.mxz.czfk.FeedBackTransform", "getUI", o);
		}
/**
	 * 领取某个礼包
	 * @param id   excel id
	 * @return
	 */

		public void responseReceiveById (FeedBackUI o) {
			responser.response("cn.mxz.czfk.FeedBackTransform", "receiveById", o);
		}

		public void responseSetUser () {
			responser.response("cn.mxz.czfk.FeedBackTransform", "setUser");
		}
	}

/**
 * 充值反馈2
 * @author 林岑
 *
 */

	public class FeedBack2Transform {
/**
	 * 充值反馈2界面
	 * @return
	 */

		public void responseGetUI (FeedBack2UI o) {
			responser.response("cn.mxz.czfk2.FeedBack2Transform", "getUI", o);
		}
/**
	 * 领取某个礼包
	 * @param id   excel id
	 * @return
	 */

		public void responseReceiveById (FeedBack2UI o) {
			responser.response("cn.mxz.czfk2.FeedBack2Transform", "receiveById", o);
		}

		public void responseSetUser () {
			responser.response("cn.mxz.czfk2.FeedBack2Transform", "setUser");
		}
	}

/**
 * 神兽
 * @author 林岑
 *
 */

	public class DogzTransform {
/**
	 * 神兽开启界面
	 * @return
	 */

		public void responseGetDogzOpenUI (DogzOpenUI o) {
			responser.response("cn.mxz.dogz.DogzTransform", "getDogzOpenUI", o);
		}
/**
	 * 开启某个神兽
	 * @param dogzId
	 */

		public void responseOpen (DogzOpenUI o) {
			responser.response("cn.mxz.dogz.DogzTransform", "open", o);
		}
/**
	 * 出战某个神兽
	 * @param dogzId
	 * @return
	 */

		public void responseSetFighting (DogzOpenUI o) {
			responser.response("cn.mxz.dogz.DogzTransform", "setFighting", o);
		}
/**
	 * 注魂
	 * @param dogzId
	 * @return
	 */

		public void responseZhuHun (DogzOpenUI o) {
			responser.response("cn.mxz.dogz.DogzTransform", "zhuHun", o);
		}

		public void responseSetUser () {
			responser.response("cn.mxz.dogz.DogzTransform", "setUser");
		}
	}

/**
 *  装备
 * @author 林岑
 *
 */

	public class EquipmentTransform {
/**
	 * 判断玩家是否有指定ID的装备
	 * @param equipmentIds 装备templetId列表  用逗号分隔的   比如:   "122341,122541,122111"
	 * @return
	 */

		public void responseHasEquipments (EquipmentHasUI o) {
			responser.response("cn.mxz.equipment.EquipmentTransform", "hasEquipments", o);
		}

		public void responseSetUser () {
			responser.response("cn.mxz.equipment.EquipmentTransform", "setUser");
		}
	}


	public class IFengshentaiService {
/**
	 * 获取主界面
	 * @return
	 */

		public void responseGetdata (IFengshenMainUI o) {
			responser.response("cn.mxz.fengshentai.IFengshentaiService", "getdata", o);
		}
/**
	 * 兑换封神令
	 */

		public void responseExchange () {
			responser.response("cn.mxz.fengshentai.IFengshentaiService", "exchange");
		}
/**
	 * 封神
	 * @return
	 */

		public void responseFengshen () {
			responser.response("cn.mxz.fengshentai.IFengshentaiService", "fengshen");
		}

		public void responseSetUser () {
			responser.response("cn.mxz.fengshentai.IFengshentaiService", "setUser");
		}

		public void responseGetExchangePropUI (IExchangePropUI o) {
			responser.response("cn.mxz.fengshentai.IFengshentaiService", "getExchangePropUI", o);
		}
/**
	 * 用声望兑换道具
	 * @param propId
	 * @return
	 */

		public void responseBuy (IPropTransform o) {
			responser.response("cn.mxz.fengshentai.IFengshentaiService", "buy", o);
		}
	}

/**
 *  神秘商店
 * @author 林岑
 *
 */

	public class HeiShiTransform {
/**
	 * 神秘商店界面
	 * @return
	 */

		public void responseGetUI (HeiShiUI o) {
			responser.response("cn.mxz.heishi.HeiShiTransform", "getUI", o);
		}
/**
	 * 刷新
	 */

		public void responseRefresh (HeiShiUI o) {
			responser.response("cn.mxz.heishi.HeiShiTransform", "refresh", o);
		}
/**
	 * 兑换
	 * @param id 物品ID
	 * @return
	 */

		public void responseExchange (HeiShiUI o) {
			responser.response("cn.mxz.heishi.HeiShiTransform", "exchange", o);
		}
/**
	 * 兑换
	 * @param id 物品ID
	 * @return
	 */

		public void responseExchange2 (HeiShiSingleUI o) {
			responser.response("cn.mxz.heishi.HeiShiTransform", "exchange2", o);
		}

		public void responseSetUser () {
			responser.response("cn.mxz.heishi.HeiShiTransform", "setUser");
		}
	}

/**
 * 豪华大礼包
 * 
 * @author 林岑
 */

	public class HhdlbTransform {

		public void responseGetUI (HhdlbUI o) {
			responser.response("cn.mxz.hhdlb.HhdlbTransform", "getUI", o);
		}
/**
	 * 领取奖励
	 */

		public void responseReceive () {
			responser.response("cn.mxz.hhdlb.HhdlbTransform", "receive");
		}

		public void responseSetUser () {
			responser.response("cn.mxz.hhdlb.HhdlbTransform", "setUser");
		}
	}

/**
 * 邀请
 * @author 林岑
 *
 */

	public class InviteTransform {
/**
	 * 获取邀请模块界面数据
	 * @return
	 */

		public void responseGetUI (InviteUI o) {
			responser.response("cn.mxz.invite.InviteTransform", "getUI", o);
		}
/**
	 * 获取我的邀请码, 我可以把这个邀请码发给别的玩家
	 * @return
	 */

		public void responseGetMyCode (CodeUI o) {
			responser.response("cn.mxz.invite.InviteTransform", "getMyCode", o);
		}
/**
	 * 提交邀请码
	 * @param code
	 */

		public void responseCommitCode (CommitResult o) {
			responser.response("cn.mxz.invite.InviteTransform", "commitCode", o);
		}
/**
	 * 领取某个礼包
	 * @param id
	 * @return
	 */

		public void responseReceive (InviteUI o) {
			responser.response("cn.mxz.invite.InviteTransform", "receive", o);
		}

		public void responseSetUser () {
			responser.response("cn.mxz.invite.InviteTransform", "setUser");
		}
	}

/**
 *  服务器提供给客户端的键值数据库
 * @author 林岑
 *
 */

	public class KeyValueTransform {
/**
	 * 放入一个值, 会覆盖之前的值
	 * @param key
	 * @param value
	 */

		public void responsePut () {
			responser.response("cn.mxz.keyvalue.KeyValueTransform", "put");
		}
/**
	 * 取值
	 * @param key
	 * @return
	 */

		public void responseGetValue (Value o) {
			responser.response("cn.mxz.keyvalue.KeyValueTransform", "getValue", o);
		}

		public void responseSetUser () {
			responser.response("cn.mxz.keyvalue.KeyValueTransform", "setUser");
		}
	}

/**
 *  黑市
 * @author 林岑
 *
 */

	public class MissionTransform {
/**
	 * 副本章节完成度
	 * @param chapterId 章节ID
	 * @return
	 */

		public void responseGetCompleteness (MissionCompletenessUI o) {
			responser.response("cn.mxz.mission.MissionTransform", "getCompleteness", o);
		}
/**
	 * 领取宝箱奖励
	 * @param chapterId 章节ID
	 * @param index 领取哪一个宝箱     宝箱的下标
	 */

		public void responseReceiveBox () {
			responser.response("cn.mxz.mission.MissionTransform", "receiveBox");
		}

		public void responseSetUser () {
			responser.response("cn.mxz.mission.MissionTransform", "setUser");
		}
	}

/**
 * 女娲造人
 *
 * @author 林岑
 *
 */

	public class NvwaTransform {
/**
	 * 界面
	 * @return
	 */

		public void responseGetUI (NvwaUI o) {
			responser.response("cn.mxz.nvwa.NvwaTransform", "getUI", o);
		}
/**
	 * 买
	 */

		public void responseBuy (NvwaUI o) {
			responser.response("cn.mxz.nvwa.NvwaTransform", "buy", o);
		}

		public void responseSetUser () {
			responser.response("cn.mxz.nvwa.NvwaTransform", "setUser");
		}
	}

/**
 * 在线奖励
 * @author 林岑
 *
 */

	public class OnlineRewardTransform {
/**
	 * 在线奖励界面
	 * @return
	 */

		public void responseGetUI (OnlineRewardUI o) {
			responser.response("cn.mxz.onlinereward.OnlineRewardTransform", "getUI", o);
		}
/**
	 * 领取某个
	 * @param id   excel id
	 * @return
	 */

		public void responseReceiveById (OnlineRewardUI o) {
			responser.response("cn.mxz.onlinereward.OnlineRewardTransform", "receiveById", o);
		}

		public void responseSetUser () {
			responser.response("cn.mxz.onlinereward.OnlineRewardTransform", "setUser");
		}
	}

/**
 * 开服礼包
 *
 * @author 林岑
 *
 */

	public class OpenServerTransform {
/**
	 * 开服礼包界面
	 * @return
	 */

		public void responseGetOpenServerUI (OpenServerUI o) {
			responser.response("cn.mxz.openserver.OpenServerTransform", "getOpenServerUI", o);
		}
/**
	 * 领取第几天的开赴礼包奖励
	 * @param day
	 */

		public void responseReceive () {
			responser.response("cn.mxz.openserver.OpenServerTransform", "receive");
		}

		public void responseSetUser () {
			responser.response("cn.mxz.openserver.OpenServerTransform", "setUser");
		}
	}


	public class PacketLogTransform {
/**
	 * 记录用户操作了一次某个功能
	 * @param packetName	操作名字
	 */

		public void responseAdd () {
			responser.response("cn.mxz.packetlog.PacketLogTransform", "add");
		}

		public void responseSetUser () {
			responser.response("cn.mxz.packetlog.PacketLogTransform", "setUser");
		}
	}


	public class IPrizeCenterTransform {

		public void responseGetData (IPrizeCenterUI o) {
			responser.response("cn.mxz.prizecenter.IPrizeCenterTransform", "getData", o);
		}
//	void getPrize( int id );  

		public void responseSetUser () {
			responser.response("cn.mxz.prizecenter.IPrizeCenterTransform", "setUser");
		}
/**
	 * 从蓝港获取奖品
	 * @param id
	 * @param count
	 */

		public void responseGetPrizeFromLinekong () {
			responser.response("cn.mxz.prizecenter.IPrizeCenterTransform", "getPrizeFromLinekong");
		}
/**
	 * 获得非蓝港的奖励
	 * @param activityId
	 */

		public void responseGetPrize () {
			responser.response("cn.mxz.prizecenter.IPrizeCenterTransform", "getPrize");
		}
	}


	public class PvpTransform {
/**
	 * Pvp界面
	 * @return
	 */

		public void responseGetDuiHuanUI (PvpDuiHuanUI o) {
			responser.response("cn.mxz.pvp.PvpTransform", "getDuiHuanUI", o);
		}
/**
	 * 兑换
	 * @param typeId
	 */

		public void responseDuiHuan (PvpDuiHuanResultPro o) {
			responser.response("cn.mxz.pvp.PvpTransform", "duiHuan", o);
		}

		public void responseSetUser () {
			responser.response("cn.mxz.pvp.PvpTransform", "setUser");
		}
	}


	public class XianShiTransform {
/**
	 * 万里寻仙所需元宝
	 * @return
	 */

		public void responseGetWanLiGold (WanLiGold o) {
			responser.response("cn.mxz.xianshi.XianShiTransform", "getWanLiGold", o);
		}

		public void responseSetUser () {
			responser.response("cn.mxz.xianshi.XianShiTransform", "setUser");
		}
	}


	public class YoXiTransform {

		public void responseYoXi (YoXi o) {
			responser.response("cn.mxz.yoxi.YoXiTransform", "yoXi", o);
		}

		public void responseSetUser () {
			responser.response("cn.mxz.yoxi.YoXiTransform", "setUser");
		}
	}

/**
 *  装备商人
 * @author 林岑
 *
 */

	public class ZbsrTransform {
/**
	 * 界面
	 * @return
	 */

		public void responseGetUI (ZbsrUI o) {
			responser.response("cn.mxz.zbsr.ZbsrTransform", "getUI", o);
		}
/**
	 * 刷新
	 */

		public void responseRefresh (ZbsrUI o) {
			responser.response("cn.mxz.zbsr.ZbsrTransform", "refresh", o);
		}
/**
	 * 兑换
	 * @param id 物品ID
	 * @return
	 */

		public void responseExchange (ZbsrUI o) {
			responser.response("cn.mxz.zbsr.ZbsrTransform", "exchange", o);
		}

		public void responseSetUser () {
			responser.response("cn.mxz.zbsr.ZbsrTransform", "setUser");
		}
	}

}
