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
//import db.dao.impl.UserCountersAllCacheDAOImplNew;
//import db.domain.UserCountersAll;
//import db.domain.UserCountersAllImpl;
//
///**
// * 历史计数器
// *
// * @author 林岑
// *
// */
//public class UserCounterHistoryImpl extends AbstractCounter<UserCountersAll> {
//
//	private boolean	isLoad;
//
//	public UserCounterHistoryImpl(City city) {
//		super(city);
//	}
//
//	@Override
//	protected DAO2<String, String, UserCountersAll> getDao() {
//		return DaoFactory.getCacheUserCountersAllDAO();
//		// return city.getDatabaseTransaction().getCacheUserCountersAllDAO();
//	}
//
//	@Override
//	public int get(CounterKey id, Object... args) {
//		ensureCacheFull();
//		return super.get(id, args);
//	}
//
//
//
//	/**
//	 * 确保读取了所有的缓存
//	 */
//	private void ensureCacheFull() {
//		if(isLoad) {
//			return ;
//		}
//
//		List<UserCountersAll> all = getDao().findByUname(city.getId());
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
//	private void putToCache(List<UserCountersAll> all) {
//		for (UserCountersAll u : all) {
//			UserCountersAllCacheDAOImplNew.getCache().put(u.getCounterId(), u.getUname(), u);
//		}
//	}
//
//
//	private void initAllZero() {
//		List<UserCountersAll> ls = Lists.newArrayList();
//
//		CounterKey[] ak = CounterKey.values();
//
//		for (CounterKey ck : ak) {
//			UserCountersAll c = new UserCountersAllImpl();
//			c.setUname(city.getId());
//			c.setCounterId(ck + "");
//			ls.add(c);
//		}
//
//		getDao().addAll(ls);
//		putToCache(ls);
//	}
//}
