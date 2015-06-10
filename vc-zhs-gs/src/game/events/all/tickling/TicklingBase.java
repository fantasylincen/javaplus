package game.events.all.tickling;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import user.UserManager;
import util.UtilBase;


/**
 * 反馈信息基础类
 * @author DXF
 */
public class TicklingBase {
	
	public int 			id;
	
	public String[]		content = null;
	
	public int			uid;
	
	public String[]		reply = null;

	public int time;

	public String getContent() {
		StringBuilder sb = new StringBuilder();
		if( content == null ) return "";
		for( int i = 0; i < content.length; i++ ){
			sb.append( content[i] );
			if( i < (content.length - 1) ){
				sb.append( "&." );
			}
		}
		return sb.toString();
	}

	public String getReplyToString() {
		StringBuilder sb = new StringBuilder();
		if( reply == null ) return "";
		for( int i = 0; i < reply.length; i++ ){
			sb.append( reply[i] );
			if( i < (reply.length - 1) ){
				sb.append( "&." );
			}
		}
		return sb.toString();
	}
	
	public String toContent() {
		StringBuilder temp = new StringBuilder();
		temp.append( "(" + id + ")【" + UtilBase.secondsToDateStr( time ) + "】" );
		temp.append( ( reply == null ) ? "◇" : "◆" );
		temp.append( " " + UserManager.getInstance().getNickName(uid) + "：" );
		temp.append( "{" + content[0].split( ";" )[1] + "}" );
		return temp.toString();
	}

	public void toOut( PrintWriter out ) {
		
		String name = UserManager.getInstance().getNickName( uid );
		List<structure> xx = new ArrayList<structure>();
		
		if( content != null )
			for( String s : content ){
				if( s.isEmpty() ) continue;
				structure sc = new structure();
				sc.time	= Integer.parseInt( s.split( ";" )[0] );
				sc.content = name + "：" + s.split( ";" )[1];
				xx.add( sc );
			}
	
		if( content != null )
			for( String s : reply ){
				if( s.isEmpty() ) continue;
				structure sc = new structure();
				sc.time	= Integer.parseInt( s.split( ";" )[0] );
				sc.content = "system：" + s.split( ";" )[1];
				xx.add( sc );
			}
		
		// 排序
		Collections.sort( xx, posComparator );
		
		int len = xx.size();
		for( int i = 0; i < len; i++ ){
			structure x = xx.get(i);
			out.print( "             " + UtilBase.secondsToDateStr( x.time ) + "\r\n" );
			out.print( x.content + "\r\n" );
		}
		
	}

	public static final Comparator<structure> posComparator = new Comparator<structure>(){
	        @Override
	        public int compare( structure f1, structure f2 ) {
	            return f1.time - f2.time;
	        }
	};
	
}


class structure{
	int time;
	String content;
}

