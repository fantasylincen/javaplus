package cn.mxz.log;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import cn.javaplus.util.Util;

import com.lemon.commons.logger.Logs.ILogger;

public class MXZLogger implements ILogger {

	static final SimpleDateFormat	F	= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public interface LogData {

		Date getTime();

		int getServerId();

		String getHead();

		String getLog();
	}

	/**
	 * 这个线程负责将队列中的日志, 输出到日志中
	 *
	 * @author 林岑
	 *
	 */
	public class PushThread extends Thread {

		@Override
		public void run() {

			while (true) {
				try {
					pushToDb();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Util.Thread.sleep(5000);
			}
		}

		private void pushToDb() {
			ArrayList<LogData> ls = new ArrayList<LogData>(queue);
			queue.clear();
			LogTableDAO DAO = new LogTableDAO(LogDB.getInstance());
			DAO.addAll(ls);
		}
	}

	ConcurrentLinkedQueue<LogData>	queue	= new ConcurrentLinkedQueue<LogData>();

	public MXZLogger() {
		new PushThread().start();
	}

	@Override
	public void debug(Object message, Throwable t) {
		LogData data = new LogDataImpl(null, message, t);
		queue.offer(data);
		System.out.println(data);
	}

	@Override
	public void debug(Object message) {
		LogData data = new LogDataImpl(null, message, null);
		queue.offer(data);
		System.out.println(data);
	}

	@Override
	public void error(Object message, Throwable t) {
		LogData data = new LogDataImpl(null, message, t);
		queue.offer(data);
		System.out.println(data);
	}

	@Override
	public void error(Object message) {
		LogData data = new LogDataImpl(null, message, null);
		queue.offer(data);
		System.out.println(data);
	}

	@Override
	public void info(Object message, Throwable t) {
		LogData data = new LogDataImpl(null, message, t);
		queue.offer(data);
		System.out.println(data);
	}

	@Override
	public void info(Object message) {
		LogData data = new LogDataImpl(null, message, null);
		queue.offer(data);
		System.out.println(data);
	}

	@Override
	public void debug(Object head, Object message, Throwable t) {
		LogData data = new LogDataImpl(null, message, t);
		queue.offer(data);
		System.out.println(data);
	}

	@Override
	public void debug(Object head, Object message) {
		LogData data = new LogDataImpl(head, message, null);
		queue.offer(data);
		System.out.println(data);
	}

	@Override
	public void error(Object head, Object message, Throwable t) {
		LogData data = new LogDataImpl(head, message, t);
		queue.offer(data);
		System.out.println(data);
	}

	@Override
	public void error(Object head, Object message) {
		LogData data = new LogDataImpl(head, message, null);
		queue.offer(data);
		System.out.println(data);
	}

	@Override
	public void info(Object head, Object message, Throwable t) {
		LogData data = new LogDataImpl(head, message, t);
		queue.offer(data);
		System.out.println(data);
	}

	@Override
	public void info(Object head, Object message) {
		LogData data = new LogDataImpl(head, message, null);
		queue.offer(data);
		System.out.println(data);
	}

}
