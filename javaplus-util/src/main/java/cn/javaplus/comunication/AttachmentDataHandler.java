package cn.javaplus.comunication;

/**
 * 附加数据处理器
 * @author 	林岑
 * @since	2014年2月16日 23:12:58
 */
public interface AttachmentDataHandler {

	/**
	 * 附加响应
	 * @param user
	 * @param rq
	 */
	void attachmentResponse(ProtocolUser user, Request rq);

}
