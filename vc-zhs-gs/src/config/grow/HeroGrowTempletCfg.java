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
 * 英雄属性成长配置表
 * @author DXF
 *
 */
public class HeroGrowTempletCfg {

	private static final Map<Short,HeroGrowTemplet> heroGrow = new HashMap<Short, HeroGrowTemplet>();
	
	private static final String FILE = SystemCfg.FILE_NAME + "resource/HeroGrow.xml";
	
	/**
	 * 通过配置表读取玩家信息
	 */
	public static void init(){
		
		heroGrow.clear();
		
		File file = new File( FILE );
		
		SAXBuilder builder = new SAXBuilder();    
		Document document;
		try {
			document = builder.build( file );
			Element root = document.getRootElement();  
			List<?> list = root.getChildren( "xml" );
			
			for( int i = 0; i < list.size(); i++ ){
				Element element = (Element) list.get( i );
				
				HeroGrowTemplet templet = new HeroGrowTemplet( element );
			
				HeroGrowTemplet temp = heroGrow.put( templet.getLevel(), templet );
				if( temp != null ){
					throw new RuntimeException( "英雄属性成长" + temp.getLevel() + "重复了" );
				}
				
			}
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		}   
		
		Logs.debug( "英雄成长 配置文件\t> 解析完毕" );
	}
	
	
	/**
	 * 通过等级获取玩家属性成长
	 * @param templetId
	 * @return
	 */
	public static HeroGrowTemplet getTempletById( short level ){
		return  heroGrow.get( level );
	}
	
	public static void main(String[] args) {
		init();
		System.out.println( getTempletById((short)110) );
	}
}
