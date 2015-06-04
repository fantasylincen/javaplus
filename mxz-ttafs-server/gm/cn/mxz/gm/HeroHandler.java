package cn.mxz.gm;

import java.util.Map;

import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.city.City;
import cn.mxz.user.team.god.Hero;

public class HeroHandler extends AbstractHandler  {

	@Override
	protected String doGet(Map<String, Object> parameters) {
		StringBuilder sb = new StringBuilder( "<?xml version=\"1.0\" encoding=\"utf-8\"?> <Response><result>1</result><details_info><info>名字,等级</info>");
		City user = getUser( parameters );
		if( user == null ){			
			return responseErr( ErrorCode.USER_NOT_FOUND ); 
		}
//		<?xml version="1.0" encoding="utf-8"?> 
//		<Response> 
//		<result>1</result> 
//		<page>1</page> 
//		<details_info> 
//		<info>ID,名称,等级,创建人,创建时间,人数,阵营</info> 
//		<info>100041,TOP 西游记军团,2,木木夕儿,2010-01-11 11:30:13,0,菩提</info> 
//		<info>100062,玩家服务,3,无情天尊,2010-01-12 11:34:43,0,菩提</info> 
//		<!--(此处省略若干个 info 节点)--> 
//		</details_info> 
//		</Response> 
		for( Hero h : user.getTeam().getAll() ){
			FighterTemplet templet = FighterTempletConfig.get( h.getTypeId() );
			sb.append("<info>");
			sb.append( templet.getName() ).append(",");
			sb.append(h.getLevel());
			sb.append("</info>");
		}
		
		sb.append("</details_info></Response>" );		
		return sb.toString();
	}

	

}
