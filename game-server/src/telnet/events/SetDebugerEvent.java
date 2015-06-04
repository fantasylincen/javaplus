package telnet.events;

import java.io.PrintWriter;

import define.SystemCfg;
import telnet.CommandBase;

public class SetDebugerEvent extends CommandBase{

	@Override
	public void run(PrintWriter out, byte jurisdiction, String... args) throws Exception {
		
		if( args.length != 1 )
			throw new Exception( "参数错误,请重新输入:" );
		
		byte isdebug		= 0;
		try {
			isdebug 		= Byte.parseByte( args[0] );
		} catch (Exception e) {
			throw new Exception( "参数错误,请重新输入:" );
		}
		
		if( isdebug > 1 || isdebug < 0 )
			throw new Exception( "参数错误,请重新输入:" );
			
		SystemCfg.IS_DEBUG 	= isdebug == 1;
		
		out.print( "服务器运行模式设置  成功  当前模式 ：" + (SystemCfg.IS_DEBUG ? "DEBUG" : "RELEASE") + "\r\n" );
	}

	@Override
	public void clear(PrintWriter out) {
		
	}

}
