package db.domain;public interface UserEquipmentTeam extends Domain , 
						UserItem
					{	String getUname();	void setUname(String uname);void addUname(String uname);	int getEquipmentTeamTypeId(int index);	void setEquipmentTeamTypeId(int index, int equipmentTeamTypeId1);}