package user.activationcode;

import java.util.ArrayList;
import java.util.List;

import util.RandomUtil;


/**
 * 激活码管理
 * @author DXF
 */
public class ActivationCodeManager {
	
	private static final ActivationCodeManager instance = new ActivationCodeManager();
	public static final ActivationCodeManager getInstance(){
		return instance;
	}
	private ActivationCodeManager() {	}
	
	
	private final ActivationCodeDataProvider 		db 		= ActivationCodeDataProvider.getInstance();
	
	/**
	 * 激活账号
	 * @param ac
	 * @return
	 */
	public byte get( String ac ) {
		
		byte code = db.get( ac );
		
		if( code == 0 )
			db.update( ac );
		
		return code;
	}	
	
	/**
	 * 创建激活码
	 * @param count
	 */
	public void create( int count ){
		
		// 生产激活码
		db.create( Hex.getVerificationCode(count) );
	}
	
	
	public static void main( String[] args ){
		
		// -----------创建激活码
		
		// 个数
		int count	= 1000;
		
		ActivationCodeManager.getInstance().create(count);
		
		System.out.println( "激活码生成成功，当前生成个数=" + count );
	}
	
	
}

/**
 * 生成由 小写英文加数字的 验证码
 * @author DXF
 */
class Hex{
	
    static private final int  LOOKUPLENGTH 			= 36;
    static final private char [] lookUpHexAlphabet 	= new char[LOOKUPLENGTH];

    // 密码长度
    static private final byte PASSWORD_LENGTH		= 8;
    
    static {
        int i = 0;
        for( i = 0; i < 10; i++ ) {
            lookUpHexAlphabet[i] = (char)('0'+i);
        }
        for( char a = 'a'; a <= 'z'; a++ ) {
            lookUpHexAlphabet[i++] = (char)( a );
        }
    }
    
	private static String generateAccount(){
		
		String password	= "";
		
		int index 		= 0;
		int length		= 0;
		
		do{
			index 		= RandomUtil.getRandomInt( 0, LOOKUPLENGTH -1 );
			password	+= lookUpHexAlphabet[ index ];
			
		} while( ++length < PASSWORD_LENGTH );
		
		return password;
	}
	
	/**
	 * 获取验证码 
	 * @param num 个数
	 * @return
	 */
	public static List<String> getVerificationCode( int num ){
		
		List<String> list 	= new ArrayList<String>();
		int i				= 0;
		
		do{
			String code 	= generateAccount();
			if( list.indexOf(code) != -1 ) {
				System.out.println( "出现重复  code=" + code );
				continue;
			}
			
			list.add( code );
			++i;
			
		}while( i < num );
			
		return list;
	}
}