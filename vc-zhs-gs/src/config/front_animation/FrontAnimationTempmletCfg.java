package config.front_animation;

import game.fighter.FighterBase;
import game.fighter.Hero;
import game.log.Logs;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import define.SystemCfg;

/**
 * 开头动画 配置表
 * @author DXF
 */
public class FrontAnimationTempmletCfg {

	private static FrontAnimationTemplet data[]	= new FrontAnimationTemplet[2];
	
	private static final String FILE 						= SystemCfg.FILE_NAME + "resource/FrontAnimation.xml";
	
	/**
	 * 通过配置表读取玩家信息
	 */
	public static void init(){
		
		data[0] = null;
		File file = new File( FILE );
		SAXBuilder builder = new SAXBuilder();    
		Document document;
		try {
			document 		= builder.build( file );
			Element root 	= document.getRootElement();  
			List<?> list 	= root.getChildren( "xml" );
			
			for( int i = 0; i < list.size(); i++ ){
				Element element = (Element) list.get( i );
				
				data[0] = new FrontAnimationTemplet( element );
			}
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		}   
		
		data[1] = null;
		file = new File( SystemCfg.FILE_NAME + "resource/FrontAnimation1.xml" );
		builder = new SAXBuilder();    
		try {
			document 		= builder.build( file );
			Element root 	= document.getRootElement();  
			List<?> list 	= root.getChildren( "xml" );
			
			for( int i = 0; i < list.size(); i++ ){
				Element element = (Element) list.get( i );
				
				data[1] = new FrontAnimationTemplet( element );
			}
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		}   
		
		Logs.debug( "开场动画 配置文件\t> 解析完毕" );
	}
	
	
	/**
	 * 通过等级获取玩家属性成长
	 * @param templetId
	 * @return
	 */
	public static List<Hero> getHero( int at ){
		
		
		
		
		return data[at].getData();
	}
	public static List<FighterBase> getEnemy( int at ){
		return data[at].getEnemy();
	}
}
