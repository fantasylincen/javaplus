package cn.mxz.thirdpaty;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import message.S;
import cn.javaplus.util.StringResolver;
import cn.javaplus.util.Util;
import cn.mxz.base.config.ServerConfig;
import cn.mxz.base.server.linekong.LineKongException;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.events.EventDispatcher;
import cn.mxz.market.Goods;
import cn.mxz.mission.old.PrizeImpl;
import cn.mxz.prizecenter.UserPrizePackage;
import cn.mxz.temp.TempCollection;
import cn.mxz.temp.TempKey;
import cn.mxz.util.debuger.Debuger;
import cn.mxz.util.debuger.SystemLog;

import com.google.common.collect.Lists;
import com.linekong.platform.protocol.erating.ErrorCode;
import com.linekong.platform.protocol.erating.LineKongServerPassword;
import com.linekong.platform.protocol.erating.PDUMessage;
import com.linekong.platform.protocol.erating.Packet;
import com.linekong.platform.protocol.erating.Response;
import com.linekong.platform.protocol.erating.define.D;
import com.linekong.platform.protocol.erating.eRatingAGIP.ActivityItemQueryExRequest;
import com.linekong.platform.protocol.erating.eRatingAGIP.ActivityItemQueryExRespond;
import com.linekong.platform.protocol.erating.eRatingAGIP.BindRequest;
import com.linekong.platform.protocol.erating.eRatingAGIP.BindRespond;
import com.linekong.platform.protocol.erating.eRatingAGIP.CreateRoleRequest;
import com.linekong.platform.protocol.erating.eRatingAGIP.CreateRoleRespond;
import com.linekong.platform.protocol.erating.eRatingAGIP.DeleteRoleRequest;
import com.linekong.platform.protocol.erating.eRatingAGIP.DeleteRoleRespond;
import com.linekong.platform.protocol.erating.eRatingAGIP.GenerateRoleNameRequest;
import com.linekong.platform.protocol.erating.eRatingAGIP.GenerateRoleNameRespond;
import com.linekong.platform.protocol.erating.eRatingAGIP.GwDataReportRequest;
import com.linekong.platform.protocol.erating.eRatingAGIP.GwDataReportRespond;
import com.linekong.platform.protocol.erating.eRatingAGIP.IERatingProtocol;
import com.linekong.platform.protocol.erating.eRatingAGIP.JointAuthenExRequest;
import com.linekong.platform.protocol.erating.eRatingAGIP.JointAuthenExRespond;
import com.linekong.platform.protocol.erating.eRatingAGIP.RoleEnterGameEx4Request;
import com.linekong.platform.protocol.erating.eRatingAGIP.RoleEnterGameEx4Respond;
import com.linekong.platform.protocol.erating.eRatingAGIP.RoleListRequest;
import com.linekong.platform.protocol.erating.eRatingAGIP.RoleListRespond;
import com.linekong.platform.protocol.erating.eRatingAGIP.UserBalanceInfoExRequest;
import com.linekong.platform.protocol.erating.eRatingAGIP.UserBalanceInfoExRespond;
import com.linekong.platform.protocol.erating.eRatingAGIP.UserIbPayExRequest;
import com.linekong.platform.protocol.erating.eRatingAGIP.UserIbPayExRespond;
import com.linekong.platform.protocol.erating.eRatingAGIP.UserItemMinusExRequest;
import com.linekong.platform.protocol.erating.eRatingAGIP.UserItemMinusExRespond;
import com.linekong.platform.protocol.erating.eRatingAGIP.UserLogoutRequest;
import com.linekong.platform.protocol.erating.eRatingAGIP.UserLogoutRespond;

