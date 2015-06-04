package ch.ethz.ssh2;

public class SendFileEvent {

	private long remain;
	private long all;

	public SendFileEvent(long remain, long all) {
		this.remain = remain;
		this.all = all;
	}
	
	public long getRemain() {
		return remain;
	}
	
	public long getAll() {
		return all;
	}
	
	public double getPercent() {
		double all = this.all;
		return (all - remain) / all;
	}

}
