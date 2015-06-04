//package cn.mxz.regist;
//
//import java.util.Iterator;
//import java.util.List;
//
//import cn.javaplus.common.util.Util;
//import cn.mxz.user.City;
//import cn.mxz.util.db.KeyValueCollection;
//import cn.mxz.util.db.KeyValueCollectionFactory;
//
//import com.google.common.collect.Lists;
//
//class RegisterDtoImpl implements RegisterDto {
//
//	private City	city;
//
//	private List<Boolean>	marks	= Lists.newArrayList();
//
//	private Long	createTime;
//
//	RegisterDtoImpl(City city, String value) {
//		this.city = city;
//
//		List<RegistRecord> ls = Lists.newArrayList();
//		String[] s = value.split(":");
//
//		this.createTime = new Long(s[0].trim());
//
//		String marks = s[1];
//
//		String[] ms = marks.split(",");
//
//		for (String m : ms) {
//			if (!m.isEmpty()) {
//				this.marks.add(m.equals("1"));
//			}
//		}
//	}
//
//	@Override
//	public List<RegistRecord> getRecords() {
//		List<RegistRecord> ls = Lists.newArrayList();
//		int day = 1;
//		for (Boolean hasReceive : this.marks) {
//			ls.add(new RegistRecordeImpl(day, hasReceive));
//			day ++;
//		}
//		return ls;
//	}
//
//	private class RegistRecordeImpl implements RegistRecord {
//
//		private boolean	hasReceive;
//		private int		day;
//
//		/**
//		 * @param day
//		 * @param hasReceive
//		 *            是否领取了奖励
//		 */
//		private RegistRecordeImpl(int day, boolean hasReceive) {
//			this.day = day;
//			this.hasReceive = hasReceive;
//		}
//
//		@Override
//		public boolean isPast() {
//			int dayNow = Util.Time.getDayOfMonthNow();
//			// 已经被签过到, 或者是昨天及昨天以前的记录
//			return hasReceived() || dayNow > getDayOfMonth();
//		}
//
//		@Override
//		public boolean hasReceived() {
//			return hasReceive;
//		}
//
//		@Override
//		public int getRewardId() {
//			return RegisterObjects.getRegisterManager().getRewardId(day);
//		}
//
//		@Override
//		public int getDayOfMonth() {
//			return day;
//		}
//
//		@Override
//		public void markSignIn() {
//			KeyValueCollection kv = KeyValueCollectionFactory.getMySqlCollection();
//			marks.set(day - 1, true);
//			kv.put(RegisterFactory.key(city), RegisterDtoImpl.this.toString());
//		}
//	}
//
//	@Override
//	public String toString() {
//		return this.createTime + ":" + buildMarks();
//	}
//
//	private String buildMarks() {
//		Iterator<Boolean> it = marks.iterator();
//		StringBuilder sb = new StringBuilder();
//		while (it.hasNext()) {
//			Boolean v = (Boolean) it.next();
//			sb.append(v ? "1" : "0");
//			if(it.hasNext()) {
//				sb.append(",");
//			}
//		}
//		return sb.toString();
//	}
//
//	@Override
//	public long getCreateTime() {
//		return createTime;
//	}
//}
