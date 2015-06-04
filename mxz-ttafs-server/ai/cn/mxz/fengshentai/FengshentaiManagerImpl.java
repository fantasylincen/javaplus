package cn.mxz.fengshentai;

import java.util.List;

import message.S;
import cn.javaplus.util.Util;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.fighter.FighterSnapshoot;
import cn.mxz.fighter.SkillDeleter;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.user.builder.UserBuilder;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.counter.CounterKey;

import com.google.common.collect.Lists;

import define.D;

public class FengshentaiManagerImpl implements IFengshentaiManager {
	private final City 				user;
	private final PropManager 		propManager;



	public FengshentaiManagerImpl(City user) {
		super();
		this.user = user;
		propManager = new PropManager(user);
	}


	/* （非 Javadoc）
	 * @see cn.mxz.fengshentai.IFengshentaiManager#exchange()
	 */
	@Override
	public void exchange()
	{

		int reduce = D.FENGSHENTAI_FENGSHENLING;
		user.getPlayer().reduce(PlayerProperty.REPUTATION, reduce);
		addFengshenling();
		MessageFactory.getUser().onUpdateUserList(user.getSocket(), new UserBuilder().build(user));
	}


	/* （非 Javadoc）
	 * @see cn.mxz.fengshentai.IFengshentaiManager#isHasfengshenling()
	 */
	@Override
	public boolean isHasfengshenling(){
		return user.getUserCounterHistory().get( CounterKey.FENGSHENGLING ) == 1;
	}

	private void removeFengshenling(){
		 user.getUserCounterHistory().set( CounterKey.FENGSHENGLING, 0 );
	}

	private void addFengshenling(){
		 user.getUserCounterHistory().set( CounterKey.FENGSHENGLING, 1 );
	}

	/* （非 Javadoc）
	 * @see cn.mxz.fengshentai.IFengshentaiManager#fengshen(int)
	 */
	@Override
	public boolean fengshen( int fighterId ){
		//////////////////////////////林岑帮忙看看，好像没有保存数据库

		FighterSnapshoot s = new FighterSnapshoot(user);
		s.snapshoot();

		Hero hero = user.getTeam().get(fighterId);
		if( hero == null ){
			throw new SureIllegalOperationException(fighterId + "英雄不存在");
		}
//		if( hero instanceof PlayerHero ){
//			throw new IllegalOperationException(fighterId + "主角不能兑换");
//		}
		if( user.getFormation().getPosition(hero) != -1 ){
			throw new SureIllegalOperationException(fighterId + "阵上伙伴不能封神");
		}
		int typeId = hero.getTypeId();
		FighterTemplet templet = FighterTempletConfig.get(typeId);
		if( templet.getStep() < 4 || templet.getStep() == 6){
			throw new SureIllegalOperationException(fighterId + "不是甲级英雄");
		}
//		user
		int findType = gerRandomGodHero();
		if( findType == 0 ){
//			user.getSpiriteManager().add(id);
			//throw new OperationFaildException(S.S10219);
			throw new SureIllegalOperationException(fighterId + "暂时没有合适的神级英雄");
//			return false;
		}

		removeFengshenling();
		remove(hero);
		create(hero, findType);

		s.snapshoot();
		return true;
	}



	private void create(Hero h, int findType) {
		Hero hero = user.getTeam().createNewHero(findType);
		int all = h.getExpAll();
		hero.addExp(all);
		hero.commit();
	}


	private void remove(Hero hero) {
		user.getTeam().remove(hero);
		new SkillDeleter().removeSkill(hero);
	}


	/**
	 * 随机产生神级弟子
	 * 如果神级弟子已经满了，返回0
	 * @return
	 */
	private int gerRandomGodHero() {
		List<Integer> filter = getShenjiTemplet();
		filter.removeAll( getShenjiHero() );
		if( filter.size() == 0 ){
			return 0;
		}



		return Util.Random.getRandomOne(filter);

	}

	/**
	 * 获取所有的神级弟子
	 * @return
	 */
	private List<Integer> getShenjiHero(){
		List<Integer> list = Lists.newArrayList();
		for( Hero hero : user.getTeam().getAll() ){
			FighterTemplet templet = FighterTempletConfig.get( hero.getTypeId() );
			if( templet.getStep() == 6 ){
				list.add( templet.getId() );
			}
		}
		return list;
	}

	/**
	 * 获取所有的神级英雄模板
	 * @return
	 */
	private List<Integer> getShenjiTemplet(){
		List<Integer> list = Lists.newArrayList();
		for( FighterTemplet t : FighterTempletConfig.getAll() ){
			if( t.getStep() == 6 && Integer.parseInt( t.getGodType() ) == 2 ){
				list.add(t.getType());
			}
		}
		return list;

	}


	@Override
	public PropItem buy( int propId ) {
		PropItem item = propManager.buy(propId);
		MessageFactory.getUser().onUpdateUserList(user.getSocket(), new UserBuilder().build(user));
		return item;

	}


	@Override
	public List<PropItem> getPropData() {
		return propManager.getAllProp();
	}

}
