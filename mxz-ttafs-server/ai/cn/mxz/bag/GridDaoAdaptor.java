package cn.mxz.bag;

import java.util.List;

import com.google.common.collect.Lists;

import db.dao.impl.UserGridDao;
import db.domain.DBGrid;
import db.domain.UserGrid;

public class GridDaoAdaptor implements GridDao {

	private UserGridDao dao;

	public GridDaoAdaptor(UserGridDao dao) {
		this.dao = dao;
	}

	@Override
	public List<DBGrid> findByTypeId(int stuffId) {
		List<UserGrid> all = dao.findByTypeid(stuffId);
		List<DBGrid> ls = Lists.newArrayList();
		for (UserGrid userGrid : all) {
			ls.add(userGrid);
		}
		return ls;
	}

}
