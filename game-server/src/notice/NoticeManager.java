package notice;


import java.util.ArrayList;
import java.util.List;

import datalogging.ConsumelogF;
import datalogging.DataLogDataProvider;

import game.award.AwardInfo;
import game.award.AwardType;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.log.Logs;
import game.mail.MailBase;
import game.mail.MailType;
import user.UserInfo;
import user.UserManager;
import util.RandomUtil;
import util.SystemTimer;


/**
 * 公告管理中心 单列
 * @author DXF
 */
public class NoticeManager {
	
	public static final String noticeName		= "";
	
	
	private List<List<Message>> messages		= new ArrayList<List<Message>>();
	private volatile List<Message> messagesx	= new ArrayList<Message>();
	private List<ChatContent> chatContents		= new ArrayList<ChatContent>();
	
	private static final NoticeManager instance = new NoticeManager();
	public static final NoticeManager getInstance(){
		return instance;
	}
	private NoticeManager() {	}
	
	
	public void clear(){
		messages.clear();
		messagesx.clear();
	}
	
	/**
	 * 发送邮件公告
	 * @param msg
	 * @param times 
	 */
	public void send( String msg, int times ){
		
		if( msg == null || msg.isEmpty() )
			return;
		
		int curTime	= times > 0 ? SystemTimer.currentTimeSecond() - times : 0;
		
		List<UserInfo> ls			= UserManager.getInstance().getMemoryAllUser();
		for( UserInfo u : ls ){
			
			if( u.getLastLogoutTime() < curTime ) 
				continue;
			
//			synchronized (u) {
				MailBase mail = new MailBase( noticeName, MailType.SYSTEM_NOTICE, msg );
				u.getMailManager().addMail( mail );
//			}
		}

	}
	/**
	 * 发送邮件 物品公告
	 * @param award
	 */
	public void send( AwardInfo award, int times ) {
		
		if( award == null )
			return;
		
		int curTime	= times > 0 ? SystemTimer.currentTimeSecond() - times : 0;
		
		for( UserInfo u : UserManager.getInstance().getMemoryAllUser() ){
			
			if( u.getLastLogoutTime() < curTime )
				continue;
			
//			synchronized (u) {
				MailBase mail = new MailBase( award, MailType.SYSTEM_PRESENT );
				u.getMailManager().addMail( mail );
				if( award.getAward() == AwardType.GOLD )
					DataLogDataProvider.getInstance().add( u, ConsumelogF.DISTRIBUTED_SYSTEM, award.getNumber() );
//			}
		}
		
		
		
	} 
	
	/**
	 * 发送滚动公告
	 * @param id
	 * @param args
	 */
	public void sendRoll( Message msg ){
//		Logs.error( "玩家个数" + UserManager.getInstance().getMaps().values().size() );
		for( UserInfo u : UserManager.getInstance().getMaps().values() ){
//			if( !u.isOnline() ) continue;
			
			UpdateManager.instance.update( u, UpdateType.U_120, msg.id, msg.content );
		}
	}

	/**
	 * 添加动态公告
	 * @param id
	 * @param args
	 */
	public void add( int id, String ... args ){
		Message m				= new Message();
		m.id					= id;
//		MessageTemplet message 	= MessageTempletCfg.getTempletById(id);
//		m.content				= MessageFormat.format( message.getContent(), args );
		m.content				= args;
		
		List<Message> list		= get( id );
		if( list == null ){
			list = new ArrayList<Message>();
			list.add(m);
			messages.add(list);
		}else{
			list.add(m);
		}
		
		Logs.debug( "添加一条公告:" + m.content );
	}
	
	/** 没20分钟刷新一天数据出来 发送 */
	public void update(){
		
		if( messages.isEmpty() ) return;
		
		List<Message> list 	= messages.remove(0);
		
		int rand 			= RandomUtil.getRandomInt( 0, list.size() - 1 );
		Message m 			= list.get(rand);
		sendRoll( m );
		Logs.debug( "发出了一条公告:" + m.content + "   当前公告数：" + messages.size() );
	}
	
	/**
	 * 每1秒
	 */
	public void update_timely(){
//		Logs.error( "一秒时间到" );
		if( messagesx.isEmpty() ) return;
		
		Message m 		= messagesx.remove(0);
		sendRoll( m );
		Logs.debug( "发出了一条公告:" + m.content + "   当前公告数：" + messagesx.size() );
	}
	
	private List<Message> get( int id ){
		for( List<Message> l : messages )
		{
			if( l.get(0).id == id )
				return l;
		}
		return null;
	}
	
	/**
	 * 添加一条及时公告  将会每隔两秒执行一次
	 * @param _isOpen
	 */
	public void addTimely( int id, String ... args ) {
		if( args == null || args.length == 0 ) return;
		
		Message m				= new Message();
		m.id					= id;
//		MessageTemplet message 	= MessageTempletCfg.getTempletById(id);
//		if( message == null ) return;
//		m.content				= MessageFormat.format( message.getContent(), args );
		m.content				= args;
		
		// 将系统公告优先
		if( id != -2 && !messagesx.isEmpty() ){
			Message temp = messagesx.get(0);
			messagesx.set( 0, m );
			messagesx.add( temp );
		}else{
			messagesx.add( m );
		}
		// 添加到容器里
		addChatContent( new ChatContent( id, args ) );
		
		Logs.debug( "添加一条及时公告:" + m.content[0] + "   当前公告数：" + messagesx.size() );
	}

	private void addChatContent( ChatContent chatContent ) {
		if( chatContents.size() >= 20 )
			chatContents.remove(0);
		chatContents.add( chatContent );
	}
	
	public List<ChatContent> getChatContent(){
		return chatContents;
	}
	
	public static void main( String[] args ){
		
		NoticeManager.getInstance().addTimely( 213, "asdasdas" );
		NoticeManager.getInstance().addTimely( 214, "asdasdas" );
		NoticeManager.getInstance().addTimely( -1, "asdasdas" );
		NoticeManager.getInstance().addTimely( -2, "asdasdas" );
		
		for( int i = 0; i < NoticeManager.getInstance().messagesx.size(); i++ ){
			Message m = NoticeManager.getInstance().messagesx.get(i);
			System.out.println( m.id + " - " + m.content[0]);
		}
		
	}
	
}

class Message{
	
	public int  	id;
	
	public String[]	content;
}
