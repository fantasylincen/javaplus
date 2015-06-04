package cn.mxz.gm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.mxz.base.config.ServerConfig;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class GMServer extends Thread {

	public class ParameterFilter extends Filter {
		@Override
		public String description() {
			return "Parses the requested URI for parameters";
		}

		@Override
		public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
			parseGetParameters(exchange);
			parsePostParameters(exchange);
			chain.doFilter(exchange);
		}

		private void parseGetParameters(HttpExchange exchange) throws UnsupportedEncodingException {
			Map<String, Object> parameters = new HashMap<String, Object>();
			URI requestedUri = exchange.getRequestURI();
			String query = requestedUri.getRawQuery();
			parseQuery(query, parameters);
			exchange.setAttribute("parameters", parameters);
		}

		private void parsePostParameters(HttpExchange exchange) throws IOException {
			if ("post".equalsIgnoreCase(exchange.getRequestMethod())) {
				@SuppressWarnings("unchecked")
				Map<String, Object> parameters = (Map<String, Object>) exchange.getAttribute("parameters");
				InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
				BufferedReader br = new BufferedReader(isr);
				String query = br.readLine();
				parseQuery(query, parameters);
			}
		}

		@SuppressWarnings("unchecked")
		private void parseQuery(String query, Map<String, Object> parameters) throws UnsupportedEncodingException {
			if (query != null) {
				String pairs[] = query.split("[&]");
				for (String pair : pairs) {
					String param[] = pair.split("[=]");
					String key = null;
					String value = null;
					if (param.length > 0) {
						key = URLDecoder.decode(param[0], System.getProperty("file.encoding"));
					}
					if (param.length > 1) {
						value = URLDecoder.decode(param[1], System.getProperty("file.encoding"));
					}
					if (parameters.containsKey(key)) {
						Object obj = parameters.get(key);
						if (obj instanceof List<?>) {
							List<String> values = (List<String>) obj;
							values.add(value);
						} else if (obj instanceof String) {
							List<String> values = new ArrayList<String>();
							values.add((String) obj);
							values.add(value);
							parameters.put(key, values);
						}
					} else {
						parameters.put(key, value);
					}
				}
			}
		}
	}

	@Override
	public void run() {
		try {
			int backLog = 5;
			int port = ServerConfig.getConfig().getGameManagerPort();
			InetSocketAddress inetSock = new InetSocketAddress(port);
			HttpServer httpServer = HttpServer.create(inetSock, backLog);

			HttpContext c = httpServer.createContext("/s", new GMHandler());
			c.getFilters().add(new ParameterFilter());

			//查询用户信息
			//http://192.168.1.55:21524/QueryUserInfo?&user_id=lc101

			c = httpServer.createContext("/QueryUserInfo", new UserHandler( true ));
			c.getFilters().add(new ParameterFilter());

			c = httpServer.createContext("/QueryUserInfoBase", new UserHandler( false ));
			c.getFilters().add(new ParameterFilter());
			
			c = httpServer.createContext("/QueryUserInfoBase1", new UserHandler1( true ));
			c.getFilters().add(new ParameterFilter());



			//查询神将信息
			//http://192.168.1.55:21524/Hero?&user_id=lc101
			c = httpServer.createContext("/QueryHeroInfo", new HeroHandler());
			c.getFilters().add(new ParameterFilter());

			//封禁账号
			//http://192.168.1.55:21524/FreezeAccount?&user_id=lc101
			c = httpServer.createContext("/FreezeAccount", new FreezeAccountHandler());
			c.getFilters().add(new ParameterFilter());

			//解除封禁
			//http://192.168.1.55:21524/UnfreezeAccount?&user_id=lc101
			c = httpServer.createContext("/UnfreezeAccount", new UnfreezeAccountHandler());
			c.getFilters().add(new ParameterFilter());

			//踢人，用户名参数和文档不同，文档要求输入为整形，请修改为字符串型
			//http://192.168.1.55:21524/KickRole?&user_id=lc101
			c = httpServer.createContext("/KickRole", new KickRoleHandler());
			c.getFilters().add(new ParameterFilter());

			//金钱补偿,移动到奖励中心可否？
			c = httpServer.createContext("/ChangeMoney", new ChangePropHandler(/* PlayerProperty.GIFT_GOLD */));
			c.getFilters().add(new ParameterFilter());

			//发送横屏滚动公告
			//http://192.168.1.55:21524/PostBulletin?&content=这是一个横屏通告
			c = httpServer.createContext("/PostBulletin", new PostBulletinHandler( ));
			c.getFilters().add(new ParameterFilter());

			//奖励中心
			//http://192.168.1.55:21502/PrizeCenter?prize=110009,1000000&isAll=0&desc=这是一个测试&type=2&user_id=lc101&title=大礼包
			c = httpServer.createContext("/PrizeCenter", new PrizeCenterHandler( ));
			c.getFilters().add(new ParameterFilter());

			//日志信息，文档上写开始和起始时间可选填，但为了服务器压力考虑，要求必须填！！！！
			//http://192.168.1.55:21524/RoleMoneyOperate?role_id=lc101&item_type=1&start_time=2014-01-01 00:00:01&end_time=2014-06-01 00:00:01
			c = httpServer.createContext("/RoleMoneyOperate", new LogViewHandler( LogType.MONEY ));
			c.getFilters().add(new ParameterFilter());
			c = httpServer.createContext("/RoleItemOperate", new LogViewHandler( LogType.PROP ));
			c.getFilters().add(new ParameterFilter());

			//监控中心
			//http://192.168.1.55:21524/WatchInfo
			c = httpServer.createContext("/WatchInfo", new WatchInfoHandler( ));
			c.getFilters().add(new ParameterFilter());

			//角色背包查询，用户名参数和文档不同，文档要求输入为整形，请修改为字符串型
			//http://192.168.1.55:21524/RoleItem/&user_id=lc101
			c = httpServer.createContext("/RoleItem", new RoleItemHandler( ));
			c.getFilters().add(new ParameterFilter());
			
			//修改玩家vip等级
			c = httpServer.createContext("/ChangeVipLevel", new ChangeVipLevel( ));
			c.getFilters().add(new ParameterFilter());
		//
			//查询玩家物品使用和获取
			c = httpServer.createContext("/RoleActionOperate", new RoleActionOperate( ));
			c.getFilters().add(new ParameterFilter());
		//

			//某人收到了GM 的消息回复
			c = httpServer.createContext("/SendMail", new SendMailHandler( ));
			c.getFilters().add(new ParameterFilter());
			
			//查询物品名称所对应的id
			c = httpServer.createContext("/PropId2Name", new PropId2Name( ));
			c.getFilters().add(new ParameterFilter());
			
			//查询excel配置文件
			c = httpServer.createContext("/ExcelFile", new ExcelFile( ));
			c.getFilters().add(new ParameterFilter());
			
			//查询服务器的配置文件
			c = httpServer.createContext("/ServerInfo", new ServerInfo( ));
			c.getFilters().add(new ParameterFilter());
			
			//重新导表
			c = httpServer.createContext("/ReloadConfig", new ReloadConfig( ));
			c.getFilters().add(new ParameterFilter());
			
			//通过web管理工具给玩家发送物品
			c = httpServer.createContext("/ChangePlayerInfo", new ChangePlayerInfo( ));
			c.getFilters().add(new ParameterFilter());
			
			//杂项管理,例如增加一个神魔，增加一个云游仙人等
			c = httpServer.createContext("/ChangePlayerMisc", new ChangePlayerMisc( ));
			c.getFilters().add(new ParameterFilter());
			
//			c = httpServer.createContext("/WatchInfo", new WatchInfoHandler( ));
//			c.getFilters().add(new ParameterFilter());


			c = httpServer.createContext("/crossdomain.xml", new CrossDomainHandel( ));
			c.getFilters().add(new ParameterFilter());




			// httpServer.createContext("/test", new HandlerTestB());
			httpServer.setExecutor(null);
			httpServer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new GMServer().start();
	}
}

//class RequestTasks implements Runnable {
//
//	static int		processedNum	= 0;
//	HttpExchange	httpExchange;
//
//	RequestTasks(HttpExchange he) {
//		httpExchange = he;
//		processedNum++;
//	}
//
//	@Override
//	public void run() {
//		String responseString = "ProcessedNum:" + processedNum + "\n";
//		try {
//			httpExchange.sendResponseHeaders(200, responseString.length());
//			OutputStream os = httpExchange.getResponseBody();
//			os.write(responseString.getBytes());
//			os.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}



//}