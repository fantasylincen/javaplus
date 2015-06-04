package cn.mxz.gm;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Lists;

import cn.mxz.FighterTempletConfig;
import cn.mxz.city.City;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.protocols.user.god.FighterP.FightersPro;
import cn.mxz.team.builder.FightersBuilder;
import cn.mxz.user.team.god.Hero;

/**
 * 把道具id转为名字，用户客户端查询
 * @author Administrator
 *
 */
public class ChangePlayerInfo  extends AbstractHandler {

//	String ret;
	
	ChangePlayerInfo(){
		
		
	}
	
	@Override
	protected String doGet(Map<String, Object> parameters) {
		String ret = "<h4 class=\"bg-success\">恭喜，设置成功，没有出现错误！<h4>";
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);
		try{
			City user = getUser( parameters );
			if( user == null ){							
				
				return buildErr( "玩家不存在" );
			}
			String propId = (String) parameters.get( "propId" );
			int count = Integer.parseInt((String) parameters.get( "count" ));
			
			user.getPrizeSender1().send(propId  +"," + count);
			updateFighter( Integer.parseInt(propId), user );
			 return ret;
		}
		catch( Exception e ){
			e.printStackTrace(out);
			return buildErr(sw.toString());
		}
		
		//ret += "\";";
//		return ret;
	}
	
	private void updateFighter(int propId, City user) {
		if(FighterTempletConfig.get(propId) != null)  {

			Hero hero = user.getTeam().get(propId);
			Collection<Hero> fighters = Lists.newArrayList(hero);
			
			FightersPro fs = new FightersBuilder().build(fighters);
			
			MessageFactory.getFighter().fightersUpdate( user.getSocket(), fs);
		}
	}
	private String buildErr( String errorInfo ){
		String ret = "<h6 class=\"bg-danger\">";
		ret += errorInfo;
		ret += "</h6>";
		return ret;
	}


}
