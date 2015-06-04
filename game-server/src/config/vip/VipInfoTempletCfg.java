package config.vip;

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

public class VipInfoTempletCfg {
	
	private static final List<VipInfoTemplet> lists = new ArrayList<VipInfoTemplet>();
	
	private static final String FILE 				= SystemCfg.FILE_NAME + "resource/VipInfo.xml";
	
	public static void init(){
		
		lists.clear();
		
		File file = new File( FILE );
		
		SAXBuilder builder = new SAXBuilder();    
		Document document;
		try {
			document 		= builder.build( file );
			Element root 	= document.getRootElement();  
			List<?> list 	= root.getChildren( "xml" ); 
			
			for( int i = 0; i < list.size(); i++ ){
				Element element = (Element) list.get( i );
				
				VipInfoTemplet templet = new VipInfoTemplet( element );
				lists.add( templet );
			}
			
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		}   
		
		Logs.debug( "VIP信息 配置文件\t> 解析完毕" );
	}
	
	
	public static VipInfoTemplet get( byte id ){
		for( VipInfoTemplet v : lists ) {
			if( v.getId() == id ) return v;
		}
		return null;
	}
	
}
