package config.luckydraw;

import game.growup.Quality;
import game.log.Logs;
import game.util.heroGrowup.HeroGrowupFormula;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import config.fighter.HeroTemplet;
import config.fighter.HeroTempletCfg;

import define.SystemCfg;

/**
 * 抽奖 
 * @author DXF
 *
 */
public class LuckydrawTempletCfg {

	private static final Map<Byte,LuckydrawHandle> maps = new HashMap<Byte, LuckydrawHandle>();
	
	private static final String FILE = SystemCfg.FILE_NAME + "resource/Luckydraw.xml";
	
	// 默认颜色
	private static byte   defaultColour;
	
	
	/**
	 * 通过配置表读取抽奖模板
	 */
	public static void init(){
		
		maps.clear();
		
		File file = new File( FILE );
		
		SAXBuilder builder = new SAXBuilder();    
		Document document;
		try {
			document = builder.build( file );
			Element root = document.getRootElement();  
			List<?> list = root.getChildren( "xml" ); 
			
			for( int i = 0; i < list.size(); i++ ){
				Element element 			= (Element) list.get( i );
				
				int nid						= Integer.parseInt( element.getChildText( "id" ) );
				HeroTemplet heroTemplet		= HeroTempletCfg.getById( nid );
				if( heroTemplet == null )
					throw new RuntimeException( "抽奖几率" + nid + " 在英雄配置表里没找到!" );
				// 如果以后有碎片那么就在这里判断
				// TODO
				
				byte actualNum				= Byte.parseByte( element.getChildText( "quality" ) );
				byte isSpecial				= Byte.parseByte( element.getChildText( "isSpecial" ) );
				int rand					= Integer.parseInt( element.getChildText( "rand" ) );
				
				Quality quality				= HeroGrowupFormula.getActualToQuality( new Quality(heroTemplet.qualityMax, heroTemplet.qualityMax, (byte)0), actualNum );
				
				LuckydrawTemplet templet 	= new LuckydrawTemplet( nid, quality, rand );
				
				LuckydrawHandle handle		= maps.get( actualNum );
				if( handle == null ){
					handle = new LuckydrawHandle();
					handle.add( templet, isSpecial );
					maps.put( actualNum, handle );
				}else{
					handle.add( templet, isSpecial );
				}
				defaultColour				= actualNum;
			}
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		}   
	
		Logs.debug( "抽奖几率 配置文件\t> 解析完毕" );
	}
	
	public static LuckydrawHandle getTempletById( byte id ) {
		return maps.get(id);
	}

	public static LuckydrawHandle getDefault(){
		return maps.get( defaultColour );
	}
}
