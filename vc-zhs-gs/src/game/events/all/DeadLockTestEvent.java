/**
 * 
 */
package game.events.all;

import game.events.EventBase;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;

import user.UserInfo;
import user.UserManager;

/**
 * 用来测试死锁问题，比如精英挑战赛等交互类的玩法
 * 如果在packageProcess函数中先就锁定user，那么在交互类的玩法当中需要获得第二个玩家的时候极有可能发生死锁
 * 
 * 所以结论是不能简单的在最外层个某个user加锁，这样的话一旦涉及到交互类玩法，就有可能形成死锁
 * 
 * 为了增加死锁的概率，增加了如下语句Thread.sleep(4000);
 * 由于findbugs会报警告，为了避免警告，暂时把此句屏蔽了
 * //Thread.sleep(4000);//为了findbugs不报错
 * 
 * @author liukun 2012-9-2
 */
public class DeadLockTestEvent extends EventBase {
	private static AtomicBoolean firstIn = new AtomicBoolean();
	static{
		firstIn.set( true );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.packages.BasePackage#run(user.UserInfo, java.nio.ByteBuffer)
	 */
	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
//		synchronized (user) 
		{

			int name = user.getUID();
			System.out.println(Thread.currentThread() + "\t" + name);
			if ( firstIn.getAndSet( false ) ) {
				try {
					System.out.println(name + "睡眠4秒");
					//Thread.sleep(4000);//为了findbugs不报错
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			int otherName = name == 1 ? 1 : 1;
			System.out.println(Thread.currentThread() + "\t" + user.getUID()
					+ "企图寻找" + otherName);
			UserInfo user2 = UserManager.getInstance().getByName(otherName);
			if (user2 == null) {
				System.out.println("未找到user2,请找数据库中已有的用户名进行测试");
				return;
			}

			/**
			 * 假设此时，user2的玩家企图挑战user，也执行到了这里，此时 线程A拥有user的锁，并试图获取user2的锁
			 * 线程B拥有user2的锁，并试图获取user的锁 此时，死锁就发生了
			 */
//			synchronized (user2) 
			{
				System.out.println(Thread.currentThread() + "\t" + "试图获取"
						+ user2.getUID() + "成功");
				//name = user2.getName();
			}
			
		}
	}

}
