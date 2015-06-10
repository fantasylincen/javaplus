package config.dangrading;

import game.log.Logs;
import game.pvp.DanGrad;

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
 * 段位提升 规则 和 福利
 * @author DXF
 */
public class LevelPromoteRuleTempletCfg {

	private static final List<LevelPromoteRuleTemplet> data = new ArrayList<LevelPromoteRuleTemplet>();
	
	private static final String FILE = SystemCfg.FILE_NAME + "resource/LevelPromoteRule.xml";
	
	
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
				
				LevelPromoteRuleTemplet templet = new LevelPromoteRuleTemplet( element );
				
				if( data.indexOf(templet) != -1 ){
					throw new RuntimeException( "段位规则" + templet.getDanGrad() + "重复了" );
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
		
		Logs.debug( "段位规则 配置文件\t> 解析完毕" );
		
	}
	
	public static LevelPromoteRuleTemplet get( DanGrad danGrad ) {
		for( LevelPromoteRuleTemplet l : data )
			if( l.getDanGrad() == danGrad ) return l;
		return null;
	}
	
}
