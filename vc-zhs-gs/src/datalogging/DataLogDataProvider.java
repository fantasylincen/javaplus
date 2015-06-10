package datalogging;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


import user.UserInfo;
import util.SystemTimer;
import util.UtilBase;
import util.db.DatabaseUtil;


public class DataLogDataProvider {
	private static DataLogDataProvider instance = new DataLogDataProvider();
	public static  DataLogDataProvider getInstance(){ return instance; }
	private DataLogDataProvider(){ }
	
	private List<Data> list = new ArrayList<Data>();
	private List<ZhongBiao> zongBiao = new ArrayList<ZhongBiao>();

	public void init(){
		zongBiao = getZongBiao();
	}
	
	
	public void run(){
		if( list.isEmpty() ) return;
		Data d = list.remove(0);
		if( isHavePlayer( d.name ) ){
			update( d.name, d.type, d.number );
		}else{
			create( d.name, d.type, d.number );
		}
	}
	
	public void add( UserInfo user, String type, int number ){
		if( number == 0 ) return;
		Data d = new Data();
		d.name = user.getNickName();
		d.type = type;
		d.number = number;
		list.add( d );
	}
	
	private void create( String name, String type, int number) {
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		String sql 				= "insert into consumelog_1 (" + ConsumelogF.PLAYER_NICKNAME + "," + type + ") values (?,?)";
		try {
			pst = con.prepareStatement( sql );
			pst.setString( 1, name );
			pst.setInt( 2, number );
			pst.executeUpdate();
		} catch (SQLException e) { } finally { DatabaseUtil.close( null, pst, con ); }
	}
	private void update( String name, String type, int number ) {
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;
		String sql 				= "update consumelog_1 set "+type+"="+type+"+"+number+" where "+ConsumelogF.PLAYER_NICKNAME+"=?";
		try {
			pst = con.prepareStatement( sql );
			pst.setString( 1, name );
			pst.executeUpdate();
		} catch (SQLException e) { } finally { DatabaseUtil.close( null, pst, con ); }
	}
	private boolean isHavePlayer( String name ) {
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		String sql 				= "SELECT "+ConsumelogF.SINGLE_PUMP+" from consumelog_1 where "+ConsumelogF.PLAYER_NICKNAME+"=?";
		try {
			pst 	= con.prepareStatement( sql );
			pst.setString( 1, name );
			rs 		= pst.executeQuery();
			return rs.next();
		} catch (SQLException e) { } finally { DatabaseUtil.close( rs, pst, con ); }
		return false;
	}
	private List<TotalData> getToDayData() {
		List<TotalData> l 		= new ArrayList<TotalData>();
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		String sql 				= "SELECT * from consumelog_1";
		try {
			pst 	= con.prepareStatement( sql );
			rs 		= pst.executeQuery();
			while( rs.next() ){
				TotalData t 	= new TotalData();
				t.name 			= rs.getString( ConsumelogF.PLAYER_NICKNAME );
				for( int i = 0; i < ConsumelogF.MAX; i++ )
					t.number[i]		= rs.getInt( ConsumelogF.get( i ) );
				l.add( t );
			}
		} catch (SQLException e) { } finally { DatabaseUtil.close( rs, pst, con ); }
		return l;
	}
	
	public void handle() {
		
		// 获取昨天的记录数据
		List<TotalData> list 	= getToDayData();
		if( list.isEmpty() ) return;
		byte[] bytes 			= listToBytes( list );// 转换为bytes
		// 然后先入库 到总表
		String date 			= UtilBase.secondsToDate( SystemTimer.currentTimeSecond()-3600, "yyyy-MM-dd" );//这里减去1个小时  是因为现在已经是第二天了
		putZongBiao( date, bytes );
		
		// 然后处理7日的
		handleTrailing( 7 );
		// 然后处理30日的
		handleTrailing( 30 );
	}
	
	private void putZongBiao(String date, byte[] bytes) {
		ZhongBiao z = new ZhongBiao( date, bytes );
		zongBiao.add( z );
		if( zongBiao.size() > 30 )// 如果大于30条了 删除最前面一条
			zongBiao.remove(0);
		//记录数据库\
		createZhongBiao( date, bytes );
		// 入库后 就清空 今日记录的数据
		clearToDay( "consumelog_1" );
	}
	
	private void handleTrailing( int at ) {
		// 先清空
		clearToDay( "consumelog_" + at );
		Map<String, TotalData> list = zhengHe( at );
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		String sql_head			= "insert into consumelog_"+at+" values (";
		String sql 				= "";
		try {
			for( TotalData t : list.values() ){
				sql = sql_head + t.numberToString() + ")";
				pst = con.prepareStatement( sql );
				pst.executeUpdate();
			}
		} catch (SQLException e) { } finally { DatabaseUtil.close( null, pst, con ); }
	}
	
	private Map<String, TotalData> zhengHe( int at ) {
		int x = 0;
		Map<String, TotalData> map = new ConcurrentHashMap<String, TotalData>();
		for( int i = zongBiao.size()-1; i >= 0; i-- ){ // 从最近的开始找
			ZhongBiao z 		= zongBiao.get(i);
			List<TotalData> o 	= bytesToList( z.bytes );
			for( TotalData t : o ){
				TotalData xx 	= map.get( t.name );
				if( xx != null )
					xx.addNubmer( t.number );
				else
					map.put( t.name, t );
			}
			if( ++x >= at ) break;
		}
		return map;
	}
	
