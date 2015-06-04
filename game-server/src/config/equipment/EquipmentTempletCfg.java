package config.equipment;

import game.equipment.EColour;
import game.log.Logs;
import game.util.DragonAwardManager;

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
 * 装备解析
 * @author DXF
 */
public class EquipmentTempletCfg {
	
	private static final Map<Integer,EquipmentTemplet> data 	= new HashMap<Integer, EquipmentTemplet>();
	
	private static final String FILE 							= SystemCfg.FILE_NAME + "resource/Equipment.xml";
	
	
	/**
	 * 通过配置表读取职业暴击
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
				
				EquipmentTemplet templet = new EquipmentTemplet( element );
				
				if( data.put( templet.getNId(), templet ) != null ){
					throw new RuntimeException( "职业暴击" + templet.getNId() + "重复了" );
				}
				
				if( templet.getColor() == EColour.WHITE )
					DragonAwardManager.prop.add( templet.getNId() );
			}
			
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		}   
		
		Logs.debug( "装    备 配置文件\t> 解析完毕" );
	}
	
	public static EquipmentTemplet get( int templetId ){
		return data.get(templetId);
	}

	public static int getByName( String name, String color ) {
		for( EquipmentTemplet e : data.values() ){
			if( e.getName().equals( name ) && e.getColor().toName().equals(color) )
				return e.getNId();
		}
		return 0;
	}
	
}
