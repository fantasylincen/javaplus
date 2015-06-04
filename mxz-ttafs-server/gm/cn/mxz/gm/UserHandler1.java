package cn.mxz.gm;

import java.util.Date;
import java.util.Map;

import org.joda.time.DateTime;

import com.linekong.platform.protocol.erating.server.RechargeLogic;

import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.user.Player;
import cn.mxz.util.counter.CounterKey;

/**
 * 基本等同于UserHandler.java，除了返回值中的<info >加了一个id
 * 主要用于自己的web管理平台，不太想去直接修改蓝港已经运行的内容，以防出现意外
 * @author Administrator
 *
 */
public class UserHandler1 extends AbstractHandler {
	public static final String DATA_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";
	StringBuilder sb;
	boolean isBase = true;
	private final static String[] clickInfo = new String[]{
			"101",	"点击仙市的总数量",
			"102",	"用户十里寻一总次数",
			"103",	"用户百里寻一总次数",
			"104",	"用户万里寻一总次数",
			"105",	"用户万里寻一十次的总次数",
			"106",	"伙伴点击总数量",
			"107",	"魂魄点击总数量",
			"108",	"传承界面点击总数量",
			"109",	"布阵阵界面点击总数量",
			"110",	"阵法界面点击总数量",
			"111",	"神兽点击总数量",
			"112",	"装备标签点击总数量",
			"113",	"打造界面点击总数量",
			"114",	"合成界面点击总数量",
			"115",	"技能界面点击总数量",
			"116",	"技能升级界面点击总数量	",
			"117",	"斗法界面点击的总数量",
			"118",	"斗法打人的点击操作总数量",
			"119",	"夺宝装备材料掠夺点击数量",
			"120",	"夺宝技能残片掠夺点击总数量",
			"121",	"夺宝阵法残片掠夺点击总数量",
			"122",	"奇遇=签到有礼点击总数量",
			"123",	"奇遇害保护妲已点击总数量",
			"124",	"奇遇每日上香点击总数量	",
			"125",	"奇遇摇钱树点击总数量",
			"126",	"奇遇等级礼包点击总数量	",
			"127",	"奇遇渡天劫点击总数量",
			"128",	"奇遇魔神入侵点击总数量	",
			"129",	"奇遇BOSS战点击总数量",
			"130",	"奇遇云游仙人点击总数量	",
			"131",	"恩怨点击总数量",
			"132",	"背包点击总数量",
			"133",	"设置点击总数量",
			"134",	"领奖中心点击总数量",
			"135",	"聊天面板点击总数量",
			"136",	"成就界面点击总数量",
			"137",	"用户详细信息点击总数量	",
			"138",	"逆转功能点击总数量",
			"139",	"随机取名总数量",
			"140",	"更换替补功能按钮点击总数量",
			"141",	"放弃副本点击总量",
			"142",	"继续副本点击总量",
			"143",	"直接挑战副本BOSS点击总量",
			"144",	"通关宝箱领取情况（每个副本每种宝箱领取的总量）",
			"145",	"等级礼包领取情况（领取总量）",
			"146",	"装备强化点击总量",
			"147",	"奇遇保护妲己领取点击总量",
			"148",	"奇遇保护妲己驱赶点击总量",
			"149",	"奇遇云游仙人拜谢情况（单倍拜谢点击数量）",
			"150",	"奇遇云游仙人拜谢情况（双倍拜谢的点击数量）",

	};
	public UserHandler1(boolean isBase ) {
		this.isBase = isBase;
	}

