package cn.mxz.city;

	private Player player;


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

		player = null;
	}

/*
	public void loadAll() {

		getPlayer();
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

	public void freePlayer() {

	public void reloadPlayer() {
}