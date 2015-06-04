//package cn.mxz.base.telnet.command.system.activity;
//
//import java.io.PrintWriter;
//
//import org.springframework.stereotype.Component;
//
//import cn.mxz.activity.boss.BossActivity;
//import cn.mxz.activity.boss.BossActivityImpl;
//import cn.mxz.base.telnet.TelnetCommand;
//
///**
// *
// * 延迟停止Boss战
// *
// * @author 林岑
// */
//@Component("telnetCommand:stopbossactivitywhile")
//public class StopBossActivityAfterWhile implements TelnetCommand {
//
//	@Override
//	public void run(PrintWriter out, String... args) {
//
//		BossActivity instance = BossActivityImpl.getInstance();
//
//		instance.stopDelay();
//	}
//
//}
