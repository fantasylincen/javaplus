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
// * 开启Boss战
// *
// * @author 林岑
// */
//@Component("telnetCommand:startbossactivity")
//public class StartBossActivity implements TelnetCommand {
//
//	@Override
//	public void run(PrintWriter out, String... args) {
//
//		BossActivity instance = BossActivityImpl.getInstance();
//
//		instance.start();
//	}
//
//}
