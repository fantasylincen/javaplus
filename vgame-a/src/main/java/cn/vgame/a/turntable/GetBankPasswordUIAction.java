package cn.vgame.a.turntable;

import cn.vgame.a.account.JsonActionAfterRoleEnterGame;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.RoleDao;
import cn.vgame.a.gen.dto.MongoGen.RoleDao.RoleDtoCursor;
import cn.vgame.a.gen.dto.MongoGen.RoleDto;
import cn.vgame.share.EncodingUtil;
import cn.vgame.share.GameException;

/**
 * 获取解锁界面数据
 * 
 * -----------------
 * 
 * A.正常情况: { "cd":3600 //秒 }
 * 
 * B.错误: 标准错误
 */
public class GetBankPasswordUIAction extends JsonActionAfterRoleEnterGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4234121643459106742L;

	public class BankPasswordUI {

		/**
		 * 银行密码强制解锁cd
		 */
		public int getBankPasswordCd() {
			return role.getBankPasswordCd();
		}

		/**
		 * 银行密码状态 0 未设置安全密码 1 已设置安全密码 2 强制解锁中
		 */
		public int getBankPasswordStatus() {
			return role.getBankPasswordStatus();
		}
	}

	@Override
	protected Object run() {
		return new BankPasswordUI();
	}
}
