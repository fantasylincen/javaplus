//package cn.mxz.util.counter;
//
//import java.util.List;
//
//import cn.javaplus.common.db.DAO2;
//import cn.mxz.user.City;
//
//import com.google.common.collect.Lists;
//
//import db.dao.factory.DaoFactory;
//import db.dao.impl.UserCountersCacheDAOImplNew;
//import db.domain.UserCounters;
//import db.domain.UserCountersImpl;
//
///**
// * 今日计数器
// *
// * @author 林岑
// *
// */
//public class UserCounterImpl extends AbstractCounter<UserCounters> {
//
//	private boolean	isLoad;
//
//	public UserCounterImpl(City city) {
//		super(city);
//	}
//
//
//	@Override
//	public int get(CounterKey id, Object... args) {
//
//		ensureCacheFull();
//
//		return super.get(id, args);
//	}
//
//	/**
//	 * 确保读取了所有的缓存
//	 */
//	private void ensureCacheFull() {
//		if(isLoad) {
//			return ;
//		}
//
//		List<UserCounters> all = getDao().findByUname(city.getId());
//
//		putToCache(all);
//
//		if(all.isEmpty()) {
//			initAllZero();
//		}
//
//		isLoad = true;
//	}
//
//
//	private void putToCache(List<UserCounters> all) {
//		for (UserCounters u : all) {
//			UserCountersCacheDAOImplNew.getCache().put(u.getCounterId(), u.getUname(), u);
//		}
//	}
//
//
//	private void initAllZero() {
//		List<UserCounters> ls = Lists.newArrayList();
//
//		CounterKey[] ak = CounterKey.values();
//
//		for (CounterKey ck : ak) {
//			UserCounters c = new UserCountersImpl();
//			c.setUname(city.getId());
//			c.setCounterId(ck + "");
//			ls.add(c);
//		}
//
//		getDao().addAll(ls);
//		putToCache(ls);
//	}
//
//
//	@Override
//	protected DAO2<String, String, UserCounters> getDao() {
//
//		return DaoFactory.getCacheUserCountersDAO();
//		// return city.getDatabaseTransaction().getCacheUserCountersDAO();
//	}
//
//	@Override
//	public void add(CounterKey id, int add, Object... args) {
//		super.add(id, add, args);
//
////		new Exception().printStackTrace();
//
////		Debuger.debug("-------------------------UserCounterImpl.add()" + id + ":" + add);
//	}
//}
