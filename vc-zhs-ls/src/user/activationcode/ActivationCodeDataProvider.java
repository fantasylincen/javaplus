package user.activationcode;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.XOExcel;
import util.db.DatabaseUtil;

/**
 * 激活码数据库
 * @author DXF
 */
public class ActivationCodeDataProvider {

	private final static Logger logger = LoggerFactory.getLogger(ActivationCodeDataProvider.class);
	private static ActivationCodeDataProvider instance = new ActivationCodeDataProvider();
	static  ActivationCodeDataProvider getInstance(){
		return instance;
	}
	private ActivationCodeDataProvider(){
	}

	public byte get( String ac ) {
		
		Connection 			con = DatabaseUtil.getConnection();
		PreparedStatement 	pst = null;
		ResultSet 			rs 	= null;
		
		try {
			String sql 		= "SELECT is_use from activation_code where code=?";
			pst 			= con.prepareStatement( sql );
			pst.setString( 1, ac );
			
			rs 				= pst.executeQuery();

			if( rs.next() ) {
				byte use	= rs.getByte("is_use");
				return (byte) (use == 0 ? 0 : 2);
			}else{// 没有该激活码
				return 1;
			}
			
		} catch (SQLException e) {
			logger.debug( e.getLocalizedMessage(), e );
			return 1;
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
	}
	
	public void update( String ac ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;								  
		String sql 				= "update activation_code set is_use=? where code=?";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			pst.setByte( i++, (byte) 1 );
			pst.setString( i++, ac );
			
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.debug( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	
	public void create( List<String> code ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;								  
		String sql 				= "insert into activation_code(code) values(?)";
		try {
			
			for( String s : code ){
				pst 				= con.prepareStatement( sql );
				pst.setString( 1, s );
				pst.executeUpdate();
			}
			
		} catch (SQLException e) {
			logger.debug( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	public List<ACData> get(){
		List<ACData> ac 		= new ArrayList<ACData>();
		
		Connection 			con = DatabaseUtil.getConnection();
		PreparedStatement 	pst = null;
		ResultSet 			rs 	= null;
		
		try {
			String sql 		= "SELECT * from activation_code";
			pst 			= con.prepareStatement( sql );
			rs 				= pst.executeQuery();

			while( rs.next() ) {
				String code = rs.getString( "code" );
				boolean use	= rs.getByte("is_use") == 1;
				ACData a	= new ACData( code, use );
				ac.add(a);
			}
			
		} catch (SQLException e) {
			logger.debug( e.getLocalizedMessage(), e );
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		
		return ac;
	}
	
	public static void main( String[] args ) throws RowsExceededException, WriteException, IOException{
		// ------ 提取激活码
		
		XOExcel e = new XOExcel( "账号激活码" );
		e.create( "激活码" );
		e.setLabelName( "激活码", "是否使用过" );
	
		List<ACData> list = ActivationCodeDataProvider.getInstance().get();
		for( int i = 0; i < list.size(); i++ ){
			e.setLabel( "激活码", list.get(i).code, i );
			e.setLabel( "是否使用过", list.get(i).isUse ? "是" : "否", i );
		}
		
		// 完成
		e.finish();
		
		System.out.println( "生成完成!" );
	}

}

class ACData{
	
	public ACData( String a, boolean b ){
		code 	= a;
		isUse 	= b;
	}
	public String  code;
	
	public boolean isUse;
}