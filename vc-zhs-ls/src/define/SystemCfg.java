package define;

import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import util.SystemTimer;

/**
 * 保留系统信息，诸如启动时间，开服时间等一系列参数
 * @author DXF
 *
 */
public class SystemCfg {

	private static final String FILE 		= "config/system.xml";
	
	/**
	 * 系统本次启动时间
	 */
	public static final long 	START_MILS 	= SystemTimer.currentTimeMillis();
	
	/**
	 * 游戏服务器端口
	 */
	public static int  	        GAME_PORT;
	
	/**
	 * 用户端口
	 */
	public static int  	        USER_PORT;
	
	/**
	 * 系统版本
	 */
	public static String	    VERSION;

	/**
	 * 充值接口端口
	 */
	public static int RECHARGE_PORT;
	
	
	static{
		SAXBuilder builder = new SAXBuilder();    
		Document document;
		try {
			document 		= builder.build( FILE );
			Element root 	= document.getRootElement();  
			GAME_PORT 		= Integer.parseInt( root.getChildText( "game_port" ) );
			USER_PORT 		= Integer.parseInt( root.getChildText( "user_port" ) );
			RECHARGE_PORT 		= Integer.parseInt( root.getChildText( "recharge_port" ) );
			VERSION 		= root.getChildText( "version" );

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}	
}
