package cn.mxz.mission.old;
//package cn.mxz.mission;
//
//import cn.mxz.base.prize.GanLu;
//import cn.mxz.base.prize.XianLu;
//import cn.mxz.battle.Camp;
//import cn.mxz.protocols.user.god.FighterListP.GodsPro;
//import cn.mxz.protocols.user.mission.MissionP.CapturesPro;
//import cn.mxz.team.builder.FightersBuilder;
//import cn.mxz.user.mission.Captures;
//import cn.mxz.user.team.god.Hero;
//import db.domain.MissionData;
//import db.domain.MissionMapData;
//
//public abstract class AbstractMissionCaptureService <T extends MissionData, MT extends MissionMapData>
//
//extends AbstractMissionService<T, MT> {
//
//	public AbstractMissionCaptureService() {
//
//		super();
//	}
//
//	/**
//	 * 使用甘露
//	 */
//	public GodsPro useGanLu() {
//
//		final int ID = 130006;
//
//		getCity().getBag().remove(ID, 1);
//
//		GanLu ganLu = new GanLu();
//
//		ganLu.award(getPlayer());
//
//		Camp<Hero> selected = getCity().getFormation().getSelected();
//
//		return new FightersBuilder().build(getCity(), selected, ganLu.getHeros());
//	}
//
//	/**
//	 * 使用仙露
//	 */
//	public GodsPro useXianLu() {
//
//		final int ID = 130007;
//
//		getCity().getBag().remove(ID, 1);
//
//		XianLu xianLu = new XianLu();
//
//		xianLu.award(getPlayer());
//
//		Camp<Hero> selected = getCity().getFormation().getSelected();
//
//		return new FightersBuilder().build(getCity(), selected, xianLu.getHeros());
//	}
//
//	/**
//	 * 捕获神将信息
//	 */
//	public final CapturesPro getCapturesData() {
//
//		return new CapturesBuilder().build(getCaptures());
//	}
//
//	protected abstract Captures getCaptures();
//
//}