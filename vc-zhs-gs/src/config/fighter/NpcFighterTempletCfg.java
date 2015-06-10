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
 * 从配置表中初始化Npc Fighter模板
 * @author liukun
 *
 */
public class NpcFighterTempletCfg {
	private static final Map<Integer,NpcFighterTemplet> npcFighterTemplets = new HashMap<Integer, NpcFighterTemplet>();
	
	private static final String FILE = SystemCfg.FILE_NAME + "resource/Npc.xml";
	
		
	/**
	 * 通过配置表读取Npc战士模板
	 */
	public static void init(){
		
		npcFighterTemplets.clear();
		
		File file = new File( FILE );
		
		SAXBuilder builder = new SAXBuilder();    
		Document document;
		try {
			document = builder.build( file );
			Element root = document.getRootElement();  
			List<?> fighterList= root.getChildren( "xml" ); 
			
			for( int i = 0; i < fighterList.size(); i++ ){
				NpcFighterTemplet ft = new NpcFighterTemplet( (Element)fighterList.get(i) );
				if( npcFighterTemplets.put( ft.templetId, ft ) != null ){
					throw new RuntimeException( "npc战士模板" + ft.templetId + "重复了" );
				}
				
			}
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		}   
		
		Logs.debug( "怪    物 配置文件\t> 解析完毕" );
	}
	
	
	/**
	 * 通过id获取战士的引用
	 * @param templetId
	 * @return
	 */
	public static NpcFighterTemplet getNpcById( int templetId ){
		return npcFighterTemplets.get( templetId );
	}
	public static void main(String[] args) {
		init();
		int id = 101101;
		System.out.println( getNpcById( id ) );
	}

}
