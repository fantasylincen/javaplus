package cn.mxz.shenmo;

import cn.javaplus.util.Util;
import cn.mxz.RandomEventRappelzTemplet;
import cn.mxz.RandomEventRappelzTempletConfig;
import cn.mxz.city.City;
import cn.mxz.util.counter.CounterKey;
import db.dao.impl.DaoFactory;
import db.domain.BossData;


/**
 * 根据玩家等级从配置表返回神魔的相关属性
 * @author Administrator
 *
 */
public class GenShenmo {

	BossData gen(City user) {
		BossData bd = DaoFactory.getBossDataDao().createDTO();
		RandomEventRappelzTemplet templet = null;
		if (user.getUserCounterHistory().get(CounterKey.SHENMO_HAS_FIRST_SHOW) == 0) {
			templet = RandomEventRappelzTempletConfig.get(1);

		} else {
			int level = user.getLevel();
			for (RandomEventRappelzTemplet t : RandomEventRappelzTempletConfig.getAll()) {
				int[] levels = Util.Array.toIntegerArray(t.getProtagonistLv());
				if (level >= levels[0] && level <= levels[1]) {
					templet = t;
					break;
				}
			}
		}
		if (templet == null) {
			return null;
		}
		bd.setLevel(calcBossLevel(templet));
		bd.setBossTempletId(calcBossId(templet));
		if( user.getUserCounterHistory().get(CounterKey.SHENMO_HAS_FIRST_SHOW) == 0 ){//引导期间第一次发现神魔不能让他逃走
			int foundSecond = (int) (System.currentTimeMillis() / 1000);
			foundSecond += 24 * 3600;//后移动24个小时
			bd.setFoundTime( foundSecond );
			
		}
		else{
			bd.setFoundTime((int) (System.currentTimeMillis() / 1000));
		}
		bd.setUname(user.getId());
		bd.setIsShared(false);

		return bd;
	}

	private int calcBossId(RandomEventRappelzTemplet t) {
		return calcRandom( t.getRappelzId() );

	}

	private int calcBossLevel(RandomEventRappelzTemplet t) {

		return calcRandom( t.getRappelzLv() );
	}

	/**
	 * 把用逗号分隔的字符串转换为整型数组，再从此数组中随机选取一个
	 * @param content
	 * @return
	 */
	private int calcRandom( String content ){
		int[] arr = Util.Array.toIntegerArray( content );
		int randomIndex = Util.Random.getRandomIndex(arr);
		return arr[randomIndex];

	}
}
