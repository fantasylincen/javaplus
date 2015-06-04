package http;

import java.io.IOException;
import java.util.Map;

import server.ServerInfo;
import server.ServerManager;
import server.event.GetUserdataEvent;
import user.UserManager;

import com.alibaba.fastjson.JSON;

import events.Event;

public class GetUserDataHandler extends AbstractAsyncHandler {

	
	public GetUserDataHandler() {
	}

	
	@Override
	protected void doGet(Map<String, Object> parameters, Response response) throws IOException {
//		System.out.println( "进入 GetUserDataHandler" );
		String aid 	= parameters.get("aid") + ""; // 帐号
		int did 	= new Integer(parameters.get("did") + ""); // 区ID
		
//		System.out.println( "aid=" + aid );
//		System.out.println( "did" + did );
		ServerInfo server = ServerManager.getInstance().get((byte)did);
		int uid		= UserManager.getInstance().getUIDtoAid( aid );
		
		if( server == null || uid == -1 ){
			String jsonString = JSON.toJSONString( new UserData() );
			response.sendString(jsonString);
			return;
		}
		
		GetUserdataEvent gue = (GetUserdataEvent)Event.GET_USERDATA.getEventInstance();
		gue.run( server.getCon(), uid, response );
	}

}
