package db.domain;public interface BossMission extends Domain , 
						MissionData
					, 
						UserItem
					{	String getUname();	void setUname(String uname);void addUname(String uname);	int getMissionId();	void setMissionId(int missionId);void addMissionId(int missionId);}