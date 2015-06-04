package config.skill.captain;

import game.log.Logs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import config.fighter.Professional;

import define.SystemCfg;

/**
 * 队长技能 读取s
 * @author DXF
 *
 */
public class CaptainSkillTempletCfg {
	
	private static final Map<Byte,List<List<CaptainSkillTemplet>>> map 		= new HashMap<Byte, List<List<CaptainSkillTemplet>>>();
	private static final Map<Integer,CaptainSkillTemplet> captains 			= new HashMap<Integer, CaptainSkillTemplet>();
	
	private static final String FILE 		= SystemCfg.FILE_NAME + "resource/CaptainSkill.xml";
	
	// 技能最高等级
	private static final int maxSkillLv 	= 5;
	
	/**
	 * 通过配置表读取Npc战士模板
	 */
	public static void init(){
		
		map.clear();
		captains.clear();
		
		File file = new File( FILE );
		
		SAXBuilder builder 	= new SAXBuilder();    
		Document document;
		int id 				= 0;
		try {
			document = builder.build( file );
			Element root = document.getRootElement();  
			List<Element> fighterList= root.getChildren( "xml" ); 
			
			for( int i = 0; i < fighterList.size(); i++ ){
				Element element = (Element) fighterList.get( i );
				
				CaptainSkillTemplet templet = new CaptainSkillTemplet( element );
				List<List<CaptainSkillTemplet>> list = map.get( templet.getProfession().toNumber() );
				if( list != null ){
					int index = -1;
					while( list.get( ++index ).size() == maxSkillLv && index + 1 < list.size() );
					
					if( list.get(index).size() != maxSkillLv ){
						list.get(index).add( templet );
					}else{
						List<CaptainSkillTemplet> temp 	= new ArrayList<CaptainSkillTemplet>();
						temp.add( templet );
						list.add( temp );
					}
					
				}else{
					List<CaptainSkillTemplet> temp 	= new ArrayList<CaptainSkillTemplet>();
					temp.add( templet );
					list							= new ArrayList<List<CaptainSkillTemplet>>();
					list.add( temp );
					map.put( templet.getProfession().toNumber(), list );
				}
				
				/*******************关闭打印****************************
					System.out.println( templet );
				 ********************************************************/
	
				if( captains.put( templet.getId(), templet ) != null ){
					throw new RuntimeException( "队长技能" + templet.getId() + "重复了" );
				}
				
			}
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch ( NumberFormatException e ){
			Logs.error( "队长技能 配置文件：(" + id + ")不能将字符串转成数字 - error：" + e.getMessage() );
		}  
		
		
		Logs.debug( "队长技能 配置文件\t> 解析完毕" );
	}

	/**
	 * 通过职业获取技能引用
	 * @param templetId
	 * @return
	 */
	public static List<List<CaptainSkillTemplet>> getByList( Byte templetId ){
		return map.get( templetId );
	}
	
	/**
	 * 通过id获取技能引用
	 * @param templetId
	 * @return
	 */
	public static CaptainSkillTemplet getById( int templetId ) {
		return captains.get( templetId );
	}
	
	public static void main( String[] arg ){
		
		init();
		
		List<List<CaptainSkillTemplet>> list = getByList( Professional.GOD.toNumber() );
		
		for( int i = 0; i < list.size(); i++ ){
			System.out.println( list.get(i) );
		}
		
		
	}

}
