package config.fighter;

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
 *  英雄配置表
 * @author DXF
 *
 */
public class HeroTempletCfg {

	private static final Map<Integer,HeroTemplet> heroTemplets = new HashMap<Integer, HeroTemplet>();
	
	private static final String FILE = SystemCfg.FILE_NAME + "resource/Hero.xml";
	
		
	/**
	 * 通过配置表读取Npc战士模板
	 */
		
	public static void init(){
		
		heroTemplets.clear();
		
		File file = new File( FILE );
		
		SAXBuilder builder = new SAXBuilder();    
		Document document;
		HeroTemplet ft = null;
		
		try {
			document = builder.build( file );
			Element root = document.getRootElement();  
			List<?> heroList= root.getChildren( "xml" );
			
			for( int i = 0; i < heroList.size(); i++ )
			{
				ft = new HeroTemplet( (Element)heroList.get(i) );
				
				if( heroTemplets.put( ft.id, ft ) != null ){
					throw new RuntimeException( "npc战士模板" + ft.id + "重复了" );
				}
			}
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() + "at=" + (ft != null ? ft.id + "" : "null" ) );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() + "at=" + (ft != null ? ft.id + "" : "null" ) );
		}   
		
		Logs.debug( "英    雄 配置文件\t> 解析完毕" );
	}
	
	/**
	 * 通过id获取战士的引用
	 * @param templetId
	 * @return
	 */
	public static HeroTemplet getById( int templetId ){
		return heroTemplets.get( templetId );
	}
	
	public static int getByName( String name ) {
		for( HeroTemplet hero : heroTemplets.values() ){
			if( hero.name.equals( name ) )
				return hero.id;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		init();
		System.out.println( getById( 100101 ) );
	}


}
