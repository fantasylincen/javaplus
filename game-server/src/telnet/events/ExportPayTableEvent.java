package telnet.events;

import java.io.PrintWriter;

import define.SystemCfg;

import recharge.RechargeDataProvider;
import telnet.CommandBase;

public class ExportPayTableEvent  extends CommandBase{

	@Override
	public void run(PrintWriter out, byte jurisdiction, String... args) throws Exception {
		String content = RechargeDataProvider.getInstance().produceExcel( SystemCfg.PLATFORM );
	
		out.print( content + "\r\n" );
		
		out.print( "充值明细生成 成功!\r\n" );
	}

	@Override
	public void clear(PrintWriter out) {
		
	}

}
