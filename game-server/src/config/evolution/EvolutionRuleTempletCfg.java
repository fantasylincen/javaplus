package config.evolution;

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
 * 读取进化规则 
 * @author DXF
 *
 */
public class EvolutionRuleTempletCfg {

	private static final Map<Byte,EvolutionRuleTemplet> data = new HashMap<Byte, EvolutionRuleTemplet>();
	
	private static final String FILE = SystemCfg.FILE_NAME + "resource/EvolutionRule.xml";
	
	
	/**
	 * 通过配置表读取天赋模板
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
				
				EvolutionRuleTemplet templet = new EvolutionRuleTemplet( element );
				
				if( data.put( templet.getId(), templet ) != null ){
					throw new RuntimeException( "进化规则" + templet.getId() + "重复了" );
				}
			}
			
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		}   
		
		Logs.debug( "进化规则 配置文件\t> 解析完毕" );
		
	}
	
	public static EvolutionRuleTemplet getTempletById( byte actualNum ) {
		return data.get( actualNum );
	}

}
