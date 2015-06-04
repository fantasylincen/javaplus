package cn.mxz.dogz;

import cn.mxz.DogzLevelTempletConfig;
import cn.mxz.city.City;
import db.dao.impl.DaoFactory;
import db.dao.impl.UserDogzDao;
import db.domain.UserDogz;

/**
 * 神兽工厂
 * @author 林岑
 *
 */
public class DogzFactory {

	/**
	 * 增加一个神兽, 该方法执行后, 内存和数据库中都会增加该神兽
	 *
	 * @param city			玩家的城池
	 *
	 * @param dogzTypeId	神兽的类型
	 */
	public static Dogz createNewDogz(City city, int dogzTypeId) {

		UserDogzDao DAO = DaoFactory.getUserDogzDao();

		UserDogz userDogz = DAO.createDTO();

		userDogz.setUname(city.getPlayer().getId());

		userDogz.setDogzId(dogzTypeId);

		userDogz.setDunwuCount(0);

		userDogz.setGrowth(0);

		userDogz.setIsBattlefield(false);

		userDogz.setIsProtected(false);

		userDogz.setLevel(DogzLevelTempletConfig.getMinKey());

//		Integer minKey = DogzQualityTempletConfig.getMinKey();

		userDogz.setStep(0);

//		userDogz.setTrait(DogzQualityTempletConfig.get(minKey).getTraitMin());
		userDogz.setTrait(0);

		DogzImpl d = new DogzImpl(userDogz);
		city.getDogzManager().addDogz(d);

		DAO.add(userDogz);

		return d;
	}

}
