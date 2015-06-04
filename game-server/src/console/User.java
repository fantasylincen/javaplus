package console;

import java.util.ArrayList;
import java.util.List;

import deng.xxoo.utils.XOConfig;


/**
 * 用户
 * @author DXF
 *
 */
public class User {
	
	private static List<Root> ls = new ArrayList<Root>();
			
	public static void init(){
		XOConfig c = XOConfig.createConfig( "src/console/user.ini" );
		String[] r 	= c.getValue( "root" ).split( "," );
		String[] p 	= c.getValue( "ip" ).split( "," );
		if( r.length != p.length ) return;
		
		for( int i = 0; i < r.length; i++ ){
			Root x 	= new Root();
			x.root	= r[i];
			x.ip	= p[i];
			ls.add( x );
		}
	}
			
	public boolean isHave( String ip ){
		for( Root r : ls ){
			if( r.ip.equals( ip ) ) return true;
		}
		return false;
	}
	
	public static String getRoot( String ip ){
		for( Root r : ls ){
			if( r.ip.equals( ip ) ) 
				return r.root;
		}
		return "";
	}
	
}

class Root{
	String root;
	String ip;
}