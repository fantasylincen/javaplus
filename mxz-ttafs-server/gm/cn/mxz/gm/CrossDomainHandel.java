package cn.mxz.gm;

import java.util.Map;

/**
 * flash安全沙箱文件
 * @author Administrator
 *
 */
public class CrossDomainHandel extends  AbstractHandler {

	@Override
	protected String doGet(Map<String, Object> parameters) {
		String content = "<?xml version=\"1.0\"?><cross-domain-policy><allow-access-from domain=\"*\" /></cross-domain-policy>";
		return content;
	}

	

}
