package console;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;


/**
 * 命令
 * @author DXF
 */
public class CommandManager {
	// 所有命令列表
	private static List<Command> ls = new ArrayList<Command>();
	
	public static void init(){
		try {
			Element element = new SAXBuilder().build( new File( "src/console/command.xml" ) ).getRootElement();
			
			read( null, ls, element );

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	private static void read( Command head, List<Command> x, Element element ) {
		List<Element> l = element.getChildren( "node" );
		if( l == null || l.isEmpty() ) return;
		
		for( Element e : l ){
			Command cand 	= new Command( );
			cand.head		= head;
			cand.command 	= e.getChildText( "command" );
			cand.desc		= e.getChildText( "desc" );
			if( cand.desc == null ) cand.desc = "";
			
			x.add( cand );
			read( cand, cand.nexts, e );
		}
	}
	
	public static Command isExist( Command curCommand, String command ) {
		if( curCommand == null ){
			return getCommand( command );
		}else{
			return curCommand.getNextCommand( command );
		}
	}
	
	
	private static Command getCommand( String command ) {
		for( Command c : ls ){
			if( c.command.equals( command ) ) return c;
		}
		return null;
	}
	
	public static String showLs() {
		String reslut = "";
		for( Command c : ls ){
			if( !reslut.isEmpty() ) reslut += "\r\n";
			reslut += c.toString();
		}
		if( reslut.isEmpty() ) reslut = "无";
		return reslut;
	}
	
	
	public static void main( String[] args ){
		
		CommandManager.init();
		
		for( Command c : ls ){
			System.out.println( c );
		}
	}
	
}
