package config.tefineith;

import game.fighter.NpcFighter;
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

import config.fighter.NpcFighterTemplet;
import config.fighter.NpcFighterTempletCfg;

import define.SystemCfg;

/**
 * 试炼表格
 * @author DXF
 */
public class RefineithFireTempletCfg {
	
	private static final Map<Integer,RefineithFireTemplet> maps = new HashMap<Integer, RefineithFireTemplet>();
	
	private static final String FILE = SystemCfg.FILE_NAME + "resource/RefineithFire.xml";
	
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
				Element element = (Element) list.get( i );
				
				RefineithFireTemplet templet = new RefineithFireTemplet( element );
			
				RefineithFireTemplet temp = maps.put( templet.m_nId, templet );
				if( temp != null ){
					throw new RuntimeException( "试炼英雄" + temp.m_nId + "重复了" );
				}
				
			}
			
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		}   
		
		Logs.debug( "试炼英雄 配置文件\t> 解析完毕" );
		
	}

	/**
	 * 随机一个英雄出来
	 * @param id -1洛克萨斯 -2德玛西亚 -3战争学院
	 * @param b 是否boss
	 * @param type 0普通试炼 1英雄试炼
	 * @return
	 */
	public static NpcFighter get( int id, boolean b, int type ) {
		
		RefineithFireTemplet r 	= maps.get(id);
		NpcFighterTemplet npc	= r.get( type, b ? 1 : 0 );
		
		NpcFighter n			= new NpcFighter( npc );
		float odds				= 100f; // boss掉落几率
		if( !b ) odds			= 2f;	// 小卡掉落几率
		
		n.getAwardContent().clear();
		n.getAwardContent().addAll( npc.getRAward( odds, type ) );
		
		return n;
	}

	/**
	 * 根据ID 获取怪物  只有Boss
	 * @param id
	 * @return
	 */
	public static NpcFighter get( int id, int type ) {
		
		NpcFighterTemplet npc	= NpcFighterTempletCfg.getNpcById( id );
		
		NpcFighter n			= new NpcFighter( npc );
		float odds				= 100f; // boss掉落几率
		
		n.getAwardContent().clear();
		n.getAwardContent().addAll( npc.getRAward( odds, type ) );
		
		return n;
	}

	/**
	 * 只获取ID  专为试炼BOSS做
	 * @param id
	 * @param b
	 * @param indexOf
	 * @return
	 */
	public static int getId( int id, boolean b, int indexOf ) {
		RefineithFireTemplet r 	= maps.get(id);
		NpcFighterTemplet npc	= r.get( indexOf, b ? 1 : 0 );
		return npc.templetId;
	}
	
}
