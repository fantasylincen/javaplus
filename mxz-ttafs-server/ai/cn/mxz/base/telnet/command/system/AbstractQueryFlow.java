package cn.mxz.base.telnet.command.system;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.javaplus.page.Page;
import cn.javaplus.util.Util;
import cn.mxz.base.telnet.TelnetCommand;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.util.flow.FlowManager;
import cn.mxz.util.flow.FlowObjects;

/**
 * 查询所有用户流量使用情况
 *
 * @author 林岑
 *
 */
public abstract class AbstractQueryFlow implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {

		Integer page = new Integer(args[0].trim());

		Integer pageCount = new Integer(args[1].trim());

		World world = WorldFactory.getWorld();

		List<City> all = new ArrayList<City>(getCitys(world));

		sort(all);

		Page<City> citys = new Page<City>(all, pageCount);

		List<City> ps = citys.getPage(page);

		for (City city : ps) {

			String x = "玩家:" + city.getId() + " " + city.getPlayer().getNick();

			FlowManager manager = FlowObjects.getManager(city.getId());

			String x2 = " 今日流量:" + manager.getToday() + " 历史流量:"
					+ manager.getHistory();

			out.println(Util.Str.polishing(x, 30) + x2);
		}
	}

	protected abstract Collection<City> getCitys(World world);

	private void sort(List<City> all) {

		Collections.sort(all, new Comparator<City>() {

			@Override
			public int compare(City o1, City o2) {

				// int f = o2.getUserCounterHistory().get(CounterKey.FLOW);
				//
				// int f2 = o1.getUserCounterHistory().get(CounterKey.FLOW);
				//
				// return f - f2;
				return o1.getId().compareTo(o2.getId());
			}
		});
	}

}
