package cn.mxz.yuanshen;

import java.util.Arrays;
import java.util.List;

import message.S;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.javaplus.util.Util;
import cn.javaplus.util.Util.Collection;
import cn.mxz.GodQurlityTemplet;
import cn.mxz.GodQurlityTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.base.service.AbstractService;
import cn.mxz.battle.YuanShen;
import cn.mxz.battle.YuanShenBuilder;
import cn.mxz.events.AttributeChangeEvent;
import cn.mxz.events.Events;
import cn.mxz.fighter.FighterSnapshoot;
import cn.mxz.handler.YuanShenService;
import cn.mxz.protocols.user.god.FighterP.YuanShenPro;
import cn.mxz.spirite.Spirite;
import cn.mxz.spirite.SpiriteManager;
import cn.mxz.user.team.Team;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.counter.CounterKey;

import com.google.common.collect.Lists;

@Component("yuanShenService")
@Scope("prototype")
public class YuanShenServiceImpl extends AbstractService implements YuanShenService {

	public class Need {

		private Integer	count;

		private Integer	id;

		public Need(Integer id, Integer count) {
			this.id = id;
			this.count = count;
		}

		public Integer getId() {
			return id;
		}

		public Integer getCount() {
			return count;
		}
	}


	@Override
	public YuanShenPro levelUp(int fighterId, int index, String ids) {

		ids = ids + " ";
		String[] split = ids.split("\\|");
		FighterSnapshoot s = new FighterSnapshoot(getCity());
		s.snapshoot();
		List<Integer> fighterIds = Collection.getIntegers(split[0]);
		List<Need> spiriteIds;
		try {
			spiriteIds = getNeed(split[1]);
		} catch (Exception e) {
			System.err.println(Arrays.toString(split));
			throw Util.Exception.toRuntimeException(e);
		}

		int oldShenJia = getCity().getFormation().getShenJia();
		
		checkLevelUp(fighterIds, spiriteIds);

		YuanShen yuanShen = getYuanShen(fighterId, index);

		checkMaxLevel(yuanShen, fighterId);

		YuanShenLevelUpCalc c = new YuanShenLevelUpCalc();

		int exp = c.calc(getCity(), fighterIds, spiriteIds);

		yuanShen.addExp(exp);

		remove(fighterIds, spiriteIds);

		getCity().getUserCounter().add(CounterKey.YUAN_SHEN_LEVEL_UP_TIMES, 1);

		YuanShenPro build = new YuanShenBuilder().build(yuanShen);
		s.snapshoot();

		Events.getInstance().dispatch(new AttributeChangeEvent(getCity(), oldShenJia));
		return build;
	}

	private void checkMaxLevel(YuanShen yuanShen, int fighterId) {
		Team team = getCity().getTeam();
		Hero hero = team.get(fighterId);
		int q = hero.getQuality();

		GodQurlityTemplet temp = GodQurlityTempletConfig.get(q);
		if(temp != null) {
			int l = temp.getShadowLevel();
			if(yuanShen.getLevel() >= l) {
				throw new OperationFaildException(S.S10223);
			}
		}
	}

	private List<Need> getNeed(String string) {
		List<Need> ls = Lists.newArrayList();
		string = string.trim();
		String[] split = string.split(",");

		for (String string2 : split) {
			if (!string2.isEmpty()) {
				ls.add(build(string2));
			}
		}
		return ls;
	}

	private Need build(String string2) {
		String[] split = string2.split("_");
		Need n = new Need(new Integer(split[0]), new Integer(split[1]));
		return n;
	}

	@Override
	public YuanShenPro reset(int fighterId, int index) {

		int oldShenJia = getCity().getFormation().getShenJia();
		
		FighterSnapshoot s = new FighterSnapshoot(getCity());
		s.snapshoot();
		YuanShen yuanShen = getYuanShen(fighterId, index);
		yuanShen.reset();
		YuanShenPro build = new YuanShenBuilder().build(yuanShen);

		s.snapshoot();

		Events.getInstance().dispatch(new AttributeChangeEvent(getCity(), oldShenJia));
		
		return build;
	}

	private void remove(List<Integer> fighterIds, List<Need> spiriteIds) {
		Team team = getCity().getTeam();
		for (Integer id : fighterIds) {
			team.remove(id);
		}

		SpiriteManager manager = getCity().getSpiriteManager();
		for (Need n : spiriteIds) {
			manager.remove(n.getId(), n.getCount());
		}
	}

	private void checkLevelUp(List<Integer> fighterIds, List<Need> spiriteIds) {
		checkSpiriteExist(spiriteIds);
		checkFighterExist(fighterIds);
		checkSelf(fighterIds);
	}

	private void checkSelf(List<Integer> fighterIds) {
		if (fighterIds.contains(fighterIds)) {
			throw new OperationFaildException(S.S10173);
		}
	}

	private void checkSpiriteExist(List<Need> spiriteIds) {
		SpiriteManager manager = getCity().getSpiriteManager();
		for (Need n : spiriteIds) {
			Spirite s = manager.get(n.getId());
			if (s == null) {
				throw new OperationFaildException(S.S10174);
			}

			if (s.getCount() < n.getCount()) {
				throw new OperationFaildException(S.S10222);
			}
		}
	}

	private void checkFighterExist(List<Integer> fighterIds) {
		for (Integer integer : fighterIds) {
			Team team = getCity().getTeam();
			Hero hero = team.get(integer);
			if (hero == null) {
				throw new SureIllegalOperationException("-神将不存在!");
			}
		}
	}

	private YuanShen getYuanShen(int fighterId, int index) {
		Team team = getCity().getTeam();
		Hero hero = team.get(fighterId);
		List<YuanShen> ys = hero.getYuanShenManager().getYuanShens();
		YuanShen yuanShen = ys.get(index);
		return yuanShen;
	}

}
