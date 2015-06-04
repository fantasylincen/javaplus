package telnet;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

public class ElementBase {

	
	private final String 			command;
	
	private final String			desc;
	
	private final List<String> 		tips;
	
	private final byte 				isLaunch;
	
	private byte					jurisdiction ;
	
	public ElementBase( Element element ){
		
		command			= element.getChildText( "command" );
		desc			= element.getChildText( "desc" );
		tips			= maping( element.getChildText( "tip" ) );
		isLaunch		= Byte.parseByte( element.getChildText( "launch" ) );
		
		try {
			jurisdiction = Byte.parseByte( element.getChildText( "jurisdiction" ) );
		} catch (Exception e) {
			jurisdiction = (byte)3;
		}
	}
	
	public byte getJurisdiction(){
		return jurisdiction;
	}
	
	public String getCommand(){
		return command;
	}
	
	public String getDesc(){
		return desc;
	}
	
	public List<String> getTips(){
		return tips;
	}
	
	private List<String> maping(String childText) {
		
		List<String> list = new ArrayList<String>();
		
		if( childText != null && !childText.isEmpty() )
		{
			String[] content = childText.split(",");
			for( String s : content )
				list.add( s ) ;
		}
		
		return list;
	}

	/**
	 * 是否需要参数
	 * @return
	 */
	public boolean isNeedArgs() {
		return isLaunch == 1;
	}
}
