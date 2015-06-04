//package cn.mxz.util.interceptor;
//
//import java.util.concurrent.ConcurrentLinkedQueue;
//
//import cn.mxz.base.config.KeyValueDefine;
//import cn.mxz.base.config.KeyValueManagerImpl;
//
//class ServiceMethodRecorderImpl implements ServiceMethodRecorder {
//
//	private class Record {
//
//		private KeyValueDefine key;
//		private String head;
//		private String time_ms;
//
//		private Record(KeyValueDefine key, String head, String time_ms) {
//			this.key = key;
//			this.head = head;
//			this.time_ms = time_ms;
//		}
//
//		private void save() {
//
//			KeyValueManagerImpl k = new KeyValueManagerImpl();
//
//			String c = k.get(key, "|" + head);
//
//			String[] split = c.split("\\|");
//
//			Integer count = new Integer(split[0].trim());
//
//			Double ms = new Double(split[1].trim());
//
//			count++;
//
//			Double double1 = new Double(time_ms);
//
//			ms += double1;
//
//			k.put(key, count + "|" + ms, "|" + head);
//		}
//
//	}
//
//	private class WriteThread extends Thread {
//
//		ConcurrentLinkedQueue<Record> query = new ConcurrentLinkedQueue<Record>();
//
//		@Override
//		public void run() {
//			while (!query.isEmpty()) {
//				Record pop = query.poll();
//				pop.save();
//			}
//			thread = null;
//		}
//
//		public void add(KeyValueDefine key, String head, String time_ms) {
//
//			query.add(new Record(key, head, time_ms));
//		}
//
//	}
////
////	public static void main(String[] args) {
////		ConcurrentLinkedQueue<Integer> query = new ConcurrentLinkedQueue<Integer>();
////		query.add(1);
////		query.add(2);
////		query.add(3);
////		query.add(4);
////		query.add(5);
////		Integer poll = query.poll();
////
////		System.out.println(poll);
////
////		System.out.println(query);
////	}
//
//	private static ServiceMethodRecorderImpl instance;
//	private WriteThread thread;
//
//	private ServiceMethodRecorderImpl() {
//	}
//
//	static final ServiceMethodRecorderImpl getInstance() {
//		if (instance == null) {
//			instance = new ServiceMethodRecorderImpl();
//		}
//		return instance;
//	}
//
//	/**
//	 * 将玩家的操作次数记录到数据库
//	 *
//	 * @param head
//	 * @param time_ms
//	 */
//	@Override
//	public synchronized void saveToDB(KeyValueDefine key, String head,
//			String time_ms) {
//		try {
//			if (thread == null) {
//				thread = new WriteThread();
//				thread.add(key, head, time_ms);
//				thread.start();
//			} else {
//				thread.add(key, head, time_ms);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
