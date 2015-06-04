package config.illegal;

import game.log.Logs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 非法字符串
 * @author DXF
 */
public class IllegalCharCfg {
	
	public static final List<String> lists 	= new ArrayList<String>();
	
	private static final String FILE 		= "config/words_outlaw.txt";

	/**
	 * @throws IOException 
	 */
	public static void init() {
		
		lists.clear();
		
		try {
			
			BufferedReader in = new BufferedReader( new FileReader( FILE ) );

			String s;

			while ((s = in.readLine()) != null)
			{
				lists.add( s );
			}
			
			in.close();
		}catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		}   
	
		Logs.debug( "非法字符 配置文件\t> 解析完毕" );
	}
	
	public static void main( String[] args ){
		
		IllegalCharCfg.init();
	}
	
}
