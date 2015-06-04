package cn.mxz.user;

import java.util.Collection;
import java.util.HashSet;

import cn.mxz.base.servertask.TaskSafetyLogToFile;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.protocols.user.UserP.UserPro;
import cn.mxz.user.builder.UserBuilder;

import com.google.common.collect.Sets;

import define.D;

public class PhysicalTask extends TaskSafetyLogToFile {

	private long lastAddTime = System.currentTimeMillis();

	public static final long RATE = D.PHYSICAL_ADD_RATE_SEC * 1000;

	private static PhysicalTask instance;

	private PhysicalTask() {
	}

	public static final PhysicalTask getInstance() {
		if (instance == null) {
			instance = new PhysicalTask();
		}
		return instance;
	}

	@Override
	public void runSafty() {

		World world = WorldFactory.getWorld();

		Collection<City> onlineAll = world.getOnlineAll();

		Collection<City> nearests = getNearests();

		HashSet<City> newHashSet = Sets.newHashSet();

		newHashSet.addAll(onlineAll);

		newHashSet.addAll(nearests);

		for (City u : newHashSet) {

			Player p = u.getPlayer();

			final int add = 1;

			if (p.getPhysical().getNumerator() + add <= p.getPhysicalMax()) {

				p.add(PlayerProperty.PHYSICAL, add);

				if (u.getSocket() != null) {

					UserPro build = null;
//					try {
						build = new UserBuilder().build(u);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}

					MessageFactory.getUser().onUpdateUserList(u.getSocket(),
							build);
				}
			}
		}

		lastAddTime = System.currentTimeMillis();
	}

	/**
	 * 距下次加体力还有多长时间(秒)
	 * 
	 * @return
	 */
	public int getRemainSec(Player player) {

		long endTime = lastAddTime + RATE; // 精力加满的时间点
		int i = (int) ((endTime - System.currentTimeMillis()) / 1000);
		if (i <= 0) {
			return (int) (RATE / 1000);
		} else {
			return i;
		}
	}

}
