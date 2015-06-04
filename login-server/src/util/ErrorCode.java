package util;

/**
 * 错误码 管理类
 * @author DXF
 */
public enum ErrorCode {
	
	SUCCESS("操作成功"), 
	/*****************************************玩家相关操作*********************************/
	USER_HAS_LOGIN( "用户已经登陆了" ),	
	USER_NOT_LOGIN( "用户还没登陆" ),	
	USER_DUPLICATE_NAME( "用户名重复" ),
	USER_NOT_FOUND( "用户尚未注册" ),	
	USER_ACCOUNT_ERROR( "账号错误" ),
	USER_PASSWORD_ERROR( "密码错误" ),	
	USER_HAS_BAN( "用户被ban，无法登陆" ),
	USER_INVALID_LOGIN("登陆信息有误"),
	
	/*****************************************通信包相关操作********************************/
	PACKAGE_NOT_FOUND( "通信包未找到" ),
	PACKAGE_SAFE_CHECK_FAIL( "包的安全检测失败，用户短时间内发送大量相同的数据包到服务器端" );
	
	
	private String desc;
	ErrorCode( String desc ) {
		this.desc = desc;
	}
	
	public static void main(String[] args) {
		for( ErrorCode code : values() ){
			System.out.println( "[" + code.ordinal() + "]\t[" + code + "]\t(" + code.desc + ")" );
		}
	}
}
