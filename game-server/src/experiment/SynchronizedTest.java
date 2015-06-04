package experiment;


public class SynchronizedTest extends Thread {

	private int no;
	private String lock;
	public SynchronizedTest(int no,String lock){
	this.no = no;
	this.lock = "asd";
	}
	public static void main(String[] args) throws Exception{
//	String lock = new String("lock");//lock为同步锁，是唯一的
	for(int i=1;i<10;i++){//创建十个进程
	new SynchronizedTest(i,"lock").start();
	Thread.sleep(1);
	}
	}
	public void run(){
	synchronized(lock){
	for(int i=1;i<10;i++){
	System.out.println("No."+no+":"+i);
	}
	}
	}
	
}
