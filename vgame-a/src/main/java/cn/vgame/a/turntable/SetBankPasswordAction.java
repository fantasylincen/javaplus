package cn.vgame.a.turntable;

import cn.vgame.a.account.JsonActionAfterRoleEnterGame;
import cn.vgame.a.message.SuccessResult;
import cn.vgame.a.result.ErrorResult;

/**
 * 在知道密码 的情况下 修改密码
 * 
 * -----------------
 * 
 * A.正常情况: { }
 * 
 * B.错误: 标准错误
 */
public class SetBankPasswordAction extends JsonActionAfterRoleEnterGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5136138793804713050L;

	private String newPassword;
	private String oldPassword;

	@Override
	protected Object run() {

		if (isSetPassword()) {
			checkOldPassword();
		}

		checkNewPasswordFormat();
		role.getBank().setBankPassword(newPassword);

		return new SuccessResult();
	}

	private void checkNewPasswordFormat() {
		if (newPassword == null)
			throw new ErrorResult(10026).toException();
		int min = 6;
		int max = 32;
		if (newPassword.length() < min || newPassword.length() > max) {
			throw new ErrorResult(100027, min, max).toException();
		}
	}

	private void checkOldPassword() {
		if (!role.getBankPassword().equals(oldPassword)) {
			throw new ErrorResult(10028).toException();
		}
	}

	/**
	 * 是否已经设置过密码
	 */
	private boolean isSetPassword() {
		String pwd = role.getBankPassword();
		return pwd != null && !pwd.isEmpty();
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
