package game.growup.captain;

import config.skill.captain.CaptainSkillTempletCfg;
import define.DefaultCfg;

/**
 * 记录重置队长技能
 * @author DXF
 *
 */
public class CaptaninSkillBase {

	// 表格ID
	private int 			nID			= 0;
	
	// 记录时间
	private int 			recordTime 	= 0;
	
	// 使用次数
	private byte			usetime 	= 0;
	
	public int getNID(){
		return this.nID;
	}
	public void setNID( int nid ){
		if( CaptainSkillTempletCfg.getById(nid) == null )
			nid = 0;
		this.nID = nid;
	}
	
	public int getRecordTime(){
		return this.recordTime;
	}
	public void setRecordTime( int recordTime ){
		this.recordTime = recordTime;
	}
	
	public boolean isUse(){
		return usetime < DefaultCfg.DEVOUR_SKILL_TIME;
	}
	public void setUseTime( byte usetime ) {
		this.usetime = usetime;
	}
	public byte getUseTime() {
		return this.usetime;
	}
	public void use() {
		if( ++usetime > 10 ) usetime = 10;
	}
	public void init(){
		usetime	= 0;
	}
	
}
