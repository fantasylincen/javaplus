package console;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Command {

	public String command	= "";
	public String desc		= "";
	
	public Command head		= null;
	public List<Command> nexts = new ArrayList<Command>();
	
	public String toString(){
		return "<" + command + "> \t" + desc;
	}

	public Command getNextCommand( String x ) {
		for( Command c : nexts ){
			if( c.command.equals( x ) ) return c;
		}
		return null;
	}

	public void run( String[] arr, PrintWriter out ) throws Exception {
		CommandEvent event = CommandEvent.fromNum( command );
		if( event == null ) return;
		event.run( arr, out );
	}

	public String showLs() {
		String reslut = "";
		for( Command c : nexts ){
			if( !reslut.isEmpty() ) reslut += "\r\n";
			reslut += c.toString();
		}
		if( reslut.isEmpty() ) reslut = "无";
		return reslut;
	}
}
