package telnet.events;

import game.events.all.tickling.TicklingBase;
import game.events.all.tickling.TicklingDB;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import telnet.CommandBase;


public class ViewFeedbackEevet extends CommandBase {
	
	private List<S> sui		= new ArrayList<S>();
	
	@Override
	public void run( PrintWriter out, byte jurisdiction, String... args ) throws Exception {
		
		S s 			= get( out.toString() );
		
		switch( s.status ){
		case 0:
			
			List<TicklingBase> list = TicklingDB.get();
			out.print( "\r\n" );
			if( list.isEmpty() ) {
				out.print( "还没有反馈信息.\r\n" );
				return ;
			}
			
			for( TicklingBase t : list )
				out.print( t.toContent() + "\r\n" );
			out.print( "\r\n" );
			
			s.status 	= 1;
			throw new Exception( "请输入对应编号进行操作：" );
		case 1:
			
//			int index			= -1;
//			try {
//				index 			= Integer.parseInt( args[0] );
//			} catch (Exception e) {
//				throw new Exception( "参数错误,请重新输入:" );
//			}
//			
//			TicklingBase t		= TicklingDB.get( index );
//			if( t == null )
//				throw new Exception( "参数错误,请重新输入:" );
//			
//			t.toOut( out );
//			out.print( "\r\n" );
//			
//			s.temp		= t;
//			s.status 	= 2;
			throw new Exception( "请输入回复信息,退出操作输入qw：" );
		case 2:
			
//			String content		= args[0];
//			for( int i = 1; i < args.length; i++ )
//				content			+= (" " + args[i]);
//			if( content.isEmpty() )
//				throw new Exception( "参数不能为空,请重新输入:" );
//			TicklingBase t1		= (TicklingBase) s.temp;
//			TicklingDB.updateReply( t1, content );
//			
//			// 发送给玩家
//			UserInfo u 			= UserManager.getInstance().getByName( t1.uid );
//			MailBase m			= new MailBase( NoticeManager.noticeName, MailType.SYSTEM_NOTICE, "-1|" + content );
//			u.getMailManager().addMail( m );
//			
//			out.print( "完成对 " + u.getNickName() + " 的回复" + "\r\n" );
			return;
		}
		
	}

	@Override
	public void clear(PrintWriter out) {
		remove( out.toString() );
	}

	private void remove( String key ) {
		for( int i = 0; i < sui.size(); i++ ){
			if( sui.get(i).key.equals( key ) ){
				sui.remove( i );
				return;
			}
		}
	}

	private S get( String key ) {
		for( S s : sui ){
			if( s.key.equals( key ) ) return s;
		}
		
		S s 	= new S();
		s.key 	= key;
		sui.add( s );
		return s;
	}
}
