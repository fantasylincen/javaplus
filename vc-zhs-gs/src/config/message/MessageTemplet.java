package config.message;


import org.jdom2.Element;


public class MessageTemplet {
	
	private final int 			id;
	
	private final String 		content;

	public MessageTemplet(Element element) {
		id				= Integer.parseInt( element.getChildText( "id" ) );
		content			= element.getChildText( "content" );
	}

	public int getId(){
		return id;
	}
	
	public String getContent(){
		return content;
	}
	
}
