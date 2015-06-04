package telnet;

import java.io.PrintWriter;

public class TextConent {

	private String  	command	= "";
	
	private String		args	= "";
	
	private boolean		isHave	= false;
	
	
	public String getText() {
		return command;
	}
	
	public String getArgs(){
		return this.args;
	}

	public boolean isHave() {
		return isHave;
	}

	public void setArg( String arg ) {
		this.args = arg;
	}

	public void setCommand( String arg ) {
		isHave			= true;
		this.command 	= arg;
	}

	public String run( PrintWriter out, byte jurisdiction )  throws Exception {
		
		String[] content 	= args.split(" ");
		if( content.length == 1 && content[0].equals("qw") )
			return "";
		
		CommandEvent event 	= CommandEvent.fromNum( command );
		
		if( event == null )
			throw new Exception( "处理命令错误!" );
		
		event.getEventInstance().run( out, jurisdiction, content );
		
		return "";
	}

	public void clear( PrintWriter out ) {
		CommandEvent event 	= CommandEvent.fromNum( command );
		if( event != null )
			event.getEventInstance().clear( out );
		
		command				= "";
		args				= "";
		isHave				= false;
	}

}
