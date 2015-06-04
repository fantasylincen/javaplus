package cn.mxz.base.telnet.command.system;

import java.io.PrintWriter;

import org.springframework.stereotype.Component;

import cn.mxz.Loader;
import cn.mxz.base.telnet.TelnetCommand;
import cn.mxz.events.Events;
import cn.mxz.events.system.ReloadPropertiesEvent;

/**
 * 重新加载所有配置表
 * @author 林岑
 */
@Component("telnetCommand:reloadproperties")

public class ReloadProperties implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {

		try {

			Loader.loadAll();	//重新加载配置表
			Events.getInstance().dispatch(new ReloadPropertiesEvent());

		} catch (Exception e) {

			e.printStackTrace(out);
		}
	}

}
