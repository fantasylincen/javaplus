package config.skill;

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
 * 被动 技能 读取
 * @author DXF
 *
 */
public class PassiveSkillTempletCfg {
	
	private static final Map<Integer,PassiveSkillTemplet> skillTemplets = new HashMap<Integer, PassiveSkillTemplet>();
	
	private static final String FILE = SystemCfg.FILE_NAME + "resource/PassiveSkill.xml";
	
	/**
	 * 通过配置表读取Npc战士模板
	 */
	public static void init(){
		
		skillTemplets.clear();
		
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
				
				PassiveSkillTemplet templet = new PassiveSkillTemplet( element );
				
				/*******************关闭打印****************************
					System.out.println( templet );
				 ********************************************************/
	
				if( skillTemplets.put( templet.getId(), templet ) != null ){
					throw new RuntimeException( "技能" + templet.getId() + "重复了" );
				}
				
			}
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch ( NumberFormatException e ){
			Logs.error( "被动技能 配置文件：(" + id + ")不能将字符串转成数字 - error：" + e.getMessage() );
		}  
		
		
		Logs.debug( "被动技能 配置文件\t> 解析完毕" );
	}
	
	/**
	 * 通过id获取技能引用
	 * @param templetId
	 * @return
	 */
	public static PassiveSkillTemplet getById( int templetId ){
		return skillTemplets.get( templetId );
	}
}
