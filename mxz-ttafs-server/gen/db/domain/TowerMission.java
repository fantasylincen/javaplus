package db.domain;public interface TowerMission extends Domain , 
				MissionData
			, 
				UserItem
			{	String getUname();	void setUname(String uname);void addUname(String uname);	int getMissionId();	void setMissionId(int missionId);void addMissionId(int missionId);}