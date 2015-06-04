package cn.mxz.bag;

import java.util.List;

import db.domain.DBGrid;

public interface GridDao {

	List<DBGrid> findByTypeId(int stuffId);

}
