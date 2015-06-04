package cn.mxz.formation;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.joda.time.DateTime;

import cn.javaplus.util.Util.Random;
import cn.mxz.Attribute;
import cn.mxz.Chipable;
import cn.mxz.FormationBasisTempletConfig;
import cn.mxz.FormationHostTemplet;
import cn.mxz.FormationHostTempletConfig;
import cn.mxz.FormationTemplet;
import cn.mxz.FormationTempletConfig;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.dogz.GenerateManager;
import cn.mxz.fighter.AttributeEmpty;
import cn.mxz.user.team.god.Hero;
import cn.mxz.user.team.god.ShenJiaAble;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounterSetter;

import com.google.common.collect.Lists;

import db.dao.impl.DaoFactory;
import db.dao.impl.TacticalDao;
import db.domain.Tactical;

/**
 * 阵法管理类
 *
 * @author Administrator
 *
 */
class TacticalManager extends GenerateManager<Tactical> implements IFormationAddtion, ShenJiaAble {

	private List<Tactical>							tacticals;
	/**
	 * 当前阵法
	 */
	private Tactical								current;

	private final TacticalDao						db	= DaoFactory.getTacticalDao();

	private AtomicInteger							atom;
	
	private final City								user;

	public TacticalManager(City user) {
		super(user);
		this.tacticals = db.findByUname(user.getId());
		for (Tactical t : tacticals) {
			if (t.getIscurrent()) {
				current = t;
				break;
			}
		}
		atom = new AtomicInteger(getMaxId());
		this.user = user;
	}

	private int getMaxId() {
		int maxId = 0;
		for (Tactical t : tacticals) {
			if (t.getIds() > maxId) {
				maxId = t.getIds();
			}
		}
		return maxId;
	}

	/**
	 * 当前阵法对上阵英雄的加成
	 */
	@Override
	public Attribute getAddition(Hero hero) {
		int position = city.getFormation().getPosition(hero);
		if (position == -1 || current == null) {
			return new AttributeEmpty();
		}
		int templetId = current.getTempletId();
		FormationTemplet templet = FormationTempletConfig.get(templetId);
		TacticalAddtionWithPosition parse = TacticalAddtionWithPosition.parse(position);

		Attribute addtion = parse.getAddtion(hero, templet, current.getLevel() );
//		if(addtion.getBlock() > 10000) {
//			Debuger.debug("TacticalManager .getAddition()");
//		}
		return addtion;
	}

	/**
	 * 创建一个阵法
	 */
	public Tactical create(int templetId) {
		if (FormationTempletConfig.get(templetId) == null) {
			throw new NullPointerException();
		}
		Tactical t = db.createDTO();
		t.setIscurrent(false);
		t.setUname(city.getId());
		t.setLevel(1);
		t.setTempletId(templetId);
		t.setIds(atom.incrementAndGet());
		tacticals.add(t);
		db.add(t);

		return t;
	}

	/**
	 * 设置当前使用阵法
	 *
	 * @param tacticalId
	 * @return
	 */
	public boolean setCurrent(int tacticalId) {
		Tactical t = get(tacticalId);
		if (t == null) {
			return false;
		}

		if (current != null) {
			current.setIscurrent(false);
			db.update(current);
		}
		current = t;
		current.setIscurrent(true);
		db.update(current);
		return true;
	}

	public List<Tactical> getAll() {
		return tacticals;
	}

