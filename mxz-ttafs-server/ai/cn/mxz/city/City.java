package cn.mxz.city;import org.springframework.context.annotation.Scope;import org.springframework.stereotype.Component;import cn.javaplus.comunication.ProtocolUser;import cn.javaplus.serchengine.SearchAble;import cn.mxz.activity.heishi.HeishiManager;import cn.mxz.bag.Bag;import cn.mxz.bag.BagAuto;import cn.mxz.bag.BagAutoImpl;import cn.mxz.bag.BagFactory;import cn.mxz.bag.Grid;import cn.mxz.base.world.World;import cn.mxz.base.world.WorldFactory;import cn.mxz.chat.UnionChater;import cn.mxz.chat.UserChater;import cn.mxz.chat.WorldChater;import cn.mxz.chengzhang.ChengZhangPlayer;import cn.mxz.chuangzhen.ChuangZhenPlayer;import cn.mxz.chuangzhen.ChuangZhenPlayerImpl;import cn.mxz.coutinuous.ContinuousManager;import cn.mxz.coutinuous.ContinuousManagerImpl;import cn.mxz.czfk.FeedBackManager;import cn.mxz.czfk2.FeedBackManager2;import cn.mxz.dogz.DogzManager;import cn.mxz.dogz.DogzManagerFactory;import cn.mxz.enemy.EnemyManager;import cn.mxz.enemy.EnemyManagerImpl;import cn.mxz.equipment.EquipmentManager;import cn.mxz.equipment.EquipmentManagerImpl;import cn.mxz.equipment.snatch.SnatchManager;import cn.mxz.equpment.snatch.SnatchManagerImpl;import cn.mxz.events.EventDispatcher2Impl;import cn.mxz.fengshentai.FengshentaiManagerImpl;import cn.mxz.fengshentai.IFengshentaiManager;import cn.mxz.formation.FormationFactory;import cn.mxz.freeze.FreezeManager;import cn.mxz.friend.FriendManager;import cn.mxz.friend.FriendManagerFactory;import cn.mxz.heishi.HeiShi;import cn.mxz.hhdlb.HhdlbManager;import cn.mxz.init.SocketManager;import cn.mxz.invite.InvitationManager;import cn.mxz.invite.InvitationManagerImpl;import cn.mxz.invite.InviteManager;import cn.mxz.levelupreward.LevelUpRewardPlayerImpl;import cn.mxz.log.LogsManager;import cn.mxz.loginreward.LoginRewardPlayer;import cn.mxz.loginreward.LoginRewardPlayerImpl;import cn.mxz.mission.IMissionManager;import cn.mxz.mission.MissionManagerImpl;import cn.mxz.monthcard.MonthCard;import cn.mxz.newpvp.PvpManager;import cn.mxz.newpvp.PvpManagerImpl;import cn.mxz.notice.NoticeSender;import cn.mxz.nvwa.Nvwa;import cn.mxz.onlinereward.OnlineRewardManager;import cn.mxz.openserver.OpenServerRewardManager;import cn.mxz.prizecenter.PrizeCenterLinekong;import cn.mxz.prompt.PromptManager;import cn.mxz.prompt.PromptManagerImpl;import cn.mxz.pvp.PvpDuiHuanManager;import cn.mxz.qiyu.QiYuManager;import cn.mxz.qiyu.QiYuManagerImpl;import cn.mxz.shangxiang.ShangXiangPlayer;import cn.mxz.shangxiang.ShangXiangPlayerImpl;import cn.mxz.shenmo.UserShenmo;import cn.mxz.skill.SkillManager;import cn.mxz.skill.SkillManagerImpl;import cn.mxz.spirite.SpiriteManager;import cn.mxz.spirite.SpiriteManagerImpl;import cn.mxz.task.achieve.AchieveTaskManager;import cn.mxz.task.achieve.AchieveTaskManagerImpl;import cn.mxz.task.achieve.AchieveTaskPlayer;import cn.mxz.task.achieve.AchieveTaskPlayerImpl;import cn.mxz.task.achieve.LevelUpRewardPlayer;import cn.mxz.task.dailytask.DailyTaskManager;import cn.mxz.task.dailytask.DailyTaskManagerImpl;import cn.mxz.task.dailytask.DailyTaskPlayer;import cn.mxz.task.dailytask.DailyTaskPlayerImpl;import cn.mxz.team.TeamFactory;import cn.mxz.temp.TempCollection;import cn.mxz.tianming.TianMingManager;import cn.mxz.user.Guide;import cn.mxz.user.Player;import cn.mxz.user.ServiceManager;import cn.mxz.user.team.Formation;import cn.mxz.user.team.Team;import cn.mxz.util.cd.CDManager;import cn.mxz.util.cd.ColdDownFactory;import cn.mxz.util.checker.Checker;import cn.mxz.util.checker.PlayerChecker;import cn.mxz.util.counter.UserCounter;import cn.mxz.util.counter.UserCounterHistoryMongoImpl;import cn.mxz.util.counter.UserCounterMongoImpl;import cn.mxz.util.counter.UserCounterSetter;import cn.mxz.util.debuger.Debuger;import cn.mxz.vip.VipPlayer;import cn.mxz.vip.VipPlayerImpl;import cn.mxz.vipcard.VipCardPlayer;import cn.mxz.vipcard.VipCardPlayerImpl;import cn.mxz.yunyou.YunYouPlayer;import cn.mxz.yunyou.YunYouPlayerImpl;import cn.mxz.zan.ZanManager;import cn.mxz.zan.ZanManagerImpl;import com.lemon.commons.socket.ISocket;import com.lemon.commons.user.IUser;import db.dao.impl.DaoFactory;import db.domain.UserBase;/** * 玩家城池 * @author 林岑 * */@Component("city")@Scope("prototype")public class City extends EventDispatcher2Impl implements IUser, ProtocolUser, SearchAble {

	private Player player;	private Team team;	private FriendManager friendManager;	private DogzManager dogzManager;	private Formation formation;	private UserCounter userCounter;	private UserCounter userCounterHistory;	private UserCounterSetter userCounterAuto;	private CDManager CDManager;	private Checker checker;	private EquipmentManager equipmentManager;	private LogsManager logsManager;	private DailyTaskManager dailyTaskManager;	private AchieveTaskManager achieveTaskManager;	private EnemyManager enemyManager;	private PvpManager newPvpManager;	private IMissionManager mission;	private BagAuto bagAuto;	private Bag<Grid> bag;	private Bag<Grid> piecesBag;	private SkillManager skillManager;	private DailyTaskPlayer dailyTaskPlayer;	private VipPlayer vipPlayer;	private AchieveTaskPlayer achieveTaskPlayer;	private UserShenmo userShenmo;	private PromptManager promptManager;	private SpiriteManager spiriteManager;	private SnatchManager snatchManager;	private ServiceManager serviceManager;	private LoginRewardPlayer loginRewardPlayer;	private ContinuousManager continuousManager;	private QiYuManager qiyuManager;	private VipCardPlayer vipCardPlayer;	private InvitationManager invitationManager;	private ZanManager ZanManager;	private IMessageSender messageSender;	private IMessageSender noticeSender;	private ShangXiangPlayer shangXiangPlayer;	private ChuangZhenPlayer chuangZhenPlayer;	private IFengshentaiManager fengshentaiManager;	private AttributeRecorder attributeRecorder;	private FreezeManager freezeManager;	private IPrizeCenter prizeCenter;	private TempCollection tempCollection;	private YunYouPlayer yunYouPlayer;	private Guide guide;	private HeiShi heiShi;	private Zbsr zbsr;	private TianMingManager tianMingManager;	private UserPrizeSender prizeSender1;	private UserPrizeSender prizeSender2;	private OpenServerRewardManager openServerRewardManager;	private World world;	private FunctionOpenManager functionOpenManager;	private LevelUpRewardPlayer levelUpRewardPlayer;	private WorldChater worldChater;	private UnionChater unionChater;	private UserChater userChater;	private HeishiManager heishiManager;	private ChengZhangPlayer chengZhangPlayer;	private MonthCard MonthCard;	private PvpDuiHuanManager pvpDuiHuanManager;	private Nvwa Nvwa;	private FeedBackManager FeedBackManager;	private OnlineRewardManager OnlineRewardManager;	private InviteManager InviteManager;	private HhdlbManager HhdlbManager;	private FeedBackManager2 FeedBackManager2;	private GameIdManager GameIdManager;


	private UserBase	dto;



	public City(UserBase dto) {
		this.dto = dto;
	}



	/**
	 * 是否是测试玩家
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public boolean isTester() {
		if(!Debuger.isDevelop()) {
			return false;
		}
		String id = getId();
		return !(id.startsWith("hzmxz") && id.endsWith("hzmxz"));
	}


	public String getId() {
		return getPlayer().getId();
	}


	public long getLastLoginMillis() {
		return dto.getLastLoginTime() * 1000L;
	}


	public void setLastLoginSec(int sec) {
		dto.setLastLoginTime(sec);
		DaoFactory.getUserBaseDao().update(dto);
	}


	public ISocket getSocket() {

		final World world = WorldFactory.getWorld();

		final SocketManager socketManager = world.getSocketManager();

		return socketManager.getSocket(this);
	}


	public int hashCode() {
		return getId().hashCode();
	}


	public String toString() {
		return getId() + ":" + getPlayer().getNick();
	}

	public UserBase getDto() {
		return dto;
	}
	/**
	 * 判断两个对象的类型是否一致
	 *
	 * @param obj1
	 * @param obj2
	 * @param c
	 * @return
	 */
	private boolean isTypeEquals(Object obj1, Object obj2, Class<?> c) {

		if (obj1 == null || obj2 == null) {

			return false;
		}

		if (!obj1.getClass().equals(c) || !obj2.getClass().equals(c)) {

			return false;
		}

		return true;
	}


	public boolean equals(Object obj) {

		if (!isTypeEquals(obj, this, City.class)) {

			return false;
		}

		final City u2 = (City) obj;

		return u2.getId().equals(getId());
	}


	public int getLevel() {
		return getPlayer().getLevel();
	}


	public String getSearchedField() {
		return getPlayer().getNick();
	}


	public void freeMemory() {

		player = null;		team = null;		friendManager = null;		dogzManager = null;		formation = null;		userCounter = null;		userCounterHistory = null;		userCounterAuto = null;		CDManager = null;		checker = null;		equipmentManager = null;		logsManager = null;		dailyTaskManager = null;		achieveTaskManager = null;		enemyManager = null;		newPvpManager = null;		mission = null;		bagAuto = null;		bag = null;		piecesBag = null;		skillManager = null;		dailyTaskPlayer = null;		vipPlayer = null;		achieveTaskPlayer = null;		userShenmo = null;		promptManager = null;		spiriteManager = null;		snatchManager = null;		serviceManager = null;		loginRewardPlayer = null;		continuousManager = null;		qiyuManager = null;		vipCardPlayer = null;		invitationManager = null;		ZanManager = null;		messageSender = null;		noticeSender = null;		shangXiangPlayer = null;		chuangZhenPlayer = null;		fengshentaiManager = null;		attributeRecorder = null;		freezeManager = null;		prizeCenter = null;		tempCollection = null;		yunYouPlayer = null;		guide = null;		heiShi = null;		zbsr = null;		tianMingManager = null;		prizeSender1 = null;		prizeSender2 = null;		openServerRewardManager = null;		world = null;		functionOpenManager = null;		levelUpRewardPlayer = null;		worldChater = null;		unionChater = null;		userChater = null;		heishiManager = null;		chengZhangPlayer = null;		MonthCard = null;		pvpDuiHuanManager = null;		Nvwa = null;		FeedBackManager = null;		OnlineRewardManager = null;		InviteManager = null;		HhdlbManager = null;		FeedBackManager2 = null;		GameIdManager = null;
	}

/*
	public void loadAll() {

		getPlayer();		getTeam();		getFriendManager();		getDogzManager();		getFormation();		getUserCounter();		getUserCounterHistory();		getUserCounterAuto();		getCDManager();		getChecker();		getEquipmentManager();		getLogsManager();		getDailyTaskManager();		getAchieveTaskManager();		getEnemyManager();		getNewPvpManager();		getMission();		getBagAuto();		getBag();		getPiecesBag();		getSkillManager();		getDailyTaskPlayer();		getVipPlayer();		getAchieveTaskPlayer();		getUserShenmo();		getPromptManager();		getSpiriteManager();		getSnatchManager();		getServiceManager();		getLoginRewardPlayer();		getContinuousManager();		getQiyuManager();		getVipCardPlayer();		getInvitationManager();		getZanManager();		getMessageSender();		getNoticeSender();		getShangXiangPlayer();		getChuangZhenPlayer();		getFengshentaiManager();		getAttributeRecorder();		getFreezeManager();		getPrizeCenter();		getTempCollection();		getYunYouPlayer();		getGuide();		getHeiShi();		getZbsr();		getTianMingManager();		getPrizeSender1();		getPrizeSender2();		getOpenServerRewardManager();		getWorld();		getFunctionOpenManager();		getLevelUpRewardPlayer();		getWorldChater();		getUnionChater();		getUserChater();		getHeishiManager();		getChengZhangPlayer();		getMonthCard();		getPvpDuiHuanManager();		getNvwa();		getFeedBackManager();		getOnlineRewardManager();		getInviteManager();		getHhdlbManager();		getFeedBackManager2();		getGameIdManager();
	}
*/


	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public Player getPlayer() {
		if(player != null) {
			return player;
		}
		return player = new PlayerImpl(this, dto);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public Team getTeam() {
		if(team != null) {
			return team;
		}
		return team = TeamFactory.createTeam(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public FriendManager getFriendManager() {
		if(friendManager != null) {
			return friendManager;
		}
		return friendManager = FriendManagerFactory.createFriendManager(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public DogzManager getDogzManager() {
		if(dogzManager != null) {
			return dogzManager;
		}
		return dogzManager = DogzManagerFactory.createDogzManager(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public Formation getFormation() {
		if(formation != null) {
			return formation;
		}
		return formation = FormationFactory.getFormation(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public UserCounter getUserCounter() {
		if(userCounter != null) {
			return userCounter;
		}
		return userCounter = new UserCounterMongoImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public UserCounter getUserCounterHistory() {
		if(userCounterHistory != null) {
			return userCounterHistory;
		}
		return userCounterHistory = new UserCounterHistoryMongoImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public UserCounterSetter getUserCounterAuto() {
		if(userCounterAuto != null) {
			return userCounterAuto;
		}
		return userCounterAuto = new UserCounterAuto(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public CDManager getCDManager() {
		if(CDManager != null) {
			return CDManager;
		}
		return CDManager = ColdDownFactory.getCDManager(getId());
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public Checker getChecker() {
		if(checker != null) {
			return checker;
		}
		return checker = new PlayerChecker(getPlayer());
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public EquipmentManager getEquipmentManager() {
		if(equipmentManager != null) {
			return equipmentManager;
		}
		return equipmentManager = new EquipmentManagerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public LogsManager getLogsManager() {
		if(logsManager != null) {
			return logsManager;
		}
		return logsManager = new LogsManagerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public DailyTaskManager getDailyTaskManager() {
		if(dailyTaskManager != null) {
			return dailyTaskManager;
		}
		return dailyTaskManager = new DailyTaskManagerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public AchieveTaskManager getAchieveTaskManager() {
		if(achieveTaskManager != null) {
			return achieveTaskManager;
		}
		return achieveTaskManager = new AchieveTaskManagerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public EnemyManager getEnemyManager() {
		if(enemyManager != null) {
			return enemyManager;
		}
		return enemyManager = new EnemyManagerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public PvpManager getNewPvpManager() {
		if(newPvpManager != null) {
			return newPvpManager;
		}
		return newPvpManager = new PvpManagerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public IMissionManager getMission() {
		if(mission != null) {
			return mission;
		}
		return mission = new MissionManagerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public BagAuto getBagAuto() {
		if(bagAuto != null) {
			return bagAuto;
		}
		return bagAuto = new BagAutoImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public Bag<Grid> getBag() {
		if(bag != null) {
			return bag;
		}
		return bag = BagFactory.createBag(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public Bag<Grid> getPiecesBag() {
		if(piecesBag != null) {
			return piecesBag;
		}
		return piecesBag = BagFactory.createPiecesBag(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public SkillManager getSkillManager() {
		if(skillManager != null) {
			return skillManager;
		}
		return skillManager = new SkillManagerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public DailyTaskPlayer getDailyTaskPlayer() {
		if(dailyTaskPlayer != null) {
			return dailyTaskPlayer;
		}
		return dailyTaskPlayer = new DailyTaskPlayerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public VipPlayer getVipPlayer() {
		if(vipPlayer != null) {
			return vipPlayer;
		}
		return vipPlayer = new VipPlayerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public AchieveTaskPlayer getAchieveTaskPlayer() {
		if(achieveTaskPlayer != null) {
			return achieveTaskPlayer;
		}
		return achieveTaskPlayer = new AchieveTaskPlayerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public UserShenmo getUserShenmo() {
		if(userShenmo != null) {
			return userShenmo;
		}
		return userShenmo = new UserShenmo(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public PromptManager getPromptManager() {
		if(promptManager != null) {
			return promptManager;
		}
		return promptManager = new PromptManagerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public SpiriteManager getSpiriteManager() {
		if(spiriteManager != null) {
			return spiriteManager;
		}
		return spiriteManager = new SpiriteManagerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public SnatchManager getSnatchManager() {
		if(snatchManager != null) {
			return snatchManager;
		}
		return snatchManager = new SnatchManagerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public ServiceManager getServiceManager() {
		if(serviceManager != null) {
			return serviceManager;
		}
		return serviceManager = new ServiceManagerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public LoginRewardPlayer getLoginRewardPlayer() {
		if(loginRewardPlayer != null) {
			return loginRewardPlayer;
		}
		return loginRewardPlayer = new LoginRewardPlayerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public ContinuousManager getContinuousManager() {
		if(continuousManager != null) {
			return continuousManager;
		}
		return continuousManager = new ContinuousManagerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public QiYuManager getQiyuManager() {
		if(qiyuManager != null) {
			return qiyuManager;
		}
		return qiyuManager = new QiYuManagerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public VipCardPlayer getVipCardPlayer() {
		if(vipCardPlayer != null) {
			return vipCardPlayer;
		}
		return vipCardPlayer = new VipCardPlayerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public InvitationManager getInvitationManager() {
		if(invitationManager != null) {
			return invitationManager;
		}
		return invitationManager = new InvitationManagerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public ZanManager getZanManager() {
		if(ZanManager != null) {
			return ZanManager;
		}
		return ZanManager = new ZanManagerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public IMessageSender getMessageSender() {
		if(messageSender != null) {
			return messageSender;
		}
		return messageSender = new MessageSenderImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public IMessageSender getNoticeSender() {
		if(noticeSender != null) {
			return noticeSender;
		}
		return noticeSender = new NoticeSender(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public ShangXiangPlayer getShangXiangPlayer() {
		if(shangXiangPlayer != null) {
			return shangXiangPlayer;
		}
		return shangXiangPlayer = new ShangXiangPlayerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public ChuangZhenPlayer getChuangZhenPlayer() {
		if(chuangZhenPlayer != null) {
			return chuangZhenPlayer;
		}
		return chuangZhenPlayer = new ChuangZhenPlayerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public IFengshentaiManager getFengshentaiManager() {
		if(fengshentaiManager != null) {
			return fengshentaiManager;
		}
		return fengshentaiManager = new FengshentaiManagerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public AttributeRecorder getAttributeRecorder() {
		if(attributeRecorder != null) {
			return attributeRecorder;
		}
		return attributeRecorder = new AttributeRecorder(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public FreezeManager getFreezeManager() {
		if(freezeManager != null) {
			return freezeManager;
		}
		return freezeManager = new FreezeManager(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public IPrizeCenter getPrizeCenter() {
		if(prizeCenter != null) {
			return prizeCenter;
		}
		return prizeCenter = new PrizeCenterLinekong(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public TempCollection getTempCollection() {
		if(tempCollection != null) {
			return tempCollection;
		}
		return tempCollection = new TempCollection(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public YunYouPlayer getYunYouPlayer() {
		if(yunYouPlayer != null) {
			return yunYouPlayer;
		}
		return yunYouPlayer = new YunYouPlayerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public Guide getGuide() {
		if(guide != null) {
			return guide;
		}
		return guide = new Guide(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public HeiShi getHeiShi() {
		if(heiShi != null) {
			return heiShi;
		}
		return heiShi = new HeiShi(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public Zbsr getZbsr() {
		if(zbsr != null) {
			return zbsr;
		}
		return zbsr = new Zbsr(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public TianMingManager getTianMingManager() {
		if(tianMingManager != null) {
			return tianMingManager;
		}
		return tianMingManager = new TianMingManager(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public UserPrizeSender getPrizeSender1() {
		if(prizeSender1 != null) {
			return prizeSender1;
		}
		return prizeSender1 = new PrizeSender1(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public UserPrizeSender getPrizeSender2() {
		if(prizeSender2 != null) {
			return prizeSender2;
		}
		return prizeSender2 = new PrizeSender2(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public OpenServerRewardManager getOpenServerRewardManager() {
		if(openServerRewardManager != null) {
			return openServerRewardManager;
		}
		return openServerRewardManager = new OpenServerRewardManager(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public World getWorld() {
		if(world != null) {
			return world;
		}
		return world = WorldFactory.getWorld();
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public FunctionOpenManager getFunctionOpenManager() {
		if(functionOpenManager != null) {
			return functionOpenManager;
		}
		return functionOpenManager = new FunctionOpenManager(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public LevelUpRewardPlayer getLevelUpRewardPlayer() {
		if(levelUpRewardPlayer != null) {
			return levelUpRewardPlayer;
		}
		return levelUpRewardPlayer = new LevelUpRewardPlayerImpl(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public WorldChater getWorldChater() {
		if(worldChater != null) {
			return worldChater;
		}
		return worldChater = new WorldChater(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public UnionChater getUnionChater() {
		if(unionChater != null) {
			return unionChater;
		}
		return unionChater = new UnionChater(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public UserChater getUserChater() {
		if(userChater != null) {
			return userChater;
		}
		return userChater = new UserChater(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public HeishiManager getHeishiManager() {
		if(heishiManager != null) {
			return heishiManager;
		}
		return heishiManager = new HeishiManager(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public ChengZhangPlayer getChengZhangPlayer() {
		if(chengZhangPlayer != null) {
			return chengZhangPlayer;
		}
		return chengZhangPlayer = new ChengZhangPlayer(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public MonthCard getMonthCard() {
		if(MonthCard != null) {
			return MonthCard;
		}
		return MonthCard = new MonthCard(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public PvpDuiHuanManager getPvpDuiHuanManager() {
		if(pvpDuiHuanManager != null) {
			return pvpDuiHuanManager;
		}
		return pvpDuiHuanManager = new PvpDuiHuanManager(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public Nvwa getNvwa() {
		if(Nvwa != null) {
			return Nvwa;
		}
		return Nvwa = new Nvwa(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public FeedBackManager getFeedBackManager() {
		if(FeedBackManager != null) {
			return FeedBackManager;
		}
		return FeedBackManager = new FeedBackManager(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public OnlineRewardManager getOnlineRewardManager() {
		if(OnlineRewardManager != null) {
			return OnlineRewardManager;
		}
		return OnlineRewardManager = new OnlineRewardManager(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public InviteManager getInviteManager() {
		if(InviteManager != null) {
			return InviteManager;
		}
		return InviteManager = new InviteManager(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public HhdlbManager getHhdlbManager() {
		if(HhdlbManager != null) {
			return HhdlbManager;
		}
		return HhdlbManager = new HhdlbManager(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public FeedBackManager2 getFeedBackManager2() {
		if(FeedBackManager2 != null) {
			return FeedBackManager2;
		}
		return FeedBackManager2 = new FeedBackManager2(this);
	}
	//该类是通过  Build.java 生成的, 千万不要手动改, 到 modules文件 里面加入你的模块就可以了
	public GameIdManager getGameIdManager() {
		if(GameIdManager != null) {
			return GameIdManager;
		}
		return GameIdManager = new GameIdManager(this);
	}

	public void freePlayer() {		this.player = null;	}	public void freeTeam() {		this.team = null;	}	public void freeFriendManager() {		this.friendManager = null;	}	public void freeDogzManager() {		this.dogzManager = null;	}	public void freeFormation() {		this.formation = null;	}	public void freeUserCounter() {		this.userCounter = null;	}	public void freeUserCounterHistory() {		this.userCounterHistory = null;	}	public void freeUserCounterAuto() {		this.userCounterAuto = null;	}	public void freeCDManager() {		this.CDManager = null;	}	public void freeChecker() {		this.checker = null;	}	public void freeEquipmentManager() {		this.equipmentManager = null;	}	public void freeLogsManager() {		this.logsManager = null;	}	public void freeDailyTaskManager() {		this.dailyTaskManager = null;	}	public void freeAchieveTaskManager() {		this.achieveTaskManager = null;	}	public void freeEnemyManager() {		this.enemyManager = null;	}	public void freeNewPvpManager() {		this.newPvpManager = null;	}	public void freeMission() {		this.mission = null;	}	public void freeBagAuto() {		this.bagAuto = null;	}	public void freeBag() {		this.bag = null;	}	public void freePiecesBag() {		this.piecesBag = null;	}	public void freeSkillManager() {		this.skillManager = null;	}	public void freeDailyTaskPlayer() {		this.dailyTaskPlayer = null;	}	public void freeVipPlayer() {		this.vipPlayer = null;	}	public void freeAchieveTaskPlayer() {		this.achieveTaskPlayer = null;	}	public void freeUserShenmo() {		this.userShenmo = null;	}	public void freePromptManager() {		this.promptManager = null;	}	public void freeSpiriteManager() {		this.spiriteManager = null;	}	public void freeSnatchManager() {		this.snatchManager = null;	}	public void freeServiceManager() {		this.serviceManager = null;	}	public void freeLoginRewardPlayer() {		this.loginRewardPlayer = null;	}	public void freeContinuousManager() {		this.continuousManager = null;	}	public void freeQiyuManager() {		this.qiyuManager = null;	}	public void freeVipCardPlayer() {		this.vipCardPlayer = null;	}	public void freeInvitationManager() {		this.invitationManager = null;	}	public void freeZanManager() {		this.ZanManager = null;	}	public void freeMessageSender() {		this.messageSender = null;	}	public void freeNoticeSender() {		this.noticeSender = null;	}	public void freeShangXiangPlayer() {		this.shangXiangPlayer = null;	}	public void freeChuangZhenPlayer() {		this.chuangZhenPlayer = null;	}	public void freeFengshentaiManager() {		this.fengshentaiManager = null;	}	public void freeAttributeRecorder() {		this.attributeRecorder = null;	}	public void freeFreezeManager() {		this.freezeManager = null;	}	public void freePrizeCenter() {		this.prizeCenter = null;	}	public void freeTempCollection() {		this.tempCollection = null;	}	public void freeYunYouPlayer() {		this.yunYouPlayer = null;	}	public void freeGuide() {		this.guide = null;	}	public void freeHeiShi() {		this.heiShi = null;	}	public void freeZbsr() {		this.zbsr = null;	}	public void freeTianMingManager() {		this.tianMingManager = null;	}	public void freePrizeSender1() {		this.prizeSender1 = null;	}	public void freePrizeSender2() {		this.prizeSender2 = null;	}	public void freeOpenServerRewardManager() {		this.openServerRewardManager = null;	}	public void freeWorld() {		this.world = null;	}	public void freeFunctionOpenManager() {		this.functionOpenManager = null;	}	public void freeLevelUpRewardPlayer() {		this.levelUpRewardPlayer = null;	}	public void freeWorldChater() {		this.worldChater = null;	}	public void freeUnionChater() {		this.unionChater = null;	}	public void freeUserChater() {		this.userChater = null;	}	public void freeHeishiManager() {		this.heishiManager = null;	}	public void freeChengZhangPlayer() {		this.chengZhangPlayer = null;	}	public void freeMonthCard() {		this.MonthCard = null;	}	public void freePvpDuiHuanManager() {		this.pvpDuiHuanManager = null;	}	public void freeNvwa() {		this.Nvwa = null;	}	public void freeFeedBackManager() {		this.FeedBackManager = null;	}	public void freeOnlineRewardManager() {		this.OnlineRewardManager = null;	}	public void freeInviteManager() {		this.InviteManager = null;	}	public void freeHhdlbManager() {		this.HhdlbManager = null;	}	public void freeFeedBackManager2() {		this.FeedBackManager2 = null;	}	public void freeGameIdManager() {		this.GameIdManager = null;	}

	public void reloadPlayer() {		freePlayer();		getPlayer();	}	public void reloadTeam() {		freeTeam();		getTeam();	}	public void reloadFriendManager() {		freeFriendManager();		getFriendManager();	}	public void reloadDogzManager() {		freeDogzManager();		getDogzManager();	}	public void reloadFormation() {		freeFormation();		getFormation();	}	public void reloadUserCounter() {		freeUserCounter();		getUserCounter();	}	public void reloadUserCounterHistory() {		freeUserCounterHistory();		getUserCounterHistory();	}	public void reloadUserCounterAuto() {		freeUserCounterAuto();		getUserCounterAuto();	}	public void reloadCDManager() {		freeCDManager();		getCDManager();	}	public void reloadChecker() {		freeChecker();		getChecker();	}	public void reloadEquipmentManager() {		freeEquipmentManager();		getEquipmentManager();	}	public void reloadLogsManager() {		freeLogsManager();		getLogsManager();	}	public void reloadDailyTaskManager() {		freeDailyTaskManager();		getDailyTaskManager();	}	public void reloadAchieveTaskManager() {		freeAchieveTaskManager();		getAchieveTaskManager();	}	public void reloadEnemyManager() {		freeEnemyManager();		getEnemyManager();	}	public void reloadNewPvpManager() {		freeNewPvpManager();		getNewPvpManager();	}	public void reloadMission() {		freeMission();		getMission();	}	public void reloadBagAuto() {		freeBagAuto();		getBagAuto();	}	public void reloadBag() {		freeBag();		getBag();	}	public void reloadPiecesBag() {		freePiecesBag();		getPiecesBag();	}	public void reloadSkillManager() {		freeSkillManager();		getSkillManager();	}	public void reloadDailyTaskPlayer() {		freeDailyTaskPlayer();		getDailyTaskPlayer();	}	public void reloadVipPlayer() {		freeVipPlayer();		getVipPlayer();	}	public void reloadAchieveTaskPlayer() {		freeAchieveTaskPlayer();		getAchieveTaskPlayer();	}	public void reloadUserShenmo() {		freeUserShenmo();		getUserShenmo();	}	public void reloadPromptManager() {		freePromptManager();		getPromptManager();	}	public void reloadSpiriteManager() {		freeSpiriteManager();		getSpiriteManager();	}	public void reloadSnatchManager() {		freeSnatchManager();		getSnatchManager();	}	public void reloadServiceManager() {		freeServiceManager();		getServiceManager();	}	public void reloadLoginRewardPlayer() {		freeLoginRewardPlayer();		getLoginRewardPlayer();	}	public void reloadContinuousManager() {		freeContinuousManager();		getContinuousManager();	}	public void reloadQiyuManager() {		freeQiyuManager();		getQiyuManager();	}	public void reloadVipCardPlayer() {		freeVipCardPlayer();		getVipCardPlayer();	}	public void reloadInvitationManager() {		freeInvitationManager();		getInvitationManager();	}	public void reloadZanManager() {		freeZanManager();		getZanManager();	}	public void reloadMessageSender() {		freeMessageSender();		getMessageSender();	}	public void reloadNoticeSender() {		freeNoticeSender();		getNoticeSender();	}	public void reloadShangXiangPlayer() {		freeShangXiangPlayer();		getShangXiangPlayer();	}	public void reloadChuangZhenPlayer() {		freeChuangZhenPlayer();		getChuangZhenPlayer();	}	public void reloadFengshentaiManager() {		freeFengshentaiManager();		getFengshentaiManager();	}	public void reloadAttributeRecorder() {		freeAttributeRecorder();		getAttributeRecorder();	}	public void reloadFreezeManager() {		freeFreezeManager();		getFreezeManager();	}	public void reloadPrizeCenter() {		freePrizeCenter();		getPrizeCenter();	}	public void reloadTempCollection() {		freeTempCollection();		getTempCollection();	}	public void reloadYunYouPlayer() {		freeYunYouPlayer();		getYunYouPlayer();	}	public void reloadGuide() {		freeGuide();		getGuide();	}	public void reloadHeiShi() {		freeHeiShi();		getHeiShi();	}	public void reloadZbsr() {		freeZbsr();		getZbsr();	}	public void reloadTianMingManager() {		freeTianMingManager();		getTianMingManager();	}	public void reloadPrizeSender1() {		freePrizeSender1();		getPrizeSender1();	}	public void reloadPrizeSender2() {		freePrizeSender2();		getPrizeSender2();	}	public void reloadOpenServerRewardManager() {		freeOpenServerRewardManager();		getOpenServerRewardManager();	}	public void reloadWorld() {		freeWorld();		getWorld();	}	public void reloadFunctionOpenManager() {		freeFunctionOpenManager();		getFunctionOpenManager();	}	public void reloadLevelUpRewardPlayer() {		freeLevelUpRewardPlayer();		getLevelUpRewardPlayer();	}	public void reloadWorldChater() {		freeWorldChater();		getWorldChater();	}	public void reloadUnionChater() {		freeUnionChater();		getUnionChater();	}	public void reloadUserChater() {		freeUserChater();		getUserChater();	}	public void reloadHeishiManager() {		freeHeishiManager();		getHeishiManager();	}	public void reloadChengZhangPlayer() {		freeChengZhangPlayer();		getChengZhangPlayer();	}	public void reloadMonthCard() {		freeMonthCard();		getMonthCard();	}	public void reloadPvpDuiHuanManager() {		freePvpDuiHuanManager();		getPvpDuiHuanManager();	}	public void reloadNvwa() {		freeNvwa();		getNvwa();	}	public void reloadFeedBackManager() {		freeFeedBackManager();		getFeedBackManager();	}	public void reloadOnlineRewardManager() {		freeOnlineRewardManager();		getOnlineRewardManager();	}	public void reloadInviteManager() {		freeInviteManager();		getInviteManager();	}	public void reloadHhdlbManager() {		freeHhdlbManager();		getHhdlbManager();	}	public void reloadFeedBackManager2() {		freeFeedBackManager2();		getFeedBackManager2();	}	public void reloadGameIdManager() {		freeGameIdManager();		getGameIdManager();	}
}
