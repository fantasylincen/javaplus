package config.fetter;

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

public class FetterTempletCfg {
	
	private static final List<FetterTemplet> ls = new ArrayList<FetterTemplet>();
	
	private static final String FILE = SystemCfg.FILE_NAME + "resource/Fetter.xml";
	
	/**
	 * 通过配置表读取Npc战士模板
	 */
		
	public static void init(){
		
		ls.clear();
		
		File file = new File( FILE );
		
		SAXBuilder builder = new SAXBuilder();    
		Document document;
		
		try {
			document = builder.build( file );
			Element root = document.getRootElement();  
			List<?> heroList= root.getChildren( "xml" );
			
			for( int i = 0; i < heroList.size(); i++ ) {
				FetterTemplet ft = new FetterTemplet( (Element)heroList.get(i) );
				ls.add( ft );
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
		
		Logs.debug( "羁    绊 配置文件\t> 解析完毕" );
	}

	public static FetterTemplet get( int id ) {
		for( FetterTemplet fetter : ls ){
			if( fetter.getId() == id ) return fetter;
		}
		return null;
	}

}
