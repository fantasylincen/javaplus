package cn.vgame.a.turntable;

import cn.vgame.a.Server;
import cn.vgame.a.account.JsonActionAfterRoleEnterGame;
import cn.vgame.a.zhuang.ShangZhuangListResult;

/**
 * 下庄
 * 
 * -----------------
 * 
 * A.正常情况: { 返回值同 GetZhuangListAction }
 * 
 * B.错误: 标准错误
 */
public class XiaZhuangAction extends JsonActionAfterRoleEnterGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4234121643459106742L;


	@Override
	protected Object run() {
		Server.getZhuangManager().requstXiaZhuang(role.getId());
		return new ShangZhuangListResult();
	}
}
