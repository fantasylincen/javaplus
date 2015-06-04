//package cn.mxz.fish;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import message.S;
//import cn.mxz.FishTemplet;
//import cn.mxz.FishTempletConfig;
//import cn.mxz.FishWellTempletConfig;
//import cn.mxz.bag.AbstractBag;
//import cn.mxz.base.exception.OperationFaildException;
//import cn.mxz.base.exception.SureIllegalOperationException;
//import db.dao.factory.DaoFactory;
//import db.domain.UserFishes;
//import db.domain.UserFishesImpl;
//import db.domain.UserJunket;
//
//class JunketImpl extends AbstractBag<Fish> implements Junket {
//
//	private UserJunket	base;
//
//	@Override
//	public int getCapacity() {
//
////		int cap = base.getCapacity() + getCity().getPlayer().getLevel() / 2;
////
////		if(cap >  D.FISH_JUNKET_GRID_MAX) {
////
////			cap =  D.FISH_JUNKET_GRID_MAX;
////		}
////
////		return cap;
//
//		return FishWellTempletConfig.getMaxKey();
//	}
//	@Override
//	public void expand(int n) {
//
//		//DONOTHING 策划说不要扩容功能:2013年8月5日 18:08:42
////		base.addCapacity(n);
////
////		DaoFactory.getUserJunketDAO().update(base);
//	}
//
//	@Override
//	public void remove(int typeId, int count) {
//
//		int c = getCount(typeId);
//
//		if(c < count) {
//
//			throw new OperationFaildException(S.S10110);
//		}
//
//		Iterator<Fish> it = all.iterator();
//
//		while (count > 0 && it.hasNext()) {
//
//			FishImpl f = (FishImpl) it.next();
//
//			if(f.getTempletId() == typeId) {
//
//				boolean isRemoveAll = count >= f.getCount();
//
//				int reduce = 0;
//
//				if(isRemoveAll) {
//
//					reduce = f.getCount();
//
//					DaoFactory.getUserFishesDAO().delete(f.getGridId(), getCity().getId());
//
//					it.remove();
//
//				} else {
//
//					reduce = count;
//
//					f.fish.setCount(f.getCount() - count);
//
//					DaoFactory.getUserFishesDAO().update(f.fish);
//				}
//
//				count -= reduce;
//
//				continue;
//			}
//		}
//	}
//
//	public void setJunketBaseInfo(UserJunket base) {
//		this.base = base;
//	}
//
//	@Override
//	public void addProp(int id, int count) {
//
//		final FishTemplet templet = FishTempletConfig.get(id);
//
//		if(templet == null) {
//
//			throw new SureIllegalOperationException("-无效ID:" + id);
//		}
//
//		if(!canPut(id, count)) {
//
//			throw new OperationFaildException(S.S10112);
//		}
//
//		List<FishImpl> ls = split(id, count);
//
//		for (FishImpl f : ls) {
//
//			DaoFactory.getUserFishesDAO().add(f.fish);
//
//			all.add(f);
//		}
//	}
//
//	/**
//	 * 将一个数量较多的鱼篓格子, 按照鱼的叠加数量, 拆分为多个格子
//	 *
//	 * @param id			鱼篓格子装的鱼ID
//	 * @param count		鱼篓格子装的鱼的数量
//	 * @return
//	 */
//	private List<FishImpl> split(int id, int count) {
//
//		List<Integer> counts = splitCount(id, count);
//
//		List<FishImpl> ls = new ArrayList<FishImpl>();
//
//		for (Integer c : counts) {
//
//			UserFishes f = new UserFishesImpl();
//
//			f.setUname(base.getUname());
//
//			f.setGridId(nextGridId());
//
//			f.setTypeid(id);
//
//			f.setCount(c);
//
//			FishImpl fs = new FishImpl(city, f);
//
//			ls.add(fs);
//		}
//
//		return ls;
//	}
//
//	private List<Integer> splitCount(int id, int count) {
//
//		FishTemplet temp = FishTempletConfig.get(id);
//
//		final int addUp = temp.getAddUp();
//
//		List<Integer> ls = new ArrayList<Integer>();
//
//		while(count > 0) {
//
//			if(count < addUp) {
//
//				ls.add(count);
//
//			} else {
//
//				ls.add(addUp);
//			}
//
//			count -= addUp;
//		}
//
//		return ls;
//	}
//
////	@Override
////	public Fish removeByGrid(int gridId, int count) {
////
////		for (Fish f : getAll()) {
////
////			if(f.getGridId() == gridId) {
////
////				remove(f.getTempletId(), count);
////
////				return f;
////			}
////		}
////
////		return null;
////	}
//
//	@Override
//	public List<Fish> getAll() {
//
//		//叠加
////		List<Fish> fishs = new ArrayList<Fish>();
////
////		ICounter<Integer> c = buildCounter();
////
////		for (Entry<Integer, Integer> p : c.entrySet()) {
////
////			Integer count = p.getValue();
////
////			Integer typeId = p.getKey();
////
////			List<FishImpl> split = split(typeId, count);
////
////
////			fishs.addAll(split);
////		}
////
////		setId(fishs);
////
////		return fishs;
//
//		//不叠加
//		return all;
//	}
//
////	private void setId(List<Fish> fishs) {
////
////		int id = 1;
////
////		for (Fish f : fishs) {
////
////			((FishImpl)f).fish.setGridId(id++);
////		}
////	}
////	/**
////	 * 遍历所有鱼, 返回以个计数器   这个计数器记录了每种鱼各有多少条
////	 * @return
////	 */
////	private ICounter<Integer> buildCounter() {
////
////		ICounter<Integer> c = new Counter<Integer>();
////
////		for (Fish f : all) {
////
////			c.add(f.getProp().getTypeId(), f.getCount());
////		}
////
////		return c;
////	}
//
//	@Override
//	public Fish getFish(int gridId) {
//
//		for (Fish f : all) {
//
//			if(f.getGridId() == gridId) {
//
//				return f;
//			}
//		}
//
//		return null;
//	}
//	@Override
//	public Fish remove(int gridId) {
//
//		Iterator<Fish> it = all.iterator();
//
//		while (it.hasNext()) {
//
//			Fish next = it.next();
//
//			if(next.getGridId() == gridId) {
//
//				it.remove();
//
//				DaoFactory.getUserFishesDAO().delete(gridId, getCity().getId());
//
//				return next;
//			}
//		}
//
//		throw new SureIllegalOperationException("-没有找到指定格子!" + gridId);
//	}
//
////	@Override
////	public void removeAllBySpotId(int spotId) {
////		Iterator<Fish> it = all.iterator();
////		while (it.hasNext()) {
////			Fish f = (Fish) it.next();
////			FishTemplet ft = FishTempletConfig.get(f.getTempletId());
////			if(ft.getSpot() == spotId) {
////				DaoFactory.getUserFishesDAO().delete(f.getGridId(), getCity().getId());
////				it.remove();
////			}
////		}
////	}
////
////	/**
////	 *
////	 * 将 f 按照叠加上限合并到fishs中
////	 *
////	 * @param fishs
////	 * @param typeId
////	 * @param count
////	 */
////	private void merge(List<Fish> fishs, Integer typeId, int count) {
////
////		List<FishImpl> split = split(typeId, count);
////
////		for (FishImpl f : split) {
////
////			if(f.isFull()) {
////
////				fishs.add(f);
////
////			} else {
////
////				mergeNotFull(f.getProp().getTypeId(), f.getCount(), fishs);
////			}
////		}
////	}
////
////	/**
////	 * @param typeId	非满状态的鱼篓格子 类型
////	 * @param count		非满状态的鱼篓格子 数量
////	 * @param fishs
////	 */
////	private void mergeNotFull(Integer typeId, int count, List<Fish> fishs) {
////
////		while(count > 0) {
////
////			FishTemp ft = findFirstNotFull(fishs, typeId);
////
////			if(ft == null) {
////
////				ft = new FishTemp(typeId, 0);	//新开一个空格子
////
////				fishs.add(ft);
////			}
////
////			int free = ft.getFree();
////
////			boolean putAll = count <= free;	//是否全部放入
////
////
////			if(putAll) {
////
////				ft.add(count);
////
////				count = 0;
////
////			} else {//没有放完, 继续放
////
////				count -= free;	//剩余的没有放入的
////
////				ft.add(free);
////
////				mergeNotFull(typeId, count, fishs);
////			}
////		}
////	}
////
////
////
////	private FishTemp findFirstNotFull(List<Fish> fishs, Integer typeId) {
////
////		for (Fish fish : fishs) {
////
////			boolean isSameType = fish.getProp().getTypeId() == typeId;
////
////			if(isSameType && !fish.isFull()) {
////
////				return (FishTemp) fish;
////			}
////		}
////
////		return null;
////	}
//
//}
