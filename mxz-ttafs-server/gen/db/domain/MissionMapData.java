package db.domain;

public interface MissionMapData {

	void setUname(String uname);

	String getUname();

	void addUname(String uname);

	void setMapId(int mapId);

	int getMapId();

	void addMapId(int mapId);

	void setPersonPath(int personPath);

	int getPersonPath();

	void addPersonPath(int personPath);

	void setPersonIndex(int personIndex);

	int getPersonIndex();

	void addPersonIndex(int personIndex);

	void setBossId(int bossId);

	int getBossId();

	void addBossId(int bossId);

	void setBossPath(short bossPath);

	short getBossPath();

	void addBossPath(short bossPath);

	void setBossIndex(short bossIndex);

	short getBossIndex();

	void addBossIndex(short bossIndex);

	void setStartDome(int startDome);

	int getStartDome();

	void addStartDome(int startDome);

	void setEndDome(int endDome);

	int getEndDome();

	void addEndDome(int endDome);

	void setPlotBegin1Id(int plotBegin1Id);

	int getPlotBegin1Id();

	void addPlotBegin1Id(int plotBegin1Id);

	void setPlotBegin2Id(int plotBegin2Id);

	int getPlotBegin2Id();

	void addPlotBegin2Id(int plotBegin2Id);

	void setPlotEnd1Id(int plotEnd1Id);

	int getPlotEnd1Id();

	void addPlotEnd1Id(int plotEnd1Id);

	void setPlotEnd2Id(int plotEnd2Id);

	int getPlotEnd2Id();

	void addPlotEnd2Id(int plotEnd2Id);

	void setPath(int index, short value);

	short getPath(int index);

	void setGoodsType(int index, short value);

	short getGoodsType(int index);

	void setIndex(int index, short value);

	short getIndex(int index);

	void setGoodsId(int index, int value);

	int getGoodsId(int index);

}