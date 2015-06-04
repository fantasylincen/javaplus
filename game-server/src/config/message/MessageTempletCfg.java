package config.message;

import game.log.Logs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import define.SystemCfg;

/**
 * 公告
 * @author DXF
 */
public class MessageTempletCfg {
	
	private static final List<MessageTemplet> maps = new ArrayList<MessageTemplet>();
	
	private static final String FILE = SystemCfg.FILE_NAME + "resource/Message.xml";
	
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
				
				MessageTemplet templet = new MessageTemplet( element );
				
				maps.add(templet);
				
			}
			
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		}   
		
		Logs.debug( "公告内容 配置文件\t> 解析完毕" );
		
	}
	
	/**
	 * 通过id获取关卡
	 * @param templetId
	 * @return
	 */
	public static MessageTemplet getTempletById( int templetId ){
		for( MessageTemplet m : maps )
			if( m.getId() == templetId )
				return m;
		return null;
	}
	
}
