package cn.mxz.base.telnet.command.system;

import java.io.PrintWriter;

import org.springframework.stereotype.Component;

import cn.mxz.Loader;
import cn.mxz.base.telnet.TelnetCommand;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.events.Events;
import cn.mxz.events.system.ReloadPropertiesEvent;
import cn.mxz.util.debuger.Debuger;
import db.dao.impl.DaoCacheWartchDog;

/**
 * 重新加载世界命令
 * @author 林岑
 *
 */
@Component("telnetCommand:reloadworld")

public class ReloadWorld implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {

		Loader.loadAll();	//重新加载配置表

		Events.getInstance().dispatch(new ReloadPropertiesEvent());
		
		DaoCacheWartchDog.getInstance().dispatchEvent(new CacheClearEvent());

		World world = WorldFactory.getWorld();

		world.reload();

		Debuger.debug("ReloadWorld.run() 管理员通过Telnet重新加载了世界!");
	}

}
