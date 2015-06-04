package define;

import user.UserInfo;
import util.db.DatabaseUtil;

public class UserUniqueID {
	
	
	// 数据库 唯一ID 记录
	private  int BATTLEINFO_ID; 	// 战报ID
	private  int EQUIPINFO_ID; 	// 装备ID
	private  int MATEINFO_ID;		// 匹配战报ID
	private  int MAILINFO_ID;		// 邮件ID
	private  int QPVPINFO_ID;		// 排位战报ID
	private  int TICKLINGINFO_ID;	// 反馈信息ID
	private  int HEROINFO_ID;		// 英雄ID
	
	private UserInfo user;
	public UserUniqueID( UserInfo user ){
		this.user = user;
	}
	
	public  int BATTLEINFO_ID(){ return ++BATTLEINFO_ID; }
	public  int EQUIPINFO_ID(){ return ++EQUIPINFO_ID; }
	public  int MATEINFO_ID(){ return ++MATEINFO_ID; }
	public  int MAILINFO_ID(){ return ++MAILINFO_ID; }
	public  int QPVPINFO_ID(){ return ++QPVPINFO_ID; }
	public  int TICKLINGINFO_ID(){ return ++TICKLINGINFO_ID; }
	public  int HEROINFO_ID(){ return ++HEROINFO_ID; }
	
	public  void initDataUniqueID(){
		BATTLEINFO_ID 	= DatabaseUtil.getMaxId( "battle_info_base", "u_id", "uname=" + user.getUID() );
		EQUIPINFO_ID 	= DatabaseUtil.getMaxId( "equip_info", "u_id", "uname=" + user.getUID() );
		MATEINFO_ID 	= DatabaseUtil.getMaxId( "pvp_mate_battle_info", "uId", "uname=" + user.getUID() );
		MAILINFO_ID 	= DatabaseUtil.getMaxId( "mail_info", "mailId", "uname=" + user.getUID() );
		QPVPINFO_ID 	= DatabaseUtil.getMaxId( "qualifying_battle_info", "u_id", "uname=" + user.getUID() );
		TICKLINGINFO_ID = DatabaseUtil.getMaxId( "tickling_info", "Id", "uname=" + user.getUID() );
		HEROINFO_ID 	= DatabaseUtil.getMaxId( "user_hero_base", "u_id", "uname=" + user.getUID() );
	}
	
}
