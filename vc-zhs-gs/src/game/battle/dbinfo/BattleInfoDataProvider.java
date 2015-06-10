package game.battle.dbinfo;

import game.log.Logs;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import user.UserInfo;
import util.db.DatabaseUtil;

/**
 * 战斗信息 数据库 操作
 * @author DXF
 *
 */
public class BattleInfoDataProvider {

	private static BattleInfoDataProvider instance = new BattleInfoDataProvider();
	static  BattleInfoDataProvider getInstance(){
		return instance;
	}
	
	private BattleInfoDataProvider(){}

	/**
	 * 修改 
	 * @param user
	 * @param data
	 * @param uid
	 */
	public void upData( UserInfo user, BattleInfo data ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;				
		
		String sql = "update battle_info_base set " +
				"ectype_id=?," +
				"points_id=?," +
				"the_lv=?," +
				"data=?," +
				"timer=? " +
				"where u_id=? and uname=?";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setShort( i++, data.getEctypeId() );
			pst.setInt( i++, data.getMissionId() );
			pst.setByte( i++, data.getTheLv() );
			pst.setBytes( i++, data.getData().array() );
			pst.setInt( i++, data.getTimer() );
			pst.setInt( i++, data.getUID() );
			pst.setInt( i++, user.getUID() );

			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}

	/**
	 * 添加
	 * @param user
	 * @param data
	 */
	public int add(UserInfo user, BattleInfo data) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;			
		
		String sql = "insert into battle_info_base (u_id,uname,ectype_id,points_id,the_lv,data,timer) "
				+ "values (?,?,?,?,?,?,?)";
		
		int i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, data.getUID() );
			
			pst.setInt( i++, user.getUID() );
			
			pst.setShort( i++, data.getEctypeId() );
			
			pst.setInt( i++, data.getMissionId() );
			
			pst.setByte( i++, data.getTheLv() );
			
			pst.setBytes( i++, data.getData().array() );
			
			pst.setInt( i++, data.getTimer() );
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return 0;
	}


	/**
	 * 获取全部数据
	 * @param user
	 * @return
	 * @throws IOException 
	 */
	public List<BattleInfo> get( UserInfo user ){
		
		List<BattleInfo> battle	= new ArrayList<BattleInfo>();
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;			
		ResultSet res			= null;
		
		String sql = "SELECT * from battle_info_base where uname=?";
		
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, user.getUID() );
			
			res = pst.executeQuery();
				
			while( res.next() ){
				BattleInfo b = parsingBattle( res );
				battle.add( b );
			}
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return battle;
	}

	// 解析
	private BattleInfo parsingBattle(ResultSet res) throws SQLException,IOException {
		int uid 			= res.getInt( "u_id" );
		short eid			= res.getShort( "ectype_id" );
		int mid				= res.getInt( "points_id" );
		byte the			= res.getByte( "the_lv" );
		
//		ByteBuffer data 	= ByteBuffer.wrap( InputStreamTOByte( res.getBlob( "data" ).getBinaryStream() ) );
		ByteBuffer data 	= ByteBuffer.wrap( res.getBytes( "data" ) );
		int time			= res.getInt( "timer" );
		
		BattleInfo battle 	= new BattleInfo( eid, mid, the, data, time);
		battle.setUID( uid );
		
		return battle;
	}

	public static void main( String[] args ){
		
		long begin		= System.nanoTime();
		
		BattleInfo b 	= new BattleInfo( (short)11, 1, (byte)1, ByteBuffer.allocate( 100 ) , 545646 );
		BattleInfoDataProvider.getInstance().add( new UserInfo(null, 1011746), b );
		
		System.out.println("逻辑耗时：" + (System.nanoTime() - begin) / 1000000000f + "秒");
		
		begin		= System.nanoTime();
		b 	= new BattleInfo( (short)11, 1, (byte)1, ByteBuffer.allocate( 100 ) , 545646 );
		BattleInfoDataProvider.getInstance().add( new UserInfo(null, 1011746), b );
		
		System.out.println("逻辑耗时：" + (System.nanoTime() - begin) / 1000000000f + "秒");
	}

//	 /** 
//     * 将InputStream转换成byte数组 
//     * @param in InputStream 
//     * @return byte[] 
//     * @throws IOException 
//     */  
//    private byte[] InputStreamTOByte(InputStream in) throws IOException{
//          
//        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
//        byte[] data = new byte[4096];  
//        int count = -1;  
//        while((count = in.read(data,0,4096)) != -1)  
//            outStream.write(data, 0, count);  
//          
//        data = null;  
//        return outStream.toByteArray();  
//    }  
	
	
	
}
