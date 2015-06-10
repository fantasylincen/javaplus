package config.mission;

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

public class TollgateTempletCfg {

	private static final Map<Integer,TollgateTemplet> tollgate = new HashMap<Integer, TollgateTemplet>();
	
	private static final String FILE = SystemCfg.FILE_NAME + "resource/Tollgate.xml";
	
	/**
	 * 通过配置表读取主线关卡模板
	 */
	public static void init(){
		
		tollgate.clear();
		
		File file = new File( FILE );
		
		SAXBuilder builder = new SAXBuilder();    
		Document document;
		try {
			document = builder.build( file );
			Element root = document.getRootElement();  
			List<?> list = root.getChildren( "xml" ); 
			
			for( int i = 0; i < list.size(); i++ ){
				Element element = (Element) list.get( i );
				
				TollgateTemplet templet = new TollgateTemplet( element );
			
				/*******************关闭打印****************************
							System.out.println( templet );
				********************************************************/
				
				TollgateTemplet temp = tollgate.put( templet.getId(), templet );
				if( temp != null ){
					throw new RuntimeException( "关卡" + temp.getId() + "重复了" );
				}
				
				
			}
			
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		}   
		
		Logs.debug( "关    卡 配置文件\t> 解析完毕" );
		
	}
	
	
	/**
	 * 通过id获取
	 * @param templetId
	 * @return
	 */
	public static TollgateTemplet getTempletById( int templetId ){
		return  tollgate.get( templetId );
	}
	
	
}
