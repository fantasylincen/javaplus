package cn.mxz.dogz;

import java.util.ArrayList;
import java.util.List;

import cn.mxz.city.City;
import db.dao.impl.DaoFactory;
import db.dao.impl.UserDogzDao;
import db.domain.UserDogz;

/**
 * 神兽工厂
 *
 * @author 林岑
 *
 */
public class DogzManagerFactory {

	public static DogzManager createDogzManager(City city) {

		DogzManagerImpl di = new DogzManagerImpl(city);

		di.setAll(createDogzsAll(city));

		return di;
	}

	private static List<Dogz> createDogzsAll(City city) {

		List<Dogz> ls = new ArrayList<Dogz>();

		UserDogzDao DAO = DaoFactory.getUserDogzDao();

		List<UserDogz> Alldogz = DAO.findByUname(city.getId());

		for (UserDogz userDogz : Alldogz) {

			ls.add(new DogzImpl(userDogz));
		}

		return ls;
	}


}
