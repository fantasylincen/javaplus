package cn.mxz.friend;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.FriendDao;
import mongo.gen.MongoGen.UserFriendRequestDao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.base.service.AbstractService;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.city.PlayerProperty;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.handler.FriendService;
import cn.mxz.log.FriendApplyListBuilder;
import cn.mxz.log.IFriendApplyListBuilder;
import cn.mxz.log.Log;
import cn.mxz.log.LogType;
import cn.mxz.log.LogsManager;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.protocols.user.friend.FirendListP.FriendReceiveListPro;
import cn.mxz.protocols.user.friend.FirendListP.FriendSendAndReceiveListPro;
import cn.mxz.protocols.user.friend.FirendListP.FriendsAllListPro;
import cn.mxz.protocols.user.friend.FriendAppayMessageListP.ApplyListPro;
import cn.mxz.protocols.user.friend.FriendAppayMessageListP.FriendWarSituationPro;
import cn.mxz.protocols.user.friend.PropsGiftableP.PropsGiftablePro;
import cn.mxz.protocols.user.friend.QueryFriendAppayP.QueryListPro;
import cn.mxz.protocols.user.log.LogsP.LogsPro;
import cn.mxz.pvp.LogsBuilder;
import cn.mxz.user.Player;
import cn.mxz.user.team.Formation;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.Marker;

@Component("friendService")
@Scope("prototype")
public class FriendServiceImpl extends AbstractService implements FriendService {

	@Override
	public FriendsAllListPro getFriendsAll(int page) {
		return getBuilder().buildFriendAllList(page);
	}

	@Override
	public void addRequest(String userId) {

		getManager().addRequest(userId);
	}

	@Override
	public final QueryListPro getQueryMessage(String userMes, int page) {

		if(userMes.startsWith("u")) {
			String id = userMes.replaceFirst("u", "");
			return new QueryListBuilder().build(id);
		}
		
		if ("请输入玩家昵称或ID".equals(userMes)) {
			userMes = "";
		}

		return getBuilder().buildQueryMessage(userMes, page);
	}

	@Override
	public void refuse(String userId) {

		getManager().refuse(userId);
	}

	@Override
	public void accept(String userId) {

		getManager().accept(userId);
	}

	@Override
	public void remove(String friendId) {

		getManager().checkFriend(friendId);

		getManager().remove(friendId);
	}

	@Override
	public FriendSendAndReceiveListPro getApplyRefuseMessage(int page) {

		return getBuilder().buildApplyRefuseMessage(page);

	}

	@Override
	public FriendReceiveListPro getReceiveMessage(int page) {
		return getBuilder().buildReceiveMessage(page);
	}

	@Override
	public ApplyListPro getApplyMessage(int page) {

		return getBuilder().buildApplyMessage(page);
	}

	@Override
	public void sendMessageToSomeOne(String userId, String message) {

		getManager().checkFriend(userId);

		City city = WorldFactory.getWorld().get(userId);

		MessageFactory.getFriend().sendMessage(city.getSocket(), message);
	}

	@Override
	public void sendProp(String friendId, int propId1, int count1, int propId2, int count2, int propId3, int count3) {

		City city2 = WorldFactory.getWorld().get(friendId);

		Marker u = getCity().getUserCounterHistory();

		if (!u.isMark(CounterKey.FRIEND_SEND_GIFT_MARK)) {

			if (count1 != -1) {

				getCity().getChecker().checkProp(propId1, count1);

				getCity().getBag().remove(propId1, count1);

				city2.getBag().addProp(propId1, count1);

			}

			if (count2 != -1) {

				getCity().getChecker().checkProp(propId2, count2);

				getCity().getBag().remove(propId2, count2);

				city2.getBag().addProp(propId2, count2);

			}

			if (count3 != -1) {

				getCity().getChecker().checkProp(propId3, count3);

				getCity().getBag().remove(propId3, count3);

				city2.getBag().addProp(propId3, count3);

			}

			u.mark(CounterKey.FRIEND_SEND_GIFT_MARK);

		}
	}

	@Override
	public PropsGiftablePro getGiftables() {

//		return new PropsGiftableBuilder().build(getCity().getBag());
		return null;
	}

	@Override
	public void cancel(String friendId) {

		getManager().cancel(friendId);
	}

	protected FriendManager getManager() {

		return getCity().getFriendManager();
	}

	protected FriendDao getFriendDataDAO() {
		return Daos.getFriendDao();
	}

	protected UserFriendRequestDao getUserFriendRequestDAO() {
		return Daos.getUserFriendRequestDao();
	}

	protected IFriendApplyListBuilder getBuilder() {

		City city = getCity();

		FriendManager fm = city.getFriendManager();
		FriendApplyListBuilder builder = new FriendApplyListBuilder(city, fm);

		builder.setRandomUserGenerator(new UserRandom(city));

		return builder;
	}

	@Override
	public void leaveAMessage(String user_id, String message) {
		City user = CityFactory.getCity(user_id);
		if (message.length() > 100) {
			throw new SureIllegalOperationException("-字数太多!");
		}
		LogsManager logsManager = user.getLogsManager();

		Player p = getCity().getPlayer();

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		logsManager.log(LogType.LEAVE_MESSAGE, getCity().getId() + "|" + p.getLevel() + "|" + p.getNick() + "|" + p.isMan() + "|" + getCity().getTeam().getPlayer().getTypeId() + "|" + p.get(PlayerProperty.CHARM) + "|" + f.format(new Date()) + "|" + message);

	}

	@Override
	public LogsPro getLeaveAMessages(int index, int count) {
		LogsManager logsManager = getCity().getLogsManager();
		List<Log> all = logsManager.getAll(LogType.LEAVE_MESSAGE);
		all = all.subList(index, all.size());
		all = cn.javaplus.util.Util.Collection.sub(all, count);

		return new LogsBuilder().build(all);
	}

	@Override
	public FriendWarSituationPro fighting(String userId) {
		City c = getCity();
		Formation formation = c.getFormation();
		PlayerCamp s = formation.getSelected();
		FriendBattle battle = new FriendBattleImpl(s, userId);
		battle.fighting();
		return new FriendWarSituationBuilder().build(battle);
	}

}