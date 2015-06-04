package cn.mxz.fengshentai;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.KeyValueDataDao;
import mongo.gen.MongoGen.KeyValueDataDto;
import cn.mxz.BossConvertTemplet;
import cn.mxz.BossConvertTempletConfig;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.prizecenter.PropIdCheck;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

/**
 * 声望兑换道具的管理类
 * @author Administrator
 *
 */
class PropManager {
	private static final String	KEY	= "SHENGWANG_TO_PROP";

	private final String 		key;
	private final City			user;
	/**
	 * 道具购买情况
	 */
	private List<PropItem>	props;
	
	private Comparator<BossConvertTemplet> comparator = new Comparator<BossConvertTemplet>(){
        @Override
        public int compare( BossConvertTemplet o1, BossConvertTemplet o2 ){
            return o1.getOrder() - o2.getOrder();
        }
    };
    

	public PropManager( City user ) {
		key = user.getId() + KEY;
		this.user = user;
		loadHistory();
	}

	PropItem getPropById( int propId ){
		for (PropItem item : props) {
			if( item.getPropId() == propId ){
				return item;
			}
		}
		throw new SureIllegalOperationException( propId + "不在配置表中，无法兑换" );
	}
	
	public PropItem buy( int propId ){
		PropItem item = getPropById(propId);

		BossConvertTemplet templet = getTemplet(propId);
		if( user.getLevel() < templet.getLv() ){
			throw new SureIllegalOperationException( propId + "声望兑换道具等级不足,当前等级" + user.getLevel() + "需要等级 " + templet.getLv() );
		}
		if( item.getRemainSec() != 0 ){
			throw new SureIllegalOperationException( propId + "冷却时间未到,结束时间" + item.getColdDown() + "当前时间 " + System.currentTimeMillis() / 1000 );
		}


		int reduce = templet.getPrestige();
		user.getPlayer().reduce(PlayerProperty.REPUTATION, reduce);

		//user.getBagAuto().addProp( propId, templet.getConvertnumber() );
		String prize = propId + "," + templet.getConvertnumber();
		user.getPrizeSender1().send(prize);


		item.setBuyCount( item.getBuyCount() + 1 );
		if( item.getBuyCount() >= templet.getConvertMax() ){//达到最大购买次数了，这时就要考虑冷却时间
			int coldDown = (int) (System.currentTimeMillis() / 1000);
			coldDown += templet.getCoolTime();
			item.setColdDown( coldDown );
			
			item.setBuyCount( 0 );//开始冷却后，就清0购买次数
		}
		saveHistory();
		return item;
	}


	/**
	 * 先保存到档期表，完成之后再换
	 */
	void saveHistory( ) {
		KeyValueDataDto o = new KeyValueDataDto();
		KeyValueDataDao DAO = Daos.getKeyValueDataDao();

		o.setUname( key );
		o.setData(JSON.toJSONString( props ) );
		//System.out.println( "xxxxxxxxxxx "+JSON.toJSONString( props ));

		DAO.save(o);
	}

	/**
	 * 如果数据库不存在，则返回空
	 */
	void loadHistory() {
		KeyValueDataDto o = Daos.getKeyValueDataDao().get(key);
		if (o != null) {

			String jsonString = o.getData();
			if (jsonString != null) {
				props = JSON.parseArray( jsonString, PropItem.class );
				if (props == null) {
					System.err.println("声望兑换道具的内容" + jsonString);
				}
				
				Iterator<PropItem> iterator = props.iterator();
				while( iterator.hasNext() ){
					PropItem next = iterator.next();
					if( BossConvertTempletConfig.get( next.getPropId() ) == null ){
						iterator.remove();
						System.out.println( next.getPropId() + "不存在");
					}
				}
			}
			
			
		}
		else{
			init();
		}
		
		
	}


	/**
	 *
	 */
	private void init() {
		props = Lists.newArrayList();
		for( BossConvertTemplet t : sort() ){
			PropItem item = new PropItem( t.getTypeId() );
			props.add(item);
		}
	}
	
	/**
	 * 按照排序列进行排序
	 * @return
	 */
	private List<BossConvertTemplet> sort(){
		List<BossConvertTemplet> list = Ordering.from( comparator ).sortedCopy(BossConvertTempletConfig.getAll());
		
		return list;
		
	}

	private BossConvertTemplet getTemplet( int propId ) {
		BossConvertTemplet bossConvertTemplet = BossConvertTempletConfig.get(propId);
		if(bossConvertTemplet == null ){
			throw new SureIllegalOperationException("无法用声望兑换此道具" + propId );
		}
		return bossConvertTemplet;
	}

	public List<PropItem> getAllProp() {
//		for( PropItem item  : props){
//			System.out.println( item.getBuyCount() + ":" + item.getPropId() + PropIdCheck.getName( item.getPropId() ));
//		}
		return this.props;
	}
}
