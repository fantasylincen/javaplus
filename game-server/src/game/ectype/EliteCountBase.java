package game.ectype;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个副本对应信息
 * @author DXF
 *
 */
public class EliteCountBase {

	public short 				m_nMid;
	public List<EliteCount> 	m_nPList;

	public EliteCountBase(){
		m_nPList = new ArrayList<EliteCount>();
	}

	public EliteCount get( int pid ) {
		
		for( EliteCount e : m_nPList ){
			if( e.m_nPid == pid ){
				return e;
			}
		}
		return null;
	}
	
	public void init(){
		for( EliteCount e : m_nPList ){
			e.init();
		}
	}

	public void activityAdd() {
		for( EliteCount e : m_nPList ){
			e.activityAdd();
		}
	}
	
}
