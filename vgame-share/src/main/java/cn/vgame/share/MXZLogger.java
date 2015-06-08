//package cn.vgame.share;
//
//import java.sql.Date;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.concurrent.ConcurrentLinkedQueue;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import cn.javaplus.log.IPrintStream;
//import cn.javaplus.log.Log;
//import cn.javaplus.log.Out;
//import cn.javaplus.log.SystemOut;
//import cn.javaplus.util.Util;
//
//public class MXZLogger implements Out {
//
//	static final SimpleDateFormat F = new SimpleDateFormat(
//			"yyyy-MM-dd HH:mm:ss");
//
//	public interface LogData {
//
//		Date getTime();
//
//		int getServerId();
//
//		String getHead();
//
//		String getLog();
//	}
//
//	/**
//	 * 这个线程负责将队列中的日志, 输出到日志中
//	 * 
//	 * @author 林岑
//	 * 
//	 */
//	public class PushThread extends Thread {
//
//		@Override
//		public void run() {
//
//			while (true) {
//				try {
//					pushToDb();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				Util.Thread.sleep(3000);
//			}
//		}
//
//		private void pushToDb() {
//			ArrayList<LogData> ls = new ArrayList<LogData>(queue);
//			queue.clear();
//			save(ls);
//		}
//	}
//
//	ConcurrentLinkedQueue<LogData> queue = new ConcurrentLinkedQueue<LogData>();
//
//	public MXZLogger() {
//		new PushThread().start();
//	}
//
//
//	SystemOut out = new SystemOut();
//	
//	@Override
//	public void println(String head, Object... message) {
//		out.println(head, message);
//		LogData data = new LogDataImpl(null, message, t);
//		queue.offer(data);
//	}
//
//}
