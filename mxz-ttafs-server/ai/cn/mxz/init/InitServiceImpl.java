package cn.mxz.init;

import java.util.Collection;

import message.S;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;
import cn.mxz.FighterTempletConfig;
import cn.mxz.IFighterTemplet;
import cn.mxz.base.config.ServerConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.base.service.AbstractService;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.events.Events;
import cn.mxz.events.OldMxzUserAccessEvent;
import cn.mxz.events.OldUserAccessEvent;
import cn.mxz.events.UserCreateEvent;
import cn.mxz.events.init.EnterGameEvent;
import cn.mxz.fighter.HeroProperty;
import cn.mxz.freeze.FreezeManager;
import cn.mxz.handler.InitService;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.protocols.user.UserP.AccessResultPro;
import cn.mxz.thirdpaty.ThirdPartyException;
import cn.mxz.thirdpaty.ThirdPartyPlatform;
import cn.mxz.thirdpaty.ThirdPartyPlatformFactory;
import cn.mxz.user.init.ReadyUser;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.checker.CheckerNickUsed;
import cn.mxz.util.checker.CheckerSencitive;
import cn.mxz.util.debuger.Debuger;

import com.lemon.commons.database.ServerProperties;
import com.lemon.commons.socket.ISocket;

import db.dao.impl.DaoFactory;
import db.domain.NewFighter;
import db.domain.NewFighterImpl;
import define.D;

@Component("initService")
@Scope("prototype")
public class InitServiceImpl extends AbstractService implements InitService {

	private class FighterBean {

		private int id;

		private FighterBean(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
	}

	private SocketManager socketManager;

	@Override
	public void init(ISocket socket) {

		super.init(socket);

		socketManager = getWorld().getSocketManager();
	}