	@Override
	protected String doGet(Map<String, Object> parameters) {
		
//		<Response> 
//	     <result>1</result> 
//	<role_info> 
//	      <info name="hp" text="性别">5</info> 
//	<info name="profession" text="职业">职业 1</info> 
//	       <info name="hp" text="生命">5</info> 
//	    <info name="mp" text="法力">500</ info> 
//	    <info name="exp" text="当前经验/升级所需经验">500/1000</info> 
//	          ……………… 
//	 </role_info> 
//	</Response> 
		sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?><Response> <result>1</result> ");
		
		City user = getUser( parameters );
		
		if( user == null ){
			return responseErr( ErrorCode.USER_NOT_FOUND );
		}
		
		sb.append("<details_info><info id='1'>用户名,昵称,ip,等级,vip等级,体力,精力,元宝（充值元宝）,金锭,铜钱,声望,修行,兽魂,最后登录时间,"
				+ "总登录次数,今日登陆次数,总在线时长,角色类型,引导的节点数据,最大关卡位置,斗法玩家仙位等级,首冲情况,</info>");//最后一个逗号有利于前端分割字符串
		sb.append("<info id='2'>");
		sb.append( user.getId() ).append(","); 
		Player player = user.getPlayer();
		sb.append( player.getNick() ).append(",");;
		if( user.getSocket() == null ){
			sb.append(  "不在线" ).append(",");;
		}
		else{
			sb.append(  user.getSocket().getIP()  ).append(",");
		}
		sb.append(  user.getLevel()).append(",");;
		sb.append(  user.getVipPlayer().getLevel() ).append(",");
		sb.append(  player.getPhysical().getNumerator()).append(",");
		sb.append(  player.getPower().getNumerator()).append(",");
		sb.append(  player.getGold() ).append("(").append(user.getUserCounterHistory().get(CounterKey.TOTAL_RECHARGE_GOLD_COUNT)).append(")").append(",");
		sb.append(  player.get(PlayerProperty.NEW_GOLD)).append(",");
		sb.append(  player.get(PlayerProperty.CASH)).append(",");
		sb.append(  player.get(PlayerProperty.REPUTATION)).append(",");
		sb.append(  player.get(PlayerProperty.CULTIVATION)).append(",");
		sb.append(  player.get(PlayerProperty.SHOU_HUN)).append(",");
		
		
		long date = user.getLastLoginMillis();
		
		sb.append(  new DateTime(date).toString(DATA_FORMAT_STR));
		sb.append(",");//最后登录时间
		sb.append(  player.get(PlayerProperty.LOGIN_TIMES_HISTORY)).append(",");//总登录次数
		sb.append(  user.getUserCounter().get(CounterKey.USER_LOGIN_TIMES_TODAY)).append(",");
		sb.append(  user.getUserCounterHistory().get(CounterKey.ONLINE_TIME)).append(",");
		sb.append(  user.getTeam().getPlayer().getTypeId()).append(",");
		sb.append(  user.getGuide().getStatus()).append(",");
		sb.append(  user.getUserCounterHistory().get( CounterKey.MAX_MISSION_ID ) ).append(",");
		sb.append(  user.getNewPvpManager().getPlayer().getDan()).append(",");
		sb.append(  new RechargeLogic(user).getFirshtRechargeStr().replace(",", "|"));//把逗号分割的充值情况转化为|分割的，否则前端会出错
		
//		if( !isBase ){
//			buildclickInfo( user );
//		}
		
		sb.append("</info></details_info></Response>" );
		

		
		return sb.toString();
	}

//	private void buildclickInfo1(  ) {
//		for( int i = 0; i < clickInfo.length; ){
//			String id = clickInfo[i++];
//			buildInfo( id, clickInfo[i++], 3 ) ;
//		}
//	}
	
	private void buildclickInfo( City user ) {
		for( int i = 0; i < clickInfo.length; ){
			String id = clickInfo[i++];
			buildInfo( id, clickInfo[i++], user.getUserCounterHistory().get(CounterKey.PACKET_LOG, Integer.parseInt( id ) ) );
		}
	}
	private void buildInfo(String name, String desc, Object value) {
		String format = String.format("<info name=\"%s\" text=\"%s\">%s</info>", name, desc, value);
		sb.append( format );
	}

	public static void main(String[] args) {
		// System.out.println( buildInfo("hp", "血量", 100) );
		UserHandler1 userHandler = new UserHandler1( true);
//		userHandler.buildclickInfo1();
		System.out.println( userHandler.sb.toString()  );
	}
}