	/**
	 * 阵法升级
	 *
	 * @param tacticalId
	 * @param fregment
	 * @return
	 */
	public boolean levelUp(int tacticalId, List<Integer> fregment) {

		boolean isSuccess = false;
		Tactical source = get(tacticalId);
		if (source == null) {
			System.err.println("被升级阵法不存在");
			return false;
		}

		List<Tactical> fregmentList = buildList(fregment);
		if (fregmentList.contains(current)) {
			System.err.println("当前阵法不能作为碎片");
			return false;
		}

		FormationTemplet formationNewTemplet = FormationTempletConfig.get(source.getTempletId());
		int quality = formationNewTemplet.getSpet();// 获取 源 品质

		String key = quality + "," + source.getLevel();
		FormationHostTemplet formationHostTemplet = FormationHostTempletConfig.get(key);
		float baseProbability = formationHostTemplet.getHostPro();// 获取 源
																	// 阵策略的基础概率


		float allAccumulate = calcLeveUpProbability(baseProbability, fregmentList);
		
		int needCash = (int) (formationHostTemplet.getCashNeed() * 100 * allAccumulate);
		city.getPlayer().reduce(PlayerProperty.CASH, needCash);// 扣钱
		
//		if(isFirstLevelUp()){
//			allAccumulate = 1;
//		}

		if (Random.isHappen(allAccumulate) || isFirstLevelUp() ) {
			isSuccess = true;
		} else {
			allAccumulate += source.getAccumulate();

			if (allAccumulate >= formationHostTemplet.getSumPro()) {
				isSuccess = true;
			}
		}
		
		
		if(isFirstLevelUp() ){
			setFirstLevelUp();
		}

		if (isSuccess) {
			source.setAccumulate(0);
			source.addLevel(1);
		} else {
			source.setAccumulate(allAccumulate);
		}

		db.update(source);

		for (Tactical t : fregmentList) {
			tacticals.remove(t);
			db.delete(t.getIds(), city.getId());
		}

		return isSuccess;
	}

	/**
	 * 阵法第一次升级一定成功
	 * CounterKey.TACTICAL_IS_FIRST_LEVELUP == 0 ，则是第一次，否则不是第一次
	 * @return
	 */
	private boolean isFirstLevelUp() {
		return user.getUserCounterHistory().get(CounterKey.TACTICAL_IS_FIRST_LEVELUP ) == 0;
	}
	
	/**
	 * 第一次升级结束
	 */
	private void setFirstLevelUp() {
		user.getUserCounterHistory().set(CounterKey.TACTICAL_IS_FIRST_LEVELUP, 1 );
	}

	/**
	 * @param fregment
	 * @return
	 */
	private List<Tactical> buildList(List<Integer> fregment) {
		List<Tactical> fregmentList = Lists.newArrayList();
		for (int tid : fregment) {
			Tactical t = get(tid);
			if (t == null) {
				continue;
			}
			fregmentList.add(t);
		}
		return fregmentList;

	}

	/**
	 * 通过碎片合成阵法
	 *
	 * @return
	 */
	public Tactical compound(int targetId) {
		Tactical generate = generate(targetId);
		UserCounterSetter uc = city.getUserCounterHistory();
		uc.add(CounterKey.TACTICAL_GENERATE_COUNT, 1);
		return generate;
	}

	/**
	 * 计算升级的基础概率
	 */
	private float calcLeveUpProbability(float baseProbability, List<Tactical> fregment) {

		float allAccumulate = 0;
		for (Tactical t : fregment) {

			FormationTemplet fnt = FormationTempletConfig.get(t.getTempletId());
			String k = fnt.getSpet() + "," + t.getLevel();// 获取品质等级key
			float basis = FormationBasisTempletConfig.get(k).getBasisPro();
			allAccumulate += basis * baseProbability;
		}
		return allAccumulate;
	}

	private Tactical get(int taticalId) {
		for (Tactical t : tacticals) {
			if (t.getIds() == taticalId) {
				return t;
			}
		}
		return null;
	}

	public Tactical getCurrentTactical() {
		return current;
//		if (current == null) {
//			return 0;
//		}
//		return current.getIds();

	}

	@Override
	public int getShenJia() {
		int ret = 0;
		if (current != null) {
			ret = getShenJia(current);
		}
		return ret;
	}

	public int getLevel(){
		if (current == null) {
			return 0;
		}
		return current.getLevel();
	}
	private int getShenJia(Tactical t) {
		FormationTemplet temp = FormationTempletConfig.get(t.getTempletId());
		int social = temp.getSocial();
		float grow = (t.getLevel() - 1) * temp.getSocialGrow();
		return (int) (social + grow);
	}

	@Override
	protected Tactical add(int id) {
		return create(id);
	}

	@Override
	protected Chipable getTemplet(int id) {
		return FormationTempletConfig.get(id);
	}
	
	public static void main(String[] args) {
		DateTime d = new DateTime(1406697622*1000l);
		System.out.println( d );
	}
}
