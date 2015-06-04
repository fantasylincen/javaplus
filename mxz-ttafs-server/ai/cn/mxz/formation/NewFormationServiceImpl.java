package cn.mxz.formation;

import java.util.ArrayList;
import java.util.List;

import message.S;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.javaplus.random.Fetcher;
import cn.javaplus.util.Util;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.base.service.AbstractService;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.dogz.Dogz;
import cn.mxz.equipment.TianMingSnapsort;
import cn.mxz.events.AttributeChangeEvent;
import cn.mxz.events.Events;
import cn.mxz.fighter.FighterSnapshoot;
import cn.mxz.handler.NewFormationService;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.newpvp.PvpFightUser;
import cn.mxz.protocols.newformation.NewCampP.BackupPositionPro;
import cn.mxz.protocols.newformation.NewCampP.NewCampPro;
import cn.mxz.protocols.newformation.NewCampP.NewCampPro.Builder;
import cn.mxz.protocols.newformation.NewCampP.OtherCampPro;
import cn.mxz.protocols.newformation.NewCampP.TacticalPro;
import cn.mxz.protocols.user.UserP.UserPro;
import cn.mxz.user.builder.UserBuilder;
import cn.mxz.user.team.Team;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.debuger.Debuger;
import db.domain.BackupPosition;
import db.domain.Tactical;
import db.domain.TacticalDTO;

