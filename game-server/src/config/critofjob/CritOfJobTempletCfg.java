package config.critofjob;

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

import config.fighter.Professional;
import config.skill.accord.ChooseFighters;
import define.SystemCfg;

/**
 * 暴击to职业
 * @author DXF
 *
 */
public class CritOfJobTempletCfg {
	
	private static final Map<Professional,CritOfJobTemplet> data 	= new HashMap<Professional, CritOfJobTemplet>();
	
	private static final String FILE 							= SystemCfg.FILE_NAME + "resource/CritOfJob.xml";
	
	
	/**
	 * 通过配置表读取职业暴击
	 */
	public static void init(){
		
		data.clear();
		
		File file = new File( FILE );
		
		SAXBuilder builder = new SAXBuilder();    
		Document document;
		try {
			document = builder.build( file );
			Element root = document.getRootElement();  
			List<?> list = root.getChildren( "xml" ); 
			
			for( int i = 0; i < list.size(); i++ ){
				Element element = (Element) list.get( i );
				
				CritOfJobTemplet templet = new CritOfJobTemplet( element );
				
				if( data.put( templet.getProfessional(), templet ) != null ){
					throw new RuntimeException( "职业暴击" + templet.getProfessional() + "重复了" );
				}
			}
			
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		}   
		
		Logs.debug( "职业暴击 配置文件\t> 解析完毕" );
		
	}
	
	public static float get( Professional professional, ChooseFighters chooseFighters ){
		
		CritOfJobTemplet c 	= data.get(professional);
		if( c == null ) return 1f;
		
		float[] odds 		= c.getCriticalDamage();
		
		int index			= chooseFighters.getCount() - 1 ;
		if( index < 0 || index >= odds.length )
			return 1f;
		
		return odds[index];
	}
}
