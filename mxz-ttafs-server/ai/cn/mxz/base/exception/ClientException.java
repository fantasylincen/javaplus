package cn.mxz.base.exception;

/**
 * 
 * 客户端异常
 * @author 	林岑
 * @since	2013年5月27日 17:19:00
 *
 */
public class ClientException extends RuntimeException {

	private static final long serialVersionUID = -6838342462191492764L;

	protected ClientException() {
	}

	protected ClientException(String m) {
		super(m);
	}
}
//
//我这边总共有3种异常:
//
//1.客户端正常操作情况下, 由于某种原因操作失败
//2.客户端发送恶意包, 进行非法操作
//3.前后端协议版本不一致造成的异常
//
//
//根据这3种异常:
//异常1: 1|错误消息号(整型)|字符串1,字符串2,字符串3.....   
//     例子:1|100002|周杰伦,蔡依林
//
//异常2: 2|非法操作原因
//     例子: 2|神将不存在
//
//异常3: 3|xxx
//     例子: 3|前后端协议版本不一致
//
//
//是否成功我就不用给你发了, 如果error为空串, 那就表示成功了
