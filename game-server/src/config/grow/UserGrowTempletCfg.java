package config.grow;


import game.log.Logs;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import define.SystemCfg;

/**
 * 玩家属性成长 配置表
 * @author DXF
 *
 */
public class UserGrowTempletCfg {

	private static final Map<Short,UserGrowTemplet> user = new HashMap<Short, UserGrowTemplet>();
	
	private static final String FILE =  SystemCfg.FILE_NAME + "resource/PlayerGrow.xml";
	
	/**
	 * 通过配置表读取玩家信息
	 */
	public static void init(){
		
		user.clear();
		
		File file = new File( FILE );
		
		SAXBuilder builder = new SAXBuilder();    
		Document document;
		try {
			document = builder.build( file );
			Element root = document.getRootElement();  
			List<?> list = root.getChildren( "xml" );
			
			for( int i = 0; i < list.size(); i++ ){
				Element element = (Element) list.get( i );
				
				UserGrowTemplet templet = new UserGrowTemplet( element );
			
				UserGrowTemplet temp = user.put( templet.getLevel(), templet );
				if( temp != null ){
					throw new RuntimeException( "玩家属性成长:" + temp.getLevel() + "重复了" );
				}
				
			}
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		}   
		
		Logs.debug( "玩家成长 配置文件\t> 解析完毕" );
	}
	
	
	/**
	 * 通过等级获取玩家属性成长
	 * @param templetId
	 * @return
	 */
	public static UserGrowTemplet getTempletById( short templetId ){
		return  user.get( templetId );
	}
	
	public static void main(String[] args) {
		init();
		System.out.println( getTempletById((short)110) );
	}
}
