package cn.mxz.base.telnet.command.system;

import java.io.PrintWriter;

import org.springframework.stereotype.Component;

import cn.mxz.base.telnet.TelnetCommand;

/**
 * 新增公告
 *
 * @author 林岑
 *
 */
@Component("telnetCommand:addnotice")

public class AddNotice implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {
//		String text = new String(args[0]);
		out.println("功能未实现");
	}

//	private void addProp(int id, String uname, int count) {
//
//		City city = WorldFactory.getWorld().get(uname);
//
//		if(EquipmentTempletConfig.get(id) != null) {
//
//			for (int i = 0; i < count; i++) {
//
//				city.getEquipmentManager().createNewEquipment(id);
//			}
//
//			return;
//		}
//
//		city.getBagAuto().addProp(id, count);
//	}
}
