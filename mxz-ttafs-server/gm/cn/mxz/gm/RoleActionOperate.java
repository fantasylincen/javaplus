package cn.mxz.gm;

import java.util.List;
import java.util.Map;

import mongo.gen.MongoGen.PropAddLogDto;
import mongo.gen.MongoGen.PropConsumeLogDto;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import cn.mxz.bag.PropLogs;
import cn.mxz.city.City;
import cn.mxz.prizecenter.PropIdCheck;

public class RoleActionOperate extends AbstractHandler {
	private static DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	private static final String DATA_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";
	
	@Override
	protected String doGet(Map<String, Object> parameters) {
		City user = getUser( parameters );
		if( user == null ){			
			return responseErr( ErrorCode.USER_NOT_FOUND ); 
		}
		StringBuilder sb = new StringBuilder( "<?xml version=\"1.0\" encoding=\"utf-8\"?><Response><result>1</result>");
		sb.append(buildLog( parameters, user ) );
		sb.append("</Response>");
		return sb.toString();
		
		
	}
	
	private String buildLog(Map<String, Object> parameters, City user ){
		DateTime start = DateTime.parse( parameters.get("start_time").toString(), format );
		DateTime end = DateTime.parse( parameters.get("end_time").toString(), format );
		
		int propId = 0;
		if(parameters.get("item_id") == null ){
			propId = -1;
		}
		else{
			
			propId = Integer.parseInt( (String) parameters.get("item_id") );
		}
		
		
		if( parameters.get("op_type") == null || ( (String) parameters.get("op_type")).equals("2")){
			List<PropAddLogDto> addLogs = PropLogs.getAddLogs(start.getMillis(), end.getMillis(), user.getId(), propId );
			return buildAddLog( addLogs, user);
		}
		else{
			List<PropConsumeLogDto> comsumeLogs = PropLogs.getComsumeLogs(start.getMillis(), end.getMillis(), user.getId(), propId );
			return buildConsumeLog( comsumeLogs, user);
		}
	}

	private String buildConsumeLog(List<PropConsumeLogDto> comsumeLogs, City user) {
		StringBuilder sb = new StringBuilder("<details_info>");
		sb.append("<info>用户名,时间,道具名(id),数量,类型</info>" );
		for (PropConsumeLogDto data : comsumeLogs) {
			sb.append("<info>");
			sb.append( user.getId() );
			sb.append(",");
			sb.append( new DateTime(data.getTime()).toString( DATA_FORMAT_STR ) );
			sb.append(",");
			sb.append( PropIdCheck.getName( data.getPropId() ) );
			sb.append( "(" + data.getPropId() + ")");
			sb.append(",");
			sb.append( data.getCount() );
			sb.append(",消耗");
			sb.append("</info>");
		}
		sb.append("</details_info>");
		return sb.toString();

	}

	private String buildAddLog(List<PropAddLogDto> addLogs, City user) {
		StringBuilder sb = new StringBuilder("<details_info>");
		sb.append("<info>用户名,时间,道具名(id),数量,类型</info>" );
		for (PropAddLogDto data : addLogs) {
			sb.append("<info>");
			sb.append( user.getId() );
			sb.append(",");
			sb.append( new DateTime(data.getTime()).toString( DATA_FORMAT_STR ) );
			sb.append(",");
			sb.append( PropIdCheck.getName( data.getPropId() ) );
			sb.append( "(" + data.getPropId() + ")");
			sb.append(",");
			sb.append( data.getCount() );
			sb.append(",获取");
			sb.append("</info>");
		}
		sb.append("</details_info>");
		return sb.toString();
	}

}
