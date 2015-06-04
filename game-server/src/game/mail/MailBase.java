package game.mail;

import config.illegal.IllegalCharCfg;
import game.award.AwardInfo;
import game.award.AwardType;
import util.SystemTimer;

/**
 * 单个邮件 具体信息 ( 赠送 是没有实体道具 )
 * @author DXF
 *
 */
public class MailBase {

	// 邮件唯一ID
	private int 		mailID;
	
	// 邮件类型
	private MailType	type;
	
	// 标题
	private String		title;
	
	// 邮件内容
	private String		content				= "空";
	
	// 赠送物品
	private AwardInfo 	presentItem			= null;
	
	// 是否读取
	private boolean 	isRead				= false;
	
	// 剩余时间  这个根据 是否读取来控制
	private int			surplusTime;
	
	/**
	 * 从数据库读取 需要
	 * @param mianID
	 * @param title
	 * @param type
	 * @param index
	 */
	public MailBase( int mianID, String title, MailType type ){
		this.mailID = mianID;
		this.title	= title;
		this.type	= type;
	}
	/**
	 * 发送信息
	 * @param title
	 * @param type
	 * @param content
	 */
	public MailBase( String title, MailType type, String content ){
		this.title 	= title;
		this.type 	= type;
		this.content= transform( content ) ;
	}
	
	/**
	 * 赠送物品
	 * @param title
	 * @param type
	 */
	public MailBase( AwardInfo award, MailType type ) {
		this.presentItem 	= award;
		this.type			= type;
		String name			= "";
		switch( award.getAward() ){
		case GOLD:
			name			= AwardType.GOLD.toNumber() + "," + award.getNumber();
			break;
		case CASH:
			name			= AwardType.CASH.toNumber() + "," + award.getNumber();
			break;
		case STRENGTH: 
			name			= AwardType.STRENGTH.toNumber() + "," + award.getNumber();
			break;
		case EXP: 
			name			= AwardType.EXP.toNumber() + "," + award.getNumber();
			break;
		case PROP:
			name			= AwardType.PROP.toNumber() + "," + award.getPropId() + "," + award.getNumber();
			break;
		case HERO:
			name			= AwardType.HERO.toNumber() + "," + award.getPropId() + "," + award.getNumber();
			break;
		case HERO_EXP: break;
		case FD_VALUE: 
			name			= AwardType.FD_VALUE.toNumber() + "," + award.getNumber();
			break;
		case TROPHY: 
			name			= AwardType.TROPHY.toNumber() + "," + award.getNumber();
			break;
		}
		this.title 			= name;
	}
	
	public void setMailID( int id ){
		this.mailID = id;
	}
	public int getMailID(){
		return this.mailID;
	}
	public String getTitle(){
		return this.title;
	}
	public MailType getType(){
		return this.type;
	}
	
	public void setContent( String msg ){
		this.content = msg;
	}
	public String getContent(){
		return this.content;
	}
	
	public void addItem( AwardInfo award ){
		this.presentItem 	= award;
	}
	public AwardInfo getItem(){
		return this.presentItem;
	}
	public String getItemToStr() {
		
		if( presentItem == null )
			return "";
		StringBuilder content = new StringBuilder();
		
		content.append( presentItem.getAward() ).append( "," );
		content.append( presentItem.getPropId() ).append( "," );
		content.append( presentItem.getNumber() ).append( "," );
		int[] args	= presentItem.getArguments();
		int size = args == null ? 0 : args.length;
		for( int i = 0; i < size; i++ ){
			content.append( args[i] ).append(",");
		}
		
		return content.toString();
	}
	
	public void setIsRead( boolean isRead ){
		this.isRead = isRead;
	}
	public boolean getIsRead(){
		return this.isRead;
	}
	
	public void setSurplusTime( int surplusTime ){
		this.surplusTime = surplusTime;
	}
	public int getTime(){
		return this.surplusTime;
	}
	/** 获取剩余时间  （单位小时） */
	public short getSurplusTime(){
		
		// 算出已经过去的时间
		int pastTime 	= SystemTimer.currentTimeSecond() - surplusTime;
		
		// 在算成小时
		pastTime		= pastTime / 3600;
		
		// 根据已读和未读 获取时间限制
		short limitHour	= isRead ? MailManager.MAIL_LIMIT_HOUR_READ : MailManager.MAIL_LIMIT_HOUR_NOTREAD;
		
		// 最后算出剩余时间
		short surplus 	= (short) ( limitHour - pastTime );
		
		return surplus < 0 ? 0 : surplus;
	}

	private String transform( String content ) {
		
		String replacement 		= "";
		int i					= 0;
		for( String target : IllegalCharCfg.lists ) {
		
			if( content.contains( target ) ){
				
				replacement = "";
				i			= target.length();
				while( i-- > 0 ) replacement	+= "*";
				
				content 	= content.replace( target, replacement );
			}
		}
		
		return content;
	}
	
}
