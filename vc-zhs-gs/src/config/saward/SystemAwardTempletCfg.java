package config.saward;

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
 * 读取系统奖励配置表
 * @author DXF
 *
 */
public class SystemAwardTempletCfg {

	private static final Map<Integer,SystemAwardTemplet> maps = new HashMap<Integer, SystemAwardTemplet>();
	
	private static final String FILE = SystemCfg.FILE_NAME + "resource/SystemAward.xml";
	
	/**
	 * 通过配置表读取主线关卡模板
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
				Element element = (Element) list.get( i );
				
				SystemAwardTemplet templet = new SystemAwardTemplet( element );
			
				/*******************关闭打印****************************
							System.out.println( templet );
				********************************************************/
				
				SystemAwardTemplet temp = maps.put( templet.m_nId, templet );
				if( temp != null ){
					throw new RuntimeException( "系统奖励" + temp.m_nId + "重复了" );
				}
				
			}
			
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		}   
		
		Logs.debug( "系统奖励 配置文件\t> 解析完毕" );
		
	}
	
	/**
	 * 通过id获取关卡
	 * @param templetId
	 * @return
	 */
	public static SystemAwardTemplet getTempletById( int templetId ){
		return  maps.get( templetId );
	}
	
}
