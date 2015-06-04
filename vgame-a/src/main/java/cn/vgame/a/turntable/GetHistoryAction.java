package cn.vgame.a.turntable;

import java.util.List;

import cn.vgame.a.account.JsonActionAfterRoleEnterGame;

/**
 * 获取开奖历史记录
 * -----------------
 * 
 * A.正常情况:
 * 		{
 * 			history: [
 * 				
 * 				{id=1},
 * 				{id=2},
 * 				{id=3},
 * 				{id=4},
 * 				{id=5},
 * 				.
 * 				.
 * 				.
 * 				.
 * 				{id=20}
 * 			]
 * 		}
 * 
 * B.错误:
 *  标准错误
 */
public class GetHistoryAction extends JsonActionAfterRoleEnterGame {

	private static final long serialVersionUID = -6099859675509539457L;

	@Override
	public Object run() {

		return Turntable.getInstance().getHistory();
	}

}
