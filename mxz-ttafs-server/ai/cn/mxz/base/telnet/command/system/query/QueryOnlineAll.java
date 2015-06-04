package cn.mxz.base.telnet.command.system.query;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.javaplus.page.Page;
import cn.mxz.base.telnet.TelnetCommand;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;

/**
 * 查询所有在线玩家信息
 * @author 林岑
 *
 */
@Component("telnetCommand:queryonlineall")

public class QueryOnlineAll implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {

		Integer page = new Integer(args[0].trim());

		Integer pageCount = new Integer(args[1].trim());

		World world = WorldFactory.getWorld();

		List<City> all = new ArrayList<City>(world.getOnlineAll());

		Page<City> citys = new Page<City>(all, pageCount);

		List<City> ps = citys.getPage(page);

		for (City city : ps) {

			out.println(city);
		}

		out.println("-------------------");
	}

}
