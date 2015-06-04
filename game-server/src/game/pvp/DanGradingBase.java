package game.pvp;

import java.util.ArrayList;
import java.util.List;

import define.DefaultCfg;

/**
 * 段位基础类
 * @author DXF
 */
public class DanGradingBase {

	// 段位
	private DanGrad		_nDanGrad;
	
	// 分数
	private int			_nGrade				= 0;
	
	// 战绩
	private int			_nStandings;
	
	// 最大连杀
	private short 		_nMaxVictory		= 0;
	private short 		_nRecordVictory		= 0;
	
	// 最大连跪
	private short		_nMaxFailure		= 0;
	private short 		_nRecordFailure		= 0;
	
	// 今日剩余次数
	private byte		_nTodayCount		= 0;
	
	// 已购买次数
	private byte  		_nBuyCount			= 0;
	
	// 记录时间 这个主要用来 看是不是过天 刷新时间用的
	private int 		_nRecordTime		= 0;
	
	// 今日匹配次数
	private byte		_nTodayMateCount	= 0;
	private List<Byte> 	_nInorderToobtain	= new ArrayList<Byte>();
	
	// 是否领取了 每日福利
	private boolean 	_nIsGetWelfare		= false;
	
	// 一共匹配次数
	private int 		_nMateCount			= 0;
	
	// 今日被抢夺次数
	private byte		_nByLootCount		= 0;
	
	public DanGradingBase(){ }
	
	public void init( byte viplv ){
		_nGrade			= 200;
		_nTodayCount	= DefaultCfg.PVP_MATE_TODAY_COUNT;
		_nByLootCount	= DefaultCfg.PVP_MATE_BY_LOOTCOUNT;
		_nDanGrad		= DanGrad.HEROIC_BRASS;
	}
	
	public void danGrad( DanGrad danGrad ){
		this._nDanGrad = danGrad;
	}
	public DanGrad danGrad(){
		return this._nDanGrad;
	}
	
	public void grade( int grade ){
		this._nGrade = grade;
	}
	public int grade(){
		return this._nGrade;
	}
	public void addGrade( int grade ) {
		this._nGrade += grade;
	}
	
	public void standings( int standings ){
		this._nStandings = standings;
	}
	public int standings(){
		return this._nStandings;
	}
	
	public void maxVictory( short maxVictory ){
		this._nMaxVictory = maxVictory;
	}
	public short maxVictory(){
		return this._nMaxVictory;
	}
	
	public void recordVictory( short maxVictory ){
		this._nRecordVictory = maxVictory;
	}
	public short recordVictory(){
		return this._nRecordVictory;
	}
	
	public void maxFailure( short maxFailure ){
		this._nMaxFailure = maxFailure;
	}
	public short maxFailure(){
		return this._nMaxFailure;
	}
	
	public void recordFailure( short maxFailure ){
		this._nRecordFailure = maxFailure;
	}
	public short recordFailure(){
		return this._nRecordFailure;
	}
	
	public void todayCount( byte todayCount ){
		this._nTodayCount = todayCount;
	}
	public byte todayCount(){
		return this._nTodayCount;
	}
	
	public void buyCount( byte buyCount ){
		this._nBuyCount = buyCount;
	}
	public byte buyCount(){
		return this._nBuyCount;
	}

	public void recordTime( int recordTime ){
		this._nRecordTime = recordTime;
	}
	public int recordTime(){
		return this._nRecordTime;
	}
	
	public void mateCount( int mateCount ) {
		this._nMateCount = mateCount;
	}
	public int mateCount(){
		return this._nMateCount;
	}
	
	public void isGetWelfare( boolean _isGetWelfare ){
		_nIsGetWelfare = _isGetWelfare;
	}
	public boolean isGetWelfare(){
		return _nIsGetWelfare;
	}
	
