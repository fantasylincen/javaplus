package cn.mxz.friend;


import java.util.List;


/**
 * 玩家好友管理器
 * @author 林岑
 *
 */
public interface FriendManager {

	/**
	 * 获得玩家所有的好友
	 * @return
	 */
	List<Friend> getAll();

	/**
	 * 判断我和某人是否是好友 ture表示是好友
	 * @param userId
	 * @return
	 */
	boolean isFriend(String userId);

	/**
	 * 获得指定ID的好友
	 * @param userId
	 * @return
	 */
	Friend getFriend(String userId);

	/**
	 * 好友请求数量
	 * @return
	 */
	int getRequestCount();

	/**
	 * 移除好友
	 * @param friendId
	 */
	void remove(String friendId);

	/**
	 * 同意某人的好友请求
	 * @param userId
	 */
	void accept(String userId);

	/**
	 * 添加好友申请
	 * @param userId
	 */
	void addRequest(String userId);

	/**
	 * 拒绝
	 * @param userId
	 */
	void refuse(String userId);

	/**
	 * 取消申请
	 * @param friendId
	 */
	void cancel(String friendId);

	void addListener(FriendManagerListener l);

	/**
	 * 检查我和他是否是好友
	 * @param friendId
	 */
	void checkFriend(String friendId);

	/**
	 * 拒绝所有
	 */
	void refuseAll();

	/**
	 * 添加一个好友
	 * @param friendId
	 */
	void add(String friendId);

	/**
	 * 所有的好友请求
	 * @return
	 */
	List<Request> getRequests();
}
