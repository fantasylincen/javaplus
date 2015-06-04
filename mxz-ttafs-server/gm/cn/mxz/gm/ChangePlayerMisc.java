package cn.mxz.gm;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import com.linekong.platform.protocol.erating.server.RechargeLogic;

import cn.mxz.city.City;
import cn.mxz.listeners.TesterCreate;
import cn.mxz.listeners.init.PassGuide;
import cn.mxz.yunyou.YunYouPlayer;

/**
 * 把道具id转为名字，用户客户端查询
 * @author Administrator
 *
 */
public class ChangePlayerMisc  extends AbstractHandler {

//	String ret;
	
	ChangePlayerMisc(){
		
		
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
			String cmd = (String) parameters.get( "cmd" );
			String arg = (String) parameters.get( "arg" );
			
			if( cmd.equals("yunyou") ){//为玩家增加一个云游仙人
				YunYouPlayer player = user.getYunYouPlayer();

				player.onEvent();
			}
			
			if( cmd.equals("moshen") ){//为玩家增加一个云游仙人
				if( user.getUserShenmo().createShenmo() == null ){
					return buildErr( "神魔同一时间只能拥有一个" );
				}
			}
			if( cmd.equals("skipGuide") ){//跳过引导
				PassGuide p = new PassGuide();
				p.pass(user);
			}
			
			if( cmd.equals("superPlayer") ){//超级账号
				new TesterCreate().toTester(user);
			}
			
			if( cmd.equals("recharge") ){//超级账号
				int amount = Integer.parseInt( arg);
				RechargeLogic rechargeLogic = new RechargeLogic(user );
				rechargeLogic.recharge(amount);//充值元宝数
			}
			
			 return ret;
		}
		catch( Exception e ){
			e.printStackTrace(out);
			return buildErr(sw.toString());
		}
		
		//ret += "\";";
//		return ret;
	}
	
	private String buildErr( String errorInfo ){
		String ret = "<h6 class=\"bg-danger\">";
		ret += errorInfo;
		ret += "</h6>";
		return ret;
	}


}
