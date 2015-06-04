//package cn.mxz.bag;
//
//import message.S;
//import cn.javaplus.db.DAO2;
//import cn.mxz.EquipmentTemplet;
//import cn.mxz.PropTemplet;
//import cn.mxz.base.exception.OperationFaildException;
//import cn.mxz.base.exception.SureIllegalOperationException;
//import cn.mxz.prop.PropTempletFactory;
//import cn.mxz.user.Player;
//import db.domain.UserTemporaryGrid;
//import db.domain.UserTemporaryGridImpl;
//
///**
// *
// * 临时背包
// *
// * @author 彭超
// *
// * @since	2013年8月13日 14:30:35
// *
// */
//class TemporaryBag extends AbstractBag<Grid>{
//
//	private DAO2<Integer, String, UserTemporaryGrid> userTemporaryGridDAO;
//
//	@Override
//	public int getCapacity() {
//		return 1000;
//	}
//
//
//	@Override
//	public void expand(final int n) {}
//
//	@Override
//	public void remove(final int typeId, int count) {
//
//        checkRemoCount(typeId, count);
//
//		TemporaryGrid temporaryGrid = null;
//
//		UserTemporaryGrid UtGrid = null;
//
//		// 数量-偏移量
//		int offset = -count;
//
//		for(int i = 0; i < all.size(); ) {
//
//			temporaryGrid = (TemporaryGrid) all.get(i);
//
//			UtGrid = temporaryGrid.getUserTemporaryGrid();
//
//			if (UtGrid.getTypeid() == typeId) {
//
//				offset = UtGrid.getCount() - count;
//
//				if (offset >= 0) {
//
//					UtGrid.setCount(offset);
//
//					// 全部移除
//					if (offset == 0) {
//
//
//						getUserTemporaryGridDAO().delete(UtGrid.getGridId(), UtGrid.getUname());
//
//						all.remove(temporaryGrid);
//
//						break;
//
//					} else {
//
//						getUserTemporaryGridDAO().update(UtGrid);
//
//						break;
//					}
//
//				} else {
//
//					getUserTemporaryGridDAO().delete(UtGrid.getGridId(), UtGrid.getUname());
//
//					all.remove(temporaryGrid);
//
//					count = -offset;
//				}
//
//			} else {
//
//				i++;
//			}
//		}
//	}
//
//	/**
//	 * 检查移除道具个数
//	 *
//	 * @param typeId 移除物品ID
//	 * @param count 移除个数
//	 */
//	private void checkRemoCount(final int typeId, final int count) {
//
//		TemporaryGrid temporaryGrid = null;
//
//		UserTemporaryGrid UtGrid = null;
//
//		int findCount = 0;
//
//		for(int i = 0; i < all.size(); i++) {
//
//			temporaryGrid = (TemporaryGrid) all.get(i);
//
//			UtGrid = temporaryGrid.getUserTemporaryGrid();
//
//			if (UtGrid.getTypeid() == typeId) {
//
//				findCount += UtGrid.getCount();
//
//			}
//		}
//
//		if(findCount < count) {
//
//			throw new SureIllegalOperationException("-移除道具" + typeId + "数量不够");
//		}
//	}
//
//	@Override
//	public void addProp(final int id, final int count) {
//
//		if(!canPut(id, count)) {
//
//			throw new OperationFaildException(S.S10113);
//		}
//
//        final PropTemplet templet = PropTempletFactory.get(id);
//
//		if(templet == null || templet instanceof EquipmentTemplet) {
//
//			throw new SureIllegalOperationException("-无效ID:" + id);
//		}
//
//		UserTemporaryGrid userTemporaryGrid = new UserTemporaryGridImpl();
//
//
//		boolean isHas = false;
//
//		for(int i = 0; i < all.size(); i++) {
//
//			if(all.get(i).getTempletId() == id && !all.get(i).isFull()) {
//
//				userTemporaryGrid = ((TemporaryGrid)all.get(i)).getUserTemporaryGrid();
//
//				isHas = true;
//
//				break;
//			}
//		}
//
//		// 是否超出叠加上限
//		boolean isBeyand = false;
//
//		// 递归添加数量缓存
//		int addBuffer = 0;
//
//
//		if(isHas) {
//
//			// 超过叠加上限
//			if(count + userTemporaryGrid.getCount() > templet.getAddUp()) {
//
//				addBuffer = userTemporaryGrid.getCount();
//
//				userTemporaryGrid.setCount(templet.getAddUp());
//
//				isBeyand = true;
//
//			} else {
//
//				userTemporaryGrid.setCount(count + userTemporaryGrid.getCount());
//			}
//
//			getUserTemporaryGridDAO().update(userTemporaryGrid);
//
//		} else {
//
//			userTemporaryGrid.setGridId(nextGridId());
//
//			userTemporaryGrid.setTypeid(id);
//
//
//		    if(count + userTemporaryGrid.getCount() > templet.getAddUp()) {
//
//				addBuffer = userTemporaryGrid.getCount();
//
//				userTemporaryGrid.setCount(templet.getAddUp());
//
//				isBeyand = true;
//
//			} else {
//
//				userTemporaryGrid.setCount(count);
//			}
//
//			Player player = getCity().getPlayer();
//
//			userTemporaryGrid.setUname(player.getId());
//
//			getUserTemporaryGridDAO().add(userTemporaryGrid);
//
//			final TemporaryGrid tGrid = new TemporaryGrid();
//
//			tGrid.setCity(city);
//
//			tGrid.setUserTemporaryGrid(userTemporaryGrid);
//
//			getAll().add(tGrid);
//
//		}
//
//		if(isBeyand) {
//
//			addProp(id, count - templet.getAddUp() + addBuffer);
//		}
//
//	}
//
//
//	public DAO2<Integer, String, UserTemporaryGrid> getUserTemporaryGridDAO() {
//		return userTemporaryGridDAO;
//	}
//
//
//	public void setUserTemporaryGridDAO(
//			final DAO2<Integer, String, UserTemporaryGrid> userTemporaryGridDAO) {
//		this.userTemporaryGridDAO = userTemporaryGridDAO;
//	}
//
//
////	@Override
////	public void removeAllBySpotId(int spotId) {
////
////		Iterator<Grid> it = all.iterator();
////		while (it.hasNext()) {
////			TemporaryGrid grid = (TemporaryGrid) it.next();
////			PropTemplet temp = PropTempletFactory.get(grid.getTempletId());
////			if(temp.getSpot() == spotId) {
////
////				getUserTemporaryGridDAO().delete(grid.getGridId(), grid.getCity().getId());
////
////				it.remove();
////			}
////		}
////
////	}
//}