	private byte[] listToBytes( List<TotalData> list ) {
		ByteBuffer data = ByteBuffer.allocate( 102400 );
		data.putInt( list.size() );
		for( TotalData t : list ){
			UtilBase.encodeString( data, t.name );
			for( int i = 0; i < ConsumelogF.MAX; i++ )
				data.putInt( t.number[i] );
		}
		ByteBuffer result = ByteBuffer.allocate( data.position() );
		data.flip();
		result.put( data );
		result.flip();
		return result.array();
	}
	private List<TotalData> bytesToList( byte[] bytes ) {
		List<TotalData> l = new ArrayList<TotalData>();
		ByteBuffer data = ByteBuffer.wrap( bytes );
		int size = data.getInt();
		for( int i = 0; i < size; i++ ){
			TotalData t = new TotalData();
			t.name = UtilBase.decodeString( data );
			for( int j = 0; j < ConsumelogF.MAX; j++ )
				t.number[j] = data.getInt();
			l.add( t );
		}
		return l;
	}
	private void createZhongBiao( String date, byte[] bytes ) {
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;
		String sql 				= "insert into consumelog (date,bytes) values (?,?)";
		try {
			pst = con.prepareStatement( sql );
			pst.setString( 1, date );
			pst.setBytes( 2, bytes );
			pst.executeUpdate();
		} catch (SQLException e) { } finally { DatabaseUtil.close( null, pst, con ); }
	}
	private List<ZhongBiao> getZongBiao() {
		List<ZhongBiao> result 	= new ArrayList<ZhongBiao>();
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		String sql 				= "SELECT * from consumelog";
		long curTime			= SystemTimer.currentTimeMillis();
		try {
			pst 	= con.prepareStatement( sql );
			rs 		= pst.executeQuery();
			while( rs.next() ){
				String date 	= rs.getString( "date" );
				long time		= getCurTime( date ) + 2678400000l;//加上31天
				if( time <= curTime ) continue;
				byte[] bytes 	= rs.getBytes( "bytes" );
				ZhongBiao z 	= new ZhongBiao(date, bytes);
				result.add( z );
			}
			if( !zongBiao.isEmpty() )
				Collections.sort( zongBiao, comparator );
		} catch (SQLException e) { } finally { DatabaseUtil.close( rs, pst, con ); }
		return result;
	}
	/** 从小到大 **/
    private final Comparator<ZhongBiao> comparator = new Comparator<ZhongBiao>() {
		@Override
		public int compare(ZhongBiao o1, ZhongBiao o2) {
			int t1 = (int) (getCurTime( o1.date ) / 1000);
			int t2 = (int) (getCurTime( o2.date ) / 1000);
			return t1 - t2;
		}
	};
	private long getCurTime( String string ) {
		Calendar c = Calendar.getInstance();
		try {//2014-09-30 00:47:45 Etc/GMT
			c.setTime( new SimpleDateFormat("yyyy-MM-dd").parse(string) );
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return c.getTimeInMillis();
	}
	private void clearToDay( String tabe ) {
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;
		try {
			String sql 		= "TRUNCATE TABLE " + tabe;
			pst 			= con.prepareStatement( sql );
			pst.executeUpdate();
		} catch (SQLException e) { } finally { DatabaseUtil.close( null, pst, con ); }
	}
	
	public static void main( String[] args ) throws RowsExceededException, WriteException, IOException{
		
		 List<ZhongBiao> zb = instance.getZongBiao();
		 ZhongBiao z = null;
		 for( ZhongBiao zz : zb ){
			 if( zz.date.equals( "2014-10-19" ) ){
				 z = zz;
				 break;
			 }
		 }
		 List<TotalData> ls = instance.bytesToList( z.bytes );
		 
		 instance.produceExecl( "C:/Users/Administrator/Desktop/消费记录.xls", ls );
		 System.out.println( "完成" );
	}
	
	private void produceExecl( String path, List<TotalData> ls ) throws IOException, RowsExceededException, WriteException {
		WritableWorkbook book 	= Workbook.createWorkbook( new File( path ) ); 
		//生成名为“第一页”的工作表，参数0表示这是第一页 
		WritableSheet sheet		= book.createSheet( "10.19", 0 );
		
		// 添加 字段名
		Label label				= new Label( 0, 0, ConsumelogF.PLAYER_NICKNAME );
		sheet.addCell( label ); 
		for( int i = 0; i < ConsumelogF.MAX; i++ ){
			label				= new Label( i+1, 0, ConsumelogF.get(i) );
			sheet.addCell( label ); 
		}
		
		WritableCellFormat headerFormat = new WritableCellFormat(NumberFormats.TEXT);  
	    headerFormat.setAlignment( Alignment.CENTRE );  
		
		Number number	= null;

		int i 			= 1;
		for( TotalData x : ls ){
			
			label 		= new Label( 0, i, x.name, headerFormat );
			sheet.addCell( label );
			
			for( int o = 0; o < x.number.length; o++){
				number 		= new Number( o+1, i, x.number[o], headerFormat );
				sheet.addCell( number );
			}
			++i;
		}
		//写入数据并关闭文件 
		book.write(); 
		book.close(); 
	}
	
}

class Data{
	String name;
	String type;
	int number;
}

class TotalData{
	String name;
	int number[] = new int[ConsumelogF.MAX];
	public void addNubmer( int[] num ) {
		for( int i = 0; i < ConsumelogF.MAX; i++ )
			number[i] += num[i];
	}
	public String numberToString() {
		String str = "'"+name+"'";
		for( int i = 0; i < ConsumelogF.MAX; i++ ){
			str	   += ("," + number[i]);
		}
		return str.isEmpty() ? "'',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0" : str;
	}
}

class ZhongBiao{
	String date 	;
	byte[] bytes	;
	public ZhongBiao(String date, byte[] bytes) {
		this.date 	= date;
		this.bytes 	= bytes;
	}
}
