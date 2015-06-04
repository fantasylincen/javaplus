//package cn.mxz.user.team;
//
//import cn.mxz.fighter.Fighter;
//import db.dao.DAO;
//import db.domain.UserPvpMessage;
//
//public interface PVPFormation<T extends Fighter> extends Formation<T>,  Iterable<T> {
//
//	/**
//	 * @return 获取阵型数据DAO
//	 */
//	public DAO<String,UserPvpMessage> getDAO();
//
//    /**
//     * 移除指定位置站
//     * @param position 位置
//     */
//    public void remove(int position);
//}
