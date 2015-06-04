//package cn.mxz.base.config;
//
//import cn.javaplus.file.IProperties;
//import cn.javaplus.util.Util;
//import cn.javaplus.web.WebContentFethcer;
//import cn.mxz.config.ConfigProperties;
//
//import com.alibaba.fastjson.JSON;
//import com.lemon.commons.database.ServerProperties;
//
//public class ConfigDAOImpl implements ConfigDAO {
//
//	private static final String	ERROR	= "error:Connection refused: connect";
//
//	/* (non-Javadoc)
//	 * @see cn.mxz.base.config.ConfigDAO#get(int)
//	 */
//	@Override
//	public ServerProperties get(int serverId) {
//
//		IProperties p = Util.Property.getProperties(ConfigProperties.PATH);
//		String ip = p.getProperty("LoginServerIp");
//
//		String rt = WebContentFethcer.get("utf8", "http://" + ip + ":" + p.getInt("loginServerAiConfigPort") + "/?serverId=" + serverId);
//
//		if (rt.equals(ERROR)) {
//			throw new RuntimeException("访问登陆服务器获取AI配置的时候, 服务器没有响应:" + ERROR);
//		}
//
//		try {
//			return JSON.parseObject(rt, ServerProperties.class);
//		} catch (Exception e) {
//			throw new RuntimeException("没有找到这个区：" + serverId + " " + rt);
//		}
//	}
//
//}
