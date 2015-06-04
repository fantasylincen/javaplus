package config.talent;

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
 * 天赋属性
 * @author DXF
 *
 */
public class TalentValueTempletCfg {
	private static final Map<Short,TalentValueTemplet> data = new HashMap<Short, TalentValueTemplet>();
	
	private static final String FILE = SystemCfg.FILE_NAME + "resource/InnateSkillNV.xml";
	
	
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
				
				TalentValueTemplet templet = new TalentValueTemplet( element );
				
				if( data.put( templet.getId(), templet ) != null ){
					throw new RuntimeException( "天赋属性" + templet.getId() + "重复了" );
				}
			}
			
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		}   
		
		Logs.debug( "天赋属性 配置文件\t> 解析完毕" );
		
	}
	
	
	/**
	 * 通过id获取天赋
	 * @param templetId
	 * @return
	 */
	public static TalentValueTemplet getTempletById( int templetId ){
		return  data.get( (short)templetId );
	}
}
