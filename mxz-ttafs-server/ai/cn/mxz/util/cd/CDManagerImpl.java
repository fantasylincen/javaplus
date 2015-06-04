package cn.mxz.util.cd;

import mongo.gen.MongoGen.ColdDownDao;
import mongo.gen.MongoGen.ColdDownDto;
import mongo.gen.MongoGen.Daos;
import cn.javaplus.time.colddown.ColdDown;
import cn.javaplus.time.colddown.ColdDownListener;

class CDManagerImpl implements CDManager {

	private String	userId;

	public CDManagerImpl(String userId) {

		this.userId = userId;
	}

	@Override
	public ColdDown get(final CDKey key) {

		// checkIndex(key.getId());

		ColdDownDao DAO = Daos.getColdDownDao();

		ColdDownDto dto = lazyGet(key.getId());

		final long end = dto.getEndTime();

		final ColdDownImpl cd = new ColdDownImpl();

		cd.setEvery(key.getAddEvery());

		cd.setEnd(end);

		cd.setFreezing(key.getFreezingTime());

		cd.addListener(new ColdDownListener() {

			@Override
			public void onClear() {

				commit(cd, key.getId());
			}

			@Override
			public void onAdd() {

				commit(cd, key.getId());
			}

			@Override
			public void onSet() {

				commit(cd, key.getId());
			}

		});

		return cd;
	}

	private void commit(ColdDownImpl cd, int index) {

		ColdDownDao DAO = Daos.getColdDownDao();

		ColdDownDto dto = lazyGet(index);

		dto.setEndTime(cd.getEndTime());

		DAO.save(dto);
	}

	private ColdDownDto lazyGet(int index) {

		ColdDownDao DAO = Daos.getColdDownDao();

		ColdDownDto dto = DAO.get(userId, index);

		if (dto == null) {

			dto = new ColdDownDto();

			dto.setUname(userId);

			dto.setIndex(index);

			DAO.save(dto);
		}
		return dto;
	}

	// private void checkIndex(int index) {
	//
	// if(index > db.domain.ColdDownImpl.END_TIME_LEN) {
	//
	// throw new RuntimeException("冷却时间长度不够:" + index + ">" +
	// db.domain.ColdDownImpl.END_TIME_LEN);
	// }
	// }

}
