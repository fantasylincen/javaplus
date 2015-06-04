package cn.mxz.bossbattle.broadcast;

import java.util.Collections;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

/**
 * 保存广播信息，用于前端演示动画
 * @author Administrator
 *
 */

public class Broadcast {

	private final static int	MESSAGE_MAX_COUNT = 20;

	/**
	 * 玩家每一次攻击都在这里面加一条记录
	 */
	private final List<DamageMessageImpl> messageList = Lists.newArrayList();
	
	public void add( String nickName, int damage ){
		DamageMessageImpl msg = new DamageMessageImpl( nickName, damage );
		messageList.add( msg );
	}
	
	/**
	 * 获取时间戳之后的攻击记录，并且按照伤害值从大到小排序,最多返回20条
	 * @param timeStamp
	 * @return
	 */
	public List<DamageMessageImpl> get( final int timeStamp ){		
		
		List<DamageMessageImpl>  filterList = Lists.newArrayList(//因为list是按照timeStamp天然排序的，因此，这里有优化的余地
		        Collections2.filter( messageList, new Predicate<DamageMessageImpl>(){ 
		    @Override 
		    public boolean apply(DamageMessageImpl msg) { 
		    	return msg.getTimeStamp() > timeStamp;
		 }}));
		
		Collections.sort( filterList, DamageMessageImpl.damageComparator );
		
		if( filterList.size() > MESSAGE_MAX_COUNT ){
			filterList = filterList.subList( 0, MESSAGE_MAX_COUNT );
		}
		
		return filterList;
	}
	
	
}
