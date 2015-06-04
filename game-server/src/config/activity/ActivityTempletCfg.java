package config.activity;

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
 * 解析活动配置表
 * @author DXF
 */
public class ActivityTempletCfg {
	
	private static final List<ActivityTemplet> maps = new ArrayList<ActivityTemplet>();
	
	private static final String FILE = SystemCfg.FILE_NAME + "resource/Activity.xml";
	
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
				
				ActivityTemplet templet = new ActivityTemplet( element );
				maps.add(templet);
				
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
	
		Logs.debug( "活    动 配置文件\t> 解析完毕" );
	}
	
	/**
	 * 通过id获取活动
	 * @param templetId
	 * @return
	 */
	public static ActivityTemplet getTempletById( short templetId ){
		for( ActivityTemplet a : maps ){
			if( a.m_nId == templetId )
				return a;
		}
		return null;
	}

	/**
	 * 根据 副本ID 获取 表格ID
	 * @param id
	 * @return
	 */
	public static short getMissionToId( short id ) {
		
		for( ActivityTemplet a : maps ){
			if( a.m_nMissionId == id )
				return a.m_nId;
		}
		
		return 0;
	}

	public static List<ActivityTemplet> getList() {
		return maps;
	}
}
