package db.domain;public class BossMissionImpl implements BossMission , 
						MissionData
					, 
						UserItem
					{	@Key	private String uname;	private int missionId;	public BossMissionImpl() {	}	public BossMissionImpl(BossMission c) {		this.uname = c.getUname();		this.missionId = c.getMissionId();	}	@Override	public String tableName() {		return "boss_mission";	}	public void setUname(String uname) {		this.uname = uname;	}	public String getUname() {		return this.uname;	}public void addUname(String uname) {		this.uname += uname;}	public void setMissionId(int missionId) {		this.missionId = missionId;	}	public int getMissionId() {		return this.missionId;	}public void addMissionId(int missionId) {		this.missionId += missionId;}}