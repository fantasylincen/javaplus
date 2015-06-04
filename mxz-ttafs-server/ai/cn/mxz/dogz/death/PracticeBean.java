package cn.mxz.dogz.death;
//package cn.mxz.dogz;
//
//
///**
// * 修炼信息
// * @author 	林岑
// * @time	2013-3-15
// */
//public class PracticeBean implements IPracticeBean {
//
//	/**
//	 * 修炼中的战士
//	 */
//	private int typeId;
//
//	/**
//	 * 修炼模式ID
//	 */
//	private int modelId;
//
//	/**
//	 * 修炼时间ID
//	 */
//	private int timeId;
//
//	/**
//	 * 修炼结束时间
//	 */
//	private long endTime;
//
//	private int index;
//
//	/**
//	 * 所属用户
//	 */
//	private String uname;
//
//	public PracticeBean(String uname) {
//		this.setUname(uname);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.lemon.ai.main.logic.practice.godpractice.IPracticeBean#getTypeId()
//	 */
//	@Override
//	public int getTypeId() {
//		return typeId;
//	}
//
//	/* (non-Javadoc)
//	 * @see com.lemon.ai.main.logic.practice.godpractice.IPracticeBean#setTypeId(int)
//	 */
//	@Override
//	public void setTypeId(int typeId) {
//		this.typeId = typeId;
//	}
//
//	/* (non-Javadoc)
//	 * @see com.lemon.ai.main.logic.practice.godpractice.IPracticeBean#getModel()
//	 */
//	@Override
//	public int getMode() {
//		return modelId;
//	}
//
//	/* (non-Javadoc)
//	 * @see com.lemon.ai.main.logic.practice.godpractice.IPracticeBean#setModel(int)
//	 */
//	@Override
//	public void setModel(int mode) {
//		this.modelId = mode;
//	}
//
//	/* (non-Javadoc)
//	 * @see com.lemon.ai.main.logic.practice.godpractice.IPracticeBean#getTime()
//	 */
//	@Override
//	public int getTime() {
//		return timeId;
//	}
//
//	/* (non-Javadoc)
//	 * @see com.lemon.ai.main.logic.practice.godpractice.IPracticeBean#setTime(int)
//	 */
//	@Override
//	public void setTime(int time) {
//		this.timeId = time;
//	}
//
//	/* (non-Javadoc)
//	 * @see com.lemon.ai.main.logic.practice.godpractice.IPracticeBean#getEndTime()
//	 */
//	@Override
//	public long getEndTime() {
//		return endTime;
//	}
//
//	/* (non-Javadoc)
//	 * @see com.lemon.ai.main.logic.practice.godpractice.IPracticeBean#setEndTime(long)
//	 */
//	@Override
//	public void setEndTime(long endTime) {
//		this.endTime = endTime;
//	}
//
//	/* (non-Javadoc)
//	 * @see com.lemon.ai.main.logic.practice.godpractice.IPracticeBean#getIndex()
//	 */
//	@Override
//	public int getIndex() {
//		return index;
//	}
//
//	/* (non-Javadoc)
//	 * @see com.lemon.ai.main.logic.practice.godpractice.IPracticeBean#setIndex(int)
//	 */
//	@Override
//	public void setIndex(int index) {
//		this.index = index;
//	}
//
//	/* (non-Javadoc)
//	 * @see com.lemon.ai.main.logic.practice.godpractice.IPracticeBean#isTimeUp()
//	 */
//	@Override
//	public boolean isTimeUp() {
//		return getEndTime() <= System.currentTimeMillis();
//	}
//
//	/* (non-Javadoc)
//	 * @see com.lemon.ai.main.logic.practice.godpractice.IPracticeBean#getUname()
//	 */
//	@Override
//	public String getUname() {
//		return uname;
//	}
//
//	/**
//	 * @param uname the uname to set
//	 */
//	private void setUname(String uname) {
//		this.uname = uname;
//	}
//
//	@Override
//	public int getRemainingSec() {
//		long time = getEndTime() - System.currentTimeMillis();
//		int sec = (int) (time / 1000);
//		return sec < 0 ? 0 : sec;
//	}
//}
