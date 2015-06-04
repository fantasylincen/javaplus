package com.cnbizmedia.gen.dtodefine;

import java.util.List;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface Project {

	@Key
	String getId();

	String getName();

	/**
	 * GM脚本
	 */
	String getGmScript();

	List<Zone> getZones();
}
