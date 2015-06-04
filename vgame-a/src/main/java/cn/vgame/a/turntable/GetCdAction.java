package cn.vgame.a.turntable;

import cn.vgame.a.account.JsonActionAfterRoleEnterGame;

/**
 * 获取开奖CD (毫秒)
 * -----------------
 * 
 * A.正常情况:
 * 		{
 * 				cd 开奖CD时间(秒)
 * 		}
 * 
 * B.错误:
 *  标准错误
 */
public class GetCdAction extends JsonActionAfterRoleEnterGame {

	public static class GetCdResult {
		public long getCd() {
			return Turntable.getInstance().getCd();
		}

	}

	private static final long serialVersionUID = -6099859675509539457L;

	@Override
	public Object run() {

		return new GetCdResult();
	}

}
