package game.mail;

import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.log.Logs;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import manager.DWType;

import user.UserInfo;
import util.ErrorCode;
import util.SystemTimer;

/**
 * 邮件 管理
 * @author DXF
 *
 */
public class MailManager {

	/** 已读的限制时间 （单位小时） */
	public static final short 							MAIL_LIMIT_HOUR_READ 	= 24;
	
	/** 未读的限制时间 （单位小时） */
	public static final short 							MAIL_LIMIT_HOUR_NOTREAD = 168;
	
	private UserInfo									user;
	private final MailDataProvider 						db 				= MailDataProvider.getInstance();	
	
	/** 玩家邮件列表 */
	private ConcurrentHashMap<Integer,MailBase> 		mails;
	
	private MailPageControl								pageControl;
	
	public MailManager( UserInfo user ) {
		super();
		this.user 			= user;
		mails				= db.get( user );
		pageControl			= new MailPageControl( mails );
	}
	public MailPageControl getPageControl(){
		return pageControl;
	}
	
	/**
	 * 添加邮件
	 * @param mail
	 */
	public ErrorCode addMail( MailBase mail ) {
		
		if( mail == null ) return ErrorCode.UNKNOW_ERROR;
		
		// 这里暂设100  如果邮件个数超过100个 那么就直接删除掉最后一个
		if( mails.size() >= 100 )
			removeMail( pageControl.getLast() );
		
		mail.setMailID( user.getBasisUniqueID().MAILINFO_ID() );
		if( db.add( user, mail ) != 0 ) return ErrorCode.UNKNOW_ERROR;
		
		mails.putIfAbsent( mail.getMailID(), mail);
		
		pageControl.updata( mails );
		
		// 这里如果在线 提示 有新的邮件
		if( this.user.isOnline() )
			UpdateManager.instance.update( user, UpdateType.U_7 );
			
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 根据页数 获取邮件列表
	 * @param index
	 * @return
	 */
	public List<MailBase> get( byte index ) {
		
		boolean isRemove = false;
		for( MailBase mail : mails.values() ){
			// 这里把时间过滤一下  如果时间到了 自动删除
			if( mail.getSurplusTime() == 0 ){
				removeMail( mail );
				isRemove = true;
			}
		}
		
		if( isRemove )
			pageControl.updata( mails );
			
		return pageControl.get( index );
	}
	
	/**
	 * 读取邮件
	 * @param mailID
	 * @return
	 */
	public MailBase readMail(int mailID) {
		
		MailBase mail = mails.get( mailID );
		if( mail == null ) {
			Logs.error( user, "读取邮件出错  mailID=" + mailID + " 不存在！" );
			return null;
		}
		
		// 如果已经读了 那么就不能再设置了
		if( !mail.getIsRead() ){
			
			// 设置为已读取
			mail.setIsRead( true );
			// 然后从新设置时间
			mail.setSurplusTime( SystemTimer.currentTimeSecond() );
			// 然后及时刷新一下数据库
			updata( mail );
			// 提示前端
			UpdateManager.instance.update( user, UpdateType.U_7 );
			
			// 这里主要是为了 排序
			pageControl.updata( mails );
		}
		
		return mail;
	}

	/**
	 * 领取物品
	 * @param mailID
	 * @return
	 */
	public MailBase receiveItem(int mailID) {
		
		// 这里先强制删除掉 
		MailBase mail = mails.remove( mailID );
		if( mail == null ){
			Logs.error( user, "领取物品出错   mailID=" + mailID + " 不存在！" );
			return null;
		}
		// 这里检查一下 看是不是 赠送邮件
		if( mail.getType() != MailType.SYSTEM_PRESENT ){
			Logs.error( user, "领取物品出错   mail.getType() != MailType.SYSTEM_PRESENT！" );
			return null;
		}
		
		user.changeAward( mail.getItem(), "系统赠送", DWType.SYSTEM_IS_PRESENTED  );
		
		db.remove( user, mail );
		
		pageControl.updata( mails );
		
		UpdateManager.instance.update( user, UpdateType.U_7 );
		
		return mail;
	}
	
	/**
	 * 删除已读邮件
	 * @param mailID
	 * @param page 
	 * @return
	 */
	public ErrorCode removeReadMail( int mailID ) {
		
		boolean remove = false; 
		
		if( mailID > 0 ){
			MailBase mail = mails.get( mailID );
			if( mail == null ){
				Logs.error( user, "删除邮件出错   mailID=" + mailID + " 不存在！" );
				return ErrorCode.UNKNOW_ERROR;
			}
			removeMail( mail );
			remove = true;
		}else if( mailID == -1 ){
			
			for( MailBase mail : mails.values() )
			{
				if( mail.getIsRead() ){
					removeMail( mail );
					remove = true;
				}
			}
		}
		
		if( remove )
			pageControl.updata( mails );
		
		return remove ? ErrorCode.SUCCESS : ErrorCode.MAIL_NOT_REMOVE; 
	}

	/**
	 * 删除邮件  在这里 处理 赠送邮件
	 * @param mail (这里不用考虑为null 由外层判断)
	 */
	public void removeMail( MailBase mail ) {
		
		// 这里检查一下 看是不是 赠送邮件 删除前先赠送了
		if( mail.getType() == MailType.SYSTEM_PRESENT )
			user.changeAward( mail.getItem(), "系统赠送", DWType.SYSTEM_IS_PRESENTED );
		
		// 数据库也要直接删除 这里不保存
		db.remove( user, mail );
		
		// 不管怎样 最后都要删除 掉
		mails.remove( mail.getMailID() );
	}
	
	/**
	 * 是否含有 未读邮件
	 * @return
	 */
	public byte isHaveRead() {
		
		for( MailBase mail : mails.values() ){
			if( !mail.getIsRead() )
				return 1;
		}
		
		return 0;
	}
	
	/** 刷新一下数据库邮件 */
	public void updata( MailBase mail ){
		if( mail == null ) 
			return;
		db.updata( user, mail );
	}
	
	public static void main( String arg[] )
	{
		MailManager manager = new MailManager( new UserInfo(null, 1011655) );
		
		for( int i = 0; i < 10000; i++ ){
			MailBase mail = new MailBase( "刘玉成", MailType.FRIEND_MSG, "刘玉成你是有多SB！");
			manager.addMail( mail );
		}
		
		MailBase mail = new MailBase( "刘玉成", MailType.FRIEND_MSG, "刘玉成你是有多SB！");
		manager.addMail(mail);
//		
//		mail = new MailBase( "杨松", MailType.FRIEND_MSG, "杨松其实也SB！");
//		manager.addMail(mail);
//		
//		mail = new MailBase( "杨松1", MailType.FRIEND_MSG, "杨松其实也SB！1");
//		manager.addMail(mail);
//		
//		mail = new MailBase( "杨松2", MailType.FRIEND_MSG, "杨松其实也SB！2");
//		manager.addMail(mail);
//		
//		mail = new MailBase( "杨松3", MailType.FRIEND_MSG, "杨松其实也SB！3");
//		manager.addMail(mail);
//		
//		mail = new MailBase( "杨松4", MailType.FRIEND_MSG, "杨松其实也SB！4");
//		manager.addMail(mail);
//		
//		mail = new MailBase( "新爷", MailType.SYSTEM_NOTICE, "VC通告 ：城和松将于10月1号结婚！");
//		manager.addMail(mail);
		
		
		System.out.println( "邮件添加完成" );
//		int sta = SystemTimer.currentTimeSecond();
//		for( MailBase mail : manager.get((byte)1) ){
//			System.out.print( mail.getTitle() + "----" + mail.getContent() );
//			System.out.println( mail.getTime() );
//		}
//		System.out.println( "用时：" + (SystemTimer.currentTimeSecond() - sta) );
	}
	

}
