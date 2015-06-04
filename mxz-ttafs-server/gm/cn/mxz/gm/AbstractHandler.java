package cn.mxz.gm;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Map;

import cn.mxz.base.world.NickManager;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class AbstractHandler implements HttpHandler {

	private static final String	SUCCESS_STRING	= "<Response><result>1</result></Response>";

	@Override
	public void handle(HttpExchange e) throws IOException {
//		System.out.println( e.getRequestURI() );

		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> id = (Map<String, Object>) e.getAttribute("parameters");

			String r = doGet(id);
			byte[] bytes = r.getBytes();

//		Debuger.debug("AbstractHandler.handle()" + bytes.length);

			
//			e.setAttribute("Access-Control-Allow-Origin", "*");
//			e.getResponseHeaders().set("Content-Type", "application/json");
			e.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
			e.sendResponseHeaders(HttpURLConnection.HTTP_OK, bytes.length);
//			e.sendResponseHeaders("Access-Control-Allow-Origin", "*");
//			response.setHeader("Access-Control-Allow-Origin", "*");
 
			OutputStream os = e.getResponseBody();
			try {
				os.write(bytes);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			os.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}


	/**
	 * 返回错误字符串
	 * @param code
	 * @return
	 */
	String responseErr(ErrorCode code ) {
		String ret = "<Response>";
		switch( code ){
		case USER_NOT_FOUND:
			ret += "-1201";

		}


		ret += "</Response>";
		return ret;
	}

	String responseSuccess(){
		return SUCCESS_STRING;
	}

	City getUser( Map<String, Object> parameters ){
		//String nick =
 		String name = (String) parameters.get( "user_id" );
 		
		if( name == null ){
			name = (String) parameters.get( "role_id" );
		}

		
//		NickManager nm = WorldFactory.getWorld().getNickManager();
//		name = new UserResetor().getNewestId( name );
		City user = CityFactory.getCity( name );
		if( user == null ){//尝试通过昵称查询
			World world = WorldFactory.getWorld();
			NickManager nickManager = world.getNickManager();
			Map<String, String> nickAll = nickManager.getNickAll();
			user = CityFactory.getCity( nickAll.get(name) ); 
			
		}
		return user;
	
	}

	protected abstract String doGet(Map<String, Object> parameters);

}