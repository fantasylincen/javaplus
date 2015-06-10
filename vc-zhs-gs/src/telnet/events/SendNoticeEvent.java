package telnet.events;

import game.award.AwardInfo;
import game.award.AwardType;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import notice.NoticeManager;

import server.ServerManager;
import telnet.CommandBase;

public class SendNoticeEvent extends CommandBase {

	private List<N> sn			= new ArrayList<N>();
	
	private String[] arglist 	= { "请输入公告信息:", "输入方式: 物品类型（5水晶） 物品ID（没有填0） 数量 其他参数" };
	
	@Override
	public void run( PrintWriter out, byte jurisdiction, String... args ) throws Exception {
		
		if( !ServerManager.isOpen() ){
			out.print( "服务器尚未开启!\r\n" );
			return;
		}
		
		N n					= get( out.toString() );
		
		switch( n.status ){
		case 0:
			try {
				n.type		=  Integer.parseInt( args[0] );
			} catch (Exception e) {
				throw new Exception( "参数错误,请重新输入:" );
			}
			
			if( n.type < 0 || n.type >= arglist.length )
				throw new Exception( "参数错误,请重新输入:" );
			
			n.status		= 1;
			throw new Exception( "请输入一个天数: (天数代表玩家在这几天内登陆过 邮件将根据这些玩家发放)" );
		case 1:
			try {
				n.times		=  Integer.parseInt( args[0] );
			} catch (Exception e) {
				throw new Exception( "参数错误,请重新输入:" );
			}
			if( n.times <= 0 ) n.times = 0;
			n.status		= 2;
			throw new Exception( arglist[ n.type ] );
		case 2:
			
			out.print( "正在发送公告...\r\n" );
			out.flush();
			
			int times = n.times * 86400;
			
			if( n.type == 0 ){
				String content		= args[0];
				for( int i = 1; i < args.length; i++ )
					content			+= (" " + args[i]);
				NoticeManager.getInstance().send( "-1|" + content, times );
				NoticeManager.getInstance().addTimely( -1, content );
			}else{
				
				if( args.length < 3 )
					throw new Exception( "参数错误,请重新输入:" );
				
				try {
					AwardType atype	= AwardType.fromNumber( Integer.parseInt( args[0] ) );
					int id			= Integer.parseInt( args[1] );
					int num			= Integer.parseInt( args[2] );
					int[] argus		= null;
					if( args.length > 3 ){
						argus		= new int[args.length-3];
						for( int i = 3; i < args.length; i++ )
							argus[i-3]	= Integer.parseInt( args[i] );
					}
					AwardInfo award	= new AwardInfo(atype, id, num);
					award.setArguments( argus );
					NoticeManager.getInstance().send( award, times );
					
				} catch (Exception e) {
					throw new Exception( "参数错误,请重新输入:" );
				}
			}
			
			
			out.print( "公告发送成功!\r\n" );
			
			return;
		}
	}

	private N get( String key ) {
		
		for( N n : sn ){
			if( n.key.equals( key ) ) return n;
		}
		
		N n = new N();
		n.key = key;
		sn.add( n );
		return n;
	}

	@Override
	public void clear(PrintWriter out) {
		
		for( int i = 0; i < sn.size(); i++ ){
			if( sn.get(i).key.equals( out.toString() ) ){
				sn.remove(i);
				return;
			}
		}
	}

}

class N{
	
	String 		key;
	
	int 		status	= 0;
	
	int 		type	= -1;
	
	int 		times	= 0;
}