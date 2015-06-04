//package cn.mxz.mission;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import cn.javaplus.common.util.Util;
//import cn.mxz.base.prize.Prize;
//import cn.mxz.base.service.AbstractService;
//import cn.mxz.base.world.WorldFactory;
//import cn.mxz.battle.CampBuilder;
//import cn.mxz.formation.PlayerCamp;
//import cn.mxz.handler.MissionService;
//import cn.mxz.mission.type.DemonCamp2;
//import cn.mxz.mission.type.DemonInCamp;
//import cn.mxz.mission.type.DemonInCampResolver;
//import cn.mxz.prop.BoxBuilder;
//import cn.mxz.protocols.user.UserP.UserPro;
//import cn.mxz.protocols.user.battle.WarSituationP.CampPro;
//import cn.mxz.protocols.user.god.FighterP.FighterPro;
//import cn.mxz.protocols.user.mission.BoxP.BoxPro;
//import cn.mxz.protocols.user.mission.MissionP.MissionPro;
//import cn.mxz.protocols.user.mission.MissionP.MissionPro.Builder;
//import cn.mxz.protocols.user.mission.MissionP.MissionWarSituationPro;
//import cn.mxz.protocols.user.mission.MissionP.RandomAddFriendPro;
//import cn.mxz.team.builder.FighterBuilder;
//import cn.mxz.user.City;
//import cn.mxz.user.builder.UserBuilder;
//import cn.mxz.user.team.god.Hero;
//
//import com.lemon.commons.user.IUser;
//
//
//public class MissionServiceImplTest extends AbstractService implements MissionService {
//
//	
//
//	@Override
//	public void enter(int mapId ) {
//		
//	}
//
//	@Override
//	public void giveUp() {
//		fightersHpFull();
//	}
//
//	private void fightersHpFull() {
//		Collection<Hero> all = getCity().getTeam().getAll();
//		for (Hero hero : all) {
//			hero.addHp(1000000);
//			hero.commit();
//		}
//	}
//	@Override
//	public MissionPro getMissionData() {
//		
//		Builder b = MissionPro.newBuilder();
//		
//		IMissionManager m = new MissionManagerImplTest();
//		
//		b.setMissionId(m.getMissionId());
//		
////		b.setMap(new MissionMapBuilder().build(m));
//		
//		return b.build();
//	}
//
//	@Override
//	public CampPro getDemonCamp(int path) {
//
//		IMissionManager mission = new MissionManagerImplTest();
//		
//		int[] demonIds = mission.getDemonCamp(path);
//		
//		List<DemonInCamp> demons = new DemonInCampResolver().resolve(demonIds, mission.getMissionId());
//		
//		DemonCamp2 camp = new DemonCamp2(demons);
//		
//		return new CampBuilder().build(camp);
//	}
//
//	@Override
//	public BoxPro openChest(int path) {
//		IMissionManager mission = new MissionManagerImplTest();
//		@SuppressWarnings("unchecked")
//		List<Prize> ls = (List<Prize>) mission.run(path);
//		
//		return new BoxBuilder().build(ls, getPlayer());
//	}
//
//	@Override
//	public void encounterQuestion(int path) {
//		
//		IMissionManager mission = new MissionManagerImplTest();
//		mission.run(path);
//	}
//
//	@Override
//	public MissionWarSituationPro fighting(int path) {
//		IMissionManager mission = new MissionManagerImplTest();
//		return (MissionWarSituationPro) mission.run(path);
//	}
//	
//	@Override
//	public RandomAddFriendPro randomAddFriend() {
//		IUser user = null;
//		while( true ){
//			List<IUser> onlineUsers = new ArrayList<IUser>( WorldFactory.getWorld().getOnlineAll() );
//			if( onlineUsers.size() <= 1 ){
//				List<IUser> allUsers = new ArrayList<IUser>( WorldFactory.getWorld().getAll() );
//				user = Util.getRandomOne( allUsers );
//			}
//			else{
//				user = Util.getRandomOne( onlineUsers );
//			}
//
//			if( !user.getId().equals( getCity().getId() ) ){
//				break;
//			}
//
//		}
//
//		RandomAddFriendPro.Builder ret = RandomAddFriendPro.newBuilder();
//		if( user != null ){
//			City city = (City) user;
//			UserPro buildOfUserPro = new UserBuilder().build( city );
//
//			PlayerCamp selected = city.getFormation().getSelected();
//			
//			List<Hero> fightersFront = selected.getFighters();
//
//			for (Hero hero : fightersFront) {
//				FighterPro gp = new FighterBuilder().build(hero);
//				ret.addGod(gp);
//			}
//
//			ret.setUser(buildOfUserPro);
//
//		}
//
//		return ret.build();
//
//	}
//
//	@Override
//	public void crossEmpty(int path) {
//		
//	}
//
//	@Override
//	public String getStarByCapterId(int chapterId) {
//		return "1:10/20,2:20/30,3:20/30,4:20/30,5:20/30";
//	}
//
//	@Override
//	public String getStarBySceneId(int sceneId) {
//		return "1:10/20,2:20/30,3:20/30,4:20/30,5:20/30";
//	}
//}
