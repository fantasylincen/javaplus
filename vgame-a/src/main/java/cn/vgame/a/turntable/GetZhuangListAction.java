package cn.vgame.a.turntable;

import cn.vgame.a.account.JsonActionAfterRoleEnterGame;
import cn.vgame.a.zhuang.ShangZhuangListResult;

/**
 * 获得上庄排队列表
 * 
 * -----------------
 * 
 * A.正常情况: { 
 *	zhuangCoin 当前庄家金币
 *	zhuangNick 当前庄家昵称
 * 		roles: [
 * 			{roleId :100122231, nick:xxxx, coin:10000000},
 * 			{roleId :100122232, nick:xxxx, coin:10000001},
 * 			{roleId :100122233, nick:xxxx, coin:10000002},
 * 			{roleId :100122234, nick:xxxx, coin:10000003}
 * 		]
 * 
 * }
 * 
 * B.错误: 标准错误
 */
public class GetZhuangListAction extends JsonActionAfterRoleEnterGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 133979185329171824L;

	@Override
	protected Object run() {

		return new ShangZhuangListResult();
	}
}
