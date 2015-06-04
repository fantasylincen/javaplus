package com.cnbizmedia.gen.dtodefine;

import java.util.List;

import cn.javaplus.db.mongo.Key;

interface Zone {

	@Key
	String getId();

	String getName();

	List<KeyValue> getProperties();

	GameXmlFile getGameXmlFile();

	GameXmlFile getClientXmlFile();
}
