package cn.mxz.base.servertask;

import java.util.Collection;
import java.util.HashSet;

import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.user.Player;
import cn.mxz.user.builder.UserBuilder;

import com.google.common.collect.Sets;

import define.D;

public class PowerTask extends TaskSafetyLogToFile {

	private long lastAddTime = System.currentTimeMillis();

	public static final long RATE = D.POWER_ADD_RATE_SEC * 1000;

	private static PowerTask	instance;


	private PowerTask() {
	}

	public static final PowerTask getInstance() {

		if (instance == null) {

			instance = new PowerTask();
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

		for (City u : newHashSet) 	{

			Player p = u.getPlayer();

			if(p.getPower().getNumerator() < p.getPowerMax()) {

				p.add(PlayerProperty.POWER, 1);

				if (u.getSocket() != null) {
					MessageFactory.getUser().onUpdateUserList(u.getSocket(), new UserBuilder().build(u));
				}
			}
		}

		lastAddTime = System.currentTimeMillis();
	}

	/**
	 * 距下次加体力还有多长时间(秒)
	 * @return
	 */
	public int getRemainSec(Player player) {
		long endTime = lastAddTime + RATE;	//精力加满的时间点
		int i = (int) ((endTime - System.currentTimeMillis()) / 1000);
		if(i <= 0) {
			return (int) (RATE / 1000);
		} else {
			return i;
		}
	}


}