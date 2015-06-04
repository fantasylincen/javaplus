package config.dangrading;

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
 * 匹配奖励
 * @author DXF
 */
public class MateAwardTempletCfg {
	
	private static final List<MateAwardTemplet> data = new ArrayList<MateAwardTemplet>();
	
	private static final String FILE = SystemCfg.FILE_NAME + "resource/MateAward.xml";
	
	
	/**
	 * 通过配置表读取天赋模板
	 */
	public static void init(){
		
		data.clear();
		
		File file 			= new File( FILE );
		SAXBuilder builder 	= new SAXBuilder();    
		Document document;
		try {
			document = builder.build( file );
			Element root = document.getRootElement();  
			List<?> list = root.getChildren( "xml" ); 
			
			for( int i = 0; i < list.size(); i++ ){
				Element element = (Element) list.get( i );
				
				MateAwardTemplet templet = new MateAwardTemplet( element );
				
				if( data.indexOf(templet) != -1 ){
					throw new RuntimeException( "匹配奖励" + templet.getId() + "重复了" );
				}else{
					data.add( templet );
				}
			}
			
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		}   
		
		Logs.debug( "匹配奖励 配置文件\t> 解析完毕" );
		
	}
	
	public static MateAwardTemplet get( byte id ) {
		for( MateAwardTemplet l : data )
			if( l.getId() == id ) return l;
		return null;
	}

	public static List<MateAwardTemplet> getLits() {
		return data;
	}
	
}
