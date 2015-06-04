package experiment;

import user.UserInfo;

/**
 * 测试传入的参数（一个类的引用）会否被在函数中改变
 * 
 * test函数中传入的user本身不会被user = u1;这句代码改变
 * @author admin
 * 2012-8-31 下午04:08:15
 */
public class ArgumentChange {
	
	void test( UserInfo user ){
		UserInfo u1 = new UserInfo( null, 1 );
		System.out.println( u1.getUID() );
		//user = u1;	避免findbug报警告，如果要查看本代码效果，则不能注释此行
	}

	public static void main(String[] args) {
		ArgumentChange ac = new ArgumentChange();
		UserInfo user = new UserInfo( null, 1 );
		
		System.out.println( "调用test函数之前：" + user.getUID() );
		ac.test( user );
		System.out.println( "调用test函数之后：" + user.getUID() );
	}
	
}
