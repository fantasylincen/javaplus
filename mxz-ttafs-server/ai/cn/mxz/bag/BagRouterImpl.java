package cn.mxz.bag;

import cn.mxz.StuffTemplet;
import cn.mxz.StuffTempletConfig;

import com.google.common.collect.Sets;

import db.dao.impl.DaoFactory;

public class BagRouterImpl implements BagRouter {

	public GridDao getDAO(int propId) {
		if(isSuiPian(propId)) {
			return new PiecesGridDaoAdaptor(DaoFactory.getUserPiecesGridDao());
		} else {
			return new GridDaoAdaptor(DaoFactory.getUserGridDao());
		}
	}

	@Override
	public boolean isSuiPian(int id) {
		StuffTemplet t = StuffTempletConfig.get(id);
		if (t != null) {

			int type = t.getType();

			return Sets.newHashSet(2, 5, 6).contains(type);
		}
		return false;

	}

}
