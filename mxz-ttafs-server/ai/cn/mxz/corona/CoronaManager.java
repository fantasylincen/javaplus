//package cn.mxz.corona;
//
//import java.util.List;
//import java.util.concurrent.ConcurrentHashMap;
//
//import cn.mxz.DogzTempletConfig;
//import cn.mxz.FighterTempletConfig;
//import cn.mxz.base.servertask.ServerTaskImpl;
//import cn.mxz.base.servertask.ZeroClockEvent;
//import cn.mxz.bossbattle.Prize;
//import cn.mxz.city.CityFactory;
//import cn.mxz.event.Listener;
//import cn.mxz.event.ServerEvent;
//import cn.mxz.mission.old.PrizeImpl;
//
//
///**
// * 幸运转盘管理类
// * @author Administrator
// *
// */
//class CoronaManager implements Listener {
//
//
//	private static final int	MAX_MESSAGE_COUNT	= 10;
//
//	private ISubSystemMessages<CoronaMessage> messages = new SubSystemMessagesImpl<CoronaMessage>( MAX_MESSAGE_COUNT );
//
//	public List<CoronaMessage> getMessages() {
//		return messages.get();
//	}
//
//	private static CoronaManager instance = new CoronaManager();
//	static CoronaManager getInstance(){
//		return instance;
//	}
//	private CoronaManager(){
//
//		ServerTaskImpl.getInstance().addListener(this);
//	}
//
//
//
//	/**
//	 * 公共转盘
//	 */
//	private Corona publicCorona = new Corona();
//
//	private ConcurrentHashMap< String, UserCoronaData > userMap = new ConcurrentHashMap<String, UserCoronaData>();
//
//	/**
//	 * 12点清空所有玩家，并且重新生成转盘奖励
//	 */
//	@Override
//	public void onEvent(ServerEvent e) {
//
//		userMap.clear();
//		publicCorona = new Corona();
////		timeStamp = ((int)System.currentTimeMillis()) ;
//
//	}
//
//	Corona refreshCorona( String id ) {
//		return getUserData(id).refreshCorona();
//
//	}
//
//	/**
//	 * 玩家点击转盘
//	 * @param id
//	 * @param ts	时间戳
//	 * @return
//	 */
//	Prize run( String id, int type, int ts ) {
//
//		Prize p = getUserData(id).run( type, ts );
//		if( DogzTempletConfig.get( p.getId() ) != null ||
//				FighterTempletConfig.get( p.getId() ) != null ){//神将，神兽需要发广播
//			CoronaMessage m = new CoronaMessage( id, p );
//			messages.add(m);
//		}
//		if( type == 3 &&  p.getCount() != 1 ){//如果是双倍抽奖，奖品翻翻,为了方便客户端处理，数量还原
//			p = new PrizeImpl( p.getId(), p.getCount() / 2 );
//		}
//		return p;
//
//	}
//
//
//	/**
//	 * 查找user数据，如果没有则添加
//	 * @return
//	 */
//	UserCoronaData getUserData( String id ){
//
//
//		UserCoronaData coronaData = userMap.get( id );
//		if( coronaData == null ){
//			coronaData = new UserCoronaData(publicCorona, CityFactory.getCity( id ) );
//			UserCoronaData c = userMap.putIfAbsent( id, coronaData );
//			if( c != null ){
//				coronaData = c;
//			}
//		}
//		return coronaData;
//	}
//
//
//	public static void main(String[] args) {
//
//	}
//	@Override
//	public Class<?> getEventListendClass() {
//		return ZeroClockEvent.class;
//	}
//
//}
