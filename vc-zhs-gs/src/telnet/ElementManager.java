package telnet;

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
 * 命令XML 管理
 * @author DXF
 *
 */
public class ElementManager {
	
	private static final List<ElementBase> maps 		= new ArrayList<ElementBase>();
	
	private static final String FILE 					= SystemCfg.FILE_NAME + "resource/commands.xml";
	
	private static int maxLength						= 0;
	
	static {
		init();
	}
	private static void init(){
		
		File file 			= new File( FILE );
		SAXBuilder builder 	= new SAXBuilder();    
		Document document;
		try {
			document 		= builder.build( file );
			Element root 	= document.getRootElement();  
			List<?> list 	= root.getChildren( "node" ); 
			
			for( int i = 0; i < list.size(); i++ ){
				Element element 	= (Element) list.get( i );
				ElementBase templet = new ElementBase( element );
				
				if( maxLength < templet.getCommand().length() )
					maxLength = templet.getCommand().length();
				
				maps.add( templet );
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}

	public static List<String> getAllContent( byte jurisdiction ) {
		List<String> list = new ArrayList<String>();
		
		for( int i = 0; i < maps.size() - 1; i++ ){
			
			ElementBase e = maps.get(i);
			if( jurisdiction >= e.getJurisdiction() )
				list.add( e.getCommand() +  getAddition( e ) + "\t" + e.getDesc() );
		}
			
		return list;
	}

	private static String getAddition(ElementBase e) {
		
		int l = maxLength - e.getCommand().length();
		
		if( l <= 0 ) return "";
		
		String msg = "";
		for( int i = 0; i < l; i++ )
			msg 	+= " ";
		
		return msg;
	}

	public static ElementBase get(String arg) {
		for( ElementBase e : maps ){
			if( e.getCommand().equals( arg ) )
				return e;
		}
		return null;
	}
	
	
	
	
	
}