	@Override
	public AccessResultPro access(String userName, String token,
			int adultState, String mac, int clientType, int UnixTime,
			String otherValue) {
		String adId = "";
		String gameId = null;

		try {
			String[] ss = otherValue.split(",");
			adId = ss[0];
			gameId = ss[1];
			Debuger.debug("InitServiceImpl.access()" + gameId  + "," + otherValue);
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		userName = userName.toLowerCase();

		checkOnlineMax();

		String userId = userName;

		ThirdPartyPlatform e = ThirdPartyPlatformFactory
				.getThirdPartyPlatform();
		AccessResultBuilder b = new AccessResultBuilder();
		if (e.isPlatformUserId(userId)) {
			try {
				userId = e.checkToken(userId, token, mac, clientType, UnixTime,
						adId, getGameId(gameId));
			} catch (ThirdPartyException e1) {
				e1.printStackTrace();
				return b.build(false, e1.getErrorCode());
			}
		}

		userId = userId.toLowerCase();

		// uname == 角色ID
		String roleId;

		if (e.isPlatformUserId(userId)) {

			roleId = e.getRoleId(userId, getGameId(gameId));

			// 2014年5月27日 13:52:18 林岑加, 为了解决
			// ensureHasRoleInGameServer(roleId, userName); //2014年5月27日
			// 14:28:00屏蔽掉, 在getRoleId方法里面处理了

		} else {
			roleId = userId;
		}

		boolean isOldUser = CityFactory.getCity(roleId) != null;

		// 老角色登陆, 如果已经创建过帐号, 就直接让用户对象和socket绑定起来
		if (isOldUser) { // 老帐号

			Debuger.debug("InitServiceImpl.access() 老帐号接入");

			World world = WorldFactory.getWorld();

			City city = world.get(roleId);

			checkFreeze(city);

			if (isInOtherPlace(city)) { // 如果在另一地登陆

				kick(city); // 踢下线
			}

			socketManager.bind(getSocket(), city);

			Events.getInstance().dispatch(new OldUserAccessEvent(city));

			// city.loadAll();

			city.getPlayer().saveThirdPartyId(userId);
			city.getPlayer().saveClientType(clientType);

			if (e.isPlatformUserId(userId)) {
				e.logIn(ThirdPartyPlatformFactory.createRole(city),
						getGameId(gameId));
			} else {
				Events.getInstance().dispatch(new OldMxzUserAccessEvent(city));
			}

			Events.getInstance().dispatch(new EnterGameEvent(city, gameId));
			// 新角色登陆, 准备用户创建所需的信息
		} else {
			Debuger.debug("InitServiceImpl.access() 新账号接入:" + userId);
			readyUser(userId, getSocket(), clientType, userName, gameId);
		}

		return b.build(!isOldUser, 0);
	}

	private long getGameId(String gameId) {
		try {
			if (gameId == null) {
				return com.linekong.platform.protocol.erating.define.D.GAME_ID;
			}
			if (gameId.trim().isEmpty()) {
				return com.linekong.platform.protocol.erating.define.D.GAME_ID;
			}
			return new Long(gameId);
		} catch (Exception e) {
			e.printStackTrace();
			return com.linekong.platform.protocol.erating.define.D.GAME_ID;
		}
	}

	// //确保蓝港有的帐号一定有用户信息
	// private void ensureHasRoleInGameServer(String roleId, String userName) {
	// if(roleId != null && CityFactory.getCity(roleId) == null ) {
	// ReadyUserImpl ready = new ReadyUserImpl();
	// ready.setAccounts(userName);
	// ready.setClientType(2);
	// List<FighterTemplet> fs = FighterTempletConfig.findByCategory(3);
	// FighterTemplet ff = Util.Collection.getRandomOne(fs);
	// ready.setFighterTypeId(ff.getId());
	// World w = WorldFactory.getWorld();
	// NickManager nm = w.getNickManager();
	//
	// ready.setNick(nm.getRandomNick());
	// ready.setRoleId(roleId);
	//
	// CityFactory.createNewCity(ready);
	// }
	// }

	private void checkFreeze(City city) {
		FreezeManager m = city.getFreezeManager();
		if (m.isFreeze()) {
			throw new SureIllegalOperationException(m.getReason());//
		}
	}

	private void checkOnlineMax() {
		Collection<City> all = WorldFactory.getWorld().getOnlineAll();
		ServerProperties c = ServerConfig.getConfig();
		int max = c.getPlayerCountMax();

		int size = all.size();

		if (size >= max) {
			throw new OperationFaildException(S.S10227);
		}
	}

	/**
	 * 是否在另一地登陆了?
	 * 
	 * @param user
	 * @return
	 */
	private boolean isInOtherPlace(City city) {
		return socketManager.getSocket(city) != null;
	}

	/**
	 * 踢掉在另一地登陆的玩家
	 */
	private void kick(City city) {

		final ISocket old = socketManager.getSocket(city);

		MessageFactory.getSystem().kick(old);

		socketManager.unbind(old);

		Closer.closeDelay(old);

		Debuger.debug("Kick", city.getId() + "|" + old.getId());
	}

	/**
	 * 准备新用户数据
	 * 
	 * @param userId
	 *            蓝港ID
	 * @param clientType
	 *            客户端类型
	 * @param userName
	 * @param gameId
	 */
	private void readyUser(String userId, ISocket s, int clientType,
			String userName, String gameId) {

		ReadyUser u = new ReadyUserImpl();

		u.setClientType(clientType);
		u.setAccounts(userId);
		u.setUserName(userName);
		u.setGameId(gameId);

		socketManager.ready(s, u);
	}

	@Override
	public int setNick(String nick) {

		int code = checkSetNick(nick);

		boolean passCheck = code == 0; // 是否通过了检查

		if (passCheck) {

			ReadyUser r = socketManager.getReady(getSocket());

			r.setNick(nick);
		}

		return code;
	}

	public int checkSetNick(String nick) {

		ReadyUser r = socketManager.getReady(getSocket());

		if (r == null) {

			new SureIllegalOperationException("用户创建信息不存在!" + getSocket())
					.printStackTrace();
			return S.S10042;
		}

		try {

			new CheckerNickUsed().check(nick); // 昵称是否已经用过了

			new CheckerSencitive().check(nick); // 敏感词检查

		} catch (OperationFaildException e) {

			return e.getCode();
		}

		return 0;
	}

	@Override
	public Boolean create(String invitationCode) {

		checkCreate();

		ReadyUser ready = socketManager.getReady(getSocket());

		new CheckerNickUsed().check(ready.getNick());

		String roleId;

		ThirdPartyPlatform e = ThirdPartyPlatformFactory
				.getThirdPartyPlatform();
		if (e.isPlatformUserId(ready.getAccounts())) {
			int fid = ready.getFighterTypeId();
			boolean isMan = FighterTempletConfig.get(fid).getSex() == 1;
			int initialLevel = D.INIT_LEVEL;
			roleId = e.createUser(ready.getAccounts(), isMan, initialLevel,
					getSocket().getIP(), ready.getNick(),
					getGameId(ready.getGameId()));
			Debuger.debug("InitServiceImpl.create() 创建角色 roleId = " + roleId);
		} else {
			roleId = ready.getAccounts();
		}

		// 角色ID

		try {
			ready.setRoleId(roleId);

			City city = CityFactory.createNewCity(ready);

			createFighters(ready);

			Collection<Hero> all = city.getTeam().getAll();
			for (Hero h : all) {
				city.getSkillManager().ensureTianFu(h);
			}

			// city.loadAll();

			socketManager.removeReadyUser(getSocket()); // 移除用户信息

			socketManager.bind(getSocket(), city);

			UserCreateEvent uc = new UserCreateEvent(city, invitationCode,
					ready.getAccounts(), ready.getUserName());
			Events.getInstance().dispatch(uc);

			city.getPlayer().saveThirdPartyId(ready.getAccounts());
			city.getPlayer().saveClientType(ready.getClientType());

			if (e.isPlatformUserId(ready.getAccounts())) {
				e.logIn(ThirdPartyPlatformFactory.createRole(city),
						getGameId(ready.getGameId()));
			}
			// try {
			// Logs.loginLog.addLoginLog(city);
			// } catch (Exception e1) {
			// e1.printStackTrace();
			// }
			Events.getInstance().dispatch(
					new EnterGameEvent(city, ready.getGameId()));

		} catch (Exception e1) {

			// 删除角色

			if (e.isPlatformUserId(ready.getAccounts())) {
				e.deleteRole(ready.getAccounts(), roleId);
			}

			e1.printStackTrace();

			throw Util.Exception.toRuntimeException(e1);
		}

		return true;
	}

	/**
	 * 初始化玩家的神将
	 * 
	 * @param ready
	 */
	private void createFighters(ReadyUser ready) {

		String uname = ready.getRoleId();

		FighterBean fb = new FighterBean(ready.getFighterTypeId());

		checkFighterExist(fb.getId());

		NewFighter f = new NewFighterImpl();

		f.setUname(uname);

		f.setTypeId(fb.getId());

		f.setLevel(1);

		f.setV(HeroProperty.SKILL_LEVEL.getValue(), 1);

		f.setV(HeroProperty.HP.getValue(), Integer.MAX_VALUE / 3);

		DaoFactory.getNewFighterDao().save(f);
	}

	private void checkFighterExist(int type) {

		if (FighterTempletConfig.get(type) == null) {

			throw new OperationFaildException(S.S10036, type);
		}
	}

	private void checkCreate() {

		ISocket s = getSocket();
		ReadyUser r = socketManager.getReady(s);

		if (r == null) {

			new OperationFaildException(S.S10042).printStackTrace();
			throw new OperationFaildException(S.S10042);
		}

		if (r.getNick() == null) {

			String accounts = r.getAccounts();

			throw new OperationFaildException(S.S10041, s, accounts);
		}
	}

	@Override
	public int setUserType(int fighterTypeId) {

		ReadyUser r = socketManager.getReady(getSocket());

		if (r == null) {

			return -1;
		}

		int code = checkSetUserType(fighterTypeId);

		boolean passCheck = code == 0; // 是否通过了检查

		if (passCheck) {

			r.setFighterTypeId(fighterTypeId);
		}

		return code;
	}

	private int checkSetUserType(int fighterTypeId) {

		IFighterTemplet ft = FighterTempletConfig.get(fighterTypeId);

		if (ft == null) {

			new OperationFaildException(S.S10040).printStackTrace();
			return S.S10040;
		}

		if (ft.getCategory() != 3) {

			new OperationFaildException(S.S10010).printStackTrace();
			return S.S10010;
		}

		return 0;
	}

	@Override
	public int createUser(int fighterTypeId, String invitationCode) {
		setUserType(fighterTypeId);
		create(invitationCode);
		return -1;
	}

	@Override
	public void resetUser() {
		City city = getCity();
		int level = city.getLevel();
		if (level > 10) {
			return;
		}
		ThirdPartyPlatform c = ThirdPartyPlatformFactory
				.getThirdPartyPlatform();
		String roleId = city.getId();
		String userId = city.getPlayer().getThirdPartyId();
		c.deleteRole(userId, roleId);
	}

	@Override
	public String getRandomNick() {

		ReadyUser r = socketManager.getReady(getSocket());
		ThirdPartyPlatform e = ThirdPartyPlatformFactory
				.getThirdPartyPlatform();
		if (e.isPlatformUserId(r.getAccounts())) {

			String nick = e.generateRoleName(r.isMan());
			setNick(nick);
			return nick;
		}

		World world = WorldFactory.getWorld();

		String nick = world.getNickManager().getRandomNick();

		setNick(nick);

		return nick;

	}

}