@Component("newFormationService")
@Scope("prototype")
public class NewFormationServiceImpl extends AbstractService implements
		NewFormationService {

	@Override
	public NewCampPro getData() {
		City city = getCity();
		FormationManager fm = (FormationManager) city.getFormation();
		return buildData(fm);
	}

	private NewCampPro buildData(FormationManager fm) {

		Builder b = NewCampPro.newBuilder();
		for (TacticalDTO tactical : fm.getAllTactical()) {
			b.addTacticalS(buildTacticalPro(tactical));
		}
		Tactical t = fm.getCurrentTactical();

		b.setCurrentTactical(t == null ? 0 : t.getIds());// 不存在则为0
		int shenJia = fm.getShenJia();
		b.setShenjia(shenJia);
//		Debuger.debug("NewFormationServiceImpl.buildData() " + shenJia);
		for (BackupPosition bp : fm.getAlternateFormation()) {
			b.addBackupPos(buildBackupPosPro(bp));
		}

		b.setHeroMaxNum(fm.getHeroMaxNum());

		b.setPosition(fm.getMainFormation());
		b.setLevels(fm.getLevels());

		return b.build();
	}

	@Override
	public void setCurrentTacticalId(int id) {

		FighterSnapshoot s = new FighterSnapshoot(getCity());
		s.snapshoot();

		FormationManager fm = (FormationManager) getCity().getFormation();
		fm.setCurrentTactical(id);
		s.snapshoot();
	}

	@Override
	public Boolean refreshBackupPos(int position) {

		int oldShenJia = getCity().getFormation().getShenJia();

		FighterSnapshoot s = new FighterSnapshoot(getCity());
		s.snapshoot();

		FormationManager fm = (FormationManager) getCity().getFormation();
		Boolean refreshBackupPos = fm.refreshBackupPos(position);

		s.snapshoot();

		Events.getInstance().dispatch(
				new AttributeChangeEvent(getCity(), oldShenJia));

		return refreshBackupPos;

	}


	private BackupPositionPro buildBackupPosPro(BackupPosition bp) {
		cn.mxz.protocols.newformation.NewCampP.BackupPositionPro.Builder b = BackupPositionPro
				.newBuilder();
		b.setHero(bp.getHeroId());
		b.setPosition(bp.getPosition());
		b.setTemplet(bp.getTempletId());

		return b.build();

	}

	private TacticalPro buildTacticalPro(TacticalDTO tactical) {
		cn.mxz.protocols.newformation.NewCampP.TacticalPro.Builder b = TacticalPro
				.newBuilder();
		b.setId(tactical.getIds());
		b.setLevel(tactical.getLevel());
		b.setTemplet(tactical.getTempletId());
		float addtion[] = TacticalAddtionWithPosition.showAddtion(
				tactical.getTempletId(), tactical.getLevel());
		b.setAddtion1(addtion[0]);
		b.setAddtion2(addtion[1]);
		b.setAddtion3(addtion[2]);
		// b.setShenjia(value)
		return b.build();
	}

	@Override
	public Boolean tacticalUp(int source, String fregment) {
		if (fregment.isEmpty()) {
			throw new SureIllegalOperationException("升级消耗的阵法不存在");
		}

		int oldShenJia = getCity().getFormation().getShenJia();

		FighterSnapshoot ss = new FighterSnapshoot(getCity());
		ss.snapshoot();

		FormationManager fm = (FormationManager) getCity().getFormation();

		List<Integer> list = new ArrayList<Integer>();
		for (String s : fregment.split(",")) {
			list.add(Integer.parseInt(s));
		}

		boolean tacticalLevelUp = fm.tacticalLevelUp(source, list);

		ss.snapshoot();

		if (tacticalLevelUp) {
			Events.getInstance().dispatch(
					new AttributeChangeEvent(getCity(), oldShenJia));
		}
		return tacticalLevelUp;
	}

	@Override
	public OtherCampPro lookUpOther(String userId) {

		City user = CityFactory.getCity(userId);

		if (user == null) {
			return buildPvpRobot(userId);
		}

		FormationManager fm = (FormationManager) user.getFormation();

		// FormationManager fm = new FormationManager(user);

		Dogz fighting = user.getDogzManager().getFighting();

		// 林岑加 2014年1月11日 13:57:12
		int dogz = fighting == null ? 0 : fighting.getTypeId();

		cn.mxz.protocols.newformation.NewCampP.OtherCampPro.Builder b = OtherCampPro
				.newBuilder();
		b.setCamp(buildData(fm));
		b.setDogz(dogz);
		return b.build();
	}

	// 林岑加2014年4月29日 20:03:04
	// 林岑加2014年4月29日 20:03:04
	// 林岑加2014年4月29日 20:03:04
	// 林岑加2014年4月29日 20:03:04
	// 林岑加2014年4月29日 20:03:04
	// |
	// |
	// |
	// |
	// ↓

	/**
	 * PVP 随机出来的玩家
	 * 
	 * @param userId
	 * @return
	 */
	private OtherCampPro buildPvpRobot(String userId) {
		OtherCampPro.Builder bb = OtherCampPro.newBuilder();

		// message TacticalPro{
		// required int32 id = 1;//id
		// required int32 templet = 2;//模板id
		// required int32 level = 3;//等级
		// required float addtion1 = 5;//阵首加成
		// required float addtion2 = 6;//阵中加成
		// required float addtion3 = 7;//阵尾加成
		//
		// }
		// message NewCampPro {
		// required string position = 1;//阵型字符串，"英雄id,位置,英雄id,位置"位置从0-8
		// required string levels = 7;////等级符串，"英雄id,等级,英雄id,等级"
		// required int32 heroMaxNum = 2;//上阵人数上限
		// repeated TacticalPro tacticalS = 3;//所有阵法
		// required int32 currentTactical = 4;//当前阵法
		// required int32 shenjia = 5;//身价
		// repeated BackupPositionPro backupPos = 6;//替补信息
		// }
		// message OtherCampPro {
		// required NewCampPro camp = 6;//替补信息
		// required int32 dogz = 7;//神兽模板id
		// }

		PvpFightUser pv = getCity().getNewPvpManager().getUserRandomed(userId);

		Builder b = NewCampPro.newBuilder();

		b.setCurrentTactical(0);// 不存在则为0
		int shenJia = pv.getPlayer().getShenJia();
		
		b.setShenjia(shenJia);

		b.setHeroMaxNum(5);

		b.setPosition(buildPosition(pv));
		b.setLevels(buildLevels(pv));

		bb.setCamp(b.build());
		bb.setDogz(-1);
		return bb.build();
	}

	private String buildLevels(PvpFightUser pv) {
		final PlayerCamp c = pv.getCamp();
		List<Hero> fs = c.getFighters();

		Fetcher<Hero, String> fc = new Fetcher<Hero, String>() {

			@Override
			public String get(Hero t) {
				return t.getTypeId() + "," + t.getLevel();
			}
		};
		return Util.Collection.linkWith(",", fs, fc);
	}

	private String buildPosition(PvpFightUser pv) {
		final PlayerCamp c = pv.getCamp();
		List<Hero> fs = c.getFighters();

		Fetcher<Hero, String> fc = new Fetcher<Hero, String>() {

			@Override
			public String get(Hero t) {
				return t.getTypeId() + "," + c.getPosition(t);
			}
		};
		return Util.Collection.linkWith(",", fs, fc);
	}

	// ↑
	// |
	// |
	// |
	// |
	// 林岑加2014年4月29日 20:03:04
	// 林岑加2014年4月29日 20:03:04
	// 林岑加2014年4月29日 20:03:04
	// 林岑加2014年4月29日 20:03:04
	// 林岑加2014年4月29日 20:03:04

	private Boolean setPosition(int heroId, int targetPos, int targetHeroId) {

		int oldShenJia = getCity().getFormation().getShenJia();

		FighterSnapshoot s = new FighterSnapshoot(getCity());
		s.snapshoot();

		FormationManager fm = (FormationManager) getCity().getFormation();
		Boolean setHeroPosition = fm.setHeroPosition(targetPos, heroId);

		s.snapshoot();

		if (setHeroPosition) {
			Events.getInstance().dispatch(
					new AttributeChangeEvent(getCity(), oldShenJia));
		}

		return setHeroPosition;
	}

	@Override
	public Boolean change(int heroId, int targetPos, int targetHeroId) {
		TianMingSnapsort tms = new TianMingSnapsort(getCity().getTeam().get(
				heroId));
		tms.snapsort();

		Team team = getCity().getTeam();
		Hero hero = team.get(targetHeroId);
		if (hero == null) {
			throw new NullPointerException("战士不存在!" + targetHeroId);
		}
		Boolean setPosition = setPosition(heroId, targetPos, targetHeroId);

		tms.snapsort();
		return setPosition;
	}

	@Override
	public Boolean move(int heroId, int targetPos) {
		return setPosition(heroId, targetPos, -1);
	}

	@Override
	public TacticalPro compound(int targetId) {
		FormationManager fm = (FormationManager) getCity().getFormation();

		Tactical tactical = fm.tacticalCompound(targetId);
		if (tactical == null) {
			throw new OperationFaildException(S.S10175);
		}
		return buildTacticalPro(tactical);
	}

}
