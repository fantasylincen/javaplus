package cn.mxz.activity.heishi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import message.S;
import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.KeyValueDataDao;
import mongo.gen.MongoGen.KeyValueDataDto;

import org.joda.time.DateTime;

import cn.mxz.ActivityTemplet;
import cn.mxz.ActivityTempletConfig;
import cn.mxz.activity.ActivityIds;
import cn.mxz.base.exception.IllegalOperationException;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.util.debuger.Debuger;

import com.alibaba.fastjson.JSON;

public enum HeishiActivity {
	INSTANCE;

	private final String					KEY = "HeishiActivity_MSG";
	private final int						MESSAGE_MAX_COUNT	= 8;
	private final int						needLevel;
	private final ActivityTemplet			templet;

	private DateTime						begin;
	private DateTime						end;

	private BroadCast<Message>				message				= new BroadCast<Message>(MESSAGE_MAX_COUNT);

	private final ScheduledExecutorService	s					= Executors.newSingleThreadScheduledExecutor();
	private ScheduledFuture<?>				future				= null;
	

	public BroadCast<Message> getMessage() {
		return message;
	}

	// private Map<String,HeishiUserData> data = Maps.newHashMap();

	private HeishiActivity() {
		templet = ActivityTempletConfig.get(ActivityIds.XianShiHeiShi_14);

		needLevel = templet.getLevel();
		loadMessage();
//		init();
	}

	/**
	 * 通过配置表设置活动的起止时间
	 */
	public void init() {
		System.out.println("黑市活动init函数开始执行");
		getBeginTime();
		getEndTime();

		if (future != null && !future.isDone()) {
			Debuger.debug("黑市活动取消 " + future.cancel(false));
		}
		long current = System.currentTimeMillis();
		int delay = (int) ((end.getMillis() - current) / 1000);
		Debuger.debug("黑市活动delay " + delay);
		if (delay > 0) {
			future = s.schedule(new TimerTask(), delay, TimeUnit.SECONDS);
		}
	}

	/**
	 * 实时读取活动的开始时间
	 * 
	 * @return
	 */
	private void getBeginTime() {
		String time = templet.getTime();// 2014-06-10|00:00 to 2014-7-10|24:00
		String beginStr = time.split(" ")[0];
		beginStr = beginStr.replace("|", " ");

		begin = new DateTime(parseDateFromStr(beginStr).getTime());
	}

	/**
	 * 实时读取活动的结束时间
	 * 
	 * @return
	 */
	private void getEndTime() {
		String time = templet.getTime();// 2014-06-10|00:00 to 2014-7-10|24:00
		String endStr = time.split(" ")[2];
		endStr = endStr.replace("|", " ");
		end = new DateTime(parseDateFromStr(endStr).getTime());
		System.out.println("黑市活动结束时间 " + end);
	}

	/**
	 * 通过字符串转换为Date对象
	 * 
	 * @param dataStr
	 * @return
	 */
	private Date parseDateFromStr(String dataStr) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;

		try {
			date = format.parse(dataStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 活动是否处于允许状态中
	 * 
	 * @return
	 */
	public boolean isRunning() {
		return getEndRemainSecond() > 0;
	}

	/**
	 * 计算活动离结束还剩余的秒数，大于等于0，等于0意味着结束了，或者还没开始
	 * 
	 * @return
	 */
	public int getEndRemainSecond() {
		long current = System.currentTimeMillis();
		if (current < begin.getMillis()) {// 还没开始
			return 0;
		}

		long millis = end.getMillis();
		
		int i = (int) (Math.max((millis - current), 0) / 1000);
		return i;
	}

	/**
	 * 计算活动离开始剩余的秒数，大于等于0
	 * 
	 * @return
	 */
	public int getBeginRemainSecond() {
		long current = System.currentTimeMillis();
		if (current > begin.getMillis()) {// 已经开始了
			return 0;
		}
		return (int) Math.max((begin.getMillis() - current), 0) / 1000;
	}

	private void end() {
		Daos.getHeishiDao().clear();// 清除所有数据
//		KeyValueDataDto o = new KeyValueDataDto();
//		o.setUname(KEY);
		Daos.getKeyValueDataDao().delete(KEY);
//		Daos.get
		Collection<City> all = WorldFactory.getWorld().getAll();
		for (City city : all) {
			city.freeHeishiManager();
		}
		Debuger.debug("黑市活动结束任务执行完毕");
	}

	/**
	 * 兑换物品
	 * 
	 * @param user
	 */
	synchronized public void exchange(City user, int propId, int count) {
		if (count <= 0) {
			throw new OperationFaildException(S.S10333);
		}
		if (user.getLevel() < needLevel) {
			throw new OperationFaildException(S.S10085);
		}
		if (!isRunning()) {
			throw new OperationFaildException(S.S10219);
		}

		user.getHeishiManager().exchange(propId, count);
		message.add(new Message(propId, user.getPlayer().getNick()));
		saveMessage();
		
	}

	private static class TimerTask implements Runnable {

		@Override
		public void run() {
			HeishiActivity.INSTANCE.end();
			
			
			
		}

	}

	public static void main(String[] args) throws InterruptedException {
		// System.out.println("活动开始时间：" + HeishiActivity.INSTANCE.begin ) ;
		// System.out.println("活动结束时间：" + HeishiActivity.INSTANCE.end ) ;
		// System.out.println( "活动离开始剩余的秒数：" +
		// HeishiActivity.INSTANCE.getBeginRemainSecond() );
		System.out.println("活动离结束剩余的秒数：" + HeishiActivity.INSTANCE.getEndRemainSecond());

		System.out.println("修改新时间");
		Thread.sleep(10000);
		HeishiActivity.INSTANCE.init();
	}
	
	/**
	 * 前端显示 的兑换信息文字保存到数据库
	 */
	private void saveMessage(){
		KeyValueDataDao DAO = Daos.getKeyValueDataDao();

		KeyValueDataDto o = new KeyValueDataDto();
		o.setUname(KEY);
		try {
			o.setData(JSON.toJSONString(message.getAllMessage()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		DAO.save(o);
	}
	
	/**
	 * 从数据库加载前端显示 的兑换信息文字
	 */
	private void loadMessage(){
		KeyValueDataDto o = Daos.getKeyValueDataDao().get(KEY);
		if (o != null) {
			String jsonString = o.getData();
			if (jsonString != null && !jsonString.isEmpty()) {
				try {

					List<Message> msgs = JSON.parseArray(jsonString, Message.class);
					message.setMessage(msgs);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		

	}

}
