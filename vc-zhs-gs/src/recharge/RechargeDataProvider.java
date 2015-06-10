package recharge;

import game.log.Logs;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

import org.apache.commons.codec.EncoderException;

import config.recharge.RechargeTemplet;
import config.recharge.RechargeTempletCfg;
import define.SystemCfg;

import user.UserInfo;
import user.UserManager;
import util.SystemTimer;
import util.UtilBase;
import util.db.DatabaseUtil;


/**
 * 充值数据库相关
 * @author DXF
 */
@SuppressWarnings("unused")
public class RechargeDataProvider {
	private static RechargeDataProvider instance = new RechargeDataProvider();
	public static  RechargeDataProvider getInstance(){
		return instance;
	}
	private RechargeDataProvider(){
	}
	
	
	public int add( UserInfo user, RechargeTemplet r ) {
		
		Connection con 	= DatabaseUtil.getConnection();
		Statement pst 	= null;			
		ResultSet rs 	= null;
		
		String sql = "insert into log_purcharse (uname,goods_id,n_id,pay_time) "
			+ "values (" + user.getUID() + ",'" + 
						r.getGoodsId() + "'," + 
						r.getId() + "," + 
						SystemTimer.currentTimeSecond() + ")";
		
		try {
			pst = con.createStatement();
			
			pst.executeUpdate( sql , Statement.RETURN_GENERATED_KEYS );
			
			rs 	= pst.getGeneratedKeys();
			
			if( rs.next() )
				return rs.getInt( 1 );
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return -1;
	}
	
	public int add( UserInfo user, int number ) {
		
		Connection con 	= DatabaseUtil.getConnection();
		Statement pst 	= null;			
		ResultSet rs 	= null;
		String goods_id	= "gm" + number;
		
		String sql = "insert into log_purcharse (uname,goods_id,n_id,server_state,money,pay_time) "
			+ "values (" + user.getUID() + ",'" + 
						goods_id + "'," + 
						-1 + "," + 
						3 + "," + 
						number/10 + "," + 
						SystemTimer.currentTimeSecond() + ")";
		
		try {
			pst = con.createStatement();
			
			pst.executeUpdate( sql , Statement.RETURN_GENERATED_KEYS );
			
			rs 	= pst.getGeneratedKeys();
			
			if( rs.next() )
				return rs.getInt( 1 );
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		// 生成 当月明细
		produceExcelToMonth();
				
		return -1;
	}
	
	public int create( UserInfo user, RechargeTemplet r ) {
		
		if( r.isRestriction() ){
			int result = get( user, r );
			if( result != -1 )
				return result ;
		}
		
		return add( user, r );
	}
	
	private int get( UserInfo user, RechargeTemplet r ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		
		String sql 				= "SELECT order_id from log_purcharse where uname=? and n_id=?";
		
		int	i = 1;
		try {
			pst 	= con.prepareStatement( sql );
			pst.setInt( i++, user.getUID() );
			pst.setInt( i++, r.getId() );
			rs 		= pst.executeQuery();

			if( rs.next() )
				return rs.getInt( "order_id" );
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return -1;
	}
	
	public boolean verify( UserInfo user, String transaction_id ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		String sql 				= "SELECT product_id from log_purcharse where uname=? and transaction_id=?";
		
		int	i = 1;
		try {
			pst 	= con.prepareStatement( sql );
			pst.setInt( i++, user.getUID() );
			pst.setString( i++, transaction_id );
			rs 		= pst.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return true;
	}
	
	public boolean updata( UserInfo user, String transaction_id, int quantity, String product_id, String purchase_date, RechargeTemplet r, String receipt) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;			
		String sql 				= "insert into log_purcharse (transaction_id,uname,product_id,money,quantity,purchase_date,receipt) " +
				"values (?,?,?,?,?,?,?)";
	
		int i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setString( i++, transaction_id );
			pst.setInt( i++, user.getUID() );
			pst.setString( i++, product_id );
			pst.setFloat( i++, r.getMoney() );
			pst.setInt( i++, quantity );
//			pst.setString( i++, purchase_date );
			pst.setString( i++, UtilBase.secondsToDateStr( SystemTimer.currentTimeSecond() ) );
			pst.setString( i++, receipt );
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
			return false;
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return true;
	}
	
	public boolean updata( UserInfo user, String transaction_id, int rmb, String receipt ) {
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;			
		String sql 				= "insert into log_purcharse (transaction_id,uname,product_id,money,quantity,purchase_date,receipt) " +
				"values (?,?,?,?,?,?,?)";
		int i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setString( i++, transaction_id );
			pst.setInt( i++, user.getUID() );
			pst.setString( i++, "lb" );
			pst.setFloat( i++, rmb );
			pst.setInt( i++, 1 );
			pst.setString( i++, UtilBase.secondsToDateStr( SystemTimer.currentTimeSecond() ) );
			pst.setString( i++, receipt );
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
			return false;
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return true;
	}
	
	/**
	 * 通用充值记录订单
	 * @param user
	 * @param transaction_id
	 * @param product_id
	 * @param money
	 * @param quantity
	 * @param receipt
	 * @return
	 */
	public boolean create( UserInfo user, String transaction_id, String product_id, float money, int quantity, String receipt ){
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;			
		String sql 				= "insert into log_purcharse (transaction_id,uname,product_id,money,quantity,purchase_date,receipt) " +
				"values (?,?,?,?,?,?,?)";
		int i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setString( i++, transaction_id );
			pst.setInt( i++, user.getUID() );
			pst.setString( i++, product_id );
			pst.setFloat( i++, money );
			pst.setInt( i++, quantity );
			pst.setString( i++, UtilBase.secondsToDateStr( SystemTimer.currentTimeSecond() ) );
			pst.setString( i++, receipt );
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
			return false;
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return true;
	}
	
	/**
	 * 验证   先验证 看数据库 有没有这条数据 
	 * @param user
	 * @param orderId
	 * @param reslut
	 * @return
	 * @throws EncoderException 
	 * @throws IOException 
	 */
	public int verify( UserInfo user, int orderId, String receipt ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		
		String sql 				= "SELECT server_state from log_purcharse where uname=? and order_id=?";
		
		int	i = 1;
		try {
			pst 	= con.prepareStatement( sql );
			pst.setInt( i++, user.getUID() );
			pst.setInt( i++, orderId );
			rs 		= pst.executeQuery();

			if( rs.next() ){
				byte serverState = rs.getByte( "server_state" );
				if( serverState != 0 ){
//					int nid = rs.getInt( "n_id" );
//					RechargeTemplet r 	= RechargeTempletCfg.get( (short) nid );
//					if( !r.isRestriction() )
						return -1;
				}
			}else{
				return -2;
			}
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		try {
			return RechargeManager.getInstance().verifyReceipt( receipt.getBytes(), true );
		} catch (IOException | EncoderException e) {
			e.printStackTrace();
			return -3;
		}
	}
	
	public int verify(UserInfo user, String goodsId, String receipt) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		
		String sql 				= "SELECT order_id,ipa_state from log_purcharse where uname=? and goods_id=?";
		
		int result				= 0;
		int	i = 1;
		try {
			pst 	= con.prepareStatement( sql );
			pst.setInt( i++, user.getUID() );
			pst.setString( i++, goodsId );
			rs 		= pst.executeQuery();

			if( rs.next() ){
				byte state 		= rs.getByte( "ipa_state" );
				if( state == 0 )
					result		= -1;
				else
					result		= rs.getInt( "order_id" );
			}else{
				result			= add( user, RechargeTempletCfg.get( goodsId ) );
			}
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		if( result != -1 ){
			int code			= -3;
			try {
				code			= RechargeManager.getInstance().verifyReceipt( receipt.getBytes(), true );
			} catch (IOException | EncoderException e) {
				e.printStackTrace();
			}
			if( code != 0 ) result = -1;
		}
		
		return result;
	}
	
	/**
	 * 充值验证数据库-91
	 * @param user
	 * @param orderId
	 * @return -1 没有该订单号  0，平台返回成功 1，前端已申请 2，订单已结束 3,还未验证
	 */
	public int[] verify_91(int orderId) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		String sql 				= "SELECT ipa_state,server_state,uname from log_purcharse where order_id=?";
		int result[]			= new int[2];
		result[1]				= 0;
		
		result[0]				= -1; // 数据库错误
		int	i = 1;
		try {
			pst 	= con.prepareStatement( sql );
			pst.setInt( i++, orderId );
			rs 		= pst.executeQuery();

			if( rs.next() ){
				byte ipa_state 		= rs.getByte( "ipa_state" );
				byte server_state	= rs.getByte( "server_state" );
				result[1]			= rs.getInt( "uname" );
				
//				System.out.println( result[1] );
				if( ipa_state != -1 && server_state != 0 ){
					result[0] = 2; // 订单已结束
				}else if( ipa_state == 0 && server_state == 0 )
					result[0] = 0; // 平台返回成功
				else if( server_state == 1 && ipa_state == -1 )
					result[0] = 1; // 前端已申请
				else if( ipa_state == -1 && server_state == 0 )
					result[0] = 3; // 还未验证
			}
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return result;
	}
	
	/**
	 * 充值验证数据库-同步
	 * @param user
	 * @param orderId
	 * @return -1 没有该订单号  0，平台返回成功 1，前端已申请 2，订单已结束 3,还未验证
	 */
	public int[] verify_tb( int orderId ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		String sql 				= "SELECT ipa_state,server_state,uname from log_purcharse where order_id=?";
		int result[]			= new int[2];
		result[1]				= 0;
		
		result[0]				= -1; // 数据库错误
		int	i = 1;
		try {
			pst 	= con.prepareStatement( sql );
			pst.setInt( i++, orderId );
			rs 		= pst.executeQuery();

			if( rs.next() ){
				byte ipa_state 		= rs.getByte( "ipa_state" );
				byte server_state	= rs.getByte( "server_state" );
				result[1]			= rs.getInt( "uname" );
				
//				System.out.println( result[1] );
				if( ipa_state != -1 && server_state != 0 ){
					result[0] = 2; // 订单已结束
				}else if( ipa_state == 0 && server_state == 0 )
					result[0] = 0; // 平台返回成功
				else if( server_state == 1 && ipa_state == -1 )
					result[0] = 1; // 前端已申请
				else if( ipa_state == -1 && server_state == 0 )
					result[0] = 3; // 还未验证
			}
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return result;
	}
	
	
	/**
	 * 91充值 前端请求更新
	 * @param orderId
	 */
	public void updata_91_c( int orderId, int result ) {
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		String str				= (result == 0 ? ", end_time = ? " : " ");
		String sql = "update log_purcharse set " +
				"server_state = ?" + str +
				"where order_id = ?";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setByte( i++, (byte) 1 );
			if( result == 0 )
				pst.setInt( i++, SystemTimer.currentTimeSecond() );
			pst.setInt( i++, orderId );
			
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	/**
	 * 91充值 平台返回 
	 * @param orderId
	 * @param result result == 1 || result == 3   【-1 没有该订单号  0，平台返回成功 1，前端已申请 2，订单已结束 3,还未验证】
	 * @param r
	 */
	public void updata_91_s( int orderId, int result, RechargeTemplet r ) {
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		String str				= (result == 1 ? ", end_time = ? " : " ");
		String sql = "update log_purcharse set " +
				"ipa_state = ?, n_id = ?, money = ?" + str +
				"where order_id = ?";
		
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, 0 );
			pst.setInt( i++, r.getId() );
			pst.setFloat( i++, r.getMoney() );
			if( result == 1 )
				pst.setInt( i++, SystemTimer.currentTimeSecond() );
			pst.setInt( i++, orderId );
			
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	/**
	 * 检查是否有该订单
	 * @param user
	 * @param orderId
	 * @param r
	 * @return 0.没有该订单  1.已经有订单了 3.数据库错误
	 */
	public int verify_google( UserInfo user, String orderId, RechargeTemplet r ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		String sql 				= "SELECT server_state from log_purcharse where uname=? and order_id=?";
		int	i = 1;
		try {
			pst 	= con.prepareStatement( sql );
			pst.setInt( i++, user.getUID() );
			pst.setString( i++, orderId );
			rs 		= pst.executeQuery();

			if( rs.next() ){
				return 1;
			}else{
				return 0;
			}
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		
		return 3;
	}
	/**
	 * google充值
	 * @param user 
	 * @param orderId
	 * @param r
	 */
	public void create( UserInfo user, String orderId, RechargeTemplet r, String productId ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;			
		String sql 				= "insert into log_purcharse (order_id,uname,goods_id,ipa_state,server_state,money,n_id,pay_time) values (?,?,?,?,?,?,?,?)";
	
		int i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setString( i++, orderId );
			pst.setInt( i++, user.getUID() );
			pst.setString( i++, productId );
			pst.setInt( i++, 0 );
			pst.setInt( i++, 1 );
			pst.setFloat( i++, r.getMoney() );
			pst.setInt( i++, r.getId() );
			pst.setInt( i++, SystemTimer.currentTimeSecond() );
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	public void updata( int orderId, int code, float money ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		String sql = "update log_purcharse set " +
				"server_state = ?, " +
				"ipa_state = ?, " +
				"money = ?, " +
				"end_time = ? " +
				"where order_id = ?";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setByte( i++, (byte) (code == 0 ? 1 : 2) );
			pst.setInt( i++, code );
			pst.setFloat( i++, money );
			pst.setInt( i++, SystemTimer.currentTimeSecond() );
			pst.setInt( i++, orderId );
			
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	public int[] get( int orderId ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		int result[]			= new int[2];
		result[0]				= 0;
		result[1]				= 0;
		
		String sql 				= "SELECT uname,n_id from log_purcharse where order_id=?";
		
		int	i = 1;
		try {
			pst 	= con.prepareStatement( sql );
			pst.setInt( i++, orderId );
			rs 		= pst.executeQuery();

			if( rs.next() ){
				result[0] 		= rs.getInt( "uname" );
				result[1] 		= rs.getInt( "n_id" );
			}
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return result;
	}
	
	public List<Structure> getTable(){
		
		List<Structure> xxx 			= new ArrayList<Structure>();
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		
		String sql 				= "SELECT * from log_purcharse";
		try {
			pst 	= con.prepareStatement( sql );
			rs 		= pst.executeQuery();

			while( rs.next() ){
				
				String[] str		= rs.getString( "receipt" ).split("&");
				if( !str[1].equals( "buy" ) ) continue;
				
				Structure s			= new Structure();
				s.transaction_id	= rs.getString( "transaction_id" );
				s.user_UID			= rs.getInt( "uname" );
				UserInfo u			= UserManager.getInstance().getByName( s.user_UID );
				s.user_name			= u != null ? u.getNickName() : "未知";
				s.product_id		= rs.getString( "product_id" );
				s.money				= rs.getFloat( "money" );
				s.money				= (float)((int)(s.money*100)/100f);
				s.quantity			= rs.getInt( "quantity" );
				s.purchase_date		= rs.getString( "purchase_date" );
				
				xxx.add( s );
			}
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return xxx;
	}
	
	private List<R> getTableToMonth(){
		
		List<R> xxx 			= new ArrayList<R>();
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		
		String sql 				= "SELECT * from log_purcharse where month(FROM_UNIXTIME( pay_time, '%Y%m%d' ))=month(now()) and where ipa_state = 0";
		
		try {
			pst 	= con.prepareStatement( sql );
			rs 		= pst.executeQuery();

			while( rs.next() ){
				
				R r				= new R();
				r.orderId		= rs.getInt( "order_id" );
				r.uId			= rs.getInt( "uname" );
				UserInfo u		= UserManager.getInstance().getByName( r.uId );
				r.name			= u != null ? u.getNickName() : "未知";
				r.nId			= rs.getInt( "n_id" );
				r.goodsId		= rs.getString( "goods_id" );
				r.IPAState		= rs.getInt( "ipa_state" );
				r.serverState	= rs.getByte( "server_state" );
				r.money			= rs.getInt( "money" );
				int t			= rs.getInt( "pay_time" );
				r.payTime		= t == 0 ? "" : UtilBase.secondsToDateStr( t );
				t				= rs.getInt( "end_time" );
				r.endTime		= t == 0 ? "" : UtilBase.secondsToDateStr( t );
				
				xxx.add( r );
			}
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return xxx;
	}

	private final String[] produceExcelName			= { "transaction_id", "player_name", "product_id", "money", "quantity", "purchase_date" };
	
	// 生成没月 充值明细
	public void produceExcelToMonth( ) {
		
//		String path = UtilBase.secondsToDate( SystemTimer.currentTimeSecond(), "yyMM-" );
		
		// 获取 充值列表
//		List<R> xxx 			= getTableToMonth();
//	
//		try {
//			produceToExcel( xxx, path );
//		} catch (RowsExceededException e) {
//			e.printStackTrace();
//		} catch (WriteException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	/**
	 * 生成Excel表格
	 * @return 
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public String produceExcel( String path ) throws IOException, RowsExceededException, WriteException{
		
		// 获取 充值列表
//		List<Structure> xxx 			= getTable();
		
//		return produceToExcel( xxx, path );
		float num	= getNum();
		return "合计：" + num;
	}
	
	public float getNum() {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		
		String sql 				= "SELECT sum(money) from log_purcharse";
		try {
			pst 	= con.prepareStatement( sql );
			rs 		= pst.executeQuery();

			if( rs.next() ) return rs.getFloat(1);
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return 0;
	}
	/**
	 * 得到充值明细列表
	 * @return
	 */
	public List<String> getRechargeList(){
		
		List<String> list 		= new ArrayList<String>();
//		
//		// 获取 充值列表
//		List<R> xxx 			= getTable();
//		
//		list.add( disconnect() );
//		list.add( "|                 以下为 " + SystemCfg.PLATFORM + " 平台 " + SystemCfg.GAME_DISTRICT + "区充值明细表" + "                  |"  );
//		list.add( disconnect() );
//		
//		// 合计
//		int totalMoney		= 0;
//		List<Integer> user	= new ArrayList<Integer>();
//		int i				= 0;
//		for( ; i < xxx.size(); i++ ){
//			R r				= xxx.get(i);
//			
//			totalMoney		+= r.money;
//			if( user.indexOf( r.uId ) == -1 )
//				user.add( r.uId );
//			
//			// 订单号	玩家名	充值钱数	   付费时间     是否到账
//			StringBuilder content = new StringBuilder();
//			content.append( "| " );
//			content.append( transformToStr_0( r.orderId )  ).append( "| " );
//			content.append( transformToStr_14( r.name ) ).append( "| " );
//			content.append( transformToStr_6( "$" + r.money + (r.nId == 1 ? "*" : "") ) ).append( "| " );
//			content.append( r.payTime + " " ).append( "| " );
//			content.append( r.serverState == 1 ? "已到账 " : "未到账 " ).append( "|" );
//			
//			
//			list.add( content.toString() );
//			list.add( disconnect() );
//		}
//		
//		list.add( "| 充值用户数量：" + transformToStr_0( user.size() ) + "              合计：" + transformToStr_0( totalMoney ) + "                |");
//		list.add( disconnect() );
		return list;
	}
	
	private String disconnect() {
		return "+--------+----------------+--------+---------------------+--------+";
	}
	private String transformToStr_0( int orderId ) {
		
		char[] c		= String.valueOf( orderId ).toCharArray();
		int cl			= 7 - c.length;
		
		String result 	= "";
		while( cl-- > 0 )
			result		+= " ";
		
		return orderId + result + "";
	}
	private static String transformToStr_6( String name ) {
		
		int l 			= name.getBytes().length - name.length();
		int cl			= name.length() + (l == 0 ? 0 : l/2);
		cl				= 6 - cl;
		
		String result 	= "";
		while( cl-- > 0 )
			result		+= " ";
		
		return name + result + " ";
	}
	private static String transformToStr_14( String name ) {
		
		int l 			= name.getBytes().length - name.length();
		int cl			= name.length() + (l == 0 ? 0 : l/2);
		cl				= 14 - cl;
		
		String result 	= "";
		while( cl-- > 0 )
			result		+= " ";
		
		return name + result + " ";
	}
	
	
	private String produceToExcel( List<Structure> xxx, String path ) throws IOException, RowsExceededException, WriteException{
		
		WritableWorkbook book 	= Workbook.createWorkbook( new File( path + "-" + SystemCfg.GAME_DISTRICT + "-recharge.xls") ); 
		//生成名为“第一页”的工作表，参数0表示这是第一页 
		WritableSheet sheet		= book.createSheet( "recharge", 0 );
		
		// 添加 字段名
		for( int i = 0; i < produceExcelName.length; i++ ){
			Label label				= new Label( i, 0, produceExcelName[i] );
			sheet.addCell( label ); 
		}
		
		WritableCellFormat headerFormat = new WritableCellFormat(NumberFormats.TEXT);  
//	            //添加字体设置  
//	            headerFormat.setFont(font);  
//	            //设置单元格背景色：表头为黄色  
//	            headerFormat.setBackground(Colour.YELLOW);  
//	            //设置表头表格边框样式  
//	            //整个表格线为粗线、黑色  
//	            headerFormat.setBorder(Border.ALL, BorderLineStyle.THICK, Colour.BLACK);  
		//表头内容水平居中显示  
	    headerFormat.setAlignment( Alignment.CENTRE );  
		
		Number number	= null;
		Label label		= null;
		float p_sum		= 0;
//		float g_sum		= 0;
		List<Integer> user	= new ArrayList<Integer>();
		int i			= 0;
		for( ; i < xxx.size(); i++ ){
			Structure r	= xxx.get(i);
			
			//{ "订单号", "玩家名", "充值钱数", "付费时间", "是否到账" };
			label 		= new Label( 0, i + 1, r.transaction_id, headerFormat );
			sheet.addCell( label );
			
			label		= new Label( 1, i + 1, r.user_name, headerFormat );
			sheet.addCell( label ); 
			
			label		= new Label( 2, i + 1, r.product_id, headerFormat );
			sheet.addCell( label ); 
			
			number 		= new Number( 3, i + 1, r.money, headerFormat );
			sheet.addCell( number );
			
			number 		= new Number( 4, i + 1, r.quantity, headerFormat );
			sheet.addCell( number );
			
			label		= new Label( 5, i + 1, r.purchase_date, headerFormat );
			sheet.addCell( label ); 
			
//			if( r.serverState == 3 )
//				g_sum			+= r.money;
			p_sum		+= r.money;
			
			if( user.indexOf( r.user_UID ) == -1 )
				user.add( r.user_UID );
			
//			String[] content	= r.get();
//			for( int x = 0; x < content.length; x++ ){
//				Label label		= new Label( x, i + 1, content[x] );
//				sheet.addCell( label ); 
//			}
		}
		p_sum 		= (float)((int)(p_sum*100)/100f);
		
		label 		= new Label( 6, i++ + 1, "total：" + p_sum , headerFormat );
		sheet.addCell( label );
		label 		= new Label( 6, i++ + 1, "player_number：" + user.size() , headerFormat );
		sheet.addCell( label );
		
		//写入数据并关闭文件 
		book.write(); 
		book.close(); 
		
		
		return "充值用户数量：" + user.size() + "              合计：" + p_sum;
	}
	
	/**
	 * 漏单处理
	 * @param user
	 * @return 
	 */
	public int[] lospHandle( UserInfo user ) {
		
		int result[]			= new int[]{0,0,0,0};
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		List<Integer> orderlist	= new ArrayList<Integer>();
		
		String sql 				= "SELECT n_id,order_id from log_purcharse where uname=? and ipa_state=0 and server_state=0";
		
		try {
			pst 	= con.prepareStatement( sql );
			pst.setInt( 1, user.getUID() );
			rs 		= pst.executeQuery();

			while( rs.next() ){
				
				int id				= rs.getInt( "n_id" );
				int order_id		= rs.getInt( "order_id" );
				RechargeTemplet r 	= RechargeTempletCfg.get( (short) id );
				if( r != null ){
					result[0]		+= r.getGold();
					if( r.isMonthCard() )
						++result[r.getId()%100];
					orderlist.add( order_id );
				}
			}
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
			if( !orderlist.isEmpty() )
				lospHandleUpdate( orderlist );
		}
		
		return result;
	}
	private void lospHandleUpdate( List<Integer> orderlist ) {
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		// UPDATE log_purcharse SET server_state = CASE order_id WHEN 29 THEN 1 WHEN 30 THEN 1 WHEN 31 THEN 1 END WHERE order_id IN ( 29, 30, 31 );
		String value			= "";
		String time				= "";
		String idstr			= "";
		for( Integer i : orderlist ){
			value				+= "WHEN " + i + " THEN " + 1 + " ";
			time				+= "WHEN " + i + " THEN " + SystemTimer.currentTimeSecond() + " ";
			idstr				+= !idstr.isEmpty() ? "," : "";
			idstr				+= i;
		}
		
		String sql 				= "UPDATE log_purcharse SET " + 
									"server_state = CASE order_id " + value + "END, " +
									"end_time = CASE order_id " + time + "END " +
									"WHERE order_id IN (" + idstr + ")";
		
		try {
			pst = con.prepareStatement( sql );
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	
	public static void main( String[] args ) throws IOException, RowsExceededException, WriteException{
		
		// --------------生成Excel表格------------------
		
		getInstance().produceExcel( "E:/Desktop/91" );
		
//		getInstance().produceExcelToMonth();
	
//		List<Integer> orderlist = new ArrayList<Integer>();
//		orderlist.add( 27 );
//		orderlist.add( 28 );
//		orderlist.add( 36 );
//		
//		instance.lospHandleUpdate( orderlist );
//		

		
//		FileHandle.creatFileToTxt( "D:/ssss" );
//		
//		List<String> list = getInstance().getRechargeList();
//		FileHandle.writeFile( list );
		
		System.out.println( "完成" );
	}
	

	


	
	
	
	
	
}


class R{
	
	public int 		uId;

	public int 		orderId ;
	
	public String 	name;
	
	public int  	nId;
	
	public String	goodsId;
	
	public int 		IPAState;
	
	public byte		serverState;
	
	public float	money;
	
	public String	payTime;
	
	public String	endTime;
	
	public String[] get(){
		return new String[]{ orderId+"", name, goodsId, IPAState+"", serverState+"", money+"", payTime, endTime };
	}
}


class Structure{
	public String 	transaction_id;
	public int		user_UID;
	public String	user_name;
	public String 	product_id;
	public float	money;
	public int		quantity;
	public String	purchase_date;
}
