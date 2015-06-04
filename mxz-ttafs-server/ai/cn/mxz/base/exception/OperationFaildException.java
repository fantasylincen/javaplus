package cn.mxz.base.exception;

import cn.javaplus.message.Message;
import cn.mxz.util.Factory;




/**
 * 操作失败异常
 * @author 	林岑
 * @since	2013年5月27日 17:41:30
 *
 */
public class OperationFaildException extends ClientException {

	private static final long serialVersionUID = 2973739063964051504L;

	private Object[] info;

	private int code;

	/**
	 * @param messageCode	消息号
	 */
	public OperationFaildException(int messageCode, Object... info) {
		this.code = messageCode;
		this.info = info;

		if(info == null) {
			this.info = new Object[0];
		}
	}

	public Object[] getInfos() {
		return info;
	}


	public int getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return "1|" + code + "|" + build(info);
	}

	private String build(Object[] o) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < o.length; i++) {
			Object object = o[i];
			sb.append(object + "");
			boolean isEnd = i >= o.length - 1;
			
			if(!isEnd) {
				sb.append(",");
			}
		}
		return sb + "";
	}

	@Override
	public String toString() {
		final Message m = (Message) Factory.get("textMessage");
		m.setCode(code);
		m.setInfo(info);
		return m + "";
//		return getMessage();
	}
}
