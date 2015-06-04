package game.talent;

import config.talent.TalentValueTemplet;
import config.talent.TalentValueTempletCfg;
import util.SystemTimer;

/**
 * 天赋 单条属性数据
 * @author DXF
 *
 */
public class TalentBase {

	// 属性类型
	private TalentType 		type;

	// 等级
	private short 			level;
	
	// 记录时间
	private int				recordingTime	= 0;
	
	public TalentBase( TalentType type, short level ){
		
		this.type 	= type;
		this.level	= level;
	}
	
	public TalentType getType(){
		return this.type;
	}
	
	public void setLevel( short level ){
		this.level = level;
	}
	public short getLevel(){
		return this.level;
	}
	
	/**
	 * 获取天赋对应值
	 * @return
	 */
	public float getValue(){
		
		if( type == null )
			return 0;
		
		TalentValueTemplet value = TalentValueTempletCfg.getTempletById(level);
		
		if( value == null )
			return 0;
		
		return value.getValue( type );
	}
	
	public void setRecordingTime( int time ){
		this.recordingTime = time;
	}
	public int getTime() {
		return this.recordingTime;
	}
	/**
	 * 获取剩余时间 已经算出来的时间
	 * @return
	 */
	public int getRecordingTime(){
		
		if( recordingTime == 0 )
			return 0;
		
		// 算出用掉的时间
		int useupTime 			= SystemTimer.currentTimeSecond() - recordingTime;
		
		// 算出剩余时间
		TalentValueTemplet t 	= TalentValueTempletCfg.getTempletById( level + 1 );
		if( t == null )
			return 0;
		
		int recordTime			= t.getNeedTime() - useupTime;
		
		// 如果时间到了 那么就将记录时间 清空
		if( recordTime < 0 ){
//			recordingTime 	= 0;
			recordTime		= 0;
		}
				
		return recordTime;
	}

	/** 升级 */
	public void updataLevel() {
		++this.level;
	}

	/** 将属性编译成字符串 */
	public String getContent() {
		
		StringBuilder output = new StringBuilder();
		
		output.append( level ).append(",");
		output.append( recordingTime );
		
		return output.toString();
	}
	
	
	
}

