package console;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import console.handle.ServerInfoEvent;
import console.handle.StartServerEvent;
import console.handle.StopServerEvent;

/**
 * 命令事件
 * @author DXF
 */
public enum CommandEvent {
	
	START 				( "start", new StartServerEvent() ),
	STOP 				( "stop", new StopServerEvent() ),
	INFO 				( "info", new ServerInfoEvent() );
	
	private final String 			command;
	private final ICEven 			eventInstance;
	
	CommandEvent( String command, ICEven eventInstance ) {
		this.command 		= command;
		this.eventInstance 	= eventInstance;
	}
	
	private static final Map<String, CommandEvent> numToEnum = new HashMap<String, CommandEvent>();
	static{
		for( CommandEvent a : values() ){
			CommandEvent p = numToEnum.put( a.command, a );
			if( p != null ){
				throw new RuntimeException( "命令事件" + a.command + "重复了" );
			}
		}
	}
	
	public ICEven getEventInstance() {
		return eventInstance;
	}
	public String toCommand() {
		return command;
	}
	public static CommandEvent fromNum( String s ){
		return numToEnum.get( s );
	}
	
	public void run( String[] arr, PrintWriter out ) throws Exception {
		eventInstance.run( out, arr );
	}
}

