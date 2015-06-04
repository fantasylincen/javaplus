package cn.javaplus.buffer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class TransactionBuffer implements ITransactionBuffer {

	/**
	 * 所有待处理的事务
	 * 
	 * 键: 事务id
	 * 值: 事物
	 * 			队列中包含的是
	 * 
	 * 
	 */
	private Map<Integer, Transaction> all = new HashMap<Integer, Transaction>();
	
	/**
	 * 最大id
	 */
	private int maxId = 0;
	
	/**
	 * {@link TransactionBuffer} 实例
	 */
	private static final ITransactionBuffer instance = new TransactionBuffer();

	/**
	 * 私有化构造
	 */
	private TransactionBuffer() {
	}

	/**
	 * 获取该对象唯一实例
	 * @return
	 */
	public static final ITransactionBuffer getInstance() {
		return instance;
	}
	
	private void run() {
		
		while(true) {
			
			try {
				
				synchronized (this) {
					Iterator<Integer> it = all.keySet().iterator();
					while (it.hasNext()) {
						int id = it.next();
						Transaction v = all.get(id);
						if (v.couldRunNext()) {
							boolean s = v.pollAndRun();
							if (!s) {
								it.remove();
							}
						}
					}
					if(all.isEmpty()) {
						break;
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public synchronized int allot() {
		return maxId;
	}

	@Override
	public synchronized void add(int id, int period, Task task) {
		
		if(period < 10) {
			System.err.println(" period值太小, 最小为10 : " + period);
			return;
		}
		
		boolean isEmpty = all.isEmpty();
		Transaction t = all.get(id);
		if(t == null) {
			t = new Transaction();
			all.put(id, t);
		}
		t.append(task, period);
		maxId++;
		
		if( isEmpty ) {
			new Thread(){
				
				@Override
				public void run() {
//					System.out.println("NewThread!");
					TransactionBuffer.this.run();
				}
			}.start();
		}
	}

	
	@Override
	public void appendDelay(int id, int period) {	
		add(id, period, new Task(	) {
			
			@Override
			public void doIt() {}
		} );
	}
	
	/*
	public static void main(String[] args) throws InterruptedException {
		TransactionBuffer t = TransactionBuffer.getInstance();
		int id = t.allot();

		for (int i = 0; i < 5; i++) {
			t.add(id, new Task() {
				
				@Override
				public void doIt() {
					System.out.println("Test");
				}
			}, 1);
		}
		
		Thread.sleep(1000);
		
		System.out.println("Over");
		
		for (int i = 0; i < 5; i++) {
			t.add(3, new Task() {
				public void doIt() {
					System.out.println("5");
				}
			}, 15);
		}
	}*/
	
}
