package config.loot;

import game.log.Logs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import define.SystemCfg;

/**
 * 抢夺金币比例
 * @author DXF
 */
public class LootScaleTempletCfg {

	private static final List<LootScaleTemplet> maps = new ArrayList<LootScaleTemplet>();
	
	private static final String FILE = SystemCfg.FILE_NAME + "resource/LootScale.xml";
	
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
				float scale					= Float.parseFloat( element.getChildText( "scale" ) );
				
				LootScaleTemplet templet 	= new LootScaleTemplet( nid, scale );
				maps.add( templet );
			}
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		}   
	
		Logs.debug( "抢夺金币 配置文件\t> 解析完毕" );
	}
	
	/**
	 * 根据玩家金币 算出抢夺金币
	 * @param cash
	 * @return
	 */
	public static int lootToCash( int cash ){
		
		LootScaleTemplet loot = null;
		
		for( int i = 0; i < maps.size(); i++ ){
			loot 	= maps.get(i);
			if( cash <= loot.getId() ) break;
		}
		
		if( loot == null ) return 0;
		
		return (int) (cash * loot.getScale());
	}
	
	
}
