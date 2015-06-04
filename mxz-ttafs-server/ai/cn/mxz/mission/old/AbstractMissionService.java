//package cn.mxz.mission.old;
//
//
//import java.util.List;
//
//import message.S;
//import cn.javaplus.common.db.DAO;
//import cn.mxz.ConsumableTemplet;
//import cn.mxz.ConsumableTempletConfig;
//import cn.mxz.DemonGroupTemplet;
//import cn.mxz.DemonGroupTempletConfig;
//import cn.mxz.activity.PropBuilder;
//import cn.mxz.base.exception.OperationFaildException;
//import cn.mxz.base.service.AbstractService;
//import cn.mxz.battle.BattleExpPrize;
//import cn.mxz.battle.BattlePrizeAble;
//import cn.mxz.battle.Camp;
//import cn.mxz.battle.CampBuilder;
//import cn.mxz.battle.WarSituationBuilder;
//import cn.mxz.city.PlayerProperty;
//import cn.mxz.fighter.Fighter;
//import cn.mxz.fighter.HeroImpl;
//import cn.mxz.formation.PlayerCamp;
//import cn.mxz.mission.builder.BoxDataBuilder;
//import cn.mxz.mission.builder.MissionBuilder;
//import cn.mxz.mission.builder.RandomMessageBuilder;
//import cn.mxz.mission.map.MapParser;
//import cn.mxz.protocols.user.PropP.PropPro;
//import cn.mxz.protocols.user.battle.WarSituationP.CampPro;
//import cn.mxz.protocols.user.battle.WarSituationP.WarSituationPro;
//import cn.mxz.protocols.user.mission.BoxP.BoxPro;
//import cn.mxz.protocols.user.mission.MissionP.MissionPrizePro;
//import cn.mxz.protocols.user.mission.MissionP.MissionPrizePro.FighterPrizePro;
//import cn.mxz.protocols.user.mission.MissionP.MissionPro;
//import cn.mxz.protocols.user.mission.MoneyMessageP.MoneyMessagePro;
//import cn.mxz.protocols.user.mission.RandomMessageP.RandomMessagePro;
//import cn.mxz.user.mission.Captures;
//import cn.mxz.user.mission.Mission;
//import cn.mxz.user.mission.MissionMark;
//import cn.mxz.user.team.god.Hero;
//import cn.mxz.util.debuger.Debuger;
//import cn.mxz.yunyou.YunYouObjects;
//import cn.mxz.yunyou.YunYouPlayer;
//import db.dao.factory.DaoFactory;
//import db.domain.MissionData;
//import db.domain.MissionMapData;
//import db.domain.NewFighter;
//import define.D;
//
//public abstract class AbstractMissionService<MissionType extends MissionData, MissionMapType extends MissionMapData> extends AbstractService implements GiveUpAble {
//
//	public AbstractMissionService() {
//
//		super();
//	}
//
//	public WarSituationPro fighting(final int path, final int index) {
//
//		checkMoveTo(path, index);
//
//		final MapDemon d = getMap().getDemon(path, index);
//
//		PlayerCamp selected = getCity().getFormation().getSelected();
//
//		int mapId = getMap().getMark().getMapId();
//
//		final BattlePrizeAble b = buildBattle(d, selected, mapId);
//
//		b.fighting();
//
//		if (b.isWin()) {
//
//			getMap().remove(d);
//
//			Mission ms = getCurrentMission();
//
//			ms.save(b.getExpPrize(), b.getPropPrize(), new MissionStarCalc().getCount(b.getRound()));
//
//			move(path, index);
//
//			Captures captures = MissionFactory.getCaptures(getId());
//
//			captures.generate(d.getCamp());
//
//		}
//
//		recoverAttackerHp(d);
//
//		recoverDemonsHp(d);
//
//		removeDemonForcible(d);	//移除需要强制移除的怪物
//
//		commitMap();
//
//		return new WarSituationBuilder().build(b.getWarSituation());
//	}
//
//	protected abstract BattlePrizeAble buildBattle(final MapDemon d, PlayerCamp selected, int mapId);
//
//	/**
//	 * 移除需要强制移除的怪物
//	 *
//	 * @param d
//	 */
//	private final void removeDemonForcible(final MapDemon d) {
//
//		final int id = d.getGroupId();
//
//		final DemonGroupTemplet temp = DemonGroupTempletConfig.get(id);
//
//		if (temp != null) {
//
//			if (temp.getIsRemoveForcible() == 1) {
//
//				getMap().remove(d);
//			}
//		}
//
//	}
//
//	/**
//	 *
//	 * 如果被攻击的战士被攻击后, 会恢复我方的气血, 则恢复
//	 *
//	 * @param c
//	 */
//	private void recoverAttackerHp(final MapDemon d) {
//
//		final int id = d.getGroupId();
//
//		final DemonGroupTemplet temp = DemonGroupTempletConfig.get(id);
//
//		if (temp != null) {
//
//			final int add = temp.getHpAdd();
//
//			// 给当前出战的所有战士恢复气血
//			final Camp<Hero> selected = getCity().getFormation().getSelected();
//
//			for (final Hero hero : selected.getFighters()) {
//
//				hero.addHp(add);
//
//				NewFighter dto = ((HeroImpl) hero).getDto();
//
//				DaoFactory.getNewFighterDAO().update(dto);
//			}
//		}
//	}
//
//	public final MissionPro getMissionData() {
//		
//		return new MissionBuilder().build(getCurrentMission());
//	}
//
//	protected abstract Mission getCurrentMission();
//
//	protected abstract MissionMap getMap();
//
//	@Override
//	public final void giveUp() {
//		getCurrentMission().giveUp();
//	}
//	
//	protected void move(final int path, final int index) {
//
//		checkMoveTo(path, index);
//
//		getPlayer().reduce(PlayerProperty.PHYSICAL, D.MOVE_NEED); // 扣一点体力
//
//		final MissionMap map = getMap();
//
//		final MissionMark markBeforePersonMove = map.getMark();
//
//		map.getPerson().moveTo(path, index);
//
//		commitMap();
//
//		checkEnd(markBeforePersonMove);
//
//		//		Debuger.debug("MissionServiceImpl.moveTo() 当前可打地图:"
//		//				+ getCurrentMission().getMark());
//	}
//
//	/**
//	 * @param markBeforePersonMove
//	 *            人物移动之前的关卡信息
//	 */
//	private void checkEnd(final MissionMark markBeforePersonMove) {
//
//		MissionMap map = getMap();
//
//		if (map.isEnd()) { // 如果地图被Person走完了
//
//			getCurrentMission().next();
//
//			getCurrentMission().onEnd(markBeforePersonMove, map);
//
//			getCurrentMission().deleteCurrentMap();
//
//			Debuger.debug("MissionServiceImpl.checkEnd() 角色通关了! 当前可挑战最大关卡:"
//					+ getCurrentMission().getMark());
//		}
//	}
//
//	protected void commitMap() {
//
//		final DAO<String, MissionMapType> DAO = getMissionMapDAO();
//
//		final MapParser<MissionMapType> parser = new MapParser<MissionMapType>();
//
//		final MissionMapType map = parser.parse(getMap(), getPlayer().getId(), DAO.createDTO());
//
//		DAO.update(map);
//	}
//
//	protected abstract DAO<String, MissionMapType> getMissionMapDAO();
//
//	public final BoxPro openChest() {
//
//		final MapBox box = getMap().getBoxTouched();
//
//		final ConsumableTemplet c = ConsumableTempletConfig.get(box.getId());
//
//		getMap().remove(box);
//
//		if(c.getIsOpen() == 1) {	//如果直接开启
//
//			box.open(getPlayer());
//
//			return new BoxDataBuilder().buildBoxContent(getPlayer(), box);
//
//		} else {
//
//			getCity().getBag().addProp(box.getId(), 1);
//
//			return new BoxDataBuilder().buildEmptyBoxContent(getPlayer(), box.getId());
//		}
//	}
//
//	public final MissionPrizePro receiveMissionPrize() {
//
//		final MissionPrizePro.Builder b = MissionPrizePro.newBuilder();
//
//		Mission ms = getCurrentMission();
//
//		MissionPrize p = ms.pickPrize();
//
//		if(p != null) {
//
//			final List<BattleExpPrize> f = p.getFighterPrize();
//
//			final List<PropPrize> ps = p.getPropPrizes();
//
//			for (final BattleExpPrize fp : f) {
//
//				fp.award(getPlayer());
//
//				b.addPrizes(buildPrize(fp));
//			}
//
//			for (PropPrize pr : ps) {
//
//				pr.award(getPlayer());
//
//				b.addProps(build(pr));
//			}
//
//			b.setStar(p.getStar());
//
//			Debuger.debug("AbstractMissionService.receiveMissionPrize() 发送给前端的 星星数:" + p.getStar());
//
//		} else {
//
//			b.setStar(0);
//		}
//
//		return b.build();
//	}
//
//	private PropPro build(PropPrize pr) {
//
//		return new PropBuilder().build(pr.getTypeId(), pr.getCount());
//	}
//
//	public final RandomMessagePro encounterQuestion() {
//
//		final MapRandomEvent randomTouched = getMap().getRandomTouched();
//
//		final String responseEvent = randomTouched.responseEvent();
//
//		getMap().remove(randomTouched);
//
//		if (randomTouched.getId() == D.YUN_YOU_XIAN_REN_EVENT_ID) {
//			
//			YunYouPlayer player = YunYouObjects.getPlayer(getId());
//			player.onEvent();
//		}
//		
//		return new RandomMessageBuilder().build(responseEvent);
//	}
//
//	public final MoneyMessagePro openMoney() {
//
//		final MapMoneyImpl mapMoneyTouched = getMap().getMapMoneyTouched();
//
//		int open;
//
//		if (mapMoneyTouched == null) {
//
//			open = 0;
//
//		} else {
//
//			open = mapMoneyTouched.open();
//		}
//
//		getMap().remove(mapMoneyTouched);
//
//
//
//		final MoneyMessagePro.Builder b = MoneyMessagePro.newBuilder();
//
//		b.setMoney(open);
//
//		return b.build();
//	}
//
//	//	if (getMap().getMapMoneyTouched() == null) {	//如果没有碰到的钱袋, 就当作宝箱开启
//	//
//	//		MapBox box = getMap().getBoxTouched();
//	//
//	//		BoxPro b = openChest();
//	//
//	//		open = b.getPrizesList().get(0).getCount();
//	//
//	//		getMap().remove(box);
//	//
//	//	} else {
//	//
//	//		MapMoneyImpl mapMoneyTouched = getMap().getMapMoneyTouched();
//	//
//	//		open = mapMoneyTouched.open();
//	//
//	//		getMap().remove(mapMoneyTouched);
//	//	}
//
//	public final CampPro getDemonCamp(final int path, final int index) {
//
//		final MapDemon d = getMap().getDemon(path, index);
//
//		return new CampBuilder().build(d.getCamp());
//	}
//
//	private final void checkEnter(final int storyId) {
//
//		if (storyId > getCurrentMission().getMark().getMapId()) {
//
//			throw new OperationFaildException(S.S10046);
//		}
//	}
//
//	/**
//	 * 恢复小怪的血
//	 * @param d
//	 */
//	private final void recoverDemonsHp(final MapDemon d) {
//
//		final Camp<? extends Fighter> camp = d.getCamp();
//
//		for (final Fighter f : camp.getFighters()) {
//
//			f.addHp(Integer.MAX_VALUE / 3);
//		}
//	}
//
//
//	/**
//	 * 检查是不是后续最临近的大石头节点
//	 *
//	 * @param path
//	 * @param index
//	 */
//	private void checkNearest(final int path, final int index) {
//
//		final MapNode node = getMap().getPath(path).getNode(index);
//
//		final List<MapNode> nearest = node.getNearest();
//
//		final boolean isNearest = nearest.contains(getMap().getPersonNode());
//
//		if (!isNearest) {
//
//			throw new OperationFaildException(S.S10043, path, index);
//		}
//
//	}
//
//	/**
//	 * 移动前检查
//	 *
//	 * @param path
//	 * @param index
//	 */
//	private void checkMoveTo(final int path, final int index) {
//
//		checkExist(path, index);
//
//		checkNearest(path, index);
//	}
//
//	private void checkExist(final int path, final int index) {
//
//		MissionMap map = getMap();
//
//		if(map == null) {
//
//			throw new RuntimeException(" 没有 地图!");
//		}
//
//		final MapPath mp = map.getPath(path);
//
//		if (mp == null) {
//
//			throw new OperationFaildException(S.S10044);
//		}
//
//		final MapNode node = mp.getNode(index);
//
//		if (node == null) {
//
//			throw new OperationFaildException(S.S10045, path, index);
//		}
//	}
//
//	private FighterPrizePro buildPrize(final BattleExpPrize fp) {
//
//		final FighterPrizePro.Builder b = FighterPrizePro.newBuilder();
//
//		b.setExpAdd(fp.getExp());
//
//		b.setFighterId(fp.getFighterId());
//
//		b.setLevelAdd(fp.getLevelAdd());
//
//		b.setExpNeed(fp.getExpNeed());
//
//		return b.build();
//	}
//
//
//	public void enter(final int storyId) {
//
//		getCurrentMission().onBeforeEnter(storyId);
//		
//		checkEnter(storyId);
//
//		getCurrentMission().buildMap(storyId);
//	}
//
//}