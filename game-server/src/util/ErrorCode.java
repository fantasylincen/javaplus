package util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public enum ErrorCode {

	SUCCESS("操作成功"), 
	/*****************************************玩家相关操作*********************************/
	USER_LEVEL_NOT_ENOUGH( "玩家等级不足" ),	
	USER_HAS_LOGIN( "玩家已经登陆了" ),	
	USER_DUPLICATE_NAME( "玩家用户名重复" ),	
	USER_NOT_FOUND( "玩家尚未注册" ),	
	USER_INVALID_LOGIN( "登录信息有误" ),
	USER_NOT_LOGIN( "玩家尚未登陆" ),	
	USER_HAS_BAN( "玩家被ban，无法登陆" ),
	USER_NOT_CODE( "验证码错误" ),
	USER_NOT_HEROID( "错误的英雄ID" ),
	USER_GOLD_NOT_ENOUTH( "玩家水晶不足" ),	
	USER_CASH_NOT_ENOUTH( "现金不足" ),
	USER_NOT_READY_HERO( "玩家还没得出战英雄" ),
	/*****************************************副本相关操作*********************************/
	ECTYPE_NOT_THROUGH( "该副本不能挑战"),	
	ECTYPE_NOT_POINTS( "该关卡不能挑战"),	
	ECTYPE_NOT_LEVEL( "等级不够"),	
	ECTYPE_NOT_STRENGTH( "体力不够"),	
	ECTYPE_NOT_THE( "波数错误"),	
	ECTYPE_NOT_WIN( "你已经失败 不能继续挑战"),	
	/*****************************************战斗相关操作*********************************/
	FIGHTER_STATUS("战斗状态"),	
	DEVOUR_NOT_HERO_LIST("没有被吞噬英雄"),	
	DEVOUR_HERO_FULL_LEVEL("该英雄已经满级"),	
	DEVOUR_OTHER_ERROR("其他错误"),	
	/*****************************************团队相关操作*********************************/
	TEAM_NOT_HERO( "玩家还没有该英雄" ),
	/*****************************************好友相关操作*********************************/
	FRIEND_GVTIMES_BEYOND( "赠送次数超出" ),
	FRIEND_GETTIMES_BEYOND( "领取次数超出" ),
	FRIEND_NUM_CAPACITY( "好友个数已达到上限" ),
	FRIEND_REMOVE_BEG( "拒绝成功" ),
	FRIEND_ADD_OK("添加成功"),
	/*****************************************邮件相关操作*********************************/
	MAIL_NOT_REMOVE( "没有可删除的邮件" ),
	/*****************************************天赋相关操作*********************************/
	TALENT_UPGRADE_TIME( "需要剩余时间" ),
	TALENT_NOT_LEVEL( "玩家等级不足" ),
	TALENT_NOT_CASH( "金币不足" ),
	TALENT_TIME_COMPLETE( "时间已经完了" ),
	TALENT_NOT_GOLD( "水晶不足" ),
	TALENT_DELAY( "出现延迟" ),
	/*****************************************英雄成长相关操作*********************************/
	HEROGROWUP_NOT_DEVOUR( "没有被吞噬的英雄" ),
	HEROGROWUP_NOT_LORD( "升级英雄不存在" ),
	HEROGROWUP_FULL_LEVEL( "已经满级" ),
	HEROGROWUP_NOT_EVOLUTION( "不可进化" ),
	HEROGROWUP_NOT_RESET_CSKILL( "没有重置技能" ),
	/*****************************************通信包相关操作********************************/
	PACKAGE_NOT_FOUND( "通信包未找到" ),
	PACKAGE_SAFE_CHECK_FAIL( "包的安全检测失败，用户短时间内发送大量相同的数据包到服务器端" ),
	
	USER_FRIENDSHIP_NOT_ENOUTH( "友情点不足" ),
	ACTIVITY_ECTYPE_NOT_COUNT( "活动副本的次数不足" ),
	ECTYPE_NOT_COUNT( "英雄副本次数不足" ),
	
	BUY_STR_NOT_COUNT( "购买体力次数不足" ),

	HAVE_MAX_COUNT( "背包已经最大" ),
	BAG_IS_FULL( "背包已满" ),
	
	CUR_NONE_MATE_INFO( "没有找到和你实力相当的对手" ),
	INVITING_IS_HAVE( "已经有该邀请好友了" ),
	INVITING_IS_MAX( "邀请好友列表已经上限" ),
	INVITING_NOT_GETGOLD( "邀请好友没有可领取的水晶" ),
	
	FRIEND_INVITE_ISSEND("好友邀请已经发送过了"),
	
	USER_ILLEGAL_NAME("用户名非法"),
	
	DANGRAD_UPGRADE_NOT_GRADE("提升段位 积分不够"),
	DANGRAD_UPGRADE_YET_MAX("提升段位 已经到最高"),
	DANGRAD_MATE_NOT_COUNT("匹配次数以用完"),
	DANGRAD_MATEAWARD_NOT_COUNT("匹配领取奖励 条件不满足"),
	DANGRAD_MATEAWARD_HAVE_RECEIVE("匹配领取奖励 已领取"),
	DANGRAD_MATE_HAVE_RECEIVE("匹配每日福利 已领取"),
	
	PVP_MATE_BUY_COUNT_MAX("购买匹配次数已用完"),
	
	MATE_TOUSER_NOT_TEAM("对方没有出战团队"),
	
	HEROGROWUP_NOT_MATERIAL("进化材料不足"),
	
	TALENT_NOT_UPGRADE("当前没有可升级的天赋"),
	
	CONTINUOUS_AWARD_SEVEN("连续登陆第七天的奖励"),
	
	CHALLENGE_THE_DRAGON_RESURGENCE("挑战大龙 玩家复活时间还没有到"),
	
	CHALLENGE_THE_DRAGON_DIE("挑战大龙 大龙已经死亡"),

	CHALLENGE_HAVE_INSPIRE("已经鼓舞了"),
	
	ACTIVITY_NOT_OPEN("活动未开启"),
	
	DB_ERROR("数据库错误"),	
	UNKNOW_ERROR("未知错误") ;

	private String desc;
	ErrorCode( String desc ) {
		this.desc = desc;
	}
	public static void main(String[] args) {
		for( ErrorCode code : values() ){
			System.out.println( "[" + code.ordinal() + "]\t[" + code + "]\t(" + code.desc + ")" );
//			System.out.println( "[" + code.ordinal() + "](" + code.desc + ")" );
		}
		
		Element rootElt 		= new Element("explain");
        for( ErrorCode code : values() ){
        	
        	Element selectElt 	= new Element("xml");  
        	
        	Element idElt		= new Element("id");
        	idElt.addContent( code.ordinal() + "" );  
        	selectElt.addContent( idElt );  
            
        	Element descElt		= new Element("desc");
            descElt.addContent( code.desc );
            selectElt.addContent( descElt );
            
            rootElt.addContent( selectElt );
        }
        
        Document doc = new Document( rootElt );
          
        try {  
        	XMLOutputter out 	= new XMLOutputter();   
        	FileOutputStream writer = new FileOutputStream("D:/手游-英雄之城/前后端通信协议/ErrorCode.xml");
            Format f			= Format.getPrettyFormat();
            out.setFormat(f);
            out.output( doc, writer );
            writer.close();
           
            System.out.println( "已生成XML表格 ！" );
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
		
	}
}

