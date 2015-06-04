package cn.mxz.base.service;

import cn.mxz.PrizeInExcel;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.base.prize.PrizeSender;
import cn.mxz.base.prize.PrizeSenderFactory;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.user.Player;
import cn.mxz.util.Service;
import cn.mxz.util.checker.Checker;
import cn.mxz.util.checker.PlayerChecker;

import com.alibaba.fastjson.JSON;
import com.lemon.commons.socket.ISocket;

/**
 * 服务
 *
 * @author 林岑
 */
public class AbstractService implements Service {

	private City	city;

	private World	world;

	private ISocket	socket;

	/**
	 * @return 城池
	 */
	@Override
	public final City getCity() {

		if (city == null) {
			SureIllegalOperationException e = new SureIllegalOperationException("Session 已失效!");
//			e.printStackTrace();
			throw e;
		}

		return city;
	}

	@Override
	public Player getPlayer() {

		if (city == null) {

			return null;
		}

		return city.getPlayer();
	}

	@Override
	public void init(final ISocket socket) {

		this.socket = socket;

		world = WorldFactory.getWorld();

		city = world.getSocketManager().getUser(socket);
	}

	@Override
	public ISocket getSocket() {
		return socket;
	}

	/**
	 * @return 世界
	 */
	protected final World getWorld() {
		return world;
	}

	/**
	 * 玩家检查器
	 *
	 * @return
	 */
	protected final Checker getChecker() {

		return new PlayerChecker(getPlayer());
	}

	/**
	 * 发送指定奖励给当前玩家
	 *
	 * @param reward
	 */
	protected final void sendPrize(final PrizeInExcel reward) {

		final PrizeSender ps = PrizeSenderFactory.getPrizeSender();

		ps.send(getPlayer(), reward.getAwards());
	}

	/**
	 * 获得指定帐号玩家的昵称
	 *
	 * @param userId
	 * @return
	 */

	protected final String getNick(final String userId) {

		final City city = getWorld().get(userId);

		return city.getPlayer().getNick();
	}

	/**
	 * 玩家ID
	 *
	 * @return
	 */
	protected final String getId() {
		return getCity().getId();
	}

	/**
	 * 玩家昵称
	 *
	 * @return
	 */

	protected final String getNick() {
		return getPlayer().getNick();
	}

	protected String toJson(Object o) {
		return JSON.toJSONString(o);
	}

	@Override
	public City getCityNullAble() {
		return city;
	}

}
