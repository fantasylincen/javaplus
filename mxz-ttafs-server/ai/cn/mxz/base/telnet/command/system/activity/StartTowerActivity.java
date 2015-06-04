//package cn.mxz.base.telnet.command.system.activity;
//
//import java.io.PrintWriter;
//
//import org.springframework.stereotype.Component;
//
//import cn.mxz.activity.tower.TowerActivity;
//import cn.mxz.base.telnet.TelnetCommand;
//
///**
// *
// * 开启爬塔活动
// *
// * @author 林岑
// */
//@Component("telnetCommand:starttoweractivity")
//
//public class StartTowerActivity implements TelnetCommand {
//
//	@Override
//	public void run(PrintWriter out, String... args) {
//
//		TowerActivity instance = TowerActivityImpl.getInstance();
//
//		instance.start();
//	}
//}
