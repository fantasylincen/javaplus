package cn.mxz.bag;

import java.util.List;

import com.google.common.collect.Lists;

import db.dao.impl.UserPiecesGridDao;
import db.domain.DBGrid;
import db.domain.UserPiecesGrid;

public class PiecesGridDaoAdaptor implements GridDao {

	private UserPiecesGridDao dao;

	public PiecesGridDaoAdaptor(UserPiecesGridDao dao) {
		this.dao = dao;
	}

	@Override
	public List<DBGrid> findByTypeId(int stuffId) {
		List<UserPiecesGrid> all = dao.findByTypeid(stuffId);
		List<DBGrid> ls = Lists.newArrayList();
		for (UserPiecesGrid userGrid : all) {
			ls.add(userGrid);
		}
		return ls;
	}

}
