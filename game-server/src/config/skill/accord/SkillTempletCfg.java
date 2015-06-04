package config.skill.accord;

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
public class SkillTempletCfg {
	
	private static final Map<Integer,SkillTemplet> skillTemplets = new HashMap<Integer, SkillTemplet>();
	
	/**
	 * 此配置表必须先于NpcFighterTempletCfg初始化（每个npc都有相应的技能），因此无需提前手动调用
	 */
	static{
//		init();	
	}
	private static final String FILE = SystemCfg.FILE_NAME + "resource/AccordSkill.xml";
	
		
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
				
				id = Integer.parseInt( element.getChildText( "id" ) );
				SkillTemplet templet = new SkillTemplet( id );
				
				templet.setName( element.getChildText( "name" ) );
				templet.setDesc( element.getChildText( "desc" ) );
				templet.setRival( Byte.parseByte( element.getChildText( "rival" ) ) );
				
				String choose = element.getChildText( "choose" );
				
				if( choose != null && !choose.isEmpty() ){
					try {
						templet.setChoose( ChooseFighters.valueOf( choose ) );
						templet.setSkillEffect( parseSkillEffect( element.getChildText( "skillEffect" ) ) );
					} catch (Exception e) {
					}
				}
				
				/*******************关闭打印****************************
					System.out.println( templet );
				 ********************************************************/
	
				SkillTemplet temp = skillTemplets.put( templet.getId(), templet );
				if( temp != null ){
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
			Logs.error( "技能 配置文件：(" + id + ")不能将字符串转成数字 - error：" + e.getMessage() );
		}  
		
		
		Logs.debug( "主动技能 配置文件\t> 解析完毕" );
	}
	
	/**
	 * 解析技能效果：ENEMY_HP,SkillAttackFormula,0|SP,DirectOutputFormula,-20
	 * @param str
	 * @return
	 */
	private static SkillEffect parseSkillEffect( String str ){
		SkillEffect skillEffects = new SkillEffect();
		
		if( str.isEmpty() )
			return skillEffects;
		
		String[] content = str.split( "," );
		
		try {
			skillEffects.setFormula( Formula.valueOf( content[0] ) );
			skillEffects.setArgument( Float.parseFloat( content[1] ) );
		} catch (Exception e) {
		}
		
		return skillEffects;
	}
	
	/**
	 * 通过id获取技能引用
	 * @param templetId
	 * @return
	 */
	public static SkillTemplet getSkillTempletById( int templetId ){
		return skillTemplets.get( templetId );
	}


	public static void main(String[] args) {
		
		for( SkillTemplet t : skillTemplets.values() ){
			
			System.out.println( t );
		}
		
	}

}