	public void todayMateCount( byte _todayMateCount ){
		this._nTodayMateCount = _todayMateCount;
	}
	public byte todayMateCount(){
		return _nTodayMateCount;
	}
	
	public void byLootCount( byte byLootCount ) {
		_nByLootCount = byLootCount;
	}
	public byte byLootCount() {
		return _nByLootCount;
	}
	
	public List<Byte> inorderToobtain(){
		return _nInorderToobtain;
	}
	public boolean isCanGetTheRewards( byte id ) {
		return _nInorderToobtain.indexOf(id) != -1;
	}
	
	public void claerAward() {
		_nInorderToobtain.clear();
	}
	public void putTheRewards( byte id ){
		_nInorderToobtain.add( id );
	}
	public String getAward() {
		StringBuilder content = new StringBuilder();
		content.append( _nTodayMateCount ).append( ":" );
		for( byte i : _nInorderToobtain )
			content.append( i ).append( "," );
		
		return content.toString();
	}
	public void setAward( String content ){
		
		if( content.isEmpty() )
		{
			_nTodayMateCount	= 0;
			return;
		}	
		
		String[] x 			= content.split( ":" );
		_nTodayMateCount 	= Byte.parseByte( x[0] );
		
		if( x.length != 2 ) return;
		
		String[] list		= x[1].split( "," );
		for( String s : list )
			putTheRewards( Byte.parseByte( s ) );
	}
	
	/**
	 * 输赢 设置数据
	 * @param isWin
	 * @param type 
	 */
	public void setData( boolean isWin, MatchingType type ) {
		
		// 今日剩余次数 减减
		if( --_nTodayCount < 0 ) 
			_nTodayCount = 0;
		
		// 一共匹配次数
		++_nMateCount;
		
		// 今日匹配次数 这里只管加 在刷新那初始为0 
		++_nTodayMateCount;
		
		// 先加上分数
		this._nGrade += isWin ? DefaultCfg.PVP_MATE_WIN_GRADE[type.toNumber()] : DefaultCfg.PVP_MATE_DEFEAT_GRADE[type.toNumber()];
//		this._nGrade += 10000;
		
		if( isWin ){ // 如果赢 那么 连胜加1 连跪全部归0   战绩不管怎样 加1
			this._nRecordVictory 	+= 1;
			this._nRecordFailure 	= 0;
			this._nStandings		+= 1;
			
		}else{ // 如果输 那么连跪加1 连胜全部归0 
			this._nRecordVictory 	= 0;
			this._nRecordFailure 	+= 1;
		}
		
		// 如果 记录 大于当前值 那么就赋予它  只有超过2次 才算 连胜或连跪
		if( _nRecordVictory > _nMaxVictory && _nRecordVictory >= 2 )
			_nMaxVictory = _nRecordVictory;
		if( _nRecordFailure > _nMaxFailure && _nRecordFailure >= 2  )
			_nMaxFailure = _nRecordFailure;
	}

	/**
	 * 是否可以抢夺
	 * @return
	 */
	public boolean isLoot(){
		return _nByLootCount > 0;
	}
	/**
	 * 执行抢夺
	 */
	public void executeLoot(){
		--_nByLootCount;
		if( _nByLootCount < 0 ) _nByLootCount = 0;
	}
	
	public void initToDay() {
		todayCount( DefaultCfg.PVP_MATE_TODAY_COUNT ) ;
		buyCount( (byte)0 ); // 这里到时候要加 充值加成
		todayMateCount( (byte)0 ); // 初始每日匹配次数
		claerAward(); // 初始每日匹配奖励数据
		isGetWelfare( false ) ; //初始每日领取福利
		_nByLootCount	= DefaultCfg.PVP_MATE_BY_LOOTCOUNT;// 初始每日被抢夺次数
	}

	/**
	 * 购买匹配次数
	 * @param changeValue 
	 */
	public void reliefBuyCount() {
		++_nTodayCount;
		++_nBuyCount;
	}

	
}
