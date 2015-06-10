package console.handle;

import java.io.PrintWriter;

import console.ICEven;

import define.SystemCfg;

import recharge.RechargeDataProvider;

public class ExportPayTableEvent  extends ICEven{

	@Override
	public void run(PrintWriter out, String[] args) throws Exception {
		String content = RechargeDataProvider.getInstance().produceExcel( SystemCfg.PLATFORM );
		out.print( content + "\r\n" );
		out.print( "充值明细生成 成功!\r\n" );
	}


}
