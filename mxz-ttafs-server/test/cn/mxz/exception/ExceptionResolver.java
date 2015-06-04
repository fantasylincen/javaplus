package cn.mxz.exception;

public class ExceptionResolver {

	public Result resolve(String string) {
		return null;
	}

	// 异常1: 1|错误消息号(整型)|字符串1,字符串2,字符串3.....
	// 例子:1|100002|周杰伦,蔡依林
	//
	// 异常2: 2|非法操作原因
	// 例子: 2|神将不存在
	//
	// 异常3: 3|xxx
	// 例子: 3|前后端协议版本不一致

	public static void main(String[] args) {
		Result e = new ExceptionResolver().resolve("1|100002|周杰伦,蔡依林");
//		ServerException e = new ExceptionResolver().resolve("2|神将不存在");
//		ServerException e = new ExceptionResolver().resolve("3|前后端协议版本不一致");
//		
//		if(e.hasError()) {
//			
//			e.hasReturnMessage
//		}
//		
		
	}

}
