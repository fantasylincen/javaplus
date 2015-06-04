package cn.mxz.mission.type;

import java.util.List;

import cn.javaplus.util.Util.Random;
import cn.mxz.RandomEventTemplet;
import cn.mxz.RandomEventTempletConfig;
import cn.mxz.city.City;
import cn.mxz.city.FunctionOpenManager.ModuleType;

import com.google.common.collect.Lists;

import define.D;

/**
 * 专门的随机事件构造器，因为云游仙人事件只能出现一次
 *
 * @author Administrator
 *
 */
public class RandomEventBuilder {
	private final City	user;

	public RandomEventBuilder(City user) {
		this.user = user;
	}

	List<IEvent>	events	= Lists.newArrayList();

	public List<IEvent> build(int size) {
		int count = 50000;
		while (events.size() < size) {
			if (count-- == 0) {// 避免死循环
				break;
			}
			//System.out.println( "产生随机事件循环了: " + events.size());
			
			RandomEventTemplet t = generate();
			int id = t.getId();
			if (getCount(id) > t.getMaxInMap()) {
				continue;
			}
			if (id == D.SHENMO_EVENT && user.getUserShenmo().getNewShenmo() != null) {// 神魔随机事件同时只能存在一个
				continue;// 处理成神魔事件，方便测试，记得修改
			}
			if( !user.getFunctionOpenManager().isOpen( ModuleType.getType(t.getModulesId() ) ) ){
				continue;
			}
			// if( ShenmoManager.INSTANCE.create(user))
			events.add(new RandomEvent(id));
			
		}
		
		/**
		 * 如果没有产生出来这么多随机事件，则手动用“获得铜钱”随机事件补齐
		 */
		if( events.size() < size ){
			System.out.println( "循环了50000次，也没有产生出来这么多随机事件，events.size():" + events.size()  );
			for( int i = 0; i < size; i++ ){
				events.add(new RandomEvent(1));
			}
		}

		return events;

	}

	private int getCount(int id) {
		int count = 0;
		for (IEvent e : events) {
			if (e.getBrief() == id) {
				count++;
			}
		}
		return count;
	}

	private RandomEventTemplet generate() {
		int[] ws = RandomEventTempletConfig.getArrayByEventWeight();
		int index = Random.getRandomIndex(ws);
		Integer id = RandomEventTempletConfig.getKeys().get(index);
		if (user.isTester()) {
			id = 14;// 处理成神魔事件(15)，云游仙人(14)方便测试，记得修改
		}
		
		return RandomEventTempletConfig.get(id);
	}

	public static void main(String[] args) {
		RandomEventBuilder randomEventBuilder = new RandomEventBuilder(null);
		int count15 = 0, count14 = 0;
		for (int i = 0; i < 1000; i++) {
			int id = randomEventBuilder.generate().getId();
			System.out.println(id);
			if (id == 14)
				count14++;
			if (id == 15)
				count15++;
		}
		System.out.println("云游仙人:" + count14 + " 神魔:" + count15);
	}
}
