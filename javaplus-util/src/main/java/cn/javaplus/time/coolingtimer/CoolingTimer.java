package cn.javaplus.time.coolingtimer;




/**
 * 冷却时间控制器
 * 
 * @author 	张腾
 * @time	2012年4月29日 13:36:11
 *
 */
public class CoolingTimer implements ICoolingTimer{
	
	/**
	 * CD时间
	 */
	private long cd;
	
	/**
	 * 下一次CD完成时间.
	 */
	private long cdEndTime;
	
	public CoolingTimer(long cd){
		this.cd = cd;
		this.clearCD();
	}
	
	public CoolingTimer(){
		this.clearCD();
	}
	
	@Override
	public boolean isCDOver(){
		return System.currentTimeMillis() >= cdEndTime;
	}
	
	@Override
	public ICoolingTimer update(){
		cdEndTime = System.currentTimeMillis() + cd;
		return this;
	}
	
	@Override
	public void clearCD() {
		this.cdEndTime = System.currentTimeMillis();
	}
	
	@Override
	public void setCd(long cd) {
		long dcd = cd - this.cd;
		this.cd = cd;
		this.cdEndTime += dcd;		//结束时间往后推迟
	}
	
	@Override
	public long getRemainingTime() {
		long timeNow = System.currentTimeMillis();
		if( timeNow >= cdEndTime) {	//如果CD完了., 就返回0
			return 0;
		} else {
			return cdEndTime - timeNow;
		}
	}
	
	public static void main(String[] args) {
		CoolingTimer coolingTimer = new CoolingTimer(1000);
		for (int i = 0; i < 10000000; i++) {
			System.out.println(coolingTimer.getRemainingTime());
		}
	}

	/**
	 * 获得CD时间
	 * @return
	 */
	@Override
	public long getCD() {
		return this.cd;
	}

	@Override
	public void setCDEndTime(long l) {
		this.cdEndTime = l;
	}

	@Override
	public long getCDEndTime() {
		return this.cdEndTime;
	}

	@Override
	public int getRemainingSec() {
		return (int) (getRemainingTime() / 1000);
	}

	@Override
	public int getRemainingMin() {
		int sec = getRemainingSec();
		if(sec % 60 == 0) {
			return sec / 60;
		} else {
			return sec / 60 + 1;
		}
	}
}
