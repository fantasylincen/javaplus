package cn.vgame.a.turntable;

import cn.vgame.a.Server;
import cn.vgame.a.account.JsonActionAfterRoleEnterGame;
import cn.vgame.a.zhuang.ShangZhuangListResult;
import cn.vgame.a.zhuang.ZhuangManager;

/**
 * 上庄
 * 
 * -----------------
 * 
 * A.正常情况: 返回结果同 getShangZhuangList
 * 
 * B.错误: 标准错误
 */
public class ShangZhuangAction extends JsonActionAfterRoleEnterGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2297121829555663145L;

	@Override
	protected Object run() {
		ZhuangManager manager = Server.getZhuangManager();
		manager.startQueuing(role);
		return new ShangZhuangListResult();
	}
}
