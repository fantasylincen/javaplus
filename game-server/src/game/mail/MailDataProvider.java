package game.mail;

import game.award.AwardInfo;
import game.award.AwardType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

import user.UserInfo;
import util.SystemTimer;
import util.db.DatabaseUtil;


/**
 * 邮件数据库
 * @author DXF
 *
 */
public class MailDataProvider {

	private static MailDataProvider instance 	= new MailDataProvider();
	static  MailDataProvider getInstance(){
		return instance;
	}
	private MailDataProvider(){}
	
	/**
	 * 获取所有邮件
	 * @param user
	 * @return
	 */
	public ConcurrentHashMap<Integer, MailBase> get(UserInfo user) {
		
		ConcurrentHashMap<Integer, MailBase> mails = new ConcurrentHashMap<Integer, MailBase>();
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		
		String sql = "SELECT * from mail_info where uname=? ";
		
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			pst.setInt( i++, user.getUID() );
			rs = pst.executeQuery();

			while( rs.next() ){
				
				int mailId				= rs.getInt( "mailId" );
				MailType type			= MailType.fromNumber( rs.getByte( "type" ) );
				String title			= rs.getString( "title" );
				String content			= rs.getString( "content" );
				boolean isRead			= rs.getByte( "is_read" ) == 1;
				int surplusTime			= rs.getInt( "surplus_time" );
				
				MailBase mail			= new MailBase( mailId, title, type );
				mail.setContent( content );
				mail.setIsRead( isRead );
				mail.setSurplusTime( surplusTime );
				
				// 这里看 如果是赠送邮件 那么就读取赠送数据
				if( type == MailType.SYSTEM_PRESENT ){
					AwardInfo item		= maping( rs.getString( "item" ) ) ;
					mail.addItem( item );
				}
				
				mails.putIfAbsent( mailId, mail );
			}
			
		} catch (SQLException e) {
//			logger.debug( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return mails;
	}
	
	private AwardInfo maping( String string ) {
		
		if( string.isEmpty() || string == null ) 
			return null;
		String[] content	= string.split(",");
		
		AwardType type		= AwardType.valueOf( content[0] );
		int pid				= Integer.parseInt( content[1] );
		int num				= Integer.parseInt( content[2] );
		int[] args			= null;
		
		if( content.length > 3 ){
			args 			= new int[content.length-3];
			for( int i = 3; i < content.length; i++ )
				args[i-3]	= Integer.parseInt( content[i] );
		}
		
		AwardInfo award		= new AwardInfo( type, pid, num );
		award.setArguments( args );
		
		return award;
	}
	/**
	 * 添加一封邮件
	 * @param user
	 * @param mail
	 * @return
	 */
	public int add(UserInfo user, MailBase mail) {
		
		Connection con 	= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;			
		
		mail.setSurplusTime( SystemTimer.currentTimeSecond() );
		
		String sql = "insert into mail_info (mailId,uname,type,title,content,surplus_time,item) "
			+ "values (" + mail.getMailID() + "," 
						+ user.getUID() + ","  
						+ mail.getType().toNumber() + ",'" 
						+ mail.getTitle() + "','"
						+ mail.getContent() + "',"
						+ mail.getTime() + ",'"
						+ mail.getItemToStr() + "')";
		
		try {
			pst = con.prepareStatement( sql );
			pst.executeUpdate();
		} catch (SQLException e) {
//			logger.debug( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return 0;
	}
	
	/**
	 * 刷新
	 * @param user
	 * @param mails
	 */
	public void updata( UserInfo user, MailBase mail ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		String sql = "update mail_info set " +
				"surplus_time = ?, " +
				"is_read = ? " +
				"where uname = ? and mailId = ?";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, mail.getTime() );
			pst.setByte( i++, (byte)(mail.getIsRead() ? 1 : 0) );
			pst.setInt( i++, user.getUID() );
			pst.setInt( i++, mail.getMailID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
//			logger.debug( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
	}
	
	/**
	 * 删除列表数据
	 * @param user
	 * @param mail
	 */
	public void remove(UserInfo user, MailBase mail) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		
		String sql = "delete from mail_info where uname = ? and mailId = ?";
		
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, user.getUID() );
			pst.setInt( i++, mail.getMailID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
//			logger.debug( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
	}
	
	
	
	
}
