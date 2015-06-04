package cn.mxz.gm;

import java.util.List;
import java.util.Map;

import cn.mxz.StuffTempletConfig;
import cn.mxz.bag.Grid;
import cn.mxz.city.City;

/**
 * 物品情况
 * @author Administrator
 *
 */
public class RoleItemHandler extends AbstractHandler {
	

	@Override
	protected String doGet(Map<String, Object> parameters) {
		
		City user = getUser( parameters );
		if( user == null ){			
			
			
			
			return responseErr( ErrorCode.USER_NOT_FOUND ); 
		}
		StringBuilder sb = new StringBuilder( "<Response><result>1</result>");
		List<Grid> all = user.getBag().getAll();
		sb.append("<count>").append(all.size()).append("</count>" ); 
		sb.append("<details_info>");
 		for(Grid grid :all ) {
			String name = StuffTempletConfig.get(grid.getTempletId()).getName();
			buildInfo( name, grid.getCount(), sb );
		}
//		
		sb.append("</details_info>"); 
		sb.append("</Response>" );		
		return sb.toString();
	}
	private void buildInfo(String name, int count, StringBuilder sb) {
		String format = String.format("<info name=id text=%s num=%d>槽位在 1</info>", name, count ); 
//		"<info name=\"%s\" text=\"%s\">%s</info>", name, desc, value);
		sb.append( format );
	}
		

}
