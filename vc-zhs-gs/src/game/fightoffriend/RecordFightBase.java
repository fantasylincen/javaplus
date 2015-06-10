package game.fightoffriend;

/**
 * 记录协助好友 数据
 * @author DXF
 */
public class RecordFightBase {


	// 用户唯一ID
	public int				m_uID;
	
	// 记录时间
	public int				m_recordTime;
	
	
	public RecordFightBase(int assistID, int currentTimeSecond) {
		m_uID 			= assistID;
		m_recordTime	= currentTimeSecond;
	}
	
	public String toString(){
		return "[" + m_uID + "," + m_recordTime + "]";
	}
	
}