public abstract class AbstractEratingConnector extends EventDispatcher
		implements ThirdPartyPlatform {

	public class Subject {

		private int id;
		private int rechargeGoldNeed;

		public Subject(int id, int amount) {
			this.id = id;
			this.rechargeGoldNeed = amount;
		}

		public long getId() {
			return id;
		}

		public long getAmount() {
			return rechargeGoldNeed;
		}

	}

	public class Consume {

		private int count;
		private List<Subject> subjects = Lists.newArrayList();

		public Consume(int count, int rechargeGoldNeed, int systemGoldNeed,
				int price) {
			this.count = count;

			if (rechargeGoldNeed > 0) {
				subjects.add(new Subject(SUBJECT_ID, rechargeGoldNeed));
			}
			if (systemGoldNeed > 0) {
				subjects.add(new Subject(SYSTEM_GOLD_SUBJECT_ID, systemGoldNeed));
			}
		}

		public int getBuyCount() {
			return count;
		}

		public long[] getSubjectIds() {
			long[] ids = new long[subjects.size()];
			for (int i = 0; i < ids.length; i++) {
				ids[i] = subjects.get(i).getId();
			}
			return ids;
		}

		public long[] getSubAmounts() {
			long[] amounts = new long[subjects.size()];

			for (int i = 0; i < amounts.length; i++) {
				amounts[i] = subjects.get(i).getAmount();
			}
			return amounts;
		}

		public long getSubjectCount() {
			return subjects.size();
		}

		public int getPrice() {
			int p = 0;
			for (Subject s : subjects) {
				p += s.getAmount();
			}
			return p / count;
		}

	}

	/**
	 * 科目编码（货币类型）: 3–表示蓝港币； 4–表示体验币或免费币； 5–表示已兑换/领取元宝；6 用于数据统计的元宝
	 */
	private static final int SUBJECT_ID = 5;
	private static final int SYSTEM_GOLD_SUBJECT_ID = 6;
	protected Socket socket;
	private String eratingPath;

	/**
	 * @param eratingPath
	 *            格式 ip:port
	 */
	public AbstractEratingConnector(String eratingPath) {
		this.eratingPath = eratingPath;
	}

	private void sendPacket(long cmdBind, IERatingProtocol request,
			IERatingProtocol response) {
		sendPacket(cmdBind, request, response, D.GAME_ID);
	}
	
	private void sendPacket(long cmdBind, IERatingProtocol request,
			IERatingProtocol response, long gameId) {
		Packet r = new Packet(socket);
		r.setCommandId(cmdBind);

		// Debuger.debug("AbstractEratingConnector.sendPacket() bindId : " +
		// cmdBind);

		r.setBody(request);
		Response rs = r.send(gameId);
		response.analyzeBody(rs.getBody());
	}

	@Override
	public void connect() {
		socket = new Socket();
		String ip = null;
		int port = 0;

		try {
			StringResolver path = new StringResolver(eratingPath);
			List<StringResolver> s = path.split(":");
			ip = s.get(0).getString();
			port = s.get(1).getInt();
			InetSocketAddress a = new InetSocketAddress(ip, port);
			SystemLog.debug("EratingConnectorImpl.connect() 正在连接Erating " + ip
					+ ":" + port);
			socket.connect(a);
			SystemLog.debug("EratingConnectorImpl.connect() Erating连接成功 " + ip
					+ ":" + port);
		} catch (IOException e) {
			SystemLog.error("EratingConnectorImpl.connect() Erating连接失败 " + ip
					+ ":" + port);
			throw new LineKongException(" 无法连接到Erating:" + eratingPath);
		}
	}

	@Override
	public void bind() {
		BindRequest body = new BindRequest();
		String gatewayCode = "gw" + ServerConfig.getServerId();
		body.setGatewayCode(gatewayCode);
		body.setGatewayPassword(getPassword(ServerConfig.getServerId()));
		body.setMac("001D7DD14955");
		body.setReconnectFlag((byte) 1);
		body.setServerId(ServerConfig.getServerId());
		BindRespond response = new BindRespond();

		sendPacket(PDUMessage.CMD_BIND, body, response);

		check(response.getResultCode());
	}

	private String getPassword(int serverId) {
		return LineKongServerPassword.get(serverId);
	}

	private void check(int resultCode) {
		if (isError(resultCode)) {
			throw new ThirdPartyException(resultCode);
		}
	}

	private boolean isError(int resultCode) {
		return resultCode != ErrorCode.S_SUCCESS;
	}

	@Override
	public String checkToken(String uname, String token, String mac,
			int clientType, int unixTime, String adId, long gameId) {

		TokenCache cache = TokenCacheManager.getCache(uname, token);
		if (cache != null) {
			if (cache.isTimeUp()) {
				cache.delete();
			} else {
				return cache.getUserId();
			}
		}

		try {
			start();

			JointAuthenExRequest body = new JointAuthenExRequest();
			int ip = Util.IP.getValue("127.0.0.1");
			int port = 12345;

			body.setParamNameAndValue("UserIP4", ip);// 客户端IP
			body.setParamNameAndValue("Port", port);// 端口
			body.setParamNameAndValue("Token", token);// 校验码
			body.setParamNameAndValue("UID", uname);// 合作运营方帐号ID；
			body.setParamNameAndValue("UN", uname);// 合作运营方用来标识其用户的唯一ID，一般情况下为第三方的用户ID；（手游）
			body.setParamNameAndValue("MAC", mac);// 客户端网卡唯一标识
			body.setParamNameAndValue("ClientType", clientType);
			body.setParamNameAndValue("UnixTime", unixTime);
			body.setParamNameAndValue("ADID", getADID(adId));

			JointAuthenExRespond rb = new JointAuthenExRespond();
			sendPacket(PDUMessage.CMD_JOINT_AUTHEN_EX, body, rb, gameId);
			check(rb.getResultCode());

			String string = rb.getUserID() + "";

			Debuger.debug("AbstractEratingConnector.checkToken() token 验证通过, userId:"
					+ string);

			TokenCacheManager.putTokenToCache(uname, token, string);

			return string;
		} finally {
			end();
		}
	}

	protected abstract void start();

	/**
	 * 广告ID
	 * 
	 * @param otherValue
	 * @return
	 */
	private String getADID(String otherValue) {
		return otherValue;
	}

	@Override
	public void logout(ThirdPartyRole user) {

		try {
			start();

			UserLogoutRequest body = new UserLogoutRequest();
			body.setRoleID(new Long(user.getRoleId()));
			body.setLogoutFlag((short) 1);
			body.setMoney1(user.getCash());
			body.setMoney2(user.getGold());
			body.setRatingID(ServerConfig.getServerId());
			body.setRoleLevel(user.getLevel());

			String s = user.getRoleType() + "";

			// 角色ID 的后 N 位
			body.setRoleOccupation(new Short(s.substring(4, s.length())));

			// 发送的是角色ID的hashCode, 不能保证完全不重复
			body.setUserID(new Long(user.getUserId()));

			UserLogoutRespond rb = new UserLogoutRespond();

			sendPacket(PDUMessage.CMD_USER_LOGOUT, body, rb);
			check(rb.getResultCode());
			Debuger.debug("AbstractEratingConnector.logout()" + user != null ? (user
					.getUserId() + "," + user.getRoleId())
					: "null");
			// Debuger.debug("EratingConnectorImpl.logout()");

		} finally {
			end();
		}

	}

	protected abstract void end();

	@Override
	public String createUser(String id, boolean isMan, int initialLevel,
			String ip, String roleName, long gameId) {

		try {
			start();

			CreateRoleRequest body = new CreateRoleRequest();
			body.setUserID(new Long(id));
			body.setRoleGender((short) (isMan ? 1 : 2));
			body.setInitialLevel(initialLevel);
			body.setUserIP(Util.IP.getValue(ip));
			body.setUserPort(-1);
			body.setRoleName(roleName);

			CreateRoleRespond rb = new CreateRoleRespond();

			sendPacket(PDUMessage.CMD_CREATE_ROLE, body, rb, gameId);
			check(rb.getResultCode());
			// Debuger.debug("EratingConnectorImpl.createUser()");

			return rb.getRoleId() + "";
		} finally {
			end();
		}
	}

	@Override
	public String getRoleId(String userId, long gameId) {
		try {
			start();

			RoleListRequest body = new RoleListRequest();
			body.setUserId(new Long(userId));

			RoleListRespond rb = new RoleListRespond();

			sendPacket(PDUMessage.CMD_ROLE_LIST, body, rb, gameId);
			check(rb.getResultCode());

			// Debuger.debug("EratingConnectorImpl.getRoleId()");

			Debuger.debug("AbstractEratingConnector.getRoleId() " + gameId);
			Debuger.debug("AbstractEratingConnector.getRoleId() 角色数量:"
					+ rb.getRoleCount());
			if (rb.getRoleCount() == 0) {
				return null;
			}

			long[] ids = rb.getRoleID();

			// 2014年5月27日 14:28:28 林岑加 为了处理循环不停创建角色的问题
			for (long id : ids) {
				String stringId = "" + id;
				City city = CityFactory.getCity(stringId);
				if (city != null) {
					Debuger.debug("AbstractEratingConnector.getRoleId() roleId: "
							+ stringId);
					return stringId;
				}
			}

			String string = ids[0] + "";

			Debuger.debug("AbstractEratingConnector.getRoleId() roleId: "
					+ string);

			return string;

		} finally {
			end();
		}
	}

	// 3 删除角色:3.6.3
	@Override
	public void deleteRole(String userId, String roleId) {
		try {
			start();

			DeleteRoleRequest body = new DeleteRoleRequest();
			body.setUserID(new Long(userId));
			body.setroleID(new Long(roleId));

			DeleteRoleRespond rb = new DeleteRoleRespond();

			sendPacket(PDUMessage.CMD_DELETE_ROLE, body, rb);
			check(rb.getResultCode());
			// Debuger.debug("EratingConnectorImpl.deleteRole()");

			Debuger.debug("AbstractEratingConnector.deleteRole() 删除角色成功!");

		} finally {
			end();
		}
	}

	// 4 角色进入游戏:3.6.8
	@Override
	public void logIn(ThirdPartyRole user, long gameId) {
		try {
			start();

			RoleEnterGameEx4Request body = new RoleEnterGameEx4Request();

			body.setServerId(ServerConfig.getServerId());
			body.setUserId(new Long(user.getUserId()));
			body.setRoleId(new Long(user.getRoleId()));
			body.setLevel(user.getLevel());
			body.setGender((short) (user.isMan() ? 1 : 2));

			body.setClientIP(Util.IP.getValue(user.getIp()));
			body.setClientType(user.getClientType());

			RoleEnterGameEx4Respond rb = new RoleEnterGameEx4Respond();

			sendPacket(PDUMessage.CMD_ROLE_ENTER_GAME_EX4, body, rb, gameId);
			check(rb.getResultCode());
			Debuger.debug("AbstractEratingConnector.logIn() 角色进入游戏!"
					+ user.getUserId() + "," + user.getRoleId());
			// Debuger.debug("EratingConnectorImpl.logIn()");

		} finally {
			end();
		}
	}

	// 消费流水号
	private static AtomicInteger serialNumber = new AtomicInteger(0);

	//
	@Override
	public List<UserPrizePackage> queryPrize(City user) {
		try {
			start();
			ActivityItemQueryExRequest req = new ActivityItemQueryExRequest();
			int activityId = 0;
			req.setActivityId(activityId);
			req.setRoleId(Long.parseLong(user.getId()));
			req.setUserId(new Long(user.getPlayer().getThirdPartyId()));
			req.setRoleLevel(user.getLevel());

			ActivityItemQueryExRespond respond = new ActivityItemQueryExRespond();

			sendPacket(PDUMessage.CMD_ACTIVITY_ITEM_QUERY_EX, req, respond);
			check(respond.getResultCode());

			return buildPrize(respond);
		} finally {
			end();
		}

	}

	private List<UserPrizePackage> buildPrize(ActivityItemQueryExRespond respond) {
		List<UserPrizePackage> ret = Lists.newArrayList();

		int activityCount = respond.getActivityCount();
		long[] activityId = respond.getActivityId();
		String[] activityDesc = respond.getActivityDesc();
		int[] itemCount = respond.getItemCount();

		String[][] itemCode = respond.getItemCode();
		int[][] itemNum = respond.getItemNum();
		long[][] bgnTime = respond.getBeginTime();
		long[][] endTime = respond.getEndTime();

		System.out.println("activityCount=" + activityCount);

		for (int i = 0; i < activityCount; i++) {
			UserPrizePackage pack = new UserPrizePackage();
			pack.setLingkong(true);
			pack.setId((int) activityId[i]);
			// System.out.println("activityId[" + i + "]=" + activityId[i]);
			pack.setDesc(activityDesc[i]);
			// System.out.println("activityDesc[" + i + "]=" + activityDesc[i]);

			// System.out.println("itemCount[" + i + "]=" + itemCount[i]);
			List<PrizeImpl> prizeList = Lists.newArrayList();
			for (int k = 0; k < itemCount[i]; k++) {
				PrizeImpl prize = new PrizeImpl(
						Integer.parseInt(itemCode[i][k]), itemNum[i][k]);
				// System.out.println("itemCode[" + i + "][" + k + "]="
				// + itemCode[i][k]);
				// System.out.println("itemNum[" + i + "][" + k + "]="
				// + itemNum[i][k]);
				pack.setCreateTime((int) bgnTime[i][k]);
				// System.out.println("bgnTime[" + i + "][" + k + "]="
				// + bgnTime[i][k]);
				pack.setEndTime((int) endTime[i][k]);
				// System.out.println("endTime[" + i + "][" + k + "]="
				// + endTime[i][k]);
				prizeList.add(prize);
			}
			pack.setPrizes(prizeList);
			ret.add(pack);
		}
		return ret;
	}

	/**
	 * 尚未完成
	 */
	@Override
	public boolean getPrize(ThirdPartyRole user, List<Prize> prize,
			long activityId) {

		try {
			start();
			UserItemMinusExRequest body = new UserItemMinusExRequest();
			int count = prize.size();
			long[] activityID = new long[count];
			String[] itemCode = new String[count];
			int[] itemNum = new int[count];
			int index = 0;
			for (Prize p : prize) {
				activityID[index] = activityId;
				itemCode[index] = p.getId() + "";
				itemNum[index] = p.getCount();
				index++;
			}
			body.setItemCount(prize.size());
			body.setActivityID(activityID);
			body.setItemCode(itemCode);
			body.setItemNum(itemNum);

			body.setRoleID(new Long(user.getRoleId()));
			body.setUserID(new Long(user.getUserId()));

			UserItemMinusExRespond rb = new UserItemMinusExRespond();

			sendPacket(PDUMessage.CMD_USER_ITEM_MINUS_EX, body, rb);
			check(rb.getResultCode());
		} finally {
			end();
		}
		return true;
	}

	@Override
	public String generateRoleName(boolean isMan) {
		try {
			start();
			GenerateRoleNameRequest body = new GenerateRoleNameRequest();
			body.setRoleCount(1);
			body.setRoleGender(isMan ? 1 : 2);

			GenerateRoleNameRespond rb = new GenerateRoleNameRespond();

			sendPacket(PDUMessage.CMD_GENERATE_ROLE_NAME, body, rb);
			check(rb.getResultCode());

			return rb.getRoleName()[0];
		} finally {
			end();
		}
	}

	/**
	 * 给Erating上载 当前服务器在线人数
	 */
	@Override
	public void updateOnlineSize(int size) {
		try {
			start();

			GwDataReportRequest body = new GwDataReportRequest();
			body.setServerID(ServerConfig.getServerId());
			body.setDataCount(1);
			body.setDataType(new int[] { 1 });
			body.setDataValue(new int[] { size });

			GwDataReportRespond rb = new GwDataReportRespond();

			sendPacket(PDUMessage.CMD_GW_DATA_REPORT, body, rb);

			check(rb.getResultCode());
		} finally {
			end();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isPlatformUserId(String userId) {

		if (userId.startsWith("hzmxz") && userId.endsWith("hzmxz")) {
			return false;
		}

		if (Debuger.isDevelop()) {
			return false;
		}

		return !Debuger.isInside();
	}

	// public static void main(String[] args) {
	// long ms = System.currentTimeMillis();
	// String s = ms + "";
	// System.out.println(s);
	//
	// s = s.substring(1, s.length() - 2);
	//
	// System.out.println(s);
	// }

	private void pay(ThirdPartyRole user, String name, int count, int price,
			int rechargeGoldNeed, int systemGoldNeed) {

		// Debuger.debug("AbstractEratingConnector.pay()" + count + ", " + price
		// + "," + reduce);
		try {
			start();

			UserIbPayExRequest body = new UserIbPayExRequest();

			int add = serialNumber.addAndGet(1);
			if (add > 100) {
				serialNumber.set(0);
			}
			String s = "" + System.currentTimeMillis();
			s = s.substring(1, s.length() - 2);
			BigInteger detailID = new BigInteger(s + add);
			body.setDetailID(detailID);
			Debuger.debug("AbstractEratingConnector.pay() detailID = "
					+ detailID + "," + rechargeGoldNeed + "," + systemGoldNeed
					+ "," + count);
			body.setUserID(new Long(user.getUserId()));
			body.setRoleId(new Long(user.getRoleId()));
			body.setRoleGender((short) (user.isMan() ? 1 : 2));
			body.setRoleLevel(user.getLevel());
			body.setRatingId(ServerConfig.getServerId());

			name = limit(name);
			Debuger.debug("AbstractEratingConnector.pay() name = " + name);

			body.setIbCode(name);
			body.setPayTime((int) (System.currentTimeMillis() / 1000));
			body.setUserIP(Util.IP.getValue(user.getIp()));

			Consume comsume = new Consume(count, rechargeGoldNeed,
					systemGoldNeed, price);
			body.setCount(comsume.getBuyCount());

			int price2 = comsume.getPrice();
			long subjectCount = comsume.getSubjectCount();
			long[] subjectIds = comsume.getSubjectIds();
			long[] subAmounts = comsume.getSubAmounts();

			body.setPrice(price2);
			body.setDiscountPrice(price2);
			body.setSubjectCount(subjectCount);
			body.setSubjectId(subjectIds);
			body.setSubAmount(subAmounts);

			UserIbPayExRespond rb = new UserIbPayExRespond();

			sendPacket(PDUMessage.CMD_USER_IB_PAY_EX_REQ, body, rb);

			int resultCode = rb.getResultCode();
			try {
				if (isError(resultCode)) {
					String roleId = user.getRoleId();
					City city = CityFactory.getCity(roleId);
					city.getMessageSender().send(S.S10154, resultCode);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			check(resultCode);

			Debuger.debug("AbstractEratingConnector.pay() 消费:"
					+ user.getUserId() + "," + user.getRoleId() + "," + count
					+ "," + price + "," + rechargeGoldNeed + systemGoldNeed);

		} finally {
			end();
		}
	}

	private static String limit(String name) {
		if (name.length() > 32) {
			String replaceAll = name.replaceAll("ServiceImpl", "")
					.replaceAll("Service", "")
					.replaceAll("cn\\.mxz\\.heishi\\.", "")
					.replaceAll("cn\\.mxz\\.chengzhang\\.", "")
					.replaceAll("TransformImpl", "")
					.replaceAll("Transform", "");
			if (replaceAll.length() > 32) {
				return replaceAll.substring(0, 32);
			}
			return replaceAll;
		}
		return name;
	}

	public static void main(String[] args) {
		System.out
				.println(limit("111111111111111Task.reccn.mxz.heishi.eiverdTransformServiceImpl"));
	}

	@Override
	public void pay(ThirdPartyRole user, int rechargeGoldNeed,
			int systemGoldNeed) {
		String roleId = user.getRoleId();
		City city = CityFactory.getCity(roleId);
		TempCollection c = city.getTempCollection();
		Object obj = c.get(TempKey.WILL_BUY_PROP_THIS_TIME);
		if (obj != null) {
			Goods goods = (Goods) obj;

			int priceOne = goods.getPrice() / goods.getCount();

			Debuger.debug("AbstractEratingConnector.pay()" + goods);
			pay(ThirdPartyPlatformFactory.createRole(city), goods.getToolId()
					+ "", goods.getCount(), priceOne, rechargeGoldNeed,
					systemGoldNeed);
		} else {
			String explain = (String) c.get(TempKey.OPERATION_THIS_TIME);
			pay(user, explain, 1, rechargeGoldNeed + systemGoldNeed,
					rechargeGoldNeed, systemGoldNeed);
			Debuger.debug("AbstractEratingConnector.pay()" + explain);
		}
	}

	//
	// @Override
	// public void payGiftGold(ThirdPartyRole user, int reduce) {
	// String roleId = user.getRoleId();
	// City city = CityFactory.getCity(roleId);
	// TempCollection c = city.getTempCollection();
	// Object obj = c.get(TempKey.WILL_BUY_PROP_THIS_TIME);
	// if (obj != null) {
	// Goods goods = (Goods) obj;
	//
	// Debuger.debug("AbstractEratingConnector.pay()" + goods + ", "
	// + reduce);
	// payGiftGold(ThirdPartyPlatformFactory.createRole(city),
	// goods.getToolId() + "", 1, reduce, reduce);
	// } else {
	// String explain = (String) c.get(TempKey.OPERATION_THIS_TIME);
	// payGiftGold(user, explain, 1, reduce, reduce);
	// Debuger.debug("AbstractEratingConnector.pay()" + explain);
	// }
	// }

	@Override
	public int getGold(ThirdPartyRole user) {
		try {
			start();

			UserBalanceInfoExRequest body = new UserBalanceInfoExRequest();

			body.setRatingId(ServerConfig.getServerId());
			body.setUserId(new Long(user.getUserId()));
			body.setRoleId(new Long(user.getRoleId()));
			body.setSubjectId(SUBJECT_ID);

			UserBalanceInfoExRespond rb = new UserBalanceInfoExRespond();

			sendPacket(PDUMessage.CMD_USER_BALANCE_INFO_EX, body, rb);
			check(rb.getResultCode());

			int leftAmount = rb.getLeftAmount();
			// Debuger.debug("AbstractEratingConnector.getGold()" + leftAmount);
			return leftAmount;
		} finally {
			end();
		}
	}

}
